package interfaces.graphiques.autres;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import obstacles.ObstacleElectrique;

/**
 * Classe graphique utilisee pour donner les options de depart a l'utilisateur qu'il choisira a l'aide des boutons
 * @author Jude
 * @version 04/03/14
 */
public class MenuDeDepart extends JPanel  implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private Image titre;	
	private ArrayList<ObstacleElectrique> obsE;
	private Thread proc;
	private double move =(int)(Math.random()*3)-1;
	/**
	 * Constructeur du composant
	 */
	public MenuDeDepart(){
		setLayout(null);
		setBackground(Color.gray);
		setPreferredSize(new Dimension(944,562));
		obsE = new ArrayList<ObstacleElectrique>();
		ajouterDesObjets();
		ajouterComposant();
		proc = new Thread(this);
		proc.start();
	}	
	/**
	 * Methode utilisee pour dessiner le menu
	 * @param Graphics g
	 */
	@Override
	public void paintComponent(Graphics g){
		AffineTransform matTransfo = matriceMondeVersComposant(0,0,944,562);
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if(!obsE.isEmpty()){
			for(int k = 0; k < obsE.size();k++){
				obsE.get(k).setPosition(obsE.get(k).getPosX()+move, obsE.get(k).getPosY()-move);				
				obsE.get(k).dessiner(g2d, matTransfo);
			}
		}
		g2d.drawImage(titre,(getWidth()/2)-300,100,600,100,null);
	}
	/**
	 * Methode utilisee pour ajouter les objets sur l'interface
	 */
	public void ajouterDesObjets(){
		double i = Math.random()*40+5;
		for(int k = 0; k < i ;k++){
			ObstacleElectrique obstacle = new ObstacleElectrique(((int)(Math.random()*8)-4),Math.random()*1100,Math.random()*700);
			obsE.add(obstacle);
		}
	}
	/**
	 * Methode utilisee pour generer le titre
	 */
	private void ajouterComposant() {	
		try {
			titre= ImageIO.read(getClass().getClassLoader().getResource("titre.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Classe utilisee pour transformer les mesures de pixels vers des mesures en monde reel
	 * @param coinMondeX
	 * @param coinMondeY
	 * @param largeurMonde
	 * @param hauteurMonde
	 * @return : matrice de transformation 
	 */
	private AffineTransform  matriceMondeVersComposant(double coinMondeX, double coinMondeY, double largeurMonde, double hauteurMonde) {
		AffineTransform matTransfo =  new AffineTransform(); 

		double pixelsParUniteX =  getWidth()/largeurMonde;
		double pixelsParUniteY = getHeight()/hauteurMonde;

		matTransfo.scale( pixelsParUniteX, pixelsParUniteY );
		matTransfo.translate( -coinMondeX, -coinMondeY );

		return (matTransfo);
	}
	/**
	 * Methode permettant d'animer le composant
	 */
	@Override
	public void run() {
		while(true){						
			repaint();
			try {
				Thread.sleep(30);			
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
}

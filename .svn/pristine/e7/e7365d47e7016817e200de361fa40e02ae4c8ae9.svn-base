package instructions;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import objets.graphiques.Canon;
import objets.graphiques.Cible;
import obstacles.ObstacleElectrique;
import obstacles.ObstaclePhysique;
/**
 * Classe utilisee pour ajouter des images aux instructions
 * @author Jude
 *@version 29/04/14
 */
public class Images extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int HAUTEUR_MONDE = 2144;
	private Canon canon;
	private Cible cible;
	private ObstacleElectrique obE1, obE2;
	private ObstaclePhysique obP1, obP2;
	private Image capture;
	private JLabel lblModeJouer, lblModeCreer;
	/**
	 * Constructeur de la classe Image
	 */
	public Images(){
		setLayout(null);
		canon = new Canon(0,370,150,100);
		cible = new Cible(370,550,100);
		obE1 = new ObstacleElectrique(5,370,1010);
		obE2 = new ObstacleElectrique(-5,370,900);
		obP1 = new ObstaclePhysique(40,300,640);
		obP2 = new ObstaclePhysique(100,30,400,640);
		lblModeJouer = new JLabel("Mode Jouer");
		lblModeJouer.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 21));
		lblModeJouer.setForeground(Color.red);
		lblModeJouer.setBounds(15, 45, 150, 150);
		add(lblModeJouer);
		lblModeCreer = new JLabel("Mode Cr\u00E9er");
		lblModeCreer.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 21));
		lblModeCreer.setForeground(Color.red);
		lblModeCreer.setBounds(15,1283,150,150);
		add(lblModeCreer);
		chargerLesImages();
	}
	/**
	 * Methode utilsee pour dessiner le composant qui est utilisé dans Instructions
	 * @param Graphics g
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform matTransfo = matriceMondeVersComposant(0,0,HAUTEUR_MONDE*getWidth()/(double)getHeight(),HAUTEUR_MONDE);	
		obE1.dessiner(g2d, matTransfo);
		obE2.dessiner(g2d, matTransfo);
		obP1.dessiner(g2d, matTransfo);
		obP2.dessiner(g2d, matTransfo);
		canon.dessiner(g2d, matTransfo);
		cible.dessiner(g2d, matTransfo);
		g2d.drawImage(capture, 370, 370, 152, 78, null);
	}
	/**
	 * Methode privee qui ajoutent les images aux
	 */
	private void chargerLesImages(){
		try {
			capture = ImageIO.read(getClass().getClassLoader().getResource("Capture.jpg"));

		} 
		catch (IOException e) {
			System.out.println("Erreur de lecture d'images");
		}
	}
	/**
	 * matrice qui transforme les mesures en pixels en centimetres
	 * @param coinMondeX : partie gauche du monde 
	 * @param coinMondeY : partie superieur du monde
	 * @param largeurMonde : largeur du monde
	 * @param hauteurMonde : hauteur du monde
	 * @return
	 */
	private AffineTransform  matriceMondeVersComposant(double coinMondeX, double coinMondeY, double largeurMonde, double hauteurMonde) {
		AffineTransform matTransfo =  new AffineTransform(); 
		double pixelsParUniteX =  getWidth()/largeurMonde;
		double pixelsParUniteY = getHeight()/hauteurMonde;
		matTransfo.scale( pixelsParUniteX, pixelsParUniteY );
		matTransfo.translate( -coinMondeX, -coinMondeY );
		return (matTransfo);
	}
}

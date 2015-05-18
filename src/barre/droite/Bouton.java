package barre.droite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Classe graphique utilisee pour creer les boutons et imager les boutons
 * @author Edouard
 * @version 04/03/14
 */

public class Bouton  extends  JButton  {
	private static final long serialVersionUID = 1L;

	private Image quitter,retour,play,playDisabled,reset,reset1,help,jouerPrinc,creer,instructions,quitterPrinc,jouerPrinc1,creer1,instructions1,quitterPrinc1;
	private String button;
	private boolean entered=true;
	/**
	 * Constructeur du  composant bouton
	 */
	public Bouton() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {			
				entered=false;				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				entered=true;				
			}
		});
		setPreferredSize(new Dimension(15,15));

		try {
			quitter= ImageIO.read(getClass().getClassLoader().getResource("quitter.png"));
			retour = ImageIO.read(getClass().getClassLoader().getResource("Retour.png"));
			play = ImageIO.read(getClass().getClassLoader().getResource("play.png"));
			playDisabled = ImageIO.read(getClass().getClassLoader().getResource("playDisabled.png"));
			reset = ImageIO.read(getClass().getClassLoader().getResource("undo.png"));
			reset1 = ImageIO.read(getClass().getClassLoader().getResource("undoDisabled.png"));
			help = ImageIO.read(getClass().getClassLoader().getResource("help.png"));
			// source : http://www.glassybuttons.com/glassy.php
			jouerPrinc=ImageIO.read(getClass().getClassLoader().getResource("button.png"));
			creer=ImageIO.read(getClass().getClassLoader().getResource("creer.png"));
			instructions=ImageIO.read(getClass().getClassLoader().getResource("instruction.png"));
			quitterPrinc=ImageIO.read(getClass().getClassLoader().getResource("quitterPrinc.png"));

			jouerPrinc1=ImageIO.read(getClass().getClassLoader().getResource("button_1.png"));
			creer1=ImageIO.read(getClass().getClassLoader().getResource("Bouton Creer actif.png"));
			instructions1=ImageIO.read(getClass().getClassLoader().getResource("button_1 (2).png"));
			quitterPrinc1=ImageIO.read(getClass().getClassLoader().getResource("button_1 (3).png"));
		} 
		catch (IOException e) {
			System.out.println("Erreur de lecture d'images");
		}
		button="quitter";		

	} //fin constructeur	
	/**
	 * Methode utilsee pour dessiner le bouton
	 * @param Graphics g
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		int sizeImage=50;
		switch(button) {
		case "quitter":			
			g2d.drawImage(quitter, 0, 0, sizeImage,sizeImage, null);
			break;
		case "retour":			
			g2d.drawImage(retour, 0, 0,sizeImage, sizeImage, null);
			break;	
		case "play":	
			if(this.isEnabled()){
				g2d.drawImage(play, 0, 0, sizeImage, sizeImage, null);
			}else {
				g2d.drawImage(playDisabled,0,0,sizeImage,sizeImage,null);
				}
			break;	
		case "reinitialiser":
			if(this.isEnabled()){
				g2d.drawImage(reset, 0, 0, sizeImage, sizeImage, null);
			}else{
				g2d.drawImage(reset1, 0, 0, sizeImage, sizeImage, null);
			}
			break;	
		case "aide":
			g2d.drawImage(help, 0,0, sizeImage,sizeImage, null);			
			break;
		case "jouer":
			if(entered){
				g2d.drawImage(jouerPrinc, 0,0, 175,60, null);	
			}else{
				g2d.drawImage(jouerPrinc1, 0,0, 175,60, null);
			}
			break;
		case "instruction":
			if(entered){
				g2d.drawImage(instructions, 0,0,175,60, null);		
			}else{
				g2d.drawImage(instructions1, 0,0,175,60, null);	
			}
			break;
		case "creer":
			if(entered){
				g2d.drawImage(creer, 0,0,175,60, null);			
			}else{
				g2d.drawImage(creer1, 0,0,175,60, null);	
			}
			break;
		case "quitterPrinc":
			if(entered){
				g2d.drawImage(quitterPrinc, 0,0, 175,60, null);	
			}else{
				g2d.drawImage(quitterPrinc1, 0,0, 175,60, null);	
			}
			break;



		}
	}

	/**
	 * methode permettant de choisir le bouton voulu
	 * @param String button
	 * @category quitter,retour,play,reinitialiser,aide,jouer,instruction,creer,quitterPrinc
	 */
	public void setbutton(String button) {
		this.button = button;
	}

} //fin classe

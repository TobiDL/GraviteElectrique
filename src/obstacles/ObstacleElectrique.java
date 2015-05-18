package obstacles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
//import java.awt.geom.Ellipse2D;
//import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *  Classe cr�e pour dessiner la g�om�trie d'un obstacle electrique
 * @author Edouard
 * @version 13/02/14
 */
public class ObstacleElectrique {
	private double posX , posY;
	private double diametre;
	private int charge;
	private Ellipse2D.Double aireElectrique;
	private Ellipse2D.Double cercleObstacle; 
	private boolean aireVisible;
	private Shape sObsElecAire;
		private Image obsElectriquePositif,obsElectriqueNegatif;
		
	/**
	 * Constructeur pour un obstacle electrique	
	 * @param charge : charge de l'obstacle electrique
	 * @param posX : position en X de l'obstacle
	 * @param posY : position en Y de l'obstacle
	 */
	public ObstacleElectrique(int charge,double posX, double posY){		
		this.posX = posX;
		this.posY = posY;		
		this.charge = charge; // diametre en fonction de la charge
		aireVisible=false;
		setDiametreObstacle();	
		aireElectrique = new Ellipse2D.Double((int)posX-1.7*diametre/2,(int)posY-1.7*diametre/2,1.7*diametre,1.7*diametre);
		cercleObstacle = new Ellipse2D.Double(posX-(int)diametre*1.2/2,posY-(int)diametre*1.2/2,(int)diametre*1.2,(int)diametre*1.2);
		lireLesImages();
	}
	/**
	 * methode creant les shapes pour les tests de collision 	
	 * @param matTransfo : la matrice qui modifie les mesures en metres
	 */
	public void creer(AffineTransform matTransfo){	
		sObsElecAire=matTransfo.createTransformedShape(aireElectrique);
	}
	/**
	 * methode choissisant le diametre de l'obstacle electrique en fonction de la charge	
	 */	
	private void setDiametreObstacle() {
		 int chargeI=Math.abs(charge);
		 switch(chargeI) {
		 case 5 :
			 diametre=165 ;
			 break;
		 case 6 :
			 diametre=175 ;
			 break;
		 case 7 :
			 diametre=185 ;
			 break;
		 case 8 :
			 diametre=195 ;
			 break;
		 case 9 :
			 diametre=200 ;
			 break;
		 case 10 :
			 diametre=210 ;
			 break;
		 case 11 :
			 diametre=220 ;
			 break;
		 }
			if(Math.abs(charge)<5){diametre=140;}
			if(Math.abs(charge)>11){diametre=230;}				
		
	}
	/**
	 * methode permettant de dessiner l'obstacle electrique 
	 * @param g2d : l'objet que l'on utilise pour dessiner
	 * @param matTransfo : la matrice qui modifie les mesures en metres
	 */	
	public void dessiner(Graphics2D g2d, AffineTransform matTransfo){
		Color couleurALentree = g2d.getColor(); 		

		aireElectrique = new Ellipse2D.Double((int)posX-1.7*diametre/2,(int)posY-1.7*diametre/2,1.7*diametre,1.7*diametre);
		cercleObstacle = new Ellipse2D.Double(posX-(int)diametre*1.2/2,posY-(int)diametre*1.2/2,(int)diametre*1.2,(int)diametre*1.2);

		//aireElectrique = new Ellipse2D.Double((int)posX-3*diametre/2,(int)posY-3*diametre/2,3*diametre,3*diametre);
		//cercleObstacle = new Ellipse2D.Double(posX-(int)diametre/2,posY-(int)diametre/2,diametre,diametre);

		Shape cercleTransfo = matTransfo.createTransformedShape(cercleObstacle);
			if(charge!=0){
			// charge positive
			if(charge>=1){					
				Shape cercleObstacleTransfo = matTransfo.createTransformedShape(aireElectrique);
				g2d.setColor(new Color(220,20,60,25));	
				if(aireVisible){
				g2d.fill(cercleObstacleTransfo);
				}
				g2d.drawImage(obsElectriquePositif,(int)cercleTransfo.getBounds2D().getCenterX()-(int)cercleTransfo.getBounds2D().getWidth()/4,(int)cercleTransfo.getBounds2D().getCenterY()-(int)cercleTransfo.getBounds2D().getWidth()/4,(int)cercleTransfo.getBounds2D().getWidth()/2,(int)cercleTransfo.getBounds2D().getWidth()/2,null);
			}
			// charge negative
			if(charge<-1){				
				Shape cercleObstacleTransfo = matTransfo.createTransformedShape(aireElectrique);
				g2d.setColor(new Color(0,245,255,25));	
				if(aireVisible){
				g2d.fill(cercleObstacleTransfo);
				}
				g2d.drawImage(obsElectriqueNegatif,(int)cercleTransfo.getBounds2D().getCenterX()-(int)cercleTransfo.getBounds2D().getWidth()/4,(int)cercleTransfo.getBounds2D().getCenterY()-(int)cercleTransfo.getBounds2D().getWidth()/4,(int)cercleTransfo.getBounds2D().getWidth()/2,(int)cercleTransfo.getBounds2D().getWidth()/2,null);
			}
		} 
		g2d.setColor(couleurALentree);
	}
	/**
	 *Modifie la positon de l'obstacle, prends en parametre une position en X 
	 * @param posX : position en X du canon
	 */
	public void setPosition(double posX, double posY){
		this.posX = posX;
		this.posY = posY;
	}
	/**
	 *Modifie la charge, prends en parametre la charge 
	 * @param charge : charge de l'obstacle
	 */
	public void setCharge(int charge){
		this.charge = charge;	
		setDiametreObstacle();
	}
	/**
	 *Retourne la charge de l'obstacle
	 */
	public int getCharge(){
		return charge;
	}
	/**
	 *Retourne la position X de l'obstacle
	 */
	public double getPosX(){
		return posX;
	}
	/**
	 *Retourne la position Y de l'obstacle
	 */
	public double getPosY(){
		return posY;
	}
	/**
	 * Methode utilisee pour retouner la forme de la charge electrique
	 * @return
	 */
	public Shape getShape(){		
		return sObsElecAire;
	}
	/**
	 * Methode utilisee pour savoir si un clic a et fait sur une forme
	 * @param posX : position en X du clic
	 * @param posY : position en Y du clic
	 * @return
	 */
	public boolean contains(double posX, double posY,AffineTransform matTransfo){
		Shape cercleTransfo = matTransfo.createTransformedShape(cercleObstacle);
			return (cercleTransfo.contains(posX, posY));
	}
	/**
	 * Methode privee pour lire les images attachees au projet
	 */
	private void lireLesImages(){
		try {
			obsElectriqueNegatif= ImageIO.read(getClass().getClassLoader().getResource("obsNegatif.png"));
			obsElectriquePositif= ImageIO.read(getClass().getClassLoader().getResource("obsPositif.png"));
		} 
		catch (IOException e) {
			System.out.println("Erreur de lecture d'images");
		}
	}
	/**
	 * @return si l'aire de l'application est visible
	 */
	public boolean isAireVisible() {
		return aireVisible;
	}
	/**
	 * Determine si le champ est visible ou pas
	 * @param aireVisible the aireVisible to set
	 */
	public void setAireVisible(boolean aireVisible) {
		this.aireVisible = aireVisible;
	}
	/**
	 * Retourne le diametre de l'obstacle
	 * @retun le diametre de l'obstacle
	 */
	public double getDiametre(){
		return diametre;
	}
}

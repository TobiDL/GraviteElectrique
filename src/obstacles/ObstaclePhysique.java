package obstacles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *  Classe crée pour dessiner la géométrie d'un obstacle physique 
 * @author Jude 
 * @version 13/02/14
 */
public class ObstaclePhysique{
	private double posX , posY;	
	private double longueur , hauteur ,rayon,masse;
	private Rectangle2D.Double obstacleRectangle;
	private Rectangle2D.Double obstacleRectOmbre;
	private Ellipse2D.Double obstacleRondOmbre;	
	private Ellipse2D.Double obstacleRond;
	private Shape sObsRond,sObsRect;

	/**
	 * Constructeur pour un obstacle de forme rectagulaire
	 * @param longueur : longueur de l'obstacle
	 * @param hauteur : hauteur de l'obstacle
	 * @param posX : position en X de l'obstacle
	 * @param posY : position en Y de l'obstacle
	 */
	public ObstaclePhysique(double longueur, double hauteur, double posX, double posY){
		this.longueur = longueur;
		this.hauteur = hauteur;
		this.posX = posX;
		this.posY = posY;		
		masse=500;
		rayon = 0;		
		obstacleRectangle = new Rectangle2D.Double(posX, posY, longueur, hauteur);
	}
	/**
	 * Contructeur pour un obstacle de forme circulaire
	 * @param rayon : rayon de l'obstacle
	 * @param posX : position en X de l'obstacle
	 * @param posY : position en Y de l'obstacle
	 */
	public ObstaclePhysique(double rayon, double posX, double posY){
		this.rayon = rayon;
		this.posX = posX;
		this.posY = posY;
		this.hauteur=0;
		this.longueur=0;		
		obstacleRond = new Ellipse2D.Double(posX-rayon, posY-rayon, 2*rayon, 2*rayon);
	}
	/**
	 * methode creant les shapes pour les tests de collision 	
	 * @param matTransfo : la matrice qui modifie les mesures en metres
	 */
	public void creer(AffineTransform matTransfo){
		sObsRect=matTransfo.createTransformedShape(obstacleRectangle);
		sObsRond=matTransfo.createTransformedShape(obstacleRond);
	}
	/**
	 * methode utile pour dessiner des obstacles physiques 
	 * @param g2d : l'objet que l'on utilise pour dessiner
	 * @param matTransfo : la matrice qui modifie les mesures en metres
	 */
	public void dessiner(Graphics2D g2d, AffineTransform matTransfo){
		Color couleurTemp = g2d.getColor();
		g2d.setColor(new Color(100, 10, 12, 255));
		obstacleRond = new Ellipse2D.Double(posX, posY, 2*rayon, 2*rayon);
		obstacleRectangle = new Rectangle2D.Double(posX, posY, longueur, hauteur);
		
		obstacleRondOmbre = new Ellipse2D.Double(posX+7, posY+7, 2*rayon, 2*rayon);
		obstacleRectOmbre = new Rectangle2D.Double(posX+7, posY+7, longueur, hauteur);
		if (rayon!= 0){
			//obstacleRond = new Ellipse2D.Double(posX, posY, 2*rayon, 2*rayon);
			g2d.setColor(new Color(0, 0, 0, 120));
			g2d.fill(matTransfo.createTransformedShape(obstacleRondOmbre));
			g2d.setColor(new Color(100, 10, 12, 255));
			g2d.fill(matTransfo.createTransformedShape(obstacleRond));
			}else{
			g2d.setColor(new Color(0, 0, 0, 120));
			g2d.fill(matTransfo.createTransformedShape(obstacleRectOmbre));
			g2d.setColor(new Color(100, 10, 12, 255));
			g2d.fill(matTransfo.createTransformedShape(obstacleRectangle));	
		}
		g2d.setColor(couleurTemp);
	}
	/**
	 * methode retournant la shape de l'obstacle
	 */
	public Shape getShape(){
		if (rayon !=0){	
			return sObsRond;	
		}
		else{				
			return sObsRect;
		}
	}
	/**
	 * methode retournant la position transforme de l'obstacle
	 */
	public double  positionMonde(int xTest, int yTest, AffineTransform matTransfo) {		
		AffineTransform matInversee = null;
		try {
			matInversee = matTransfo.createInverse();
		} catch (NoninvertibleTransformException e1) {
			e1.printStackTrace();
		}
		Point2D.Double ptEnPixels = new Point2D.Double(xTest, yTest);		
		Point2D.Double ptEnUnitesDuMondeReel = new Point2D.Double();		
		matInversee.transform( ptEnPixels , ptEnUnitesDuMondeReel );
		return ptEnUnitesDuMondeReel.getX()  ; 
	}	
	/**
	 * methode retournant la position X de l'obstacle
	 */
	public double getPosX() {
		if (rayon !=0){	
			return posX+rayon/2;	
		} else {
		return posX;
		}
	}
	/**
	 * methode settant la position X de l'obstacle
	 * @param double posX
	 */
	public void setPosX(double posX) {
		this.posX = posX;
	}
	/**
	 * methode retournant  la position Y de l'obstacle
	 */
	public double getPosY() {
		if (rayon !=0){	
			return posY+rayon/2;	
		} else {
		return posY;
		}
	}
	/**
	 * methode modifiant la position Y de l'obstacle
	 * @param double posY
	 */
	public void setPosY(double posY) {
		this.posY = posY;
	}
	/**
	 * methode retournant la longueur de l'obstacle
	 */
	public double getLongueur() {
		return longueur;
	}
	/**
	 * Retourne la masse
	 */
	public double getMasse() {
		return masse;
	}
	/**
	 * Modifie la masse 
	 */
	public void setMasse(double masse) {
		this.masse = masse;
	}
	/**
	 * methode modifiant la longueur de l'obstacle
	 * @param double longueur
	 */
	public void setLongueur(double longueur) {
		this.longueur = longueur;
	}
	/**
	 * methode retournant la hauteur de l'obstacle
	 */
	public double getHauteur() {
		return hauteur;
	}
	/**
	 * methode settant la hauteur de l'obstacle
	 * @param double hauteur
	 */
	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}
	/**
	 * methode retournant le rayon de l'obstacle
	 */
	public double getRayon() {
		return rayon;
	}
	/**
	 * methode settant le rayon de l'obstacle de l'obstacle
	 * @param double rayon
	 */
	public void setRayon(double rayon) {
		this.rayon = rayon;
	}
	/**
	 *  Methode utilisee pour deplacer un obstacle physique a une autre positon
	 * @param posX : position en X desiree 
	 * @param posY : position en Y desiree
	 */
	public void setPosition(double posX, double posY){
		this.posX = posX;
		this.posY = posY;
	}	
	/**
	 * methode retournant  vrai si on est sur l'objet
	 */
	public boolean surLObstacle(double posXs, double posYs, AffineTransform matTransfo){
		if(rayon != 0){
			if(matTransfo.createTransformedShape(obstacleRond).contains(posXs,posYs)){
				return true;
			}else{
				return false;
			}
		}else{
			if(matTransfo.createTransformedShape(obstacleRectangle).contains(posXs,posYs)){
				return true;
			}else{
				return false;
			}
		}
	}
	
}

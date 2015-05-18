package objets.graphiques;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * Classe de geometrie du canon qui permet de choisir l'angle de tir et la position
 * @author Jude Fort
 * @version 14/02/14
 */
public class Canon  {	
	private double angleDuCanon;
	private double posX, posY; // en centimetres
	private double largeur; // en centimetres
	private Image imageCanon1,imageCanon2,imageCanon1I;
	private Rectangle2D.Double rectImg, rectImg2;
	private Shape rectCanon1Transf;
	private Shape rectCanon2Transf;
	/**
	 * Initialise le canon a une position et un angle entre en parametre mais a une grandeur fixe
	 * @param angleDuCanon : l'angle de tir du canon
	 * @param posX : position du canon en X (sur la longueur)
	 * @param posY : position du canon en Y 
	 * @param largeur : la largeur du canon 
	 */
	public Canon(double angleDuCanon, double posX, double posY, double largeur){
		this.angleDuCanon = -0.65;
		this.posX = posX;
		this.posY = posY;
		this.largeur = largeur; // valeur temporaire;
		lireLesImages();
	}
	/**
	 * Methode servant a creer la shape le canon
	 * @param matransfo
	 */
	public void creer(AffineTransform matTransfo){			
		rectImg = new Rectangle2D.Double(posX, posY, largeur, largeur);
		rectCanon1Transf = matTransfo.createTransformedShape(rectImg);
		rectImg2 = new Rectangle2D.Double(posX-largeur/10,posY-largeur/100, largeur, largeur);
		rectCanon2Transf = matTransfo.createTransformedShape(rectImg2);
	}
	/**
	 * Dessine le canon 
	 * @param g2d
	 * @param matTransfo : matrice qui transforme les mesures en pixels en metres
	 */
	public void dessiner(Graphics2D g2d, AffineTransform matTransfo){
		AffineTransform mat = g2d.getTransform();

		rectImg = new Rectangle2D.Double(posX, posY, largeur, largeur);
		rectCanon1Transf = matTransfo.createTransformedShape(rectImg);
		rectImg2 = new Rectangle2D.Double(posX-largeur/10,posY-largeur/100, largeur, largeur);
		rectCanon2Transf = matTransfo.createTransformedShape(rectImg2);
		lireLesImages();
		if(angleDuCanon<1.57 && angleDuCanon>-1.57){
		g2d.drawImage(imageCanon1, (int) rectCanon1Transf.getBounds2D().getX() ,(int) rectCanon1Transf.getBounds2D().getY(), (int) rectCanon1Transf.getBounds2D().getWidth(), (int) rectCanon1Transf.getBounds2D().getHeight(),null);
		} else{
			g2d.drawImage(imageCanon1I, (int) rectCanon1Transf.getBounds2D().getX() ,(int) rectCanon1Transf.getBounds2D().getY(), (int) rectCanon1Transf.getBounds2D().getWidth(), (int) rectCanon1Transf.getBounds2D().getHeight(),null);
		}
		g2d.rotate(angleDuCanon,(posX+largeur/2)+1,(posY+largeur/2)-5);
		g2d.drawImage(imageCanon2, (int) rectCanon2Transf.getBounds2D().getX(), (int) rectCanon2Transf.getBounds2D().getY(), (int) rectCanon2Transf.getBounds2D().getWidth(), (int) rectCanon2Transf.getBounds2D().getHeight(),null);
		g2d.rotate(-angleDuCanon,(posX+largeur)+1,(posY+largeur/2)-5);
		
		g2d.setTransform(mat);  
		if(angleDuCanon<1.57 && angleDuCanon>-1.57){
			g2d.drawImage(imageCanon1, (int) rectCanon1Transf.getBounds2D().getX() ,(int) rectCanon1Transf.getBounds2D().getY(), (int) rectCanon1Transf.getBounds2D().getWidth(), (int) rectCanon1Transf.getBounds2D().getHeight(),null);
			} else{
				g2d.drawImage(imageCanon1I, (int) rectCanon1Transf.getBounds2D().getX() ,(int) rectCanon1Transf.getBounds2D().getY(), (int) rectCanon1Transf.getBounds2D().getWidth(), (int) rectCanon1Transf.getBounds2D().getHeight(),null);
			}
	}
	/**
	 * Retourne la poistion x du canon en pixels	 
	 * @return : retourne la position en X de la forme du canon
	 */
	public Double getBoundsX(){
		return rectCanon2Transf.getBounds2D().getX();		
	}
	/**
	 * Retourne la position y du personnage en pixels	 
	 * @return : retourne la position en Y de la forme du canon
	 */
	public Double getBoundsY(){
		return rectCanon2Transf.getBounds2D().getY();	
	}
	/**
	 * Retourne la largeur du canon
	 * @return largeur : La largeur
	 */
	public Double getLargeur(){
		return largeur;
	}
	/**
	 * Modifie l'angle de tir du canon, prends en parametre un angle en radian
	 * @param angle : angle de tir en radian
	 */
	public void setAngle(double angle){		
			angleDuCanon = angle;		
	}
	/**
	 *Modifie la positon du canon, prends en parametre une position en X 
	 * @param posX : position en X du canon
	 */
	public void setPosX(double posX){
		this.posX = posX;		
	}
	/**
	 * Methode retornant une valeure boolean pour indiquer que le canon a tiré
	 * @return : vrai pour activer le canon
	 */
	public boolean tirer(){
		return true;
	}
	/**
	 * Methode publique pour savoir si un clic a ete fait sur le canon
	 * @param posX : La position en X du clic
	 * @param posY : La position en Y du clic
	 * @return : vrai si le clic est sur le canon faux dans le cas contraire
	 */
	public boolean surLeCanon(double posX, double posY){
		
		if (rectCanon1Transf.contains(posX,posY)||rectCanon2Transf.contains(posX, posY)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Methode privee retournant la position Y
	 * @return: position en Y
	 */
	public double getPosY() {
		return posY;
	}
	/**
	 * Methode privee pour modifier la position Y
	 * @param posY : position en Y
	 */
	public void setPosY(double posY) {
		this.posY = posY;
	}
	/**
	 * Methode retournant la position X
	 * @return posX : position en X
	 */
	public double getPosX() {
		return posX;
	}
	/**
	 * Methode utilisee pour avoir la forme du canon
	 * @return rectImg : le rectangle de la forme du canon
	 */
	public Shape getShape(){
		return rectImg;
	}
	/**
	 * Methode privee pour lire les images attachees au projet
	 */
	private void lireLesImages(){
		try {
			imageCanon1= ImageIO.read(getClass().getClassLoader().getResource("Canon1.gif"));
			imageCanon1I= ImageIO.read(getClass().getClassLoader().getResource("canonnG.gif"));
			imageCanon2 = ImageIO.read(getClass().getClassLoader().getResource("main2.gif"));

		} 
		catch (IOException e) {
			System.out.println("Erreur de lecture d'images");
		}
	}
}

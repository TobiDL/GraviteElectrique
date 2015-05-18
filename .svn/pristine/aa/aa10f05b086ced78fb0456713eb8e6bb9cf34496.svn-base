package objets.graphiques;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
/**
 *  Classe permettant de creer les vecteurs
 * @author Jude
 * @version 13/02/14
 */
public class VecteurGraphique {

	private double norme, x, y;
	private double origX;
	private double origY;
	private double orientation; //angle en radians, a partir de l'origine du vecteur
	private double longPointe = 20; //longueur des deux traits formant la pointe de la fleche
	private double rotPointe = 0.3; //angle entre les traits de tete et la fleche, en radians
	private Color couleur;
	/**
	 * Constructeur du vecteur
	 * @param  double origX
	 * @param  double origY
	 * @param  double norme
	 * @param  double orientation (rad)
	 */
	public VecteurGraphique(double origX, double origY, double norme, double orientation) {
		this.origX = origX;
		this.origY = origY;
		this.norme = norme;
		this.orientation = orientation;
		couleur=Color.green;
	}
	/**
	 * Constructeur du vecteur pour calcul
	 * @param  double x
	 * @param  double y	
	 */
	public VecteurGraphique(double x, double y){
		this.x=x;
		this.y=y;
	}
	/**
	 * Methode permettatn de dessiner l'objet vecteur graphique
	 * @param Graphics g2d	 
	 */
	public void dessiner(Graphics2D g2d,AffineTransform matTransfo) {
		//sauvegarde des transformations courantes
		AffineTransform mat = g2d.getTransform();
		g2d.setColor(couleur);
		g2d.rotate(orientation, origX, origY);

		double finX = origX+norme;

		g2d.draw( matTransfo.createTransformedShape(new Line2D.Double(origX, origY, finX, origY)));  //ligne formant le vecteur

		Line2D.Double traitDeTete = new Line2D.Double( finX-longPointe, origY, finX, origY);
		g2d.rotate(rotPointe, finX,  origY);
		g2d.draw(matTransfo.createTransformedShape(traitDeTete));  //un des deux traits qui forment la tete de fleche
		g2d.rotate(-2*rotPointe, finX, origY);
		g2d.draw(matTransfo.createTransformedShape(traitDeTete));

		g2d.setTransform(mat);  //restauration des transformations precedentes
	}
	/**
	 * Methode retournant un vecteur multiplie par une constant k
	 */
	public VecteurGraphique multiplicationParK( double k){	
		VecteurGraphique v= new VecteurGraphique(x*k,y*k);
		System.out.println("x:"+x*k+"par k , y: "+y*k);
		return  v;
	}
	/**
	 * Methode retournant les valeur du vecteur sous forme de String
	 */
	public String toString() {
		return String.format("(%.2f, %.2f)", x, y);
	}
	/**
	 * Methode retournant le produit scalaire de deux vecteurs
	 */
	public double produitScalaire( double xP, double yP){	
		double  scalaire=0;
		if(x!=0 && y!=0){			
			scalaire=x*xP+y*yP;
		}	
		System.out.println("scalaire: "+scalaire);	
		return scalaire;
	}
	/**
	 * Methode retournant l'angle entre le vecteur un vecteur passe en parametre
	 */
	public double angle(double xP, double yP){	
		// cos(a ) = (A x B) / A / B
		return Math.acos(x * xP + y * yP)/ (Math.sqrt(x*x+y*y)/Math.sqrt(xP*xP+yP*yP))*180/Math.PI;
	}
	/**
	 * Methode retournant un vecteur additione d'un autre vecteur
	 */
	public VecteurGraphique additionnerLesVecteurs(VecteurGraphique vecteur2){		
		System.out.println("x: "+ x+"(addition ,y: "+y);
		VecteurGraphique v= new VecteurGraphique(x + vecteur2.getX(),y + vecteur2.getY());
		return v;
	}
	/**
	 * Methode retournant le vecteur unitaire
	 */
	public VecteurGraphique unitaire(){
		double u=Math.sqrt(Math.pow(x,2)+Math.pow(y, 2));		
		VecteurGraphique v= new VecteurGraphique(x/u,y/u);
		return v;
	}
	/**
	 * Methode permettant de modifier la longueur de la pointe vecteur 
	 * @param Graphics g2d	 
	 */
	public void setLongPointe(double longPointe){
		this.longPointe = longPointe;
	}
	/**
	 * Methode retournant la valeur x du vecteur
	 */
	public double getX() {
		return x;
	}
	/**
	 * Methode permettatn de modifier la valeur x
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * Methode retournant la valeur y du vecteur
	 */
	public double getY() {
		return y;
	}
	/**
	 * Methode permettant de modifier la valeur x
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * Methode retourne l'orientation du vecteur
	 */
	public double getOrientation() {
		return orientation;
	}
	/**
	 * Methode modifiant l'orientation du vecteur
	 */
	public void setOrientation(double orientation) {
		this.orientation = orientation;
	}
	/**
	 * Methode retournant la norme du vecteur
	 */
	public double getNorme() {
		return norme;
	}
	/**
	 * Methode permettant de modifier la norme du vecteur
	 */
	public void setNorme(double norme) {
		this.norme = norme;
	}
	/**
	 * Methode retournant la couleur du vecteur
	 */
	public Color getCouleur() {
		return couleur;
	}
	/**
	 * Methode retournant la couleur du vecteur
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	/**
	 * Methode retournant la position d'origine X du vecteur
	 */
	public double getOrigX() {
		return origX;
	}
	/**
	 * Methode modifiant la position X du vecteur
	 */
	public void setOrigX(double origX) {
		this.origX = origX;
	}
	/**
	 * Methode retournant la position d'origine Y du vecteur
	 */
	public double getOrigY() {
		return origY;
	}
	/**
	 * Methode modifiant la position y du vecteur
	 */
	public void setOrigY(double origY) {
		this.origY = origY;
	}

}//fin classe
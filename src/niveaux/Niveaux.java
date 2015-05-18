package niveaux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;





import objets.graphiques.Canon;
import objets.graphiques.Cible;
import objets.graphiques.VecteurGraphique;
import objets.graphiques.Projectile;
import obstacles.ObstacleElectrique;
import obstacles.ObstaclePhysique;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.io.IOException;

import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JLabel;
import javax.swing.JSpinner;

import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.EventListenerList;

import ecouteur.niveau.EcouteurDeNiveaux;

import javax.swing.JButton;
import javax.swing.JProgressBar;

import java.awt.Font;

/**
 * Classe permettant d'instancier un niveau 
 * @author Edouard
 * @version 14/02/14
 */
public class Niveaux extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	//Constantes
	//	private final double LARGEUR_MONDE=1100;
	private final double HAUTEUR_MONDE= 600;

	//Objets dessin�s
	private Canon canon;
	private Projectile projectile;
	private Projectile balleHypo;
	private Rectangle2D boundsHaut,boundsDroit,sol,barreGauche,boundsBas;
	private ArrayList<ObstaclePhysique> obstaclesPhysiques;
	private ArrayList<ObstacleElectrique> obstaclesElectriques;
	private ArrayList<Shape> testShapePhy,testShapeElec;
	private ArrayList<ObstaclePhysique> listeObstacle;
	private ArrayList<ObstacleElectrique> listeObstacleElec;
	private Cible cible;

	//autres donnees
	private double rotate = 25;
	private double hauteur;
	private double largeur;
	private boolean enAnimation = false, premierClick = true, cibleAtteinte = false; 
	private Thread proc=null;
	private double distTang;
	private int posXSouris,posYSouris;
	private boolean collisionElectrique,modeScientifique,vecteurVItesse,vecteurAcceleration, indice=false;
	private int nombreDeTirs = 0 ;

	// niveau
	private Integer numero=1;

	// projectile
	private Integer chargeProjectile;
	private double vitesseX;
	private double vitesseY;
	private double deltaT=0.001;
	private Image niveauReussi;
	private double temps = 0;
	private JCheckBox chckbxModeScienti,chckbxModeVitesse,chckbxModeAcceleration;

	// vecteur
	private VecteurGraphique vecteur;
	private Ellipse2D pointer;

	//panneau laterale gauche
	private JLabel lblVitesseXI;
	private JLabel lblVitesseYI;
	private JLabel selecteurDeNiveaux;
	// indicateur
	private JLabel lblVitesseX;
	private JLabel lblVitesseY;
	private JSpinner spinnerChargeProjectile;	
	private JSpinner spinnerDT;
	private JLabel lblCharge,lblChargeCoulomb;
	private JLabel lblDT;

	//Ecouteurs
	private final EventListenerList ecouteurs;
	private JButton btnSuivant,btnSuivantN;
	private JButton btnPrecedent;
	private JButton buttonRecommencer;
	private JProgressBar progressBarX;
	private JProgressBar progressBarY;
	private JLabel labelVitesseIniX;
	private JLabel labelVitesseIniY;
	private JLabel lblTirs;
	private java.awt.geom.Rectangle2D.Double barreGaucheP;
	private Rectangle2D.Double backgroundChangeurDeNiveaux;
	private boolean dessiner = false;

	/**
	 * Constructeur du niveau il initalise un niveau vide
	 */
	public Niveaux(){	

		barreLateraleGauche();	
		if(!enAnimation){
			mouvementsDeSouris();				
		}

		pointer=new Ellipse2D.Double(0,0,0,0);
		ecouteurs = new EventListenerList();
		vecteur= new VecteurGraphique(250,250,3,1.3);
		obstaclesPhysiques = new ArrayList<ObstaclePhysique>();
		obstaclesElectriques = new ArrayList<ObstacleElectrique>();
		testShapePhy= new ArrayList<Shape>();
		testShapeElec= new ArrayList<Shape>();
		listeObstacle= new ArrayList<ObstaclePhysique>();
		listeObstacleElec=new ArrayList<ObstacleElectrique>();
		collisionElectrique=false;
		modeScientifique=false;		
		vecteurVItesse=true;
		chargeProjectile=1;
		try {
			niveauReussi = ImageIO.read(getClass().getClassLoader().getResource("niveauReussi.png"));
		} catch (IOException e) {			
			e.printStackTrace();
		}		

		setBackground(new Color(0,0,50));


		setPreferredSize(new Dimension(960,560));
		setLayout(null);
		selecteurDeNiveaux = new JLabel("Selecteur \n de niveau");
		selecteurDeNiveaux.setBounds(10,22,89,40);
		selecteurDeNiveaux.setFont(new Font("Lao UI", Font.PLAIN, 11));
		add(selecteurDeNiveaux);

		btnSuivantN = new JButton("Suivant");
		btnSuivantN.setFont(new Font("Lao UI", Font.PLAIN, 13));
		btnSuivantN.setEnabled(false);
		btnSuivantN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numero!=5){
					lanceEvenNiveauTerminer();
				}
			}
		});
		btnSuivantN.setBounds(10, 56, 89, 23);
		add(btnSuivantN);

		btnPrecedent = new JButton("Pr\u00E9c\u00E9dent");		
		btnPrecedent.setFont(new Font("Lao UI", Font.PLAIN, 13));
		btnPrecedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(numero!=1){
					rejouer();
					lanceEvenNiveauPrecedent();				
				}
			}
		});
		btnPrecedent.setBounds(10, 90, 89, 23);
		add(btnPrecedent);



		progressBarX = new JProgressBar();
		progressBarX.setForeground(new Color(255, 69, 0));
		progressBarX.setToolTipText("");
		progressBarX.setMaximum(78);
		progressBarX.setBounds(10, 151, 100, 17);
		add(progressBarX);

		progressBarY = new JProgressBar();
		progressBarY.setForeground(new Color(255, 69, 0));
		progressBarY.setToolTipText("");
		progressBarY.setMaximum(87);
		progressBarY.setBounds(10, 192, 100, 17);
		add(progressBarY);

		labelVitesseIniX = new JLabel("Vit Initiale X :");
		labelVitesseIniX.setFont(new Font("Lao UI", Font.PLAIN, 11));
		labelVitesseIniX.setBounds(10, 126, 153, 14);
		add(labelVitesseIniX);

		labelVitesseIniY = new JLabel("Vit Initiale Y :");
		labelVitesseIniY.setFont(new Font("Lao UI", Font.PLAIN, 11));
		labelVitesseIniY.setBounds(10, 172, 153, 14);
		add(labelVitesseIniY);

		lblTirs = new JLabel("Tirs : "+ nombreDeTirs);
		lblTirs.setFont(new Font("Lao UI", Font.PLAIN, 17));
		lblTirs.setBackground(new Color(255, 255, 0));
		lblTirs.setBounds(10, 287, 70, 30);
		add(lblTirs);

		lblVitesseXI = new JLabel("Vitesse en X");		
		lblVitesseXI.setFont(new Font("Lao UI", Font.PLAIN, 12));
		lblVitesseXI.setBounds(10, 406, 100, 15);
		add(lblVitesseXI);

		lblVitesseX = new JLabel(" "+0+ " (cm/s)");		
		lblVitesseX.setFont(new Font("Lao UI", Font.PLAIN, 12));
		lblVitesseX.setBounds(10, 426, 100, 14);
		add(lblVitesseX);

		lblVitesseYI = new JLabel("Vitesse en Y");
		lblVitesseYI.setFont(new Font("Lao UI", Font.PLAIN, 12));
		lblVitesseYI.setBounds(10, 446, 100, 14);
		add(lblVitesseYI);

		lblVitesseY = new JLabel(" "+ 0 +" (cm/s)");
		lblVitesseY.setFont(new Font("Lao UI", Font.PLAIN, 12));
		lblVitesseY.setBounds(10, 466, 100, 14);
		add(lblVitesseY);

		lblVitesseXI.setEnabled(false);				
		lblVitesseX.setEnabled(false);
		lblVitesseYI.setEnabled(false);
		lblVitesseY.setEnabled(false);

		ajouterLeChangeurDeNiveaux();
	}
	/**
	 * Methode utilisee pour r�initialiser les parametres du niveau
	 */
	private void  rejouer(){
		cibleAtteinte=false;
		nombreDeTirs=0;				
		enAnimation=false;
		premierClick=true;	
		collisionElectrique=false;	
		modeScientifique=false;		
		vecteurVItesse=true;
		indice=false;
		chckbxModeScienti.setSelected(false);					
		chckbxModeVitesse.setEnabled(false);
		chckbxModeAcceleration.setEnabled(false);
		lblDT.setEnabled(false);
		spinnerDT.setEnabled(false);
		lblVitesseXI.setEnabled(false);				
		lblVitesseX.setEnabled(false);
		lblVitesseYI.setEnabled(false);
		lblVitesseY.setEnabled(false);
		buttonRecommencer.setEnabled(false);
		repaint();	
	}
	/**
	 * Methode utilisee pour ajouter les elements de la barre laterale gauche	 
	 */
	private void barreLateraleGauche() {	

		chckbxModeScienti = new JCheckBox("Mode Scientifique");
		chckbxModeScienti.setFont(new Font("Lao UI", Font.PLAIN, 13));
		chckbxModeScienti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				if(chckbxModeScienti.isSelected()){
					modeScientifique=true;						
					chckbxModeVitesse.setEnabled(true);
					chckbxModeAcceleration.setEnabled(true);
					lblDT.setEnabled(true);
					spinnerDT.setEnabled(true);					
					lblVitesseXI.setEnabled(true);				
					lblVitesseX.setEnabled(true);
					lblVitesseYI.setEnabled(true);
					lblVitesseY.setEnabled(true);

					repaint();
				} else {			
					modeScientifique=false;	
					chckbxModeScienti.setSelected(false);					
					chckbxModeVitesse.setEnabled(false);
					chckbxModeAcceleration.setEnabled(false);
					lblDT.setEnabled(false);
					spinnerDT.setEnabled(false);
					lblVitesseXI.setEnabled(false);				
					lblVitesseX.setEnabled(false);
					lblVitesseYI.setEnabled(false);
					lblVitesseY.setEnabled(false);
					repaint();				
				}
			}

		});
		chckbxModeScienti.setBounds(10, 316, 150, 23);
		add(chckbxModeScienti);

		chckbxModeVitesse = new JCheckBox("Affi. Vitesse");
		chckbxModeVitesse.setFont(new Font("Lao UI", Font.PLAIN, 13));
		chckbxModeVitesse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				if(chckbxModeVitesse.isSelected()){
					vecteurAcceleration=false;
					vecteurVItesse=true;
					chckbxModeAcceleration.setSelected(false);
					repaint();
				} else {			
					vecteurAcceleration=true;	
					vecteurVItesse=false;
					chckbxModeVitesse.setSelected(false);
					chckbxModeAcceleration.setSelected(true);
					repaint();				
				}
			}

		});
		chckbxModeVitesse.setBounds(13, 346, 97, 23);
		chckbxModeVitesse.setSelected(true);
		chckbxModeVitesse.setEnabled(false);
		add(chckbxModeVitesse);

		chckbxModeAcceleration = new JCheckBox("Affi. Acceleration");
		chckbxModeAcceleration.setFont(new Font("Lao UI", Font.PLAIN, 13));
		chckbxModeAcceleration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				if(chckbxModeAcceleration.isSelected()){
					vecteurAcceleration=true;
					vecteurVItesse=false;
					chckbxModeVitesse.setSelected(false);
					repaint();
				} else {			
					vecteurAcceleration=false;	
					vecteurVItesse=true;
					chckbxModeVitesse.setSelected(true);
					chckbxModeAcceleration.setSelected(false);
					repaint();				
				}
			}

		});
		chckbxModeAcceleration.setBounds(13, 376, 130, 23);
		chckbxModeAcceleration.setEnabled(false);
		add(chckbxModeAcceleration);

		spinnerChargeProjectile = new JSpinner();
		spinnerChargeProjectile.setFont(new Font("Lao UI", Font.PLAIN, 13));
		spinnerChargeProjectile.setModel(new SpinnerNumberModel(1, -5, 5, 1));
		spinnerChargeProjectile.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				chargeProjectile=(Integer) spinnerChargeProjectile.getValue();				
			}
		});
		spinnerChargeProjectile.setBounds(0, 257, 110, 35);
		add(spinnerChargeProjectile);			

		spinnerDT = new JSpinner();
		spinnerDT.setFont(new Font("Lao UI", Font.PLAIN, 12));
		spinnerDT.setModel(new SpinnerListModel(new String[] {"0.01","0.005","0.001"}));
		spinnerDT.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				deltaT=  Double.parseDouble( (String) spinnerDT.getValue());				
			}
		});	
		spinnerDT.setBounds(0, 499, 110, 35);
		add(spinnerDT);

		lblCharge = new JLabel("Ch. du Projectile");
		lblCharge.setFont(new Font("Lao UI", Font.PLAIN, 11));
		lblCharge.setBounds(10, 223, 100, 14);
		add(lblCharge);
		lblChargeCoulomb=new JLabel("en Coulomb");
		lblChargeCoulomb.setFont(new Font("Lao UI", Font.PLAIN, 11));
		lblChargeCoulomb.setBounds(10, 241, 100, 14);
		add(lblChargeCoulomb);

		lblDT = new JLabel("Dt du calcul");
		lblDT.setFont(new Font("Lao UI", Font.PLAIN, 12));
		lblDT.setEnabled(false);
		spinnerDT.setEnabled(false);
		lblDT.setBounds(10, 484, 70, 14);
		add(lblDT);
	}
	/**
	 * Methode utilis�e pour ajouter un obstacle physique de forme rectangulaire dans un niveau
	 * @param longueur : la longueur du rectangle
	 * @param hauteur : la hauteur du rectangle
	 * @param posX : la position en X du rectangle
	 * @param posY : la position en Y du rectangle
	 * @param angle : l'angle d'inclinaison du rectangle
	 */
	public void ajouterUnObstaclePhysiqueCarre(double longueur, double hauteur, double posX, double posY){
		ObstaclePhysique obstaclePhysique = new ObstaclePhysique(longueur,hauteur,posX,posY);
		obstaclesPhysiques.add(obstaclePhysique);
	}
	/**
	 * Methode utilisee pour ajouter un obstacle physique de forme circulaire dans un niveau
	 * @param rayon : le rayon qu'aura le cercle
	 * @param posX : la position en X du cercle
	 * @param posY : la position en Y du cercle
	 */
	public void ajouterUnObstaclePhysiqueRond(double rayon, double posX, double posY){
		ObstaclePhysique obstaclePhysique = new ObstaclePhysique(rayon,posX,posY);
		obstaclesPhysiques.add(obstaclePhysique);
	}
	/**
	 * Methode utilisee pour ajouter un obstacle physique dans un niveau quand l'obstacle a deja ete creer
	 * @param obsPhys : L'obstacle que l'on veut ajouter
	 */
	public void ajouterUnObstaclePhysique(ObstaclePhysique obsPhys){
		obstaclesPhysiques.add(obsPhys);
	}
	/**
	 * Methode utilisee pour ajouter un obstacle electrique dans un niveau
	 * @param obsElectrique : l'obstacle electrique que l'on veut ajouter
	 */
	public void ajouterUnObstacleElectrique(ObstacleElectrique obsElectrique){
		obstaclesElectriques.add(obsElectrique);
	}
	/**
	 * Methode utilisee pour placer la cible dans le niveau
	 * @param posX : position en X de la cible
	 * @param posY : position en Y de la cible
	 * @param largeur : largeur de la cible
	 */
	public void ajouterlaCible(double posX,double posY,double largeur){
		cible = new Cible(posX, posY,largeur);
	}
	/**
	 * Methode utilisee pour modifier la charge du projectile
	 * @param charge : charge du projectile
	 */
	public void setChargeProjectile(Integer charge){
		chargeProjectile=charge;
	}
	/**
	 * Methode utilisee pour ajouter un canon
	 * @param angle : angle du canon	
	 * @param posX : position en X de la cible
	 */
	public void ajouterCanon(double angle, double positionX){
		canon = new Canon(angle, positionX,440,100);
	}
	/**
	 * Methode servant a dessiner le niveau
	 * @param Graphics g 	 
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		//AffineTransform matTransfo = matriceMondeVersComposant(0,0,LARGEUR_MONDE,HAUTEUR_MONDE);
		AffineTransform matTransfo = matriceMondeVersComposant(0,0,HAUTEUR_MONDE*getWidth()/(double)getHeight(),HAUTEUR_MONDE);	
		g2d.setColor(Color.blue);

		//limite
		boundsHaut=new Rectangle2D.Double(0, -850, 1100, 800);	
		boundsDroit=new Rectangle2D.Double(1115, 0, 800, getHeight());
		boundsBas = new Rectangle2D.Double(150,545,getWidth(),800);	

		g2d.fill(matTransfo.createTransformedShape(boundsDroit));
		g2d.fill(matTransfo.createTransformedShape(boundsHaut));
		g2d.fill(matTransfo.createTransformedShape(boundsBas));		
			if (cible != null){
			cible.creer(matTransfo);
			cible.dessiner(g2d, matTransfo);
		}
		// ensemble test
		if (enAnimation && balleHypo!=null && projectile!=null){			
			testBounds();	
			projectile.dessiner(g2d,matTransfo);

		}		// vecteur
		if(projectile!=null && modeScientifique){

			if(vecteurVItesse && enAnimation){			
				vecteur.setOrigX((int)projectile.getPosX()+6);
				vecteur.setOrigY((int)projectile.getPosY()+6);
				vecteur.setNorme(projectile.getForceVecteur());
				vecteur.setOrientation(projectile.getOrientationVecteur());	
				if(projectile.getCharge() >0){
					vecteur.setCouleur((new Color(255,0,0,255)));}				
				if(projectile.getCharge() <0){
					vecteur.setCouleur((new Color(0,128,128,255)));}				
				if(projectile.getCharge()==0){
					vecteur.setCouleur((new Color(255,215,0,255)));}				
				vecteur.dessiner(g2d,matTransfo);
			}
			if(vecteurAcceleration && enAnimation){			
				vecteur.setOrigX((int)projectile.getPosX()+6);
				vecteur.setOrigY((int)projectile.getPosY()+6);
				vecteur.setNorme(projectile.getForceAcceleration()*4);
				if(projectile.getCharge() >0){
					vecteur.setCouleur((new Color(255,0,0,255)));}				
				if(projectile.getCharge() <0){
					vecteur.setCouleur((new Color(0,128,128,255)));}				
				if(projectile.getCharge()==0){
					vecteur.setCouleur((new Color(255,215,0,255)));}	
				vecteur.setOrientation(projectile.getOrientationAcceleration());
				vecteur.dessiner(g2d,matTransfo);
			}

		}
		//obsPhysique
		if (!obstaclesPhysiques.isEmpty()){			
			for (int i = 0; i < obstaclesPhysiques.size(); i++) {
				obstaclesPhysiques.get(i).dessiner(g2d, matTransfo);
				obstaclesPhysiques.get(i).creer( matTransfo);
				testShapePhy.add(obstaclesPhysiques.get(i).getShape());	
				listeObstacle.add(obstaclesPhysiques.get(i));
			}
		}
		//obsElectriques
		if (!obstaclesElectriques.isEmpty()){			
			for (int i = 0; i < obstaclesElectriques.size(); i++) {
				if(collisionElectrique){
					obstaclesElectriques.get(i).setAireVisible(true);
				} else {
					obstaclesElectriques.get(i).setAireVisible(false);
				}
				obstaclesElectriques.get(i).creer( matTransfo);
				obstaclesElectriques.get(i).dessiner(g2d, matTransfo);	
				testShapeElec.add(obstaclesElectriques.get(i).getShape());	
				listeObstacleElec.add(obstaclesElectriques.get(i));
			}
		}	
		
		if(dessiner){
			g2d.setColor(new Color(0,0,0,85));
			g2d.fill(backgroundChangeurDeNiveaux);
			g2d.drawImage(niveauReussi, ((getWidth()/2)-200), ((getHeight()/2)-50)-100, null);
			add(btnSuivant);
			add(buttonRecommencer);
			add(btnPrecedent);
		}else{
			remove(btnSuivant);
			remove(buttonRecommencer);
			remove(btnPrecedent);
			//remove(backgroundChangeurDeNiveaux);
		} 
		
		barreGauche=new Rectangle2D.Double(-800,0,950,getHeight());	
		barreGaucheP=new Rectangle2D.Double(-805,0,955,getHeight());
		sol = new Rectangle2D.Double(0,440+90,getWidth(),400);	
		g2d.setColor(Color.black);	
		g2d.fill(matTransfo.createTransformedShape(sol));
		g2d.setColor(new Color(0,0,0,120));
		g2d.fill(matTransfo.createTransformedShape(barreGauche));
		g2d.setColor(Color.white);
		g2d.fill(matTransfo.createTransformedShape(barreGaucheP));		

		g2d.setColor(Color.gray);
		g2d.fill(matTransfo.createTransformedShape(pointer));

		if(projectile!=null){
			lblVitesseX.setText(" "+ Math.round(projectile.getVitesseX()) +" (cm/s)");
			lblVitesseY.setText(" "+ Math.round(projectile.getVitesseY()) +" (cm/s)");
		}

		g2d.setColor(Color.black);	

		labelVitesseIniX.setText("Vit Initiale X: "+ Math.round(vitesseX)+ " (cm/s)");
		labelVitesseIniY.setText("Vit Initiale Y: "+ -Math.round(vitesseY)+ " (cm/s)");
		lblTirs.setText("Tirs : " +nombreDeTirs);

		if(nombreDeTirs>3){
			indice=true;
		}		
		if(indice){
			switch(numero) {
			case 1:			
				g2d.drawString(" Indice : 48X,-81Y ", 0, 575);
				break;
			case 2:			
				g2d.drawString(" Indice : 78X,-74Y ", 0,575);
				break;	
			case 3:			
				g2d.drawString(" Indice : 57X,74Y ", 0, 575);
				g2d.drawString("+ charge nul ", 5, 590);
				break;
			case 4:			
				g2d.drawString(" Indice : 37X,-83Y ", 0, 575);
				g2d.drawString("+ charge nul ", 5, 590);
				break;
			case 5:			
				g2d.drawString(" Indice : 77x,-84Y ", 0, 575);
				g2d.drawString("+ charge de -3 ", 5, 590);
				break;

			}			
		}

		if(cibleAtteinte){
			//g2d.drawImage(niveauReussi, ((getWidth()/2)-200), ((getHeight()/2)-50), null);
		}

		canon.setAngle(-rotate);
		canon.dessiner(g2d,matTransfo); 		
	}
	/**
	 * methode responsable de l'animation
	 */
	@Override
	public void run() {
		while(enAnimation){
			int i;
			//10,20000
			//40,40000
			for(i=0; i < 10; i++) {
				temps=temps + deltaT / 10;	
				if ( balleHypo!=null && projectile!=null){
					AffineTransform matTransfo = matriceMondeVersComposant(0,0,HAUTEUR_MONDE*getWidth()/(double)getHeight(),HAUTEUR_MONDE);	
					deplacer(matTransfo);
					testCollisionCible();
					repaint();
				}
			}			
			if(balleHypo.getVitesseY()>200 || balleHypo.getVitesseX()>200){				
				finThread();
				System.out.println("fin vitesse");
			}
			try {
				Thread.sleep((long) (deltaT*20000));			
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}	
	}
	/**
	 * methode pour mettre fin au  thread de l'animation 
	 */
	private void finThread() {
		enAnimation=false;
		premierClick=true;	
		collisionElectrique=false;		
		lblVitesseX.setText(" "+ Math.round(projectile.getVitesseX()) +" (cm/s)");
		lblVitesseY.setText(" "+ Math.round(projectile.getVitesseY()) +" (cm/s)");	
		nombreDeTirs=nombreDeTirs +1 ;		
		if(nombreDeTirs>8 || cibleAtteinte){
			if(numero!=6 && numero!=5){
				btnSuivantN.setEnabled(true);
				btnSuivant.setEnabled(true);
				}
			buttonRecommencer.setEnabled(true);
			if(numero!=1 && numero!=6){
				btnPrecedent.setEnabled(true);		
			}			
		}
		repaint();
	}
	/**
	 * methode deplacant le projectile tout en faisant les test
	 */
	private void deplacer(AffineTransform matTransfo){
		balleHypo.creer(matTransfo);				
		projectile.creer(matTransfo);
		//if(!balleHypo.isEnCollision()){			
			testCollision();
	//	}	
		if( balleHypo.getCharge()!=0 && !balleHypo.isEnCollisionElec()){	
			testCollisionElectrique();
		}
		balleHypo.deplacerLeProjectile(temps);	
		projectile.deplacerLeProjectile(temps);	
		repaint();
	}
	/**
	 * methode partant l'animation
	 */
	private void play() {			
		proc = new Thread(Niveaux.this);
		proc.start();
		temps=0;		
		premierClick = false;		
	}
	/**
	 * Methode privee pour ajouter les mouvements de la souris a l'application
	 */
	private void mouvementsDeSouris(){
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				if(premierClick && arg0.getX()>150){	
					enAnimation = true;
					projectile = new Projectile(chargeProjectile,vitesseX,vitesseY,canon.getPosX()+50,canon.getPosY()+35);
					balleHypo= new Projectile(chargeProjectile,vitesseX,vitesseY,canon.getPosX()+50,canon.getPosY()+35);
					pointer=new Ellipse2D.Double(arg0.getX()-4,arg0.getY()-4,8,8);						
					premierClick=false;						
					if(enAnimation){							
						play();
					}
				}
				if(arg0.getClickCount()==2){					
					finThread();
				}
			}
		});		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				if(!enAnimation) {
					angleDeTir(arg0);
					repaint();
				}				
			}
		});
	}
	/**
	 * Methode privee faire les test de collision
	 * @param Graphics2D g2d 
	 * @param AffineTransform matTransfo : matrice de transformation
	 */
	private void testCollision() {
		if (!testShapePhy.isEmpty()){	
			for (int i = 0; i < testShapePhy.size(); i++) {
				if(testShapePhy.get(i).getBounds2D().intersects((Rectangle2D) balleHypo.getShape().getBounds2D())){ 					
					if(listeObstacle.get(i).getRayon()==0){
						Area balle=new Area(balleHypo.getShape());
						Area obs=new Area(testShapePhy.get(i));
						Area coliPhy= new Area(balle);
						coliPhy.intersect(obs);

						if(!coliPhy.isEmpty()){	
							if(!balleHypo.isEnCollision()){						
								balleHypo.collisonPhysique(listeObstacle.get(i));	
								projectile.setVitesse(balleHypo.getVitesseX(),balleHypo.getVitesseY());
								projectile.setPosY(balleHypo.getPosY());
								projectile.setPosX(balleHypo.getPosX());
								balleHypo.setEnCollision(true);
								projectile.setEnCollision(true);
							}
						}
					} else {						
						//  # calculate distance between circle and particle
						double  dX = balleHypo.getPosX()+12 - listeObstacle.get(i).getPosX()-listeObstacle.get(i).getRayon();
						double  dY = balleHypo.getPosY()+12 - listeObstacle.get(i).getPosY()-listeObstacle.get(i).getRayon();
						double  distance = Math.sqrt(dX * dX + dY * dY);
						if (distance <= listeObstacle.get(i).getRayon() + 12){
							if(!balleHypo.isEnCollision()){
								balleHypo.collisonPhysique(listeObstacle.get(i));	
								projectile.setVitesse(balleHypo.getVitesseX(),balleHypo.getVitesseY());
								projectile.setPosY(balleHypo.getPosY());
								projectile.setPosX(balleHypo.getPosX());							
								balleHypo.setEnCollision(true);
								projectile.setEnCollision(true);
							}

						}

					}

				} else {					
					balleHypo.setEnCollision(false);
					projectile.setEnCollision(false);
				}
			}

		}//fin null
	} // fin methode
	/**
	 * Methode determinant si le projectile a atteint les limites	du composant 
	 */
	private void testBounds() {
		if(boundsBas.getBounds2D().intersects((Rectangle2D) projectile.getShape().getBounds2D())){											
			Area balle=new Area(balleHypo.getShape());
			Area obs=new Area(boundsBas);
			Area coliPhy= new Area(balle);
			coliPhy.intersect(obs);
			if(!coliPhy.isEmpty()){	
				finThread();
			}			
		} 	
		if(boundsHaut.getBounds2D().intersects((Rectangle2D) projectile.getShape().getBounds2D())){								
			Area balle=new Area(balleHypo.getShape());
			Area obs=new Area(boundsHaut);
			Area coliPhy= new Area(balle);
			coliPhy.intersect(obs);
			if(!coliPhy.isEmpty()){	
				finThread();
			}	
		} 	
		if(boundsDroit.getBounds2D().intersects((Rectangle2D) projectile.getShape().getBounds2D())){										
			Area balle=new Area(balleHypo.getShape());
			Area obs=new Area(boundsDroit);
			Area coliPhy= new Area(balle);
			coliPhy.intersect(obs);
			if(!coliPhy.isEmpty()){	
				finThread();
			}	
		} 
		if(barreGaucheP.getBounds2D().intersects((Rectangle2D) projectile.getShape().getBounds2D())){									
			Area balle=new Area(balleHypo.getShape());
			Area obs=new Area(barreGaucheP);
			Area coliPhy= new Area(balle);
			coliPhy.intersect(obs);
			if(!coliPhy.isEmpty()){	
				finThread();
			}	
		} 
		//fin 
	}
	/**
	 * Methode determinant si il y a eu collision electrique	
	 */
	private void testCollisionElectrique(){	
		if(projectile.getCharge()!=0){
			if (!testShapeElec.isEmpty()){				
				for (int i = 0; i < testShapeElec.size(); i++) { // pour chaque shape
					if(testShapeElec.get(i).getBounds2D().intersects((Rectangle2D) balleHypo.getShape().getBounds2D())){ // test preliminaire
						Area aireProjectile = new Area(balleHypo.getShape());		
						Area aireObstacleElectrique = new Area(testShapeElec.get(i));
						Area coliElec= new Area(aireProjectile);
						coliElec.intersect(aireObstacleElectrique);
						collisionElectrique=true;
						if(!coliElec.isEmpty()){	
							if(!balleHypo.isEnCollisionElec()){													
								balleHypo.collisionElectrique(listeObstacleElec.get(i));
								projectile.collisionElectrique(listeObstacleElec.get(i));	
								balleHypo.setEnCollisionElec(true);	
								projectile.setEnCollisionElec(true);
							}
						} else {	
							balleHypo.setEnCollisionElec(false);
							projectile.setEnCollisionElec(false);
							balleHypo.retablir();
							projectile.retablir();
							collisionElectrique=false;					
						}	
					}
				}				
			}
		}// fin test preliminaire
	}
	/**
	 * Methode determinant si le projectile atteint la cible
	 * @param Graphics2D g2d : 
	 * @param AffineTransform matTransfo : matrice monde vers composant
	 */
	private void testCollisionCible(){		
		if(cible.getShape().getBounds2D().intersects((Rectangle2D) projectile.getShape().getBounds2D())){										
			cibleAtteinte = true;		
			dessiner = true;
			finThread();					
		} 
	}
	/**
	 * Methode privee pour ajouter les mouvements de la souris a l'application
	 * @param MouseEvent arg0 : action de la souris
	 */
	private void angleDeTir(MouseEvent arg0){
		hauteur=canon.getPosY()-arg0.getY();				
		largeur=arg0.getX()-canon.getPosX();	
		posYSouris =arg0.getY();
		posXSouris=arg0.getX();

		distTang= Math.sqrt(Math.pow(hauteur, 2)+  Math.pow(largeur, 2));		
		rotate= Math.atan2(hauteur,largeur);

		if(hauteur<350 && hauteur> 0 ){	
			vitesseY=-(Math.sin(rotate)*(distTang)/4);
			progressBarY.setValue((int)-vitesseY);}

		if(posXSouris<canon.getPosX()){
			vitesseX=(Math.cos(rotate)*(distTang)/7);
			progressBarX.setValue((int)vitesseX);
		}
		if(posXSouris>canon.getPosX()&& largeur<550){
			vitesseX=(Math.cos(rotate)*(distTang)/7);	
			progressBarX.setValue((int)vitesseX);
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
	/**
	 * Methode qui permetera a l'application de savoir si elle doit passer au niveau suivant
	 */
	public void addNiveauTerminer(EcouteurDeNiveaux ecoute){
		ecouteurs.add(EcouteurDeNiveaux.class, ecoute);
	}
	/**
	 * Methode privee utilisee pour lancer l'evennement d'un niveau terminer
	 */
	private void lanceEvenNiveauTerminer(){
		for(EcouteurDeNiveaux ecoute: ecouteurs.getListeners(EcouteurDeNiveaux.class)){
			ecoute.niveauTerminer();
		}
	}
	/**
	 * Methode qui permetera a l'application de savoir si elle doit passer au niveau precedent
	 */
	public void addNiveauPrecedent(EcouteurDeNiveaux ecoute){
		ecouteurs.add(EcouteurDeNiveaux.class, ecoute);
	}
	/**
	 * Methode privee utilisee pour lancer l'evennement d'un niveau terminer
	 */
	private void lanceEvenNiveauPrecedent(){
		for(EcouteurDeNiveaux ecoute: ecouteurs.getListeners(EcouteurDeNiveaux.class)){
			ecoute.niveauPrecedent();
		}
	}
	/**
	 * Methode modifiant le numero du niveau
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	/**
	 * Classe pour ajouter le changeur de niveaux et les boutons
	 */
	private void ajouterLeChangeurDeNiveaux(){
		backgroundChangeurDeNiveaux = new Rectangle2D.Double(0, 0, 1250, 600);

		btnSuivant = new JButton("Suivant");
		btnSuivant.setFont(new Font("Lao UI", Font.PLAIN, 13));		
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numero!=5){
					lanceEvenNiveauTerminer();
				}
			}
		});
		btnSuivant.setBounds((1250/2)-89/2, 300-(23/2)-30, 89, 23);
		//add(btnSuivant);

		btnPrecedent = new JButton("Pr\u00E9c\u00E9dent");		
		btnPrecedent.setFont(new Font("Lao UI", Font.PLAIN, 13));
		btnPrecedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(numero!=1){
					rejouer();
					lanceEvenNiveauPrecedent();				
				}
			}
		});
		btnPrecedent.setBounds((1250/2)-89/2, 300-(23/2), 89, 23);
		//add(btnPrecedent);

		buttonRecommencer = new JButton("Rejouer");
		buttonRecommencer.setFont(new Font("Lao UI", Font.PLAIN, 13));
		buttonRecommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rejouer();
				dessiner = false;
			}
		});		
		//buttonRecommencer.setEnabled(false);
		buttonRecommencer.setBounds((1250/2)-89/2, 300-(23/2)+30, 89, 23);
		//add(buttonRecommencer);
	}
	/**
	 * Methode qui permet de savoir combien de tirs l'utilisateur a fait dans un niveau
	 * @return nombreDeTirs : le nombre de tirs qui ont �t� effectu�s
	 */
	public int getNombreDeTirs() {
		return nombreDeTirs;
	}
	/**
	 * Methode qui permet au panel de savoir l'etati  de l'animation 
	 * @return si le niveau est en animation ou pas
	 */
	public boolean getEnAnimation(){
		return enAnimation;
	}
	/**
	 * Methode qui permet au panel de mettre fin a l'animation
	 * @param enAnimation
	 */
	public void setEnAnimation( boolean enAnimation){
		this.enAnimation=enAnimation;
	}
	/**
	 * methode utilisee pour retourner le bouton precedent
	 * @return le bouton "precedent"
	 */
	public JButton getBtnPrecedent() {
		return btnPrecedent;
	}
	/**
	 * Methode utilisee pour modifier le bouton precedent
	 * @param btnPrecedent 
	 */
	public void setBtnPrecedent(JButton btnPrecedent) {
		this.btnPrecedent = btnPrecedent;
	}
	/**
	 * Methode utiliser pour retourner le bouton suivant
	 * @return the btnSuivant
	 */
	public JButton getBtnSuivant() {
		return btnSuivant;
	}
	/**
	 * Methode utilisee pour modifier le boutton : Suivant
	 * @param btnSuivant 
	 */
	public void setBtnSuivant(JButton btnSuivant) {
		this.btnSuivant = btnSuivant;
	}
	/**
	 * Methode utilisee pour retourner le bouton recommencer
	 * @return the buttonRecommencer
	 */
	public JButton getButtonRecommencer() {
		return buttonRecommencer;
	}
	/**
	 * Methode utilise pour savoir s'il faut activer le bouton recommencer
	 * @param buttonRecommencer : le button en question
	 */
	public void setButtonRecommencer(JButton buttonRecommencer) {
		this.buttonRecommencer = buttonRecommencer;
	}
	
}

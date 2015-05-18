package niveaux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.util.ArrayList;
//import java.util.TreeSet;

import javax.swing.JPanel;

import ecouteur.niveau.EcouteurDeNiveaux;

import obstacles.ObstacleElectrique;
/**
 *  Classe cr�e pour gerer les niveaux
 * @author Jude et Edouard
 * @version 13/02/14
 */
public class GestionnareDeNiveaux extends JPanel {
	/**
	 * Classe utilisis�e pour generer et gerer les niveaux qui sont cr�es
	 * @author Jude Fort
	 * @version : 13/03/14
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Niveaux> niveaux;
	private Niveaux niveauPerso;
	private boolean dessinerLesNiveaux = false;
	private int niveauEnCours = 0;
	private int k=0;
	private int nbDeNiveaux = 5 ;
	/**
	 * constructeur du gestionnaire de niveau	
	 */
	public GestionnareDeNiveaux(){
		setLayout(null);
		setBackground(Color.black);
		setPreferredSize(new Dimension(944,562));
		niveaux = new ArrayList<Niveaux>();
		ajouterDesNiveaux();
		modification();
	}
	/**
	 * Methode qui permet de retourner un niveau
	 * @return le niveaux
	 */
	public ArrayList<Niveaux> getNiveaux() {
		return niveaux;
	}
	/**
	 * Methode qui permet de modifier un niveau
	 * @param niveaux the niveaux to set
	 */
	public void setNiveaux(ArrayList<Niveaux> niveaux) {
		this.niveaux = niveaux;
	}
	/**
	 * methode permettant de dessiner le gestionnaire
	 * @param Graphics g
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Graphics2D g2d = (Graphics2D) g;
		if (dessinerLesNiveaux){			
			if(k<=nbDeNiveaux-1){
				if(niveaux.get(k)!=null) {
					add(niveaux.get(k));					
				}
			}else{
				k= 0;
				add(niveaux.get(k));
			}
			if(niveauEnCours!= 0){
				remove(niveaux.get(niveauEnCours- 1));			}
		}else{
			if(niveauPerso!= null){
				add(niveauPerso);				
			}
		}
	}
	/**
	 * methode permettant d'ajouter un niveau au gestionnaire
	 * @param Niveaux niveau
	 */
	public void ajouterUnNiveau(Niveaux niveau){
		niveauPerso = niveau;
	}
	/**
	 * Methide permettant d'enlever un niveau au gestionnaire
	 */
	public void retirerUnNiveau(){
		remove(niveauPerso);
		repaint();
	}
	/**
	 * Methode qui permet de dessiner les niveaux qui sont deja dans le jeu 
	 * @param b
	 */
	public void setDessinerLesNiveaux(boolean b){
		dessinerLesNiveaux = b ;
	}
	/**
	 * Methide qui permet de savoir quel niveau qui est en cours
	 * @return the k
	 */
	public int getK() {
		return k;
	}
	/**
	 * Methode qui permet de chager le niveau en cours pour le suivant
	 * @param k the k to set
	 */
	public void setK() {
		k++;
	}
	/**
	 * methode prive permettant d'ajouter plusieurs niveau au gestionnaire	
	 */
	private void ajouterDesNiveaux(){
		Niveaux niveau1 = new Niveaux();	

		niveau1.ajouterUnObstaclePhysiqueCarre(50,275,550,225);			
		niveau1.setNumero(1);
		niveau1.getBtnSuivant().setEnabled(false);
		niveau1.getBtnPrecedent().setEnabled(false);
		niveau1.ajouterlaCible(900,400,150);			
		niveau1.ajouterCanon(0,150);
		niveau1.setBounds(0, 0, 1100, 600);
		niveaux.add(niveau1);

		Niveaux niveau2 = new Niveaux();		
		niveau2.ajouterUnObstaclePhysiqueCarre(250,125,292,140);
		niveau2.setNumero(2);		
		niveau2.ajouterUnObstacleElectrique(new ObstacleElectrique(8, 925, 350));
		niveau2.ajouterlaCible(739,108,150);
		niveau2.ajouterCanon(0,150);
		niveau2.setBounds(0, 0, 1100, 600);
		niveaux.add(niveau2);

		Niveaux niveau3 = new Niveaux();		
		niveau3.setNumero(3);
		niveau3.ajouterUnObstacleElectrique(new ObstacleElectrique(10, 730, 61));
		niveau3.ajouterUnObstacleElectrique(new ObstacleElectrique(-10, 628, 310));
		niveau3.ajouterUnObstacleElectrique(new ObstacleElectrique(10, 464, 104));
		niveau3.ajouterlaCible(740,133,150);
		niveau3.ajouterCanon(0,150);
		niveau3.setBounds(0, 0, 1100, 600);
		niveaux.add(niveau3);	

		Niveaux niveau4 = new Niveaux();
		niveau4.ajouterUnObstaclePhysiqueRond(85,537,123);
		niveau4.ajouterUnObstaclePhysiqueCarre(150,125,190,205);
		niveau4.setNumero(4);
		niveau4.ajouterUnObstacleElectrique(new ObstacleElectrique(10, 632, 427));	
		niveau4.ajouterlaCible(235,109,150);
		niveau4.ajouterCanon(0,150);
		niveau4.setBounds(0, 0, 1100, 600);
		niveaux.add(niveau4);	

		Niveaux niveau5 = new Niveaux();
		niveau5.ajouterUnObstaclePhysiqueCarre(75,300,786,206);
		niveau5.setNumero(5);
		niveau5.getBtnSuivant().setEnabled(false);
		niveau5.ajouterUnObstacleElectrique(new ObstacleElectrique(-5, 974, 108));	
		niveau5.ajouterlaCible(922,408,175);
		niveau5.ajouterCanon(0,150);
		niveau5.setBounds(0, 0, 1100, 600);
		niveaux.add(niveau5);	

	}
	/**
	 * Methode privee qui permet de changer de niveau
	 */
	private void modification(){
		if(!niveaux.isEmpty()){
			for(int i = 0 ; i < niveaux.size();i++){
				if(niveaux.get(i)!=null){
					niveaux.get(i).addNiveauTerminer(new EcouteurDeNiveaux(){					
						public void niveauTerminer(){	
							if(niveaux.get(k)!=null){														
								niveaux.get(k).setVisible(false);
								k=k+1;	
								niveaux.get(k).setVisible(true);
							}							
							//System.out.println(k + "terminer");
							repaint();							
						}
						@Override
						public void niveauPrecedent() {
							if(niveaux.get(k)!=null){														
								niveaux.get(k).setVisible(false);
								k=k-1;		
								niveaux.get(k).setVisible(true);
							}							
							//System.out.println(k + "avant");
							repaint();								
						}

					});
				}//
			}
		}

	}
}

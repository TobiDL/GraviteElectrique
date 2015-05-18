package aapplication;

import instructions.Instructions;
import interfaces.graphiques.autres.MenuDeDepart;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import barre.droite.Bouton;
import barre.droite.ChoixDOptions;
import barre.droite.OptionsListener;
import barre.droite.TypeDOption;

import niveaux.EditeurDeNiveaux;
import niveaux.GestionnareDeNiveaux;
import composant.menu.depart.ChoixDAction;
import composant.menu.depart.ActionChoisieListener;
import composant.menu.depart.TypeDAction;

/**
 * Classe graphique affichant tous les composants de l'application
 * @author Jude et Edouard
 * @version 29/04/14
 */
public class App07GraviteElectrique extends JFrame {

	/**
	 * Classe qui cree le frame de l'application et tout ce quelle contient
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MenuDeDepart menuDeDepart;	
	private GestionnareDeNiveaux gdnJ,gdnC;
	private EditeurDeNiveaux edn;
	private Instructions instruction;
	private ChoixDOptions copJouer,copEditeur,copInstructions;
	private ChoixDAction choixDAction;
	private Bouton boutonRetour, boutonQuitter;
	
	int k=0;

	/**
	 * Lancer l'application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App07GraviteElectrique frame = new App07GraviteElectrique();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setExtendedState(JFrame.NORMAL);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App07GraviteElectrique() {
		setResizable(false);
		setTitle("Gravite Electrique");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1215, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		boutonQuitter = new Bouton();
		boutonQuitter.setbutton("quitter");
		boutonQuitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Object[] options = {"Oui","Non"};
				int n = JOptionPane.showOptionDialog(null,"Quitter l'application ? ","Quitter",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[0]);	
				if (n == JOptionPane.YES_OPTION) {									
					System.exit(0);
				}
			}
		});
		boutonQuitter.setBounds(1150,0,50,50);
		gdnJ = new GestionnareDeNiveaux();
		gdnJ.setBounds(0, 25, 1100, 600);

		gdnC = new GestionnareDeNiveaux();
		gdnC.setBounds(0, 25, 1100, 600);

		edn = new EditeurDeNiveaux();
		edn.setBounds(0, 25, 1100, 600);

		instruction = new Instructions();
		instruction.setBounds(0, 25, 1250, 650);
		instruction.add(boutonQuitter);
		menuDeDepart = new MenuDeDepart();
		menuDeDepart.setBounds(0, 25, 1250, 650);
		contentPane.add(menuDeDepart);			

		choixDAction = new ChoixDAction();

		choixDAction.addChoixDActionListener(new ActionChoisieListener() {
			public void uneActionChoisie(TypeDAction action) {
				if (action == TypeDAction.JOUER){
					menuDeDepart.ajouterDesObjets();
					contentPane.remove(menuDeDepart);
					gdnJ.setDessinerLesNiveaux(true);
					contentPane.add(gdnJ);
					contentPane.add(copJouer);
					repaint();
				}
				if (action == TypeDAction.CREER){
					gdnC.setDessinerLesNiveaux(false);
					menuDeDepart.ajouterDesObjets();
					contentPane.remove(menuDeDepart);
					contentPane.add(edn);
					contentPane.add(copEditeur);
					repaint();
				}
				if (action == TypeDAction.INSTRUCTION){
					//menuDeDepart.ajouterDesObjets();
					contentPane.remove(menuDeDepart);				
					contentPane.add(instruction);
					contentPane.add(copInstructions);
					repaint();
				}
				if (action == TypeDAction.QUITTER){
					Object[] options = {"Oui","Non"};
					int n = JOptionPane.showOptionDialog(null,"Quitter l'application ? ","Quitter",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
							null,
							options,
							options[0]);	
					if (n == JOptionPane.YES_OPTION) {									
						System.exit(0);
					}
				}
			}
		});
		choixDAction.setBounds(((getWidth()/2)-(196/2)),((getHeight()/2)-(288/2)), 196, 288);
		menuDeDepart.add(choixDAction);
		SwingUtilities.updateComponentTreeUI(choixDAction);		

		copJouer= new ChoixDOptions();
		copJouer.getBoutonJouer().setVisible(false);
		copJouer.getLabelPlay().setVisible(false);
		copJouer.getLabelUndo().setVisible(false);
		copJouer.getBoutonUndo().setVisible(false);		
		copJouer.addChoixDOptionListener(new OptionsListener() {
			public void uneOptionChoisie(TypeDOption opt) {							
				if(opt == TypeDOption.RETOUR){
					// RETOUR AU MENU
					contentPane.remove(gdnJ);
					contentPane.remove(copJouer);
					contentPane.add(menuDeDepart);
					menuDeDepart.add(choixDAction);					 
					repaint(); 
				}
				if(opt == TypeDOption.QUITTER){
					Object[] options = {"Oui","Non"};
					int n = JOptionPane.showOptionDialog(null,"Quitter l'application ? ","Quitter",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
							null,
							options,
							options[0]);	
					if (n == JOptionPane.YES_OPTION) {									
						System.exit(0);
					}
				}
				if(opt == TypeDOption.AIDE){
					JOptionPane.showMessageDialog(null,"Bienvenue !!\n Choisis la puissance de tir et clique pour tirer.( Celle-ci d\u00E9pend de la position de la souris par rapport au canon)" +
							"\n Ton objectif est d'atteindre la cible ( en jaune et noir ). \n"
							+ "Astuces :"
							+ "\nModifie la charge du projectile."
							+"\nUtilise les obstacles \u00E0 ton avantage."
							+ "\nBonne CHANCE!!");					
				}
			}
		});
		copJouer.setBounds(1100, 26, 100, 600);		


		copInstructions= new ChoixDOptions();
		copInstructions.getBoutonJouer().setVisible(false);
		copInstructions.getLabelPlay().setVisible(false);
		copInstructions.getLabelUndo().setVisible(false);
		copInstructions.getBoutonUndo().setVisible(false);	
		copInstructions.getBoutonAide().setVisible(false);	
		copInstructions.addChoixDOptionListener(new OptionsListener() {
			public void uneOptionChoisie(TypeDOption opt) {							
				if(opt == TypeDOption.RETOUR){
					// RETOUR AU MENU
					contentPane.remove(edn);
					contentPane.remove(gdnJ);
					contentPane.remove(gdnC);					
					contentPane.remove(copEditeur);
					contentPane.remove(copJouer);				
					contentPane.remove(instruction);
					contentPane.remove(copInstructions);
					contentPane.add(menuDeDepart);
					menuDeDepart.add(choixDAction);


					repaint();
				}
				if(opt == TypeDOption.QUITTER){
					Object[] options = {"Oui","Non"};
					int n = JOptionPane.showOptionDialog(null,"Quitter l'application ? ","Quitter",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
							null,
							options,
							options[0]);	
					if (n == JOptionPane.YES_OPTION) {									
						System.exit(0);
					}				
				}				
			}
		});
		copInstructions.setBounds(0, 0, 100, 600);		



		copEditeur= new ChoixDOptions();
		copEditeur.getBoutonJouer().setEnabled(true);
		copEditeur.getBoutonUndo().setEnabled(false);
		copEditeur.addChoixDOptionListener(new OptionsListener() {
			public void uneOptionChoisie(TypeDOption opt) {		
				if(opt == TypeDOption.JOUER){
					if(edn.estPret()){
						gdnC.ajouterUnNiveau(edn.getNiveau());
						copEditeur.getBoutonJouer().setEnabled(false);
						copEditeur.getBoutonUndo().setEnabled(true);
						contentPane.remove(edn);
						contentPane.add(gdnC);
						repaint();
					}else{
						JOptionPane.showMessageDialog(null, "Oups, Ton niveau n'est pas encore prêt.\n \n \nAssure-toi d'avoir mis une cible ainsi qu'un canon.");
					}
				}
				if(opt == TypeDOption.REINITIALISER){
					contentPane.remove(gdnC);	
					copEditeur.getBoutonJouer().setEnabled(true);
					copEditeur.getBoutonUndo().setEnabled(false);
					edn.reinitialiserLeNiveau();
					contentPane.add(edn);
					gdnC.retirerUnNiveau();
					repaint();
				}			
				if(opt == TypeDOption.RETOUR){
					// RETOUR AU MENU		
					copEditeur.getBoutonJouer().setEnabled(true);
					copEditeur.getBoutonUndo().setEnabled(false);
					edn.reinitialiserLeNiveau();
					contentPane.remove(edn);
					contentPane.remove(gdnJ);
					contentPane.remove(gdnC);
					contentPane.remove(copEditeur);
					contentPane.remove(copJouer);
					contentPane.add(menuDeDepart);
					menuDeDepart.add(choixDAction);
					repaint();
				}
				if(opt == TypeDOption.AIDE){
					JOptionPane.showMessageDialog(null,"Bienvenue dans l'éditeur de niveaux \nPour cr\u00E9er ton niveau, tu n'as qu'\u00E9 cliquer et glisser les \u00E9l\u00E9ments \nque tu veux dans ton niveau.\n"
							+ "Pour commencer le niveau, appuie sur le bouton Play.\nPour revenir dans l'\u00E9diteur, appuie sur le bouton r\u00E9initialiser.");
				}
				if(opt == TypeDOption.QUITTER){
					Object[] options = {"Oui","Non"};
					int n = JOptionPane.showOptionDialog(null,"Quitter l'application ? ","Quitter",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
							null,
							options,
							options[0]);	
					if (n == JOptionPane.YES_OPTION) {									
						System.exit(0);
					}				
				}
			}
		});
		copEditeur.setBounds(1100, 26, 100, 600);



		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 944, 26);
		contentPane.add(menuBar);

		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);

		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Oui","Non"};
				int n = JOptionPane.showOptionDialog(null,"Quitter l'application ? ","Quitter",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[0]);	
				if (n == JOptionPane.YES_OPTION) {									
					System.exit(0);
				}
			}
		});

		JMenuItem mntmRetourAuMenu = new JMenuItem("Retour au Menu");
		mntmRetourAuMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				contentPane.remove(edn);
				contentPane.remove(gdnJ);
				contentPane.remove(gdnC);
				contentPane.remove(instruction);
				contentPane.remove(copInstructions);
				contentPane.remove(copEditeur);
				contentPane.remove(copJouer);
				contentPane.add(menuDeDepart);
				menuDeDepart.add(choixDAction);
				repaint();
			}
		});
		mnFichier.add(mntmRetourAuMenu);
		mnFichier.add(mntmQuitter);

		JMenu mnAide = new JMenu("Aide");
		menuBar.add(mnAide);

		JMenuItem mntmInstructions = new JMenuItem("Instructions");
		mntmInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.remove(edn);
				contentPane.remove(gdnJ);
				contentPane.remove(gdnC);				
				contentPane.remove(copEditeur);
				contentPane.remove(copJouer);
				contentPane.remove(menuDeDepart);				
				contentPane.add(instruction);
				contentPane.add(copInstructions);
				instruction.getPane().setSelectedIndex(0);
				repaint();
			}
		});
		mnAide.add(mntmInstructions);

		JMenuItem mntmConceptsScientifiques = new JMenuItem("Concepts scientifiques");
		mntmConceptsScientifiques.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.remove(edn);
				contentPane.remove(gdnJ);
				contentPane.remove(gdnC);				
				contentPane.remove(copEditeur);
				contentPane.remove(copJouer);
				contentPane.remove(menuDeDepart);				
				contentPane.add(instruction);
				contentPane.add(copInstructions);
				instruction.getPane().setSelectedIndex(1);
				repaint();
			}
		});
		mnAide.add(mntmConceptsScientifiques);

		JMenuItem mntmPropos = new JMenuItem("\u00C0 propos");
		mntmPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.remove(edn);
				contentPane.remove(gdnJ);
				contentPane.remove(gdnC);				
				contentPane.remove(copEditeur);
				contentPane.remove(copJouer);
				contentPane.remove(menuDeDepart);				
				contentPane.add(instruction);
				contentPane.add(copInstructions);
				instruction.getPane().setSelectedIndex(2);
				repaint();
			}
		});
		mnAide.add(mntmPropos);

		JMenuItem mntmNewMenuItem = new JMenuItem("Sources");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.remove(edn);
				contentPane.remove(gdnJ);
				contentPane.remove(gdnC);				
				contentPane.remove(copEditeur);
				contentPane.remove(copJouer);
				contentPane.remove(menuDeDepart);				
				contentPane.add(instruction);
				contentPane.add(copInstructions);
				instruction.getPane().setSelectedIndex(3);
				repaint();
			}
		});
		mnAide.add(mntmNewMenuItem);

		JMenuBar menuBar_1 = new JMenuBar();
		mnAide.add(menuBar_1);
		
		boutonRetour = new Bouton();
		boutonRetour.setbutton("retour");
		boutonRetour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {				
				contentPane.remove(instruction);
				contentPane.remove(copInstructions);
				contentPane.add(menuDeDepart);
				menuDeDepart.add(choixDAction);
				repaint();
			}
		});
		boutonRetour.setBounds(1100, 0, 50, 50);
		instruction.add(boutonRetour);
	}
}

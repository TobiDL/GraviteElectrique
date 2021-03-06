package composant.menu.depart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import barre.droite.Bouton;

/**
 * Classe graphique utilisee pour creer un composant comportant des boutons pour le menu de depart
 * @author Jude Fort
 * @version 18/03/14
 */
public class ChoixDAction extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final EventListenerList ecouteurs = new EventListenerList();
	private Bouton jouer, creer, instruction, quitter;	
	/**
	 *  Constructeur du composant menuDeDepart
	 */
	public ChoixDAction(){
		setLayout(null);
		setPreferredSize(new Dimension(195,220));
		setBackground(new Color(0,0,0,0));
		ajouterBoutonsEtLesEcouteurs();		
	}
	/**
	 * Methode utilisee pour ajouter un ecouteur personnalise aux boutons
	 * @param ecout : type d'action choisie
	 */
	public void addChoixDActionListener(ActionChoisieListener ecout){
		ecouteurs.add(ActionChoisieListener.class, ecout);
	}
	/**
	 * Methode utilisee pour lancer une action apres un qu'il y ait eu une 
	 * @param action : type de l'action executee
	 */
	private void lancerEvenUneActionChoisie(TypeDAction action){
		for(ActionChoisieListener ecout : ecouteurs.getListeners(ActionChoisieListener.class)){
			ecout.uneActionChoisie(action);
		}
	}
	/**
	 * Methode privee utilisee pour ajouter les boutons au composant
	 */
	private void ajouterBoutonsEtLesEcouteurs(){

		jouer = new Bouton();
		jouer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				lancerEvenUneActionChoisie(TypeDAction.JOUER);
			}
		});
		jouer.setbutton("jouer");
		jouer.setBounds(0,0,175,60);
		add(jouer);

		creer = new Bouton();
		creer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				lancerEvenUneActionChoisie(TypeDAction.CREER);
			}
		});
		creer.setbutton("creer");
		creer.setBounds(0,65,175,60);
		add(creer);

		instruction = new Bouton();
		instruction.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				lancerEvenUneActionChoisie(TypeDAction.INSTRUCTION);
			}
		});
		instruction.setbutton("instruction");		
		instruction.setBounds(0,130,175,60);
		add(instruction);

		quitter = new Bouton();
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				lancerEvenUneActionChoisie(TypeDAction.QUITTER);
			}
		});
		quitter.setbutton("quitterPrinc");
		quitter.setBounds(0,200,175,60);
		add(quitter);
	}
	/**
	 * Methode retournant le bouton jouer	 
	 */
	public Bouton getBoutonJouer() {
		return jouer;
	}
	/**
	 * Methode qui permet de modifier le bouton jouer
	 * @param Bouton jouer
	 */
	public void setBoutonJouer(Bouton jouer) {
		this.jouer = jouer;
	}
	/**
	 * Methode retournant le bouton creer	 
	 */
	public Bouton getBoutonJCreer() {
		return creer;
	}
	/**
	 * Methode qui permet de modifier le bouton creer
	 * @param Bouton creer 
	 */
	public void setBoutonCreer(Bouton creer) {
		this.creer = creer;
	}
	/**
	 * Methode retournant le bouton Instruction	 
	 */
	public Bouton getBoutonInstructions() {
		return instruction;
	}
	/**
	 * Methode qui permet de modifier le bouton quitter
	 * @param Bouton instruction 
	 */
	public void setBoutonInstruction(Bouton instruction) {
		this.instruction = instruction;
	}
	/**
	 * Methode retournant le bouton quitter	 
	 */
	public Bouton getBoutonQuitter() {
		return quitter;
	}
	/**
	 * Methode qui permet de modifier le bouton quitter
	 * @param Bouton quitter 
	 */
	public void setBoutonQuitter(Bouton quitter) {
		this.quitter = quitter;
	}

}



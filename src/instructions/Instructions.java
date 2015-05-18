package instructions;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.Color;

import java.awt.Dimension;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ScrollPaneConstants;
import java.awt.Cursor;
/**
 * Classe graphique utilis�e pour afficher un menu d'instruction a l'utilisateur
 * @author Jude
 * @version 04/03/14
 */
public class Instructions extends JPanel  {

	private static final long serialVersionUID = 1L;
	private JTabbedPane Pane;
	private JPanel Utilisation;
	private JPanel Concept;
	private JPanel Sources;
	private JPanel Apropos;
	private JLabel lblNewLabel;
	private JTextArea txtrGravitlectriqueEst_1;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTextArea txtrLesConceptsScientifiques;
	private JPanel panel_1;
	private JScrollPane scrollPane_1;
	private JTextArea txtrGravitlectriqueVersion;
	private Images images;
	private JTextArea txtrImagesHttpheadshotsmarathonorghomewpcontentuploadsmegamanbitjpg;
	/**
	 * Constructeur des instruction qui cr�e tous les onglets et les textes qui y sont ajout�s
	 */
	public Instructions(){
		setLayout(null);		
		setBackground(Color.lightGray);

		Pane = new JTabbedPane(JTabbedPane.TOP);
		Pane.setToolTipText("Utilisation");
		Pane.setBounds(0, 50, 1250, 1250);
		add(Pane);
		
		Utilisation = new JPanel();
		Utilisation.setPreferredSize(new Dimension(1250,1250));
		Utilisation.setBackground(SystemColor.controlDkShadow);
		Pane.addTab("Utilisation", null, Utilisation, null);
		Utilisation.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 1212, 512);
		scrollPane.setPreferredSize(new Dimension(1000,900));
		scrollPane.getVerticalScrollBar().setValue(0);
		Utilisation.add(scrollPane);
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setPreferredSize(new Dimension(1150,2159));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		images = new Images();
		images.setBounds(0, 0, 1214, 2144);
		panel.add(images);
		//Utilisation.add(utilisation);
		txtrGravitlectriqueEst_1 = new JTextArea();

		txtrGravitlectriqueEst_1.setBounds(0, 0, 1220, 2150);
		panel.add(txtrGravitlectriqueEst_1);
		txtrGravitlectriqueEst_1.setEditable(false);
		txtrGravitlectriqueEst_1.setForeground(Color.black);
		txtrGravitlectriqueEst_1.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
		txtrGravitlectriqueEst_1.setBackground(Color.white);
		txtrGravitlectriqueEst_1.setText(" Gravit\u00E9 \u00E9lectrique est un jeu interactif qui permet \u00E0 l\u2019utilisateur d\u2019en apprendre plus sur la\r\n physique m\u00E9canique et \u00E9lectrique.  Les instructions qui concernent l\u2019utilisation de notre\r\n application est s\u00E9par\u00E9e en deux parties la premi\u00E8re concerne la fa\u00E7on dont l\u2019utilisateur joue \r\n et la seconde comment  l\u2019utilisateur doit utiliser l\u2019\u00E9diteur de niveaux\r\n\r\n                              : Pour jouer l\u2019utilisateur dispose d\u2019un canon non d\u00E9pla\u00E7able mais dont le \r\n canon est amovible donc l\u2019utilisateur peut choisir l\u2019angle avec lequel il voudra tirer vers la\r\n cible voici une repr\u00E9sentation photo du canon : \r\n\r\n\r\n\r\n\r\nPour choisir l\u2019angle de tir l\u2019utilisateur n\u2019a qu\u2019\u00E0 d\u00E9placer sa souris dans la fen\u00EAtre de l\u2019application l\u2019angle du canon sera d\u00E9terminer avec l\u2019angle de la\r\nsouris. Pour d\u00E9cider de la vitesse initiale que l\u2019utilisateur donnera \u00E0 son projectile le joueur n\u2019a qu\u2019\u00E0 : approcher le curseur du canon pour r\u00E9duire la \r\nvitesse ou \u00E9loigner le curseur du canon pour l\u2019augmenter. \r\n\r\nLe canon tire des projectiles qui poss\u00E8dent une charge qui est changeable par l\u2019utilisateur a l\u2019aide du tourniquet plac\u00E9 \u00E0 gauche du niveau comme\r\n le montre cette capture d\u2019\u00E9cran :\r\n\r\n\r\n\r\n\r\nLe but de chaque niveaux est d\u2019atteindre la avec le projectile en utilisant le canon pour le propulser. La cible est de forme rectangulaire et est\r\n repr\u00E9sent\u00E9e sur la capture d\u2019\u00E9cran suivante :\r\n\r\n\r\n\r\n\r\nAfin d\u2019ajouter une difficult\u00E9 aux niveaux nous avons ajout\u00E9s des obstacles de nature physiques et des obstacles de nature \u00E9lectrique.\r\n Les obstacles physiques sont pr\u00E9sents sous deux formes diff\u00E9rentes. Soit de forme circulaire ou rectangulaire comme \r\nsur la capture d\u2019\u00E9cran :\r\n\r\n\r\n\r\n\r\nTous les obstacles physiques ont le m\u00EAme effet sur le projectile peu importe la charge que porte le projectile.\r\n\r\nLes obstacles \u00E9lectriques ne sont  pr\u00E9sents que sur une seule forme mais l\u2019intensit\u00E9 varie. Plus la charge de l\u2019obstacle est \r\ngrande plus elle influencera le d\u00E9placement du projectile lors de leurs interactions. Si la charge de l\u2019obstacle \u00E9lectrique et \r\ncelle du projectile ont le m\u00EAme signe (positive ou n\u00E9gative) elle se repousserons mais si elle sont de signes contraires \r\nelles s\u2019attirerons. Un obstacle \u00E9lectrique ayant une charge n\u00E9gative (charge < 0) est repr\u00E9sent\u00E9 par:\r\n\r\n\r\n\r\n\r\nEt un obstacle \u00E9lectrique ayant une charge positive (charge > 0 ) est repr\u00E9sent\u00E9 par\r\n\r\n\r\n\r\n\r\n\r\nApr\u00E8s 3 essais d\u2019un niveau sans r\u00E9ussite le joueur a le droit \u00E0 un indice, apr\u00E8s 7 essais il passe automatiquement \r\nau niveau suivant.\r\n\r\n\r\nPendant le jeu l\u2019utilisateur peut en tout temps changer la vitesse de l\u2019animation donc il peut voir le d\u00E9placement \r\ndu projectile au ralenti.\r\n\r\nL\u2019utilisateur a aussi acc\u00E8s \u00E0 un mode scientifique ou il a le choix de soit afficher le vecteur vitesse du projectile ou\r\n le vecteur acc\u00E9l\u00E9ration du projectile pendant qu\u2019il se d\u00E9place. Ce mode est d\u00E9s-activable ou activable a la guise de l\u2019utilisateur.\r\n\r\nEnfin, le joueur peut en tout temps retourner au menu principal ou quitter l\u2019application \u00E0 l\u2019aide des boutons retour\r\n ou quitter disponibles \u00E0 droite compl\u00E8tement de la fen\u00EAtre.\r\n\r\nMode Cr\u00E9er : Ce mode permet \u00E0 l\u2019utilisateur de cr\u00E9er son propre niveau personnalis\u00E9. \r\nPour cr\u00E9er son niveau, l\u2019utilisateur n\u2019a qu\u2019a s\u00E9lectionn\u00E9 les \u00E9l\u00E9ments qu\u2019il d\u00E9sire avoir dans son niveau dans\r\n la barre de droite en effectuant un clic prolong\u00E9 du bouton droit de la souris et de gliss\u00E9 cette \u00E9l\u00E9ment a \r\nl\u2019endroit dans la partie noire de la fen\u00EAtre. Une fois la cr\u00E9ation termin\u00E9e les \u00E9l\u00E9ments garderons la m\u00EAme \r\nposition dans le niveau.\r\n\r\nPour qu\u2019un niveau soit complet, il doit au moins contenir le canon avec la cible. Ces \u00E9l\u00E9ments ne peuvent\r\n qu\u2019\u00EAtre ajout\u00E9s qu\u2019une seule fois dans le niveau cr\u00E9er. Le nombre d\u2019obstacles qu\u2019ils soient \u00E9lectriques ou \r\nphysiques ne d\u00E9pend que de l\u2019utilisateur.\r\n\r\nPour modifier la grandeur de la cible il ne suffit que d\u2019y faire un clic droit, un fen\u00EAtre apparait et demande \r\n\u00E0 l\u2019utilisateur sa nouvelle grandeur puis il appuis sur le bouton \u00AB ok \u00BB alors la cible changera de grandeur. \r\nIl n\u2019y a pas de modification de grandeur pour le canon ni de modification de position en Y l\u2019utilisateur\r\n ne peut que changer sa position en X.\r\n\r\nL\u2019utilisateur peut changer l\u2019intensit\u00E9 de la charge d\u2019un obstacle \u00E9lectrique en effectuant un clic droit sur\r\n l\u2019objet lorsqu\u2019il est ajout\u00E9 dans le niveau en construction. La charge peut \u00EAtre positive ou n\u00E9gative.\r\n La grosseur de l\u2019obstacle varie en fonction de son intensit\u00E9.\r\n\r\nPour modifier la grandeur d\u2019un obstacle \u00E9lectrique carr\u00E9 l\u2019utilisateur n\u2019a qu\u2019\u00E0 y faire un clic droit et rentrer \r\nles param\u00E8tres qu\u2019il veut que son obstacle poss\u00E8de. Si l\u2019obstacle a une forme circulaire l\u2019utilisateur entre le \r\nrayon voulu. S\u2019il s\u2019agit d\u2019un obstacle rectangulaire la largeur et la hauteur sont modifiables.\r\n\r\nL\u2019\u00E9diteur de niveau contient une \u00AB poubelle \u00BB il ne suffit que d\u2019y glisser un \u00E9l\u00E9ment non d\u00E9sir\u00E9 pour l\u2019enlever\r\n du niveau en construction. Il y a aussi un bouton \u00AB reset \u00BB pour tout recommenc\u00E9 a z\u00E9ro.\r\nLorsque l\u2019utilisateur est pr\u00EAt a essayer son niveau il n\u2019a qu\u2019\u00E0 appuyer sur le bouton \u00AB play \u00BB qui est en haut \u00E0 \r\ndroite de l\u2019application et il pourra jouer avec son propre niveau. Toutes les fonctions disponibles pour les\r\n niveaux le seront aussi pour les niveaux personnalis\u00E9s. Il peut en tout temps revenir \u00E0 l\u2019\u00E9diteur \u00E0 l\u2019aide du \r\nbouton \u00AB retour \u00E0 l\u2019\u00E9diteur \u00BB. \r\n\r\nApr\u00E8s avoir essayer son niveau l\u2019utilisateur peut revenir \u00E0 l\u2019\u00E9diteur cr\u00E9er un autre niveau ou le r\u00E9essayer.\r\nIl y a un bouton aide disponible pour aider l\u2019utilisateur en cas de besoin. Le bouton retour au menu est \r\ndisponible en tout temps.");
		txtrGravitlectriqueEst_1.setHighlighter(null);
		Concept = new JPanel();
		Pane.addTab("Concept", null, Concept, null);
		Concept.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 1212, 512);
		Concept.add(scrollPane_1);
		
		panel_1 = new JPanel();
		scrollPane_1.setViewportView(panel_1);
		
		txtrLesConceptsScientifiques = new JTextArea();
		txtrLesConceptsScientifiques.setEditable(false);
		txtrLesConceptsScientifiques.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel_1.add(txtrLesConceptsScientifiques);
		txtrLesConceptsScientifiques.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
		txtrLesConceptsScientifiques.setText("Les concepts scientifiques derri\u00E8re l\u2019application tournent principalement derri\u00E8re la formule du mouvement uniform\u00E9ment acc\u00E9l\u00E9r\u00E9 et l\u2019\u00E9quation de la \r\nsommation des forces. La premi\u00E8re \u00E9quation physique est celle des MUA qui nous permet de savoir la trajectoire du projectile en lui donnant une \r\nvitesse en X et en Y avec cette \u00E9quation on peut calculer la position du projectile tout le temps de son vol. L\u2019\u00E9quation est :\r\n\r\n\tXf = Xi + Vi(t) + 1/2 a (t)^2\r\n \r\nO\u00F9 Xf est la position finale, Xi la position initiale, Vi la vitesse initiale, t le temps et a l\u2019acc\u00E9l\u00E9ration. L\u2019inconv\u00E9nient avec cette \u00E9quation ne fonctionne\r\n que lorsqu\u2019il n\u2019y a pas de collision et que l\u2019acc\u00E9l\u00E9ration est constante. Afin de combl\u00E9 le probl\u00E8me de l\u2019acc\u00E9l\u00E9ration changeante nous avons ensuite \r\ncalcul\u00E9 la position en utilisant l\u2019\u00E9quation des forces appliqu\u00E9es sur le projectile cette formule fonctionne en additionnant toutes les forces appliqu\u00E9es\r\n sur le projectile la formule est : \r\n\r\n\t\u2211 F = ma\r\n \r\nO\u00F9 F repr\u00E9sente l\u2019ensemble des forces appliqu\u00E9es sur l\u2019objet  et ma repr\u00E9sente la masse acc\u00E9l\u00E9r\u00E9e. Cette \u00E9quation a \u00E9t\u00E9 utilis\u00E9e pour calculer la \r\nposition du projectile apr\u00E8s qu\u2019il ait \u00E9t\u00E9 affect\u00E9 par une force \u00E9lectrique g\u00E9n\u00E9r\u00E9e par une charge ponctuelle pour calculer la force \u00E9lectrique d\u2019une \r\ncharge ponctuelle est :\r\n\r\n\tFe = q|E|\r\n\r\nO\u00F9 q est la charge du projectile et E le champ \u00E9lectrique g\u00E9n\u00E9r\u00E9 par la charge ponctuelle. Il existe aussi une formule pour calculer le champ \r\n\u00E9lectrique que la charge g\u00E9n\u00E8re par l\u2019\u00E9quation : \r\n\r\n\tE= k(Q/R^2)\r\n\r\nO\u00F9 k est la constante de coulombs qui est 9x10-9C. Q la charge du champ \u00E9lectrique et R le rayon du champ.\r\nPour calculer la direction que la particule prendra apr\u00E8s une collision avec un obstacle physique il nous a fallu 2 \u00E9quations distinctes la premi\u00E8re\r\n \u00E9tait pour calculer l\u2019angle de la trajectoire du projectile et la deuxi\u00E8me pour calculer la vitesse quelle aura apr\u00E8s la collision.\r\nLa premi\u00E8re formule utilis\u00E9e est : \r\n\r\n\tR= v +2(E \u25CFN)N\r\n\r\nR, v, N et E sont tous des vecteurs. R repr\u00E9sente le vecteur de l\u2019orientation qui a \u00E9t\u00E9 r\u00E9fl\u00E9chis, v le vecteur de la vitesse du projectile avant qu\u2019il\r\n entre en collision avec l\u2019obstacle E est l\u2019inverse de ce vecteur et N le vecteur normale de la surface de l\u2019obstacle. \r\nUn vecteur est un \u00E9l\u00E9ment dans l\u2019espace qui poss\u00E8de une orientation et une norme (ex : Le d\u00E9placement du projectile peut \u00EAtre repr\u00E9sent\u00E9 comme \r\nun vecteur puisqu\u2019il a une orientation et un module)\r\nEnfin, pour trouver la vitesse \u00E0 laquelle le projectile repartira nous avons utilis\u00E9 les \u00E9quations des collisions \u00E9lastiques qui dit que la quantit\u00E9 de \r\nmouvement initiale est \u00E9quivalente a la quantit\u00E9 de mouvement finale entre les 2 objets : \r\n\r\n \t\u2211 px f = \u2211 p i\r\n\r\nPour trouver la quantit\u00E9 de mouvement \u00E9quivaut \u00E0 :\r\n\r\n\t(m1)(vi1) + (m2)(vi2) = (m1)(vf1) + (m2)(vf2)\r\n\r\nO\u00F9 m est la masse et v est la vitesse. \r\n\r\n\r\n\r\n");
		txtrLesConceptsScientifiques.setHighlighter(null);
		lblNewLabel = new JLabel("Concept");
		lblNewLabel.setBounds(144, 152, 46, 14);
		
		Apropos = new JPanel();
		Pane.addTab(" \u00C0 propos", null, Apropos, null);
		Apropos.setLayout(null);
		
		txtrGravitlectriqueVersion = new JTextArea();
		txtrGravitlectriqueVersion.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 21));
		txtrGravitlectriqueVersion.setText("Gravit\u00E9 \u00C9lectrique version : 1.0\r\nJude Fort et Bruce Edouard Brazier\r\nColl\u00E8ge De Maisonneuve\r\n420-SCD-MA - Int\u00E9gration des apprentissages en Sciences\r\n, informatiques et math\u00E9matiques");
		txtrGravitlectriqueVersion.setBounds(6, 0, 1200, 512);
		Apropos.add(txtrGravitlectriqueVersion);
		
		Sources = new JPanel();
		Pane.addTab("Sources", null, Sources, null);
		Sources.setLayout(null);
		
		txtrImagesHttpheadshotsmarathonorghomewpcontentuploadsmegamanbitjpg = new JTextArea();
		txtrImagesHttpheadshotsmarathonorghomewpcontentuploadsmegamanbitjpg.setText("Images : \r\nhttp://headshotsmarathon.org/home/wp-content/uploads/2012/02/Megaman8bit.jpg\r\n http://www.psdgraphics.com/graphics/colorful-3d-crystal-balls/ \r\n http://www.glassybuttons.com/glassy.php\r\n\r\nPhysique : \r\nhttp://profs.cmaisonneuve.qc.ca/svezina/SIM/nya/nya_SIM.html\r\nhttp://profs.cmaisonneuve.qc.ca/svezina/SIM/nyc/nyc_SIM.html");
		txtrImagesHttpheadshotsmarathonorghomewpcontentuploadsmegamanbitjpg.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 21));
		txtrImagesHttpheadshotsmarathonorghomewpcontentuploadsmegamanbitjpg.setBounds(0, 0, 1200, 512);
		Sources.add(txtrImagesHttpheadshotsmarathonorghomewpcontentuploadsmegamanbitjpg);



	
// source 
	}
	/**
	 * Retourne le panneau qui contiens les elements de l'aide
	 */
	public JTabbedPane getPane() {
		return Pane;
	}
	/**
	 * Modifie les panneaus du JTabbedPane
	 */
	public void setPane(JTabbedPane pane) {
		Pane = pane;
	}
}

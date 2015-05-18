package ecouteur.niveau;

import java.util.EventListener;
/**
 * interface qui permet au panel de passer  ou de revenir a un niveau
 * @author Jude Fort
 * @version 04/03/14
 */
public interface EcouteurDeNiveaux extends EventListener {
	public void niveauTerminer();
	public void niveauPrecedent();
}

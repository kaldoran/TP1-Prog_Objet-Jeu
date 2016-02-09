package cardgame.ResultUtils;

import cardGame.API.Resultat;

/**
 * Implémentation de Resultat pour décrire la conséquence d'un déploiment d'un
 * perso et de son arme sur le jeu.
 *
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class PersoDeploieResult implements Resultat {

    private final String description;
    private int joueurId;
    private final boolean coupAFonctionne;
    private final int persoId;
    private final int armeId;

    public PersoDeploieResult(int jId, boolean coupCorrect, int pId, int aId) {
        joueurId = jId;
        coupAFonctionne = coupCorrect;
        persoId = pId;
        armeId = aId;
        description = "Le joueur " + jId + "vient de déployer les cartes " + pId + " + " + aId;
    }

    /**
     * Getter
     * @return l'identifiant du perso déployé. 
     */
    public int getPersoId() {
        return persoId;
    }

    /**
     * Getter
     * @return l'identifiant de l'arme déployé. 
     */
    public int getArmeId() {
        return armeId;
    }

    /**
     * @return True si l'action a fonctionné,false sinon.
     */
    @Override
    public boolean coupAMarcher() {
        return coupAFonctionne;
    }

    /**
     * Getter
     *
     * @return Description de ce type de coup.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Getter
     *
     * @return l'identifiant du joueur qui a joué de coup.
     */
    @Override
    public int coupJouerPar() {
        return joueurId;
    }

    /**
     * Setter
     *
     * @param idJoueur l'identifiant du joueur qui a fait le coup.
     */
    @Override
    public void setJoueur(int idJoueur) {
        this.joueurId = idJoueur;
    }
}

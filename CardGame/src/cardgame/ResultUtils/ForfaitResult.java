package cardgame.ResultUtils;

import cardGame.API.Resultat;

/**
 * Implémentation de Resultat pour décrire la conséquence d'un forfait.
 *
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class ForfaitResult implements Resultat {

    private final String description;
    private int joueurId;
    private final boolean coupAFonctionne;
    private final int joueurPerdu;
    
    public ForfaitResult(int jId, boolean coupCorrect, int idJoueurPerdu) {
        joueurId = jId;
        coupAFonctionne = coupCorrect;
        joueurPerdu = idJoueurPerdu;
        description = "Le joueur" + idJoueurPerdu + "vient de perdre la partie";
    }
    
    
    /**
     * @return True si l'action a fonctionné,false sinon.
     */
    @Override
    public boolean coupAMarcher() {
        return  coupAFonctionne;
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
     * Getter
     * @return l'identifiant du joueur  qui a gagné.
     */
    public int getJoueurQuiAPerdu() {
        return joueurPerdu;
    }


    /**
     * Setter
     * @param idJoueur l'identifiant du joueur qui a fait le coup.
     */
    @Override
    public void setJoueur(int idJoueur) {
        this.joueurId = idJoueur;
    }
}

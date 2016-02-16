package cardgame.ResultUtils;

/**
 * Implémentation de Resultat pour décrire la conséquence d'un sortilège de
 * soins.
 *
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class SoinsResult implements Resultat {

    private final String description;
    private int joueurId;
    private final boolean coupAFonctionne;
    private final int healerId;
    private final int persoSoigneeId;

    public SoinsResult(int jId, boolean coupCorrect, int hId, int cId) {
        description = "Le personnage " + hId + " vient de soigner " + cId + ".";
        joueurId = jId;
        coupAFonctionne = coupCorrect;
        healerId = hId;
        persoSoigneeId = cId;
    }

    public SoinsResult(boolean coupCorrect, int hId, int cId) {
        description = "Le personnage " + hId + " vient de soigner " + cId + ".";
        coupAFonctionne = coupCorrect;
        healerId = hId;
        persoSoigneeId = cId;
    }

    /**
     * Getter
     * @return L'identifiant du perso qui a fait le sort de soins. 
     */
    public int getHealerId() {
        return healerId;
    }

    /**
     * Getter
     * @return L'identifiant du perso soignée. 
     */
    public int getCarteSoigneeId() {
        return persoSoigneeId;
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

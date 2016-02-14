package cardgame.ResultUtils;

/**
 * Implémentation de Resultat pour décrire la conséquence d'un coup refusé
 * puisqu'il était impossible.
 *
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class RefuseResult implements Resultat {

    private final String description;
    private int joueurId;

    public RefuseResult(int idJoueur, String coupRefuse) {
        description = coupRefuse;
        joueurId = idJoueur;
    }

    public RefuseResult(String coupRefuse) {
        description = coupRefuse;
    }

    /**
     * @return True si l'action a fonctionné,false sinon.
     */
    @Override
    public boolean coupAMarcher() {
        return false;
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

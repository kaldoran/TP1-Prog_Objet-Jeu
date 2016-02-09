package cardgame.ResultUtils;

import cardgame.Cartes.Carte;
import cardGame.API.Resultat;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonObject;

/**
 * Implémentation de Resultat pour décrire la conséquence d'une pioche.
 *
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class PiocheResult implements Resultat {

    private final String description;
    private int joueurId;
    private final boolean coupAFonctionne;
    private final List<Integer> cartesId;
    private final List<JsonObject> cartesJSON;

    public PiocheResult(int jId, boolean coupCorrect, List<Carte> cartes) {
        description = "Résultat de pioches";
        joueurId = jId;
        coupAFonctionne = coupCorrect;
        cartesId = new ArrayList<>();
        cartesJSON = new ArrayList<>();
        for (Carte cartePioche : cartes) {
            cartesId.add(cartePioche.getCardID());
            cartesJSON.add(cartePioche.toJSON());
        }
    }

    /**
     * Getter
     *
     * @return les identifiant des cartes pigés. (Utile pour une vue qui
     * l'utilise en tandem avec le Json de la partie.)
     */
    public List<Integer> getCartesID() {
        return cartesId;
    }

    /**
     * Getter
     *
     * @return La représentation JSON des cartes pigés. (Utile pour une vue qui
     * veut faire des animations.)
     */
    public List<JsonObject> getCartesJSON() {
        return cartesJSON;
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

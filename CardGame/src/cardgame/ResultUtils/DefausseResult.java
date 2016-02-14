package cardgame.ResultUtils;

import cardgame.JeuxCartes.Carte;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonObject;

/**
 * Implémentation de Resultat pour décrire les conséquences d'une action de
 * défaussage.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class DefausseResult implements Resultat {

    private final List<Integer> cartesId;
    private final List<JsonObject> cartesJSON;
    private int joueurId;
    private final String description;
    private final boolean coupAFonctionne;

    public DefausseResult(int idJoueur, boolean coupCorrect, List<Carte> cartes) {
        cartesId = new ArrayList<>();
        cartesJSON = new ArrayList<>();
        String cartesStr = "";
        for (Carte cartePioche : cartes) {
            cartesId.add(cartePioche.getCardID());
            cartesJSON.add(cartePioche.toJSON());
            cartesStr = cartesStr + cartePioche.toJSON().toString() + "\n";
        }
        joueurId = idJoueur;
        description = "Les cartes suivantes ont été défaussés :" + cartesStr;
        coupAFonctionne = coupCorrect;
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
     * Getter
     *
     * @return les identifiant des cartes défaussés. (Utile pour une vue qui
     * l'utilise en tandem avec le Json de la partie.)
     */
    public List<Integer> getCartesID() {
        return cartesId;
    }

    /**
     * Getter
     *
     * @return La représentation JSON des cartes défaussés. (Utile pour une vue
     * qui veut faire des animations.)
     */
    public List<JsonObject> getCartesJSON() {
        return cartesJSON;
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

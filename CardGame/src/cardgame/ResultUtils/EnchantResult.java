package cardgame.ResultUtils;

import cardgame.JeuxCartes.Carte;
import cardgame.JeuxCartes.Enchant;
import java.util.List;
import javax.json.JsonObject;

/**
 * Implémentation de Resultat pour décrire les conséquences d'une action
 * d'enchantement.
 *
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class EnchantResult implements Resultat {

    private final String description;
    private int joueurId;
    private final boolean coupAFonctionne;
    private final Carte carteEnchant;
    private final List<Enchant> enchant;

    public EnchantResult(boolean coupCorrect, Carte carteEnch,List<Enchant> enchants) {
        coupAFonctionne = coupCorrect;
        carteEnchant = carteEnch;
        enchant = enchants;
        String enchStr = "";
        for (Enchant ench : enchants) 
            enchStr = enchStr + ench.toJSON().toString();
        description = "La carte " + carteEnch.toJSON().toString() + "a recu les enchantements suivants :" + enchStr;
    }

    public EnchantResult(int jId, boolean coupCorrect, Carte carteEnch,List<Enchant> enchants) {
        joueurId = jId;
        coupAFonctionne = coupCorrect;
        carteEnchant = carteEnch;
        enchant = enchants;
        description = "Enchantement d'une carte";
    }



    /**
     * Getter
     *
     * @return identifiant de la carte enchanté.
     */
    public JsonObject getCarteEnchante() {
        return carteEnchant.toJSON();
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

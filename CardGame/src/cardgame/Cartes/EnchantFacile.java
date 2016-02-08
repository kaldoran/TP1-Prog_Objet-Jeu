package cardgame.Cartes;

import cardgame.Regles.TypePerso;
import java.util.Arrays;

/**
 * Implémentation de la classe abstraite Enchant.
 *
 * EnchantFacile permet de changer les utilisateurs possibles de l'arme.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reymaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class EnchantFacile extends Enchant {

    public EnchantFacile() {
        super("Cette carte rend cette arme utilisable par tout le monde.");
    }

    /**
     * Applique l'enchantement sur l'arme passé en paramètre.
     *
     * @param arme arme qui pourra être équipée par tout le monde
     */
    @Override
    public void placerEnchant(Arme arme) {
        arme.listUtilisateurs.clear();
        arme.listUtilisateurs = Arrays.asList(TypePerso.values());
    }
}

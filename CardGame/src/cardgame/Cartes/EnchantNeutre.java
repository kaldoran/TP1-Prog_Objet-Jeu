package cardgame.Cartes;

import cardgame.Regles.TypeArme;

/**
 * Implémentation de la classe abstraite Enchant.
 *
 * EnchantNeutre permet de changer le type de l'arme.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class EnchantNeutre extends Enchant {

    public EnchantNeutre() {
        super("Cette carte rend cette arme neutre.");
    }

    /**
     * Modifie l'arme sur lequel le triangle des dégats ne sera plus appliqué.
     *
     * @param arme arme dont le triangle de dégat va etre retiré
     */
    @Override
    public void placerEnchant(Arme arme) {
        arme.type = TypeArme.Neutre;
    }
}

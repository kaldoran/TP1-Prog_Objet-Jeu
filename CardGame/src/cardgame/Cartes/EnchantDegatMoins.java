package cardgame.Cartes;

/**
 * Implémentation de la classe abstraite Enchant.
 *
 * EnchantDegatMoins permet d'abaisser la force d'une arme.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class EnchantDegatMoins extends Enchant {

    public EnchantDegatMoins() {
        super("Cette carte abaisse les dommages de l'arme choisi par 1.");
    }

    /**
     * Applique l'enchantement sur l'arme passé en paramètre.
     *
     * @param arme arme dont les degats vont etre diminué
     */
    @Override
    public void placerEnchant(Arme arme) {
        arme.degat--;
    }
}

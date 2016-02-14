package cardgame.JeuxCartes;

/**
 * Implémentation de la classe abstraite Enchant.
 *
 * EnchantDegatPlus permet d'augmenter la force d'une arme.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class EnchantDegatPlus extends Enchant {

    public EnchantDegatPlus() {
        super("Cette carte augmente les degats de l'arme choisi par un.");
    }

    /**
     * Applique l'enchantement sur l'arme passé en paramètre.
     *
     * @param arme arme dont les degats vont etre augmenté
     */
    @Override
    public void placerEnchant(Arme arme) {
        arme.degat++;
    }
}

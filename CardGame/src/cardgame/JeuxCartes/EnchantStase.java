package cardgame.JeuxCartes;

/**
 * Implémentation de la classe abstraite Enchant.
 *
 * EnchantStase permet de placer l'effet de stase sur une arme.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class EnchantStase extends Enchant {

    public EnchantStase() {
        super("Cette carte applique un effet de Stase sur l'arme choisi.");
    }

    /**
     * Modifie l'arme et la met en stase.
     *
     * @param arme arme qui va être mise sous stase.
     */
    @Override
    protected void placerEnchant(Arme arme) {
        arme.staserArme();
        arme.reset();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.EnchantUtils;

import cardgame.Arme;
import cardgame.Enchant;

/**
 *
 * @author kaldoran
 */
public class EnchantStase extends Enchant {

    public EnchantStase() {
        super("Cette carte applique un effet de Stase sur l'arme choisi.");
    }
    
    /**
     * Modifie l'arme est la met en stase
     * @param arme arme qui va être mise sous stase.
     */
    @Override
    public void placerEnchant(Arme arme) {
        arme.staserArme();
        arme.reset();
    }
}

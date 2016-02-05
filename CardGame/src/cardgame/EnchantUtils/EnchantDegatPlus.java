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
public class EnchantDegatPlus extends Enchant {

    public EnchantDegatPlus() {
        super("Cette carte augmente les degats de l'arme choisi par un.");
    }
    @Override
    public void placerEnchant(Arme arme) {
        arme.setDegat(arme.getDegat() + 1);
    }
}

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
    @Override
    public void placerEnchant(Arme arme) {
        arme.setStase(true);
        arme.reset();
    }
}

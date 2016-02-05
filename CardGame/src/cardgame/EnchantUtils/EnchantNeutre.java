/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.EnchantUtils;

import cardgame.Arme;
import cardgame.Enchant;
import cardgame.TypeArme;


/**
 *
 * @author kaldoran
 */
public class EnchantNeutre extends Enchant {

    public EnchantNeutre() {
        super("Cette carte rend cette arme neutre.");
    }
    @Override
    public void placerEnchant(Arme arme) {
        arme.setType(TypeArme.Neutre);
    }
}

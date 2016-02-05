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
public class EnchantDegatMoins extends Enchant {
    
    public EnchantDegatMoins() {
        super("Cette carte abaisse les dommages de l'arme choisi par 1.");
    }
    
    @Override
    public void placerEnchant(Arme arme) {
            arme.setDegat(arme.getDegat() - 1);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.EnchantUtils;

import cardgame.Arme;
import cardgame.Enchant;
import cardgame.TypePerso;
import java.util.Arrays;

/**
 *
 * @author kaldoran
 */
public class EnchantNeutre extends Enchant {
    @Override
    public void placerEnchant(Arme arme) {
        arme.setStase(true);
        arme.setListUtilisateurs(Arrays.asList(TypePerso.values()));
    }
}

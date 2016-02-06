/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Init;

import cardgame.Enchant;
import cardgame.EnchantUtils.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kaldoran
 */
public class EnchantFactory {
    
    
     public List<Enchant> creerSetEnchants(int nbCopies) {
        List<Enchant> enchantements = new ArrayList<>();
    
        for (int copieAct = 0; copieAct < nbCopies; copieAct++) {
            enchantements.add(new EnchantNeutre());
            enchantements.add(new EnchantStase());
            enchantements.add(new EnchantDegatPlus());
            enchantements.add(new EnchantDegatMoins());
            enchantements.add(new EnchantFacile());

        }
        return enchantements;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Init;

import cardgame.Cartes.EnchantNeutre;
import cardgame.Cartes.EnchantFacile;
import cardgame.Cartes.EnchantStase;
import cardgame.Cartes.EnchantDegatMoins;
import cardgame.Cartes.EnchantDegatPlus;
import cardgame.Cartes.Enchant;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kaldoran
 */
public class EnchantFactory {
    
    /**
     * Permet de crée un ensemble de même enchant
     * @param nbCopies nombre de copie de l'enchant
     * @return Une liste de nbCopie élement contenant tout les énchants.
     */
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

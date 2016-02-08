/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Cartes;

import cardgame.Cartes.Arme;
import cardgame.Cartes.Enchant;

/**
 *
 * @author kaldoran
 */
public class EnchantDegatMoins extends Enchant {
    
    public EnchantDegatMoins() {
        super("Cette carte abaisse les dommages de l'arme choisi par 1.");
    }
    
    /**
     * Modifie les degats de l'arme passé en parametre.
     * @param arme arme dont les degats vont etre diminué
     */
    @Override
    public void placerEnchant(Arme arme) {
            arme.degat--;
    }
}

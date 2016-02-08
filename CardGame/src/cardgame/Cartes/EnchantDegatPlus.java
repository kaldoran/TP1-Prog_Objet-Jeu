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
public class EnchantDegatPlus extends Enchant {

    public EnchantDegatPlus() {
        super("Cette carte augmente les degats de l'arme choisi par un.");
    }
    /**
     * Modifie les degats de l'arme passé en parametre.
     * @param arme arme dont les degats vont etre augmenté
     */
    @Override
    public void placerEnchant(Arme arme) {
        arme.degat++;
    }
}

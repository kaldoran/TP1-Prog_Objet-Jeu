/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Cartes;

import cardgame.Cartes.Arme;
import cardgame.Cartes.Enchant;
import cardgame.Regles.TypePerso;
import java.util.Arrays;

/**
 *
 * @author mathieu
 */
public class EnchantFacile extends Enchant {

    public EnchantFacile() {
        super("Cette carte rend cette arme utilisable par tout le monde.");
    }
    
    /**
     * Modifie les utilisateurs de l'arme
     * @param arme arme qui pourra être équipée par tout le monde
     */
    @Override
    public void placerEnchant(Arme arme) {
        arme.listUtilisateurs.clear();
        arme.listUtilisateurs = Arrays.asList(TypePerso.values());
    }
}

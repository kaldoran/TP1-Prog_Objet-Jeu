/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Controller.CommandeCoup;

import cardgame.JeuxCartes.Carte;
import cardgame.API.Jeux;
import cardgame.ResultUtils.Resultat;
import java.util.List;

/**
 *
 * @author mathieu
 */
public class EnchantCommande implements Commande {

    private final Jeux partie;
    private final int joueur;
    private final List<Carte> enchantements;
    private final Carte enchantee;
    
    public EnchantCommande(Jeux jeu, int idJoueur,List<Carte> enchants,Carte carteEnch){
        partie = jeu;
        joueur = idJoueur;
        enchantements = enchants;
        enchantee  = carteEnch;
                
    }
    
    @Override
    public Boolean coupPossible() {
        return partie.peutAjouterEnchantements(joueur,enchantee, enchantements);
    }

    @Override
    public Boolean coupFinitTour() {
        return true;
    }

    @Override
    public Resultat placerCoup() {
        return partie.ajouterEnchantements(joueur,enchantee,enchantements);
    }
}

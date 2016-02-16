/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Controller.CommandeCoup;

import cardgame.JeuxCartes.Carte;
import cardgame.API.Jeux;
import cardgame.ResultUtils.Resultat;

/**
 *
 * @author mathieu
 */
public class SoinsCommande implements Commande {

    private final int idJoueur;
    private final  Jeux api;
    private final Carte soigneur;
    private final Carte soignee;
    
    public SoinsCommande(Jeux jeu, int idJ,Carte healer,Carte soins) {
        idJoueur = idJ;
        api = jeu;
        soigneur =  healer;
        soignee = soins;
    }
    
    
    
    @Override
    public Boolean coupPossible() {
        return api.peutSoignerPerso(idJoueur, soigneur, soignee);
    }

    @Override
    public Boolean coupFinitTour() {
        return true;
    }

    @Override
    public Resultat placerCoup() {
        return api.soignerPerso(idJoueur,soigneur, soignee);
    }
    
}

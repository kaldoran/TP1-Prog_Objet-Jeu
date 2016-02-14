/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.CommandeCoup;

import cardgame.JeuxCartes.Carte;
import cardgame.API.Jeux;
import cardgame.ResultUtils.Resultat;

/**
 *
 * @author mathieu
 */
public class AttaqueJoueurCommande implements Commande {

      private final Jeux api;
    private final int idJ;
    private final int idA;
    private final Carte attaque;
    
    public AttaqueJoueurCommande(Jeux jeu, int idJoueur,int idAdversaire,Carte attaqueur){
        api = jeu;
        idJ = idJoueur;
        idA = idAdversaire;
        attaque = attaqueur;
    }
    
    
    @Override
    public Boolean coupPossible() {
        return api.attaqueJoueurValide(idJ, idA, attaque);
    }

    @Override
    public Boolean coupFinitTour() {
        return true;
    }

    @Override
    public Resultat placerCoup() {
        return api.attaqueJoueur(idJ, idA, attaque);
    }
    
}

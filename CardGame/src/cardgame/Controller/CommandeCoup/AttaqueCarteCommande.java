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
public class AttaqueCarteCommande implements Commande {

    private final Jeux api;
    private final int idJ;
    private final int idA;
    private final Carte attaque;
    private final Carte defense;
    
    public AttaqueCarteCommande(Jeux jeu, int idJoueur,int idAdversaire,Carte attaqueur,Carte attaquee){
        api = jeu;
        idJ = idJoueur;
        idA = idAdversaire;
        attaque = attaqueur;
        defense = attaquee;
    }
    
    @Override
    public Boolean coupPossible() {
        return api.attaquePersoValide(idJ, idA, attaque, defense);
    }

    @Override
    public Boolean coupFinitTour() {
        return true;
    }

    @Override
    public Resultat placerCoup() {
        return api.attaquePerso(idJ, idA, attaque, defense);
    }
}

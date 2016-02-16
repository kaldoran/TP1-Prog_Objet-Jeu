/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Controller.CommandeCoup;

import cardgame.JeuxCartes.Carte;
import cardgame.API.Jeux;
import cardgame.ResultUtils.Resultat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mathieu
 */
public class DeploieCommande implements Commande {

    private final Jeux api;
    private final int idJoueur;
    private final Carte perso;
    private final Carte arme;
    private final List<Carte> enchants;
    
    public DeploieCommande(Jeux jeu, int idJ,Carte p, Carte a,List<Carte> e) {
        api = jeu;
        idJoueur = idJ;
        perso = p;
        arme = a;
        if (e == null)
            enchants = new ArrayList<>();
        else
            enchants = e;
    }
    
    @Override
    public Boolean coupPossible() {
        return api.peutDeployerPerso(idJoueur, perso, arme, enchants);
    }

    @Override
    public Boolean coupFinitTour() {
        return true;
    }

    @Override
    public Resultat placerCoup() {
        return api.placerPerso(idJoueur, perso, arme, enchants );
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.CommandeCoup;

import cardgame.API.Jeux;
import cardgame.ResultUtils.Resultat;

/**
 *
 * @author mathieu
 */
public class PigerCommande implements Commande {

    private final Jeux partie;
    private final int idJoueur;
    
    public PigerCommande(Jeux jeu,int joueur){
        partie = jeu;
        idJoueur = joueur;
    }
    
    
    @Override
    public Boolean coupPossible() {
        return partie.peutPiocherCartes(idJoueur);
    }

    @Override
    public Boolean coupFinitTour() {
        return false;
    }

    @Override
    public Resultat placerCoup() {
        return partie.piocherCartes(idJoueur);
    }
    
}

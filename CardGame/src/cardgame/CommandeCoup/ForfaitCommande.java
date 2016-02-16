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
public class ForfaitCommande implements Commande {

    private final int joueur;
    private final Jeux partie;
    
    public ForfaitCommande(Jeux jeu,int idJoueur){
        partie = jeu;
        joueur = idJoueur;
    }

    @Override
    public Boolean coupPossible() {
        return !partie.partieFini();
    }

    @Override
    public Boolean coupFinitTour() {
        return true;
    }

    @Override
    public Resultat placerCoup() {
        return partie.declarerForfait(joueur);
    }
}

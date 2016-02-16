/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Controller.CommandeCoup;

import cardgame.API.Jeux;
import cardgame.JeuxCartes.Carte;
import cardgame.ResultUtils.Resultat;
import java.util.List;

/**
 *
 * @author mathieu
 */
public class DefausseCommande implements Commande {

    private final Jeux api;
    private final int idJ;
    private final List<Carte> defausse;
    
    public DefausseCommande(Jeux jeu, int idJoueur,List<Carte> debarras){
        api = jeu;
        idJ = idJoueur;
        defausse = debarras;
    }
    
    @Override
    public Boolean coupPossible() {
        return api.peutDefausserCartes(idJ, defausse);
    }

    @Override
    public Boolean coupFinitTour() {
        return true;
    }

    @Override
    public Resultat placerCoup() {
        return api.defausserCartes(idJ, defausse);
    }
}

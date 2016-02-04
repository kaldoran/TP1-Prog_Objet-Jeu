/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.List;

/**
 *
 * @author kaldoran
 */
public class Jeux {
    
    private Joueur joueurA;
    private Joueur joueurB;
    private int joueurTour;
    private boolean partieEnMarche;
    
    public int aQuiLeTour() {
        return joueurTour;
    }
    
    public void demarrerPartie() {
        /*
        if ( !partieEnMarche ) {
            joueurA.getCarteDeck().initialiserDeck();
            joueurB.getCarteDeck().initialiserDeck();
        }
        */
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result declarerForfait(int idJoueur) {
        throw new UnsupportedOperationException("Not implemented");   
    }
    
    public String getEtatJeu() {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public boolean partieFini() {
        return joueurA.aPerdu() || joueurB.aPerdu();
    }
    
    public int getJoueurGagnant() {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public List<Result> piocherCartes(int idJoueur) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result attaquePerso(int idJoueur, int idAttaqueur, int idReceveur) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result attaqueJoueur(int idJoueur, int idAttaqueur ) {
        throw new UnsupportedOperationException("Not implemented");   
    }
    
    // Not finished   
    
}

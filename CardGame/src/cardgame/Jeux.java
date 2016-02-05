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
    private List<Joueur> joueurList;
    // private Joueur joueurA;
    // private Joueur joueurB;
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
        int totalPerdu = 0;
        for ( int i = 0; i < joueurList.size(); i++ )
            if ( joueurList.get(i).aPerdu() )
                ++totalPerdu;
        
        return totalPerdu == joueurList.size() - 1; // tout les joueurs ont perdu sauf 1
    }
    
    public int getJoueurGagnant() {
        for ( int i = 0; i < joueurList.size(); i++ )
            if ( !joueurList.get(i).aPerdu() )
                return i;
        
        return -1;
    }
    
    public List<Result> piocherCartes(int idJoueur) {
        joueurList.get(idJoueur).piocher();
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result attaquePerso(int idJoueur, int idAttaqueur, int idReceveur) {
        // tester si la carte est morte, si oui modifier le Result recu de attaque Joueur
        // et mettre aTuer à True.
        
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result attaqueJoueur(int idJoueur, int idAttaqueur ) {
        throw new UnsupportedOperationException("Not implemented");   
    }
    
    public List<Result> ajouterEnchantements(int idJoueur, int carteTouche, List<Integer> enchant) {
        throw new UnsupportedOperationException("Not implemented");   
    }
    
    public Result placerPerso(int idJoueur, int personnage, int arme) {
        throw new UnsupportedOperationException("Not implemented");   
    }
    
    public Result defausserCartes(int idJoueur, List<Integer> defausse) {
        throw new UnsupportedOperationException("Not implemented");   
    }
    
    public Result soignerPerso(int idJoueur, int soingeur, int soignee) {
        throw new UnsupportedOperationException("Not implemented");   
    }
    
    public void finPartie() {
        throw new UnsupportedOperationException("Not implemented");
    }    
}

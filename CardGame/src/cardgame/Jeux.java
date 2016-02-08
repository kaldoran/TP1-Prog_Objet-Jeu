/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.ResultUtils.RefusedResult;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kaldoran
 */
public class Jeux {
    private List<Joueur> joueurList = new ArrayList<>();
    private int joueurTour;
    private boolean partieEnMarche;
    
    /**
     * Permet de savoir à qui est le tour ( id du joueur )
     * @return l'id du joueur à qui c'est le tour de jouer
     */
    public int aQuiLeTour() {
        return joueurTour;
    }
    
    /**
     * Permet de démarrer une partie à nbJoueur
     * @param nbJoueur nombre de joueur voulant jouer
     */
    public void demarrerPartie(int nbJoueur) {
        if ( !partieEnMarche )
            for ( int i = 0; i < nbJoueur; i++)
                joueurList.add(new Joueur());
    }
    
    /**
     * Permet au joueur idJoueur de déclarer forfait
     * @param idJoueur id du joueur déclarant forfait
     * @return 
     */
    public Result declarerForfait(int idJoueur) {
        if ( joueurTour == idJoueur )
            return joueurList.get(idJoueur).declarerForfait();
        
        
        throw new UnsupportedOperationException("Not implemented");   
    }
    
    
    public String getEtatJeu() {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    /**
     * Permet de savoir si la partie est finie
     * @return true si la partie est fini, autrement dit si un joueur à gagné
     *         false sinon
     */
    public boolean partieFini() {
        return getJoueurGagnant() == -1; // tout les joueurs ont perdu sauf 1
    }
    
    /**
     * Permet d'avoir l'id du joueur ayant gagné
     * @return l'id du joueur gagnant
     *         -1 si aucun joueur n'a gagné
     */
    public int getJoueurGagnant() {
        for ( int i = 0; i < joueurList.size(); i++ )
            if ( !joueurList.get(i).aPerdu() )
                return i;
        
        return -1;
    }
    
    /**
     * Permet au joueur idJoueur de piocher
     * @param idJoueur id du joueur voulant piocher
     * @return une liste de Result, PiocheResult en cas de succes de la pioche
     *         un RefusedResult sinon
     */
    public List<Result> piocherCartes(int idJoueur) {
        List<Result> res = new ArrayList<>();
        if ( idJoueur != aQuiLeTour() ) {
            res.add(new RefusedResult("Ce n'est pas le tour de ce joueur."));
            return res;
        }
        
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

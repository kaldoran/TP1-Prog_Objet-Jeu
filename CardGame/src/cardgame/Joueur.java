/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.List;
import java.util.Map;

/**
 *
 * @author kaldoran
 */
public class Joueur {
    private Deck carteDeck;
    private Map<Integer, Card> main;
    private Map<Integer, Card> carteEnJeu;
    private Map<Integer, Card> cimetiere;
    
    public Map<Integer, Card> getMain() {
        return main;
    }
    
    public Map<Integer, Card> getCarteEnJeu() {
        return carteEnJeu;
    }
    
    public Map<Integer, Card> getCimetiere() {
        return cimetiere;
    }
    
    public boolean aPerdu() {
        return main.size() > 0 && carteEnJeu.size() > 0 && carteDeck.carteRestantes() > 0;
    }
    
    public Result defausserCartes(List<Integer> defausse) {
        /*
        Result r = null;
        for ( int i = 0; i < defausse.size(); i++)
            cimetiere.put(cimetiere.size(), main.remove(defausse.get(i)));
        return r;
        */
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result piocher() {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result recoitAttaque(int degat) {
        /*
        Result r = null;
        carteDeck.dommageJoueur(degat);
        return r;
        */
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result attaque(int attaqueur, Card attaque) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result attaque(int attaqueur, Joueur attaque) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public List<Result> (List<Map<Integer, Integer>> enchs, Joueur opposant) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result placerPerso(int personnage, int arme) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result declarerForfait() {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result soignerPerso(int soigneur, int soignee) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public boolean detientCarte(int idCarte) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public String toJSon() {
        throw new UnsupportedOperationException("Not implemented");
    }  
}

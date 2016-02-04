/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kaldoran
 */
public class Deck {
    private List<Card> cartespioches;
    
    private void initialiserDeck() {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public List<Card> piocherCarte(int nbCartes) {
        int nbAPiocher = Math.min(nbCartes, this.carteRestantes());
        
        List<Card> l = new ArrayList<>();
        
        while ( nbAPiocher != 0) {
            l.add(cartespioches.remove(0));
            --nbAPiocher;
        }
    
        return l;
    }
    
    public List<Card> dommageJoueur(int nbCartes) {
        return piocherCarte(nbCartes);
    }
    
    public int carteRestantes() {
        return cartespioches.size();
    }
    
    public String toJSon() {
        throw new UnsupportedOperationException("Not implemented");
    }
}

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
    // private Map<Integer, Card> main;
    // private Map<Integer, Card> carteEnJeu;
    // private Map<Integer, Card> cimetiere;
    private List<Card> main;
    private List<Card> carteEnJeu;
    private List<Card> cimetiere;
    
    
    public Deck getCarteDeck() {
        return carteDeck;
    }
    
    public List<Card> getMain() {
        return main;
    }
    
    public List<Card> getCarteEnJeu() {
        return carteEnJeu;
    }
    
    public List<Card> getCimetiere() {
        return cimetiere;
    }
    
    public boolean aPerdu() {
        return main.size() > 0 && carteEnJeu.size() > 0 && carteDeck.carteRestantes() > 0;
    }
    
    public Result defausserCartes(List<Integer> defausse) {
        // Should return list of Result (If card is in hand etc )
        for ( int i = 0; i < defausse.size(); i++)
            cimetiere.add(main.remove((int) defausse.get(i)));
        
        // return good result
        
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result piocher() {
        int nbAPiocher = Regle.CARTEMAIN - main.size();
        List<Card> lc = carteDeck.piocherCarte(nbAPiocher);
        
        for ( int i = 0; i < nbAPiocher; i++)
            main.add(lc.get(i));
        
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result recoitAttaque(int degat) {
        carteDeck.dommageJoueur(degat);
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result attaque(int attaqueur, Card attaque) {
        if ( !carteEnJeu.contains(attaque) || !(attaque instanceof Perso) ) {
            // return Error result
        }
        
        int degat = ((Perso) carteEnJeu.get(attaqueur)).forceAttaque((Perso) attaque);
        ((Perso) attaque).prendreDommage(degat,attaqueur);
        
        // return good result
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result attaque(int attaqueur, Joueur attaque) {
        if ( carteEnJeu.size() != 0) {
            // return Error result
        }
        
        int degat = ((Perso) carteEnJeu.get(attaqueur)).forceAttaque();
        attaque.recoitAttaque(degat);
        
        // return good result
        
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public List<Result> ajouterEnchants (List<? extends Map<Integer, Integer>> enchs, Joueur opposant) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result placerPerso(int personnage, int arme) {
        Card perso = main.get(personnage);
        Card arm = main.get(arme);
        
        if ( !(perso instanceof Perso) || !(arm instanceof Arme) ) {
            // return Error result
        }
        
        ((Perso) main.get(personnage)).placerArme((Arme) arm);
        carteEnJeu.add(main.remove(personnage));
        
        // return good Result
        
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result declarerForfait() {
        int i;
        for ( i = 0; i < main.size(); i++)
            cimetiere.add(main.remove(i));
        
        List<Card> allDeck = carteDeck.piocherCarte(carteDeck.carteRestantes());
        
        for ( i = 0; i < allDeck.size(); i++)
            cimetiere.add(allDeck.remove(i));
        
        for ( i = 0; i < carteEnJeu.size(); i++)
            cimetiere.add(carteEnJeu.remove(i));
        
        // return Result good.
        
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result soignerPerso(int soigneur, int soignee) {
        return ( (Perso) carteEnJeu.get(soigneur))
                        .soigner(
                                (Perso) carteEnJeu.get(soignee)
                );
    }
    
    public boolean detientCarte(int idCarte) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public String toJSon() {
        throw new UnsupportedOperationException("Not implemented");
    }  
}

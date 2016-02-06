/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.Init.*;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author kaldoran
 */
public class Deck {
    private final List<Card> cartespioches;
    
    public Deck(){
        cartespioches = new ArrayList<>();
        initialiserDeck();
    }
    
    private void initialiserDeck() {
        ArmeFactory createurArmes = new ArmeFactory();
        EnchantFactory createurEnchants = new EnchantFactory();
        GuerrierBuilder carteGuerriers = new GuerrierBuilder();
        PretreBuilder cartePretres = new PretreBuilder();
        PaladinBuilder cartePaladins = new PaladinBuilder();
        
        int persoCompteur;
        for (persoCompteur = 0 ; persoCompteur < Regle.CARTEGUERRIER;persoCompteur++){
            cartespioches.add(carteGuerriers.buildNewPerso());
        }
        for (persoCompteur = 0 ; persoCompteur < Regle.CARTEPRETRE;persoCompteur++){
            cartespioches.add(cartePretres.buildNewPerso());
        }
        for (persoCompteur = 0 ; persoCompteur < Regle.CARTEPALADIN;persoCompteur++){
            cartespioches.add(cartePaladins.buildNewPerso());
        }
        cartespioches.addAll(createurArmes.creerSetArmes(Regle.CARTEARMEUN, 1));
        cartespioches.addAll(createurArmes.creerSetArmes(Regle.CARTEARMEDEUX, 2));
        cartespioches.addAll(createurEnchants.creerSetEnchants(Regle.CARTEENCHANTEMENT));

        
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
    
    public JsonObject toJSon() {
        JsonObjectBuilder obj = Json.createObjectBuilder();    
        obj.add("Nombre de cartes restantes a piger", cartespioches.size());
        return obj.build();
    }
}

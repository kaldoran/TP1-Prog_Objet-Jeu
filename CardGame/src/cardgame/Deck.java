/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.Init.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author kaldoran
 */
public class Deck {
    private List<Card> cartespioches;
    
    public Deck(){
        cartespioches = new ArrayList<>();
        initialiserDeck();
    }
    
    /**
     * Permet de crée le deck et d'initialiser son contenu
     */
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

        Collections.shuffle(cartespioches, new Random(System.nanoTime()));
    }
    
    /**
     * Permet de piocher une liste de carte
     * @param nbCartes nombre de carte à piocher
     * @return Liste des cartes piochées
     */
    public List<Card> piocherCarte(int nbCartes) {
        int nbAPiocher = Math.min(nbCartes, this.carteRestantes());
        nbAPiocher = Math.min(nbAPiocher, 5);
        
        List<Card> l = new ArrayList<>();
        
        while ( nbAPiocher != 0) {
            l.add(cartespioches.remove(0));
            --nbAPiocher;
        }
    
        return l;
    }
    
    /**
     * Permet d'appliquer les dégats recu par un joueur sur son Deck
     * @param nbDegatCarte nombre de dégat pris
     * @return la Liste des cartes perdues
     */
    public List<Card> dommageJoueur(int nbDegatCarte) {
        return piocherCarte(nbDegatCarte);
    }
    
    /**
     * Permet de savoir le nombre de carte réstante dans la pioche
     * @return le nombre de carte encore présente dans la pioche
     */
    public int carteRestantes() {
        return cartespioches.size();
    }
    
    /**
     * Pemet d'avoir la représentation en JSon du Deck
     * A noter : Le contenu du deck n'est pas communiqué
     * @return la représentation du Deck en JSon
     */
    public JsonObject toJSon() {
        JsonObjectBuilder obj = Json.createObjectBuilder();    
        obj.add("Nombre de cartes restantes a piger", cartespioches.size());
        return obj.build();
    }
}

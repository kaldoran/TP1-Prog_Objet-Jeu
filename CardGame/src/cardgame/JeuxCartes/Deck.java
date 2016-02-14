package cardgame.JeuxCartes;

import cardgame.Regles.Regle;
import cardgame.JeuxCartes.Carte;
import cardgame.Init.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Classe représentant le paquet de cartes non-pigés d'un joueur. La classe
 * permet d'initialiser le deck et de traiter la logique de pioche et de vie
 * (puisque les points de vie sont == au nombre de cartes restantes.) sans
 * donner accès à cette logique au joueur.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0 08-Fév-2016 : 1.0 - Version initiale.
 */
public class Deck {
    
    /**
     * Structure représentant le deck lui-même.
     */
    private final List<Carte> cartespioches;
    
    public Deck(){
        cartespioches = new ArrayList<>();
        initialiserDeck();
    }
    
    /**
     * Permet de créer le deck et d'initialiser son contenu.
     * La classe initialise le nombre de cartes nécessaire de chaque type
     * selon les règles du jeu et ensuite mélange le deck.
     */
    private void initialiserDeck() {
        ArmeFactory createurArmes = new ArmeFactory();
        EnchantFactory createurEnchants = new EnchantFactory();
        PersoFactory createurPersos = new PersoFactory();
        
        cartespioches.addAll(createurPersos.creerSetGuerrier(Regle.CARTEGUERRIER));
        cartespioches.addAll(createurPersos.creerSetGuerrier(Regle.CARTEPRETRE));
        cartespioches.addAll(createurPersos.creerSetGuerrier(Regle.CARTEPALADIN));        
        cartespioches.addAll(createurArmes.creerSetArmes(Regle.CARTEARMEUN, 1));
        cartespioches.addAll(createurArmes.creerSetArmes(Regle.CARTEARMEDEUX, 2));
        cartespioches.addAll(createurEnchants.creerSetEnchants(Regle.CARTEENCHANTEMENT));

        Collections.shuffle(cartespioches, new Random(System.nanoTime()));
    }
    
    /**
     * Permet de piocher une liste de carte.
     * @param nbCartes nombre de carte à piocher
     * @return Liste des cartes piochées
     */
    public List<Carte> piocherCarte(int nbCartes) {
        
        /*On s'assure de piocher le min entre le nombre de cartes restantes,
        le nombre demandé ou le nombre maximal dans une main.*/
        int nbAPiocher = Math.min(nbCartes, this.carteRestantes());
        nbAPiocher = Math.min(nbAPiocher, Regle.CARTEMAIN);
        
        List<Carte> nouvCartes = new ArrayList<>();
        
        while ( nbAPiocher != 0) {
            nouvCartes.add(cartespioches.remove(0));
            --nbAPiocher;
        }
    
        return nouvCartes;
    }
    
    /**
     * Permet d'appliquer les dégats recu par un joueur sur son Deck.
     * @param nbDegatCarte Le nombre de dégat pris
     * @return la Liste des cartes perdues
     */
    public List<Carte> dommageJoueur(int nbDegatCarte) {
        return piocherCarte(nbDegatCarte);
    }
    
    /**
     * Permet de savoir le nombre de carte réstante dans la pioche.
     * @return le nombre de cartes encore présentes dans la pioche.
     */
    public int carteRestantes() {
        return cartespioches.size();
    }
    
    
    public boolean deckEstVide(){
        return cartespioches.isEmpty();
    }

    /**
     * Pemet d'avoir la représentation en JSon du Deck
     * A noter : Le contenu du deck n'est pas communiqué pour éviter la triche.
     * @return la représentation du Deck en JSon
     */
    public JsonObject toJSon() {
        JsonObjectBuilder obj = Json.createObjectBuilder();    
        obj.add("Nombre de cartes restantes a piger", cartespioches.size());
        return obj.build();
    }
}

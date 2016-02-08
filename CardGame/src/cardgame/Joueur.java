/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.ResultUtils.AttaqueResult;
import cardgame.ResultUtils.DefausseResult;
import cardgame.ResultUtils.FinDePartieResult;
import cardgame.ResultUtils.PersoDeploieResult;
import cardgame.ResultUtils.PiocheResult;
import cardgame.ResultUtils.RefuseResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author kaldoran
 */
public class Joueur {
    private int idJoueur;
    private Deck carteDeck;
    private List<Carte> main;
    private List<Carte> carteEnJeu;
    private List<Carte> cimetiere;
    
    public Joueur() { 
        carteDeck = new Deck();
        main = new ArrayList<>();
        cimetiere = new ArrayList<>();
        carteEnJeu = new ArrayList<>();
    }
    
    public int getIdjoueur() {
        return idJoueur;
    }
    
    public void setIdJoueur(int _idJoueur) {
        idJoueur = _idJoueur;
    }
    
    /**
     * Permet d'obtenir le deck du joueur
     * @return le deck du joueur
     */
    public Deck getCarteDeck() {
        return carteDeck;
    }
    
    /**
     * Permet d'avoir la main du joueur
     * @return la liste de Carte contenu dans la main du joueur
     */
    public List<Carte> getMain() {
        return main;
    }
    
    /**
     * Pemet d'avoir les cartes en jeu du joueur
     * @return la liste de carte présente sur le jeu, cartes associées au joueur
     */
    public List<Carte> getCarteEnJeu() {
        return carteEnJeu;
    }
    
    /**
     * Permet d'avoir le cimetiere du joueur
     * @return la liste de carte présente dans le cimetiere du joueur
     */
    public List<Carte> getCimetiere() {
        return cimetiere;
    }
    
    /**
     * Permet de savoir si le joueur à perdu
     * @return true si le joueur à perdu (autrement dit si il n'a plus de carte null part ) [Cimetiere non compris]
     *         false sinon
     */
    public boolean aPerdu() {
        return main.size() > 0 && carteEnJeu.size() > 0 && carteDeck.carteRestantes() > 0;
    }
    
    /**
     * Permet au joueur de defausser une liste de Carte
     * @param defausse liste des cartes à défausser
     * @return Un DefausseResult si la defausse s'est bien passé
     *         Un RefusedResult sinon
     */
    public Resultat defausserCartes(List<Integer> defausse) {
        Resultat res;
        List<Carte> lc = new ArrayList();
        
        if ( defausse.isEmpty() ) {
            res = new RefuseResult("La Liste est vide.");
            return res;
        }
        
        for ( int i = 0; i < defausse.size(); i++) {
            int card = defausse.get(i);
            if ( card < 0 || card > main.size() ) {
                res = new RefuseResult("La carte " + i + " n'est pas présente dans votre main.");
                return res;
            }
            
            Carte tmp = main.remove(card);
            
            lc.add(tmp);
            cimetiere.add(tmp);
        }
        
        res = new DefausseResult(this.getIdjoueur(), true, lc);
        return res;
    }
    
    /**
     * Permet au joueur de piocher des cartes 
     * @return un PiocheResult si tout s'est bien passé
     *         un RefusedResult sinon
     */
    public Resultat piocher() {
        Resultat res;
        int nbAPiocher = Regle.CARTEMAIN - main.size();
        List<Carte> lc = carteDeck.piocherCarte(nbAPiocher);
        
        if ( nbAPiocher == 0 ) {
            res = new RefuseResult("Votre main contient déjà le maximum de carte");
            return res;
        }
        
        main.addAll(lc);
        
        res = new PiocheResult(this.getIdjoueur(), true, lc);
        return res;
    }
    
    /** 
     * Permet à un joueur de recevoir une attaque
     * @param degat Quantité de dégats pris
     * @return un AttackResult si tout c'est bien passé
     *         un RefusedResult sinon
     */
    public Resultat recoitAttaque(int degat) {
        Resultat res;
        
        if ( degat <= 0 ) { 
            res = new RefuseResult("Un dégat ne peux être négatif.");
            return res;
        }
               
        cimetiere.addAll(carteDeck.dommageJoueur(degat));
        
        res = new AttaqueResult(degat, true, 0, 0, this.aPerdu());
        return res;
    }
    
    /**
     * Permet à un joueur d'attaquer une carte à l'aide d'une autre carte
     * @param attaqueur position de la carte attaquant sur le jeu
     * @param attaque   Carte à attaquer
     * @return un AttackResult si tout c'est bien passé
     *         un RefusedResult sinon
     */
    public Resultat attaque(int attaqueur, Carte attaque) {
        Resultat res;
        if ( !carteEnJeu.contains(attaque) || !(attaque instanceof Perso) ) {
            res = new RefuseResult("Impossible d'attaquer cette carte, celle ci n'est pas un perso ou sur le terrain.");
            return res;
        }
        
        int degat = ((Perso) carteEnJeu.get(attaqueur)).forceAttaque((Perso) attaque);
        res = ((Perso) attaque).prendreDommage(degat,attaqueur);
        
        return res;
    }
    
    /**
     * Permet à un joueur d'attaquer un joueur à l'aide d'une de ses cartes
     * @param attaqueur position de la carte attaquant sur le jeu
     * @param attaque   Joueur à attaquer
     * @return un AttackResult si tout c'est bien passé
     *         un RefusedResult sinon
     */
    public Resultat attaque(int attaqueur, Joueur attaque) {
        Resultat res;
        if ( carteEnJeu.size() != 0) {
            res = new RefuseResult("Impossible d'attaquer le joueur, il y a encore des cartes en jeux.");
            return res;
        }
        
        int degat = ((Perso) carteEnJeu.get(attaqueur)).forceAttaque();
        res = attaque.recoitAttaque(degat);
        
        return res;
    }
    
    /**
     * Permet d'ajouter une liste d'enchant à un joueur
     * @param enchs liste des positions des enchants dans la main
     * @param opposant Joueur à enchanter
     * @return une Liste de Result, chaqu'un étant soit un EnchantResult si tout c'est bien passé
     *         sinon un RefusedResult
     */
    public List<Resultat> ajouterEnchants (List<Integer> enchs, int perso) {
        List<Resultat> lr = new ArrayList<>();
        Resultat res;
        if ( carteEnJeu.get(perso) == null ) {
            res = new RefuseResult("Impossible d'enchanter cette carte.");
            lr.add(res);
            return lr;
        }
        
        for ( int i = 0; i < enchs.size(); i++)
           lr.add(
                    ((Perso)carteEnJeu.get(perso)).
                                ajouterEnchant(
                                        (Enchant) main.get(enchs.get(i))
                                )
           );
        
        return lr;
    }
    
    /**
     * Permet au joueur de placer un personnage en jeu
     * @param personnage position dans la main du personnage à jouer
     * @param arme       position dans la main de l'arme à jouer sur le joueur
     * @return un PersoDeploieResult si tout c'est bien passé
     *         un Refusedresult sinon
     */
    public Resultat placerPerso(int personnage, int arme) {
        Carte perso = main.get(personnage);
        Carte arm = main.get(arme);
        Resultat res;
        if ( !(perso instanceof Perso) || !(arm instanceof Arme) ) {
            res = new RefuseResult("L'une des 2 cartes est invalide (non perso ou non arme).");
            return res;
        }
        
        if ( !((Perso) main.get(personnage)).placerArme((Arme) main.get(arme)) ) {
            res = new RefuseResult("Impossible d'équiper l'arme");
            return res;
        }
        
        carteEnJeu.add(main.remove(personnage));
        
        res = new PersoDeploieResult(this.getIdjoueur(), true, personnage, arme);
        return res;
    }
    
    /**
     * Permet au joueur de declarerForfait
     * Autrement dit, de passer toutes ces cartes dans le cimetiere.
     * @return RefusedResult si le joueur à déjà perdu
     *         FinDePartieResult si le joueur à déclarer forfait
     */
    public Resultat declarerForfait() {
        Resultat res;
        int i;
        
        if ( this.aPerdu() ) {
            res = new RefuseResult("Vous avez déjà perdu");
            return res;
        }
        
        for ( i = 0; i < main.size(); i++)
            cimetiere.add(main.remove(i));
        
        List<Carte> allDeck = carteDeck.piocherCarte(carteDeck.carteRestantes());
        
        for ( i = 0; i < allDeck.size(); i++)
            cimetiere.add(allDeck.remove(i));
        
        for ( i = 0; i < carteEnJeu.size(); i++)
            cimetiere.add(carteEnJeu.remove(i));
        
        res = new FinDePartieResult(this.getIdjoueur(), true, -1);
        return res;
    }
    
    /**
     * Permet d'effectué l'action de soin sur un personnage présent sur le jeu.
     * @param soigneur Position dans la liste des carteEnJeu du soigneur
     * @param soignee  Position dans la liste des carteEnJeu du soignee
     * @return RefusedResult si le joueur ne peux pas soigner le personnage soignee
     *         SoinsResult si le joueur peu être soigné
     */
    public Resultat soignerPerso(int soigneur, int soignee) {
        return ( (Perso) carteEnJeu.get(soigneur))
                        .soigner(
                                (Perso) carteEnJeu.get(soignee)
                );
    }
    
    /**
     * Permet de vérifier que le joueur detient la carte qu'il souhaite jouer
     * @param idCarte id de la carte à verifier
     * @return true si le joueur posede la carte ayant le carteID égale à idCarte
     *         false sinon
     */
    public boolean detientCarte(int idCarte) {
        for ( int i = 0; i < main.size(); i++)
            if ( main.get(i).cardID == idCarte ) 
                return true;
        
        return false;
    }
    
    /**
     * Permet d'avoir le JSon associé à un joueur
     * @return le JSon objet représentant le joueur
     */
    public JsonObject toJSon() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("main", this.mainToJSon());
        obj.add("cimetiere", this.cimetiereToJSton());
        obj.add("deck", this.deckToJSon());
        
        return obj.build();
    }
    
    /**
     * Permet d'avoir le JSon associé au contenu de la main du joueur
     * @return le JSon associé à la main
     */
    public JsonObject mainToJSon() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        
        Iterator<Carte> cd = main.iterator();
        int numCarte = 1;
        while ( cd.hasNext() ) {
            obj.add("carte #" + numCarte, cd.next().toJSON());
            ++numCarte;
        }
        
        return obj.build();
    } 
    
    /**
     * Permet d'avoir le JSon associé au contenu du cimetiere du joueur
     * @return le JSon associé au cimetiere du joueur
     */
    public JsonObject cimetiereToJSton() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        
        Iterator<Carte> cd = cimetiere.iterator();
        int numCarte = 1;
        while ( cd.hasNext() ) {
            obj.add("carte #" + numCarte, cd.next().toJSON());
            ++numCarte;
        }
        
        return obj.build(); 
    }
    
    /** 
     * Permet d'avoir le contenu du Deck en format jSon
     * @return le JSon associé au contenu du Deck du joueur
     */
    public JsonObject deckToJSon() {
        return carteDeck.toJSon();
    }
}

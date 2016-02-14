package cardgame.JeuxCartes;

import cardgame.ResultUtils.Resultat;

import cardgame.Regles.Regle;
import cardgame.ResultUtils.AttaquePlayerResult;
import cardgame.ResultUtils.DefausseResult;
import cardgame.ResultUtils.EnchantResult;
import cardgame.ResultUtils.FinDePartieResult;
import cardgame.ResultUtils.PersoDeploieResult;
import cardgame.ResultUtils.PiocheResult;
import cardgame.ResultUtils.RefuseResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Classe utilisé par l'API pour placé le coup du joueur reçu du controlleur.
 * Comparé à Jeux qui sert de facade pour le controlleur, Joueur traite les appels
 * des joueurs dans le modèle et retourne ces conséquences.
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class Joueur implements Cible {
    private int idJoueur;
    private final Deck carteDeck;
    private final Map<Integer,Carte> main;
    private final Map<Integer,Perso> carteEnJeu;
    private final List<Carte> cimetiere;
    
    public Joueur(int i) { 
        idJoueur = i;
        carteDeck = new Deck();
        main = new HashMap<>();
        cimetiere = new ArrayList<>();
        carteEnJeu = new HashMap<>();
        List<Carte> mainDeb = carteDeck.piocherCarte(Regle.CARTEMAIN);
        for (Carte c : mainDeb) {
            main.put(c.getCardID(), c);
        }
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
    public Map<Integer,Carte> getMain() {
        return main;
    }
    
    /**
     * Pemet d'avoir les cartes en jeu du joueur
     * @return la liste de carte présente sur le jeu, cartes associées au joueur
     */
    public Map<Integer,Perso> getCarteEnJeu() {
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
    public Resultat defausserCartes(List<Carte> defausse) {
        Resultat res;
        
        for (Carte c : defausse) {
            cimetiere.add(main.remove(c.getCardID()));
        }
        
        res = new DefausseResult(this.getIdjoueur(), true, defausse);
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
        
        for (Carte c : lc){
            main.put(c.getCardID(), c);
        }
        
        
        res = new PiocheResult(this.getIdjoueur(), true, lc);
        return res;
    }
    
    /**
     * Permet à un joueur d'attaquer un joueur à l'aide d'une de ses cartes
     * @param attaqueur position de la carte attaquant sur le jeu
     * @param attaque   Joueur à attaquer
     * @return un AttackResult si tout c'est bien passé
     *         un RefusedResult sinon
     */
    public Resultat attaque(Attaquant attaqueur, Cible attaque) {
        Resultat res;
        if (!attaque.peutEtreAttaque()) {
            res = new RefuseResult("Impossible d'attaquer le joueur, il y a encore des cartes en jeux.");
            return res;
        }
        if (attaque == this){
            res = new RefuseResult("Vous n'avez pas le droit de vous attaque vous-même.");
            return res;
        }
        
        res = attaqueur.attaque(attaque);
        
        return res;
    }
    
    
    public void MAJCartesPlancher() {
        for (Carte c: this.carteEnJeu.values()) {
            if (((Perso)c).estMort()){
                cimetiere.add(carteEnJeu.remove(c.getCardID()));
            }
        }
    }
    
    /**
     * Permet d'ajouter une liste d'enchant à un joueur
     * @param enchs liste des positions des enchants dans la main
     * @param carteTouchee carte étant affectée par l'enchant
     * @return une Liste de Result, chaqu'un étant soit un EnchantResult si tout c'est bien passé
     *         sinon un RefusedResult
     */
    public Resultat ajouterEnchants (List<Enchant> enchs, Carte carteTouchee) {
        Resultat res;
        if ( !carteEnJeu.containsKey(carteTouchee.getCardID()) && !main.containsKey(carteTouchee.getCardID())  ) {
            res = new RefuseResult("Impossible d'enchanter cette carte.");
            return res;
        } else if (!(carteTouchee instanceof Arme) && !(carteTouchee instanceof Perso)) {
            res = new RefuseResult("Vous ne pouvez pas enchanter cet carte.");
            return res;
        }
        Arme arm = null;
        if (carteTouchee instanceof Perso)
            arm = ((Perso)carteTouchee).getArme();
        else if (carteTouchee instanceof Arme)
            arm = (Arme)carteTouchee;
            
        for (Enchant ench : enchs) {
            arm.ajouterEnchant(ench);
        }
        res = new EnchantResult(true,carteTouchee,enchs);
        return res;
    }
    
    /**
     * Permet au joueur de placer un personnage en jeu
     * @param personnage position dans la main du personnage à jouer
     * @param arm        carte arme à appliquer sur le perso
     * @return un PersoDeploieResult si tout c'est bien passé
     *         un Refusedresult sinon
     */
    public Resultat placerPerso(Perso personnage, Arme arm) {
        Resultat res;
       
        if (personnage.equiperArme(arm) ) {
            res = new RefuseResult("Impossible d'équiper l'arme");
            return res;
        }
        main.remove(personnage.getCardID());
        carteEnJeu.put(personnage.getCardID(),personnage);
        
        res = new PersoDeploieResult(this.getIdjoueur(), true, personnage.getCardID(), arm.getCardID());
        return res;
    }
    
    /**
     * Permet au joueur de placer un personnage en jeu
     * @param personnage position dans la main du personnage à jouer
     * @param arm        arme à équiper au perso
     * @param ench       Liste des enchants à ajouter à l'arme
     * @return un PersoDeploieResult si tout c'est bien passé
     *         un Refusedresult sinon
     */
    public Resultat placerPerso(Perso personnage, Arme arm,List<Carte> ench) {
       Resultat res;

        if (arm.armeEstDeploye() || arm.peutAjouterEnchantement() || personnage.getArme() != null || arm.peutUtiliserArme(personnage))
        {
            res = new RefuseResult("Impossible de déployer le perso avec cette arme et enchantement.");
            return res;
        }
        
        personnage.equiperArme(arm);
        main.remove(personnage.getCardID());
        carteEnJeu.put(personnage.getCardID(),personnage);
        
        for (Carte c : ench) {
            Enchant en = (Enchant) c;
            arm.ajouterEnchant(en);
        }
        
        res = new PersoDeploieResult(this.getIdjoueur(), true, personnage.getCardID(), arm.getCardID());
        return res;
    }

    public Carte getCarte(int idCarte){
        Carte card;
        card = main.containsKey(idCarte) ? main.get(idCarte) : null;
        card = carteEnJeu.containsKey(idCarte) ? carteEnJeu.get(idCarte) : card;
        
        return card;
    }
    
    public boolean carteDansMain(int idCarte){
        return main.containsKey(idCarte);
    }
    
    public boolean carteDansJeu(int idCarte){
        return carteEnJeu.containsKey(idCarte);
    }
    
    public boolean destVide() {
        return carteDeck.deckEstVide();
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
            res = new RefuseResult("Vous avez déjà perdu.");
            return res;
        }
        
        for ( i = 0; i < main.size(); i++) {
            Carte card = main.remove(i);
            cimetiere.add(card);
        }
        
        List<Carte> allDeck = new ArrayList<>();
        while (!carteDeck.deckEstVide())
            allDeck.addAll(carteDeck.piocherCarte(Regle.CARTEMAIN));
        
        for ( i = 0; i < allDeck.size(); i++) {
            Carte card = allDeck.remove(i);
            cimetiere.add(card);
        }
        
        for ( i = 0; i < carteEnJeu.size(); i++) {
            Carte card = carteEnJeu.remove(i);
            cimetiere.add(card);
        }
        
        res = new FinDePartieResult(this.getIdjoueur(), true, -1);
        return res;
    }
    
    /**
     * Permet d'effectué l'action de soin sur un personnage présent sur le jeu.
     * @param soins    Carte effectuant le soin
     * @param soignee  Position dans la liste des carteEnJeu du soignee
     * @return RefusedResult si le joueur ne peux pas soigner le personnage soignee
     *         SoinsResult si le joueur peu être soigné
     */
    public Resultat soignerPerso(Soigneur soins, Perso soignee) {
        
        if (!soins.peutSoigner(soignee))
            return new RefuseResult("Le soins ne peut pas être éffectué.");
        
        return soins.soigner(soignee);
    }

    /**
     * Permet d'avoir le JSon associé à un joueur
     * @return le JSon objet représentant le joueur
     */
    public JsonObject toJSON() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("main", this.mainToJSon());
        obj.add("cimetiere", this.cimetiereToJSon());
        obj.add("deck", this.deckToJSon());
        
        return obj.build();
    }
    
    /**
     * Permet d'avoir le JSon associé au contenu de la main du joueur
     * @return le JSon associé à la main
     */
    public JsonObject mainToJSon() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        
        Iterator<Carte> cd = main.values().iterator();
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
    public JsonObject cimetiereToJSon() {
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

    public boolean peutPiocher() {
        return main.size() < Regle.CARTEMAIN && carteDeck.deckEstVide();
    }

    @Override
    public boolean peutEtreAttaque() {
        return !carteEnJeu.isEmpty();
    }

    @Override
    public AttaquePlayerResult recoitAttaque(Perso attaqueur) {
       int degat = attaqueur.forceAttaque();
       List<Carte> cartePerdus = this.carteDeck.dommageJoueur(degat);
       for (Carte c : cartePerdus)
           cimetiere.add( c);
       AttaquePlayerResult res = new AttaquePlayerResult(degat, idJoueur, attaqueur.getCardID(), idJoueur, estMort());
       return res;
    }

    @Override
    public boolean estMort() {
        return carteDeck.deckEstVide() && main.isEmpty() && carteEnJeu.isEmpty();
    }
}

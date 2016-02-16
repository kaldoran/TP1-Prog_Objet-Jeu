package cardgame.JeuxCartes;

import cardgame.ResultUtils.Resultat;
import cardgame.Regles.*;
import cardgame.ResultUtils.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.json.*;

/**
 * Classe utilisé par l'API pour placé le coup du joueur reçu du controlleur.
 * Comparé à Jeux qui sert de facade pour le controlleur, Joueur traite les
 * appels des joueurs dans le modèle et retourne ces conséquences.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.1
 * Historique : 08-Fév-2016 : 1.0 - Version initiale.
 *              13-Fév-2016 : 1.1 - Réécriture des fonctions pour 
 *                                  fonctionner avec cible.
 */
public class Joueur implements Cible {

    private final int idJoueur;
    private final Deck carteDeck;
    private final Map<Integer, Carte> main;
    private final Map<Integer, Perso> carteEnJeu;
    private final List<Carte> cimetiere;

    public Joueur(int i) {
        idJoueur = i;
        carteDeck = new Deck();
        main = new ConcurrentHashMap<>();
        cimetiere = new CopyOnWriteArrayList<>();
        carteEnJeu = new ConcurrentHashMap<>();
        List<Carte> mainDeb = carteDeck.piocherCarte(Regle.CARTEMAIN);
        for (Carte c : mainDeb) {
            main.put(c.getCardID(), c);
        }
    }

    /**
     * @return L'identifiant unique du joueur.
     */
    public int getIdjoueur() {
        return idJoueur;
    }

    /**
     * Permet d'obtenir le deck du joueur
     *
     * @return le deck du joueur
     */
    public Deck getCarteDeck() {
        return carteDeck;
    }

    /**
     * Permet d'avoir la main du joueur
     *
     * @return la liste de Carte contenu dans la main du joueur
     */
    public Map<Integer, Carte> getMain() {
        return main;
    }

    /**
     * Pemet d'avoir les cartes en jeu du joueur
     *
     * @return la liste de carte présente sur le jeu, cartes associées au joueur
     */
    public Map<Integer, Perso> getCarteEnJeu() {
        return carteEnJeu;
    }

    /**
     * Permet d'avoir le cimetiere du joueur
     *
     * @return la liste de carte présente dans le cimetiere du joueur
     */
    public List<Carte> getCimetiere() {
        return cimetiere;
    }

    /**
     * Permet de savoir si le joueur à perdu
     *
     * @return true si le joueur à perdu (autrement dit si il n'a plus de carte
     * null part ) [Cimetiere non compris] false sinon
     */
    public boolean aPerdu() {
        return (main.isEmpty() && carteEnJeu.isEmpty() && carteDeck.deckEstVide());
    }

    /**
     * Permet au joueur de defausser une liste de Carte
     *
     * @param defausse liste des cartes à défausser
     * @return Un DefausseResult si la defausse s'est bien passé Un
     * RefusedResult sinon
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
     *
     * @return un PiocheResult si tout s'est bien passé un RefusedResult sinon
     */
    public Resultat piocher() {
        int nbAPiocher = Regle.CARTEMAIN - main.size();
        nbAPiocher = Math.max(nbAPiocher, 0);
        List<Carte> lc = carteDeck.piocherCarte(nbAPiocher);

        for (Carte c : lc) {
            main.put(c.getCardID(), c);
        }

        return new PiocheResult(this.getIdjoueur(), true, lc);
    }

    /**
     * Permet à un joueur d'attaquer un joueur à l'aide d'une de ses cartes
     *
     * @param attaqueur position de la carte attaquant sur le jeu
     * @param attaque Joueur à attaquer
     * @return un AttackResult si tout c'est bien passé un RefusedResult sinon
     */
    public Resultat attaque(Combattant attaqueur, Cible attaque) {
        Resultat res;

        res = attaqueur.Attaque(attaque);

        return res;
    }

    /**
     * Fonction auxiliaire appelé après chaque attaque reçu, MAJCartesPlancher
     * enlève les perso mort de la partie.
     */
    public void MAJCartesPlancher() {
        for (Perso pers : this.carteEnJeu.values()) {
            if (pers.estMort()) {
                cimetiere.add(carteEnJeu.remove(pers.getCardID()));
            }
        }
    }

    /**
     * Permet d'ajouter une liste d'enchant à un joueur
     *
     * @param enchs liste des positions des enchants dans la main
     * @param carteTouchee carte étant affectée par l'enchant
     * @return une Liste de Result, chaqu'un étant soit un EnchantResult si tout
     * c'est bien passé sinon un RefusedResult
     */
    public Resultat ajouterEnchants(List<Enchant> enchs, Carte carteTouchee) {
        Resultat res;
        Arme arm;
        if (carteTouchee instanceof Perso) {
            arm = ((Perso) carteTouchee).getArme();
        } else if (carteTouchee instanceof Arme) {
            arm = (Arme) carteTouchee;
        } else {
            return new RefuseResult("Erreur interne.");
        }

        for (Enchant ench : enchs) {
            arm.ajouterEnchant(ench);
            cimetiere.add(ench);
            main.remove(ench.getCardID());
        }
        res = new EnchantResult(true, carteTouchee, enchs);
        return res;
    }

    /**
     * Permet au joueur de placer un personnage en jeu
     *
     * @param personnage position dans la main du personnage à jouer
     * @param arm arme à équiper au perso
     * @param ench Liste des enchants à ajouter à l'arme
     * @return un PersoDeploieResult si tout c'est bien passé un Refusedresult
     * sinon
     */
    public Resultat placerPerso(Perso personnage, Arme arm, List<Carte> ench) {
        Resultat res;

        for (Carte c : ench) {
            Enchant en = (Enchant) c;
            arm.ajouterEnchant(en);
            cimetiere.add(main.remove(en.getCardID()));
        }

        personnage.equiperArme(arm);
        main.remove(personnage.getCardID());
        main.remove(arm.getCardID());
        carteEnJeu.put(personnage.getCardID(), personnage);

        res = new PersoDeploieResult(this.getIdjoueur(), true, personnage);
        return res;
    }

    /**
     * @param car La carte qu'on veut vérifier l'emplacement
     * @return True si la carte est dans la main du joueur.
     */
    public boolean carteDansMain(Carte car) {
        return main.containsKey(car.getCardID());
    }

    /**
     * @param car La carte qu'on veut vérifier l'emplacement
     * @return True si la carte est dans la jeu du joueur.
     */
    public boolean carteDansJeu(Carte car) {
        return carteEnJeu.containsKey(car.getCardID());
    }

    /**
     * Permet au joueur de declarerForfait Autrement dit, de passer toutes ces
     * cartes dans le cimetiere.
     *
     * @return RefusedResult si le joueur à déjà perdu FinDePartieResult si le
     * joueur à déclarer forfait
     */
    public Resultat declarerForfait() {
        Resultat res;
        main.clear();
        carteDeck.viderDeck();
        carteEnJeu.clear();
        res = new FinDePartieResult(this.getIdjoueur(), true, -1);
        return res;
    }

    /**
     * Permet d'effectué l'action de soin sur un personnage présent sur le jeu.
     *
     * @param soins Carte effectuant le soin
     * @param soignee Position dans la liste des carteEnJeu du soignee
     * @return RefusedResult si le joueur ne peux pas soigner le personnage
     * soignee SoinsResult si le joueur peu être soigné
     */
    public Resultat soignerPerso(Soigneur soins, Perso soignee) {

        if (!soins.peutSoigner(soignee)) {
            return new RefuseResult("Le soins ne peut pas être éffectué.");
        }

        return soins.soigner(soignee);
    }

    /**
     * Permet d'avoir le JSon associé à un joueur
     *
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
     *
     * @return le JSon associé à la main
     */
    private JsonObject mainToJSon() {
        JsonObjectBuilder obj = Json.createObjectBuilder();

        Iterator<Carte> cd = main.values().iterator();
        int numCarte = 1;
        while (cd.hasNext()) {
            obj.add("carte #" + numCarte, cd.next().toJSON());
            ++numCarte;
        }

        return obj.build();
    }

    /**
     * Permet d'avoir le JSon associé au contenu du cimetiere du joueur
     *
     * @return le JSon associé au cimetiere du joueur
     */
    private JsonObject cimetiereToJSon() {
        JsonObjectBuilder obj = Json.createObjectBuilder();

        Iterator<Carte> cd = cimetiere.iterator();
        int numCarte = 1;
        while (cd.hasNext()) {
            obj.add("carte #" + numCarte, cd.next().toJSON());
            ++numCarte;
        }

        return obj.build();
    }

    /**
     * Permet d'avoir le contenu du Deck en format jSon
     *
     * @return le JSon associé au contenu du Deck du joueur
     */
    private JsonObject deckToJSon() {
        return carteDeck.toJSon();
    }

    /**
     * @return True si le joueur a le droit de piocher. 
     */
    public boolean peutPiocher() {
        return ((main.size() < Regle.CARTEMAIN) && (!carteDeck.deckEstVide()));
    }

    @Override
    public boolean peutEtreAttaque() {
        return carteEnJeu.isEmpty();
    }

    @Override
    public AttaquePlayerResult recoitAttaque(Combattant attaqueur) {
        int degat = attaqueur.forceAttaque(TypeArme.Neutre);
        List<Carte> cartePerdus = this.carteDeck.dommageJoueur(degat);
        for (Carte c : cartePerdus) {
            cimetiere.add(c);
        }
        AttaquePlayerResult res = new AttaquePlayerResult(degat, idJoueur, attaqueur.getCardID(), idJoueur, estMort());
        return res;
    }

    @Override
    public boolean estMort() {
        return ((carteDeck.deckEstVide() && main.isEmpty()) && (carteEnJeu.isEmpty()));
    }

}

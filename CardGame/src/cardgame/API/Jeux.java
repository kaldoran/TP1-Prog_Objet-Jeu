package cardgame.API;

import cardgame.JeuxCartes.Arme;
import cardgame.JeuxCartes.Attaquant;
import cardgame.JeuxCartes.Carte;
import cardgame.JeuxCartes.Cible;
import cardgame.JeuxCartes.Enchant;
import cardgame.JeuxCartes.Joueur;
import cardgame.JeuxCartes.Joueur;
import cardgame.JeuxCartes.Perso;
import cardgame.JeuxCartes.Soigneur;
import cardgame.ResultUtils.Resultat;
import cardgame.ResultUtils.RefuseResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Classe API utilisé pour communiquer entre le modèle et le controlleur. Toutes
 * les actions possibles par un joueur doit passer par une des fonctions de
 * cette classe. Afin d'éviter toutes triches, chaque action demandé par le
 * joueur est vérifié avant de s'effectuer. (Ex : Le joueur 2 essaie de jouer
 * pour le joueur . Le joueur 1 essaie de fair eune action impossible tel que
 * déployer des cartes de l'adversaire.) ) Si l'action est mauvaise, Jeux
 * retourne un RefuseResult. Sinon, l'action est éffectué et Jeux retourne un
 * Resultat décrivant les conséquences de l'acte.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0 08-Fév-2016 : 1.0 - Version initiale.
 */
public class Jeux {

    private List<Joueur> joueurList;
    private boolean partieEnMarche;
    private int joueurTour;

    public Jeux() {
    }

    /**
     * Permet de passer au joueur suivant
     */
    public void prochainJoueur() {
        joueurTour = (joueurTour++) % joueurList.size();
    }

    /**
     * Permet de savoir à qui est le tour ( id du joueur )
     *
     * @return l'id du joueur à qui c'est le tour de jouer
     */
    public int aQuiLeTour() {
        return joueurTour;
    }

    /**
     * Permet de démarrer une partie à nbJoueur
     *
     * @param nbJoueur nombre de joueur voulant jouer
     */
    public void demarrerPartie(int nbJoueur) {
        Random r = new Random();
        joueurTour = r.nextInt(nbJoueur);

        if (!partieEnMarche) {
            for (int i = 0; i < nbJoueur; i++) {
                joueurList.add(new Joueur(i));
            }
        }

        partieEnMarche = true;
    }

    /**
     * Permet au joueur idJoueur de déclarer forfait
     *
     * @param idJoueur id du joueur déclarant forfait
     * @return 
     */
    public Resultat declarerForfait(int idJoueur) {
        if (joueurTour == idJoueur) {
            return joueurList.get(idJoueur).declarerForfait();
        }

        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Permet de savoir l'état du jeu à tout moment ( sous un format JSon)
     *
     * @return le contenu de chaque joueur
     */
    public JsonObject getEtatJeu() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("aQuiLeTour", this.joueurTour);
        obj.add("partieEnCours", this.partieEnMarche);

        Iterator<Joueur> j = joueurList.iterator();
        int numJoueur = 1;
        while (j.hasNext()) {
            obj.add("Joueur #" + numJoueur, j.next().toJSON());
            ++numJoueur;
        }

        return obj.build();
    }

    /**
     * Permet de savoir si la partie est finie
     *
     * @return true si la partie est fini, autrement dit si un joueur à gagné
     * false sinon
     */
    public boolean partieFini() {
        return getJoueurGagnant() != -1; // tout les joueurs ont perdu sauf 1
    }

    /**
     * Permet d'avoir l'id du joueur ayant gagné
     *
     * @return l'id du joueur gagnant -1 si aucun joueur n'a gagné
     */
    public int getJoueurGagnant() {
        int joueursPerdants = 0;
        int joueurGagnant = 0;
        for (int i = 0; i < joueurList.size(); i++) {
            if (!joueurList.get(i).aPerdu()) {
                joueurGagnant = i;
            } else {
                joueursPerdants++;
            }
        }

        return joueursPerdants == joueurList.size() - 1 ? joueurGagnant : -1;
    }

    /**
     * Permet au joueur idJoueur de piocher
     *
     * @param idJoueur id du joueur voulant piocher
     * @return une liste de Result, PiocheResult en cas de succes de la pioche
     * un RefusedResult sinon
     */
    public Resultat piocherCartes(int idJoueur) {
        Resultat res;
        if (idJoueur != aQuiLeTour()) {
            res = new RefuseResult("Ce n'est pas le tour de ce joueur.");
            return res;
        }

        res = joueurList.get(idJoueur).piocher();
        return res;
    }

    public boolean peutPiocherCartes(int idJoueur) {
        return idJoueur != aQuiLeTour() &&  joueurList.get(idJoueur).peutPiocher();
    }

    /**
     * Permet d'attaquer un perso présent sur le deck
     *
     * @param idJoueur id du joueur effectuant l'action
     * @param idAdversaire position relative sur le terrain de jeu de la carte
     * attaquante
     * @param attaqueur carte attaquant la seconde carte 
     * @param receveur  carte recevant le coupŝ
     * @return un AttackResult si tout c'est bien passé un refusedResult sinon
     */
    public Resultat attaquePerso(int idJoueur,int idAdversaire, Carte attaqueur,Carte receveur) {
        Resultat res;
        
        if (attaquePersoValide(idJoueur,idAdversaire, attaqueur, receveur)) {
            Attaquant att = (Attaquant)attaqueur;
            Cible attaquee = (Cible)receveur;
            res = joueurList.get(idJoueur).attaque(att, attaquee);
            joueurList.get(idAdversaire).MAJCartesPlancher();
        } else {
            res = new RefuseResult("L'attaque n'est pas possible");
        }
        
        return res;
    }

    public boolean attaquePersoValide(int idJoueur,int idAdversaire,Carte attaqueur,Carte receveur){
        
       boolean coupP = ((this.aQuiLeTour() == idJoueur) && (idJoueur != idAdversaire));
       coupP = coupP && (idAdversaire >= 0) && (joueurList.size() >= idAdversaire);
       coupP = coupP && (attaqueur instanceof Perso) && (receveur instanceof Perso);
       if (coupP){
           coupP = coupP && joueurList.get(idJoueur).carteDansJeu(attaqueur.getCardID());
           coupP = coupP && joueurList.get(idAdversaire).carteDansJeu(receveur.getCardID());
           coupP = coupP && ((Cible)receveur).peutEtreAttaque();
       }
       return coupP;        
        
    }

    /**
     * Permet d'attaquer un perso présent sur le deck
     *
     * @param idJoueur id du joueur effectuant l'action
     * @param idAdversaire position relative sur le terrain de jeu de la carte
     * attaquante
     * @param attaqueur Carte effectuant l'attaque sur le joueur
     * @return un AttackResult si tout c'est bien passé un refusedResult sinon
     */
    public Resultat attaqueJoueur(int idJoueur,int idAdversaire, Carte attaqueur) {
        Resultat res;
        
        if (attaqueJoueurValide(idJoueur,idAdversaire,attaqueur)) {
            Attaquant att = (Attaquant)attaqueur;
            res = joueurList.get(idJoueur).attaque(att, joueurList.get(idAdversaire));
        } else {
            res = new RefuseResult("L'attaque n'est pas possible");
        }
        
        return res;
    }
    
    
    public boolean attaqueJoueurValide(int idJoueur,int idAdversaire,Carte attaqueur){
        
        boolean verifValide =  idJoueur == aQuiLeTour() && idAdversaire >=0 && idAdversaire <= joueurList.size() && idJoueur != idAdversaire;
        if (verifValide) {
            verifValide = joueurList.get(idJoueur).getCarteEnJeu().containsKey(attaqueur.getCardID());
            verifValide = verifValide && joueurList.get(idAdversaire).peutEtreAttaque();
            verifValide = verifValide && (attaqueur instanceof Attaquant);
        }
        
        return verifValide;
    }
    
    

    /**
     * Permet d'ajouter une liste d'enchant sur un joueur
     *
     * @param idJoueur id du joueur qui va recevoir les enchants
     * @param carteTouche La carte qui va recevoir les enchants
     * @param enchant listes des positions relatives dans le deck des cartes
     * d'enchantements à appliquer
     * @return un list Result, chaqu'un contenant un EnchantResult si l'enchant
     * fonctionne un refused Result sinon
     */
    public Resultat ajouterEnchantements(int idJoueur, Carte carteTouche, List<Carte> enchant) {
        Resultat res;
        
        if (peutAjouterEnchantements(idJoueur, carteTouche, enchant)) {
            List<Enchant> enchs = new ArrayList<>();
            for (Carte c : enchant) {
                enchs.add((Enchant)c );
            }
            res = joueurList.get(idJoueur).ajouterEnchants(enchs, carteTouche);
        } else {
            res = new RefuseResult("Vous ne pouvez pas enchanter cette cartes avec votre sélection.");
        }
        return res;
    }

    public boolean peutAjouterEnchantements(int idJoueur,Carte carteTouche, List<Carte> enchants) {
        boolean verifValide = idJoueur == aQuiLeTour();
        Joueur j = joueurList.get(idJoueur);
        boolean carteDeploye = false;
        for (Carte c : enchants){
            verifValide = verifValide && j.carteDansMain(c.getCardID());
        }
        for (Joueur joueurAct : joueurList){
            carteDeploye = carteDeploye || joueurAct.carteDansJeu(carteTouche.getCardID());
        }
        carteDeploye = carteDeploye && carteTouche instanceof Perso;
        if (carteDeploye){
            Perso p = (Perso)carteTouche;
            carteDeploye = p.getArme().peutAjouterEnchantement();
        }
        
        return verifValide && carteDeploye;
    }
    
    public Collection<Carte> getCartesMainJoueur(int idJoueur){
        return joueurList.get(idJoueur).getMain().values();
    }
    
    public Collection<Perso> getCartesJeuJoueur(int idJoueur){
        Perso p;
        return joueurList.get(idJoueur).getCarteEnJeu().values();
    }
    
    public Collection<Carte> getCimetiereJoueur(int idJoueur){
        return joueurList.get(idJoueur).getCimetiere();
    }
    public Joueur getJoueur(int idJoueur) {
        return joueurList.get(idJoueur);
    }

    /**
     * Permet de placer un personnage
     *
     * @param idJoueur id relatif du joueur dans la liste Joueur List
     * @param personnage position relative dans la main du joueur à placer
     * @param arme position relative dans la main de l'arme a placer
     * @param enchants liste des cartes d'enchants à placer sur le perso
     * @return un PersoDeploieResult si tout ce passe bien un refusedResult
     * sinon
     */
    public Resultat placerPerso(int idJoueur, Carte personnage, Carte arme,List<Carte> enchants) {
        Resultat res;
        if (this.peutDeployerPerso(idJoueur, arme, arme, enchants)) {
            res = joueurList.get(idJoueur).placerPerso((Perso) personnage, (Arme) arme, enchants);
        } else {
            res = new RefuseResult("Vous n'avez pas le droit d'utiliser une des cartes choisi.");
        }
        return res;
        
    }
    
    public boolean peutDeployerPerso(int idJoueur, Carte perso, Carte arme, List<Carte> enchants) {
        boolean verif = idJoueur == aQuiLeTour();
        Joueur j = joueurList.get(idJoueur);
        verif = verif && j.carteDansMain(perso.getCardID()) && j.carteDansMain(arme.getCardID());
        verif = verif && (perso instanceof Perso) && (arme instanceof Arme);
        for (Carte c : enchants) {
            verif = verif && j.carteDansMain(c.getCardID());
            verif = verif && c instanceof Enchant;
        }
        if (verif) {
            Perso p = (Perso) perso;
            Arme a = (Arme)arme;
            verif = !a.armeEstDeploye();
            verif = verif && a.peutUtiliserArme(p);
        }
        
        return verif;
        
    }
    

    /**
     * Permet de défausser n Carte à partie de l'api
     *
     * @param idJoueur id du joueur souhaitant se défausser
     * @param defausse Liste des positions relatives dans la main des cartes à
     * defausser
     * @return un defausseResult si tout ce passe bien un refusedResult en cas
     * d'erreur
     */
    public Resultat defausserCartes(int idJoueur, List<Carte> defausse) {
        Resultat res;
        if (peutDefausserCartes(idJoueur, defausse)) {
            res = joueurList.get(idJoueur).defausserCartes(defausse);
        } else {
            res = new RefuseResult("La liste est soit vide, soit rempli de cartes pas situés dans la main.");
        }
        
        return res;
    
    }
    
    public boolean peutDefausserCartes(int idJoueur,List<Carte> defausse) {
        
        boolean coupValide = idJoueur == aQuiLeTour();
        coupValide = coupValide && defausse != null;
        coupValide = coupValide && !defausse.isEmpty();
        Joueur joueurAct = joueurList.get(idJoueur);
        for (Carte c : defausse){
            coupValide = coupValide && joueurAct.carteDansMain(c.getCardID());
            if (!coupValide) break;
        }
        
        
        return coupValide;
        
    }
    

    /**
     * Permet de soigner un personnage du jeu
     *
     * @param idJoueur id du joueur qui effectue l'action
     * @param soigneur id relatif de la position de la carte sur le terrain de
     * jeu
     * @param soignee id relatif de la position de la carte sur le terrain de
     * jeu ( carte à soigner)
     * @return un SoinsResult si tout c'est bien passé un RefusedResult sinon
     */
    public Resultat soignerPerso(int idJoueur, Carte soigneur, Carte soignee) {
        Resultat res;
        
        if (peutSoignerPerso(idJoueur, soigneur,soignee)){
            Soigneur s = (Soigneur) soigneur;
            Perso p = (Perso) soignee;
            res = joueurList.get(idJoueur).soignerPerso(s, p);
        } else {
            res = new RefuseResult("Vous n'avez pas le droit de faire ce soin.");
        }
        
        return res;
        
        
    }

    public boolean peutSoignerPerso(int idJoueur, Carte soigneur, Carte soignee) {
        boolean coupValide = idJoueur == aQuiLeTour();
        coupValide = coupValide && soigneur != soignee;
        Joueur joueurAct = joueurList.get(idJoueur);
        coupValide = coupValide && joueurAct.carteDansJeu(soigneur.getCardID()) &&
                joueurAct.carteDansJeu(soignee.getCardID());
        coupValide = coupValide && soigneur instanceof Soigneur && soignee instanceof Perso;
        if (coupValide) {
            Soigneur s = (Soigneur)soigneur;
            Perso p = (Perso)soignee;
            coupValide = s.peutSoigner(p);
        }
        return coupValide;
    }
    

    /**
     * Permet de liberer les informations de jeu et reinialisé les valeurs de
     * l'api
     */
    public void finPartie() {
        joueurList.clear();
        joueurTour = 0;
        partieEnMarche = false;
    }
}

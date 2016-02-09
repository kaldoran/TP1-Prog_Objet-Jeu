/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.ResultUtils.RefusedResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author kaldoran
 */
public class Jeux {
    private List<Joueur> joueurList;
    private boolean partieEnMarche;
    private int joueurTour;
    
    public Jeux() { }
    
    /**
     * Permet de passer au joueur suivant
     */
    public void prochainJoueur() {
        joueurTour = (joueurTour++) % joueurList.size();
    }
    
    /**
     * Permet de savoir à qui est le tour ( id du joueur )
     * @return l'id du joueur à qui c'est le tour de jouer
     */
    public int aQuiLeTour() {
        return joueurTour;
    }
    
    /**
     * Permet de démarrer une partie à nbJoueur
     * @param nbJoueur nombre de joueur voulant jouer
     */
    public void demarrerPartie(int nbJoueur) {
        Random r = new Random();
        joueurTour = r.nextInt(nbJoueur);
        
        if ( !partieEnMarche )
            for ( int i = 0; i < nbJoueur; i++)
                joueurList.add(new Joueur(i));
        
        partieEnMarche = true;
    }
    
    /**
     * Permet au joueur idJoueur de déclarer forfait
     * @param idJoueur id du joueur déclarant forfait
     * @return 
     */
    public Result declarerForfait(int idJoueur) {
        if ( joueurTour == idJoueur )
            return joueurList.get(idJoueur).declarerForfait();
        
        
        throw new UnsupportedOperationException("Not implemented");   
    }
    
    /**
     * Permet de savoir l'état du jeu à tout moment ( sous un format JSon)
     * @return le contenu de chaque joueur
     */
    public JsonObject getEtatJeu() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("aQuiLeTour", this.joueurTour);
        obj.add("partieEnCours", this.partieEnMarche);
        
        Iterator<Joueur> j = joueurList.iterator();
        int numJoueur = 1;
        while ( j.hasNext() ) {
            obj.add("Joueur #" + numJoueur, j.next().toJSON());
            ++numJoueur;
        }
        
        return obj.build();
    }
    
    /**
     * Permet de savoir si la partie est finie
     * @return true si la partie est fini, autrement dit si un joueur à gagné
     *         false sinon
     */
    public boolean partieFini() {
        return getJoueurGagnant() == -1; // tout les joueurs ont perdu sauf 1
    }
    
    /**
     * Permet d'avoir l'id du joueur ayant gagné
     * @return l'id du joueur gagnant
     *         -1 si aucun joueur n'a gagné
     */
    public int getJoueurGagnant() {
        for ( int i = 0; i < joueurList.size(); i++ )
            if ( !joueurList.get(i).aPerdu() )
                return i;
        
        return -1;
    }
    
    /**
     * Permet au joueur idJoueur de piocher
     * @param idJoueur id du joueur voulant piocher
     * @return une liste de Result, PiocheResult en cas de succes de la pioche
     *         un RefusedResult sinon
     */
    public List<Result> piocherCartes(int idJoueur) {
        List<Result> res = new ArrayList<>();
        if ( idJoueur != aQuiLeTour() ) {
            res.add(new RefusedResult("Ce n'est pas le tour de ce joueur."));
            return res;
        }
        
        joueurList.get(idJoueur).piocher();
        throw new UnsupportedOperationException("Not implemented");
    }
    
    /**
     * Permet d'attaquer un perso présent sur le deck
     * @param idJoueur id du joueur effectuant l'action
     * @param idAttaqueur position relative sur le terrain de jeu de la carte attaquante
     * @param idReceveur position relative sur le terrain de jeu de la carte recevant l'attaque
     * @return un AttackResult si tout c'est bien passé
     *         un refusedResult sinon
     */
    public Result attaquePerso(int idJoueur, int idAttaqueur, int idReceveur) {
        return joueurList.get(idJoueur).attaque(idAttaqueur, idReceveur);
    }
    
    /**
     * Permet d'attaquer un joueur à partie de l'API
     * @param idJoueur id relatif du joueur lancant l'attaque
     * @param idAttaquer id relatif du joueur qui va se faire attaquer
     * @return un attackResult si tout est ok
     *         un refusedResult sinon
     */
    public Result attaqueJoueur(int idJoueur, int idAttaquer ) {
        return joueurList.get(idJoueur).attaque(idJoueur, joueurList.get(idAttaquer));
    }
    
    /**
     * Permet d'ajouter une liste d'enchant sur un joueur
     * @param idJoueur id du joueur qui va recevoir les enchants
     * @param carteTouche La carte qui va recevoir les enchants
     * @param enchant listes des positions relatives dans le deck des cartes d'enchantements à appliquer
     * @return un list Result, chaqu'un contenant un EnchantResult si l'enchant fonctionne
     *                         un refused Result sinon
     */
    public List<Result> ajouterEnchantements(int idJoueur, int carteTouche, List<Integer> enchant) {
        return joueurList.get(idJoueur).ajouterEnchants(enchant, carteTouche);
    }
    
    /**
     * Permet de placer un personnage 
     * @param idJoueur id relatif du joueur dans la liste Joueur List
     * @param personnage position relative dans la main du joueur à placer
     * @param arme position relative dans la main de l'arme a placer
     * @return un PersoDeploieResult si tout ce passe bien
     *         un refusedResult sinon
     */
    public Result placerPerso(int idJoueur, int personnage, int arme) {
        return joueurList.get(idJoueur).placerPerso(personnage, arme);
    }
    
    /**
     * Permet de défausser n Carte à partie de l'api
     * @param idJoueur id du joueur souhaitant se défausser
     * @param defausse Liste des positions relatives dans la main des cartes à defausser
     * @return un defausseResult si tout ce passe bien
     *         un refusedResult en cas d'erreur
     */
    public Result defausserCartes(int idJoueur, List<Integer> defausse) {
        return joueurList.get(idJoueur).defausserCartes(defausse);
    }
    
    /**
     * Permet de soigner un personnage du jeu
     * @param idJoueur id du joueur qui effectue l'action
     * @param soigneur id relatif de la position de la carte sur le terrain de jeu
     * @param soignee id relatif de la position de la carte sur le terrain de jeu ( carte à soigner)
     * @return un SoinsResult si tout c'est bien passé
     *         un RefusedResult sinon
     */
    public Result soignerPerso(int idJoueur, int soigneur, int soignee) {
        return joueurList.get(idJoueur).soignerPerso(soigneur, soignee);
    }
    
    /**
     * Permet de liberer les informations de jeu et reinialisé les valeurs de l'api
     */
    public void finPartie() {
        joueurList.clear();
        joueurTour = 0;
        partieEnMarche = false;
    }    
}

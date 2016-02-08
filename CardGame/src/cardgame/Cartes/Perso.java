/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Cartes;

import cardGame.API.Resultat;
import cardgame.ResultUtils.AttaqueResult;
import cardgame.ResultUtils.RefuseResult;
import cardgame.ResultUtils.SoinsResult;
import cardgame.Regles.TypeArme;
import cardgame.Regles.TypePerso;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author kaldoran
 */
public class Perso extends Carte {
    private int hp;
    private final int maxHp;
    private int mp;
    private Arme armePerso;
    private final TypePerso typeperso;
    
    public Perso(int _hp, int _mp, TypePerso type) {
        super();
        hp = _hp;
        mp = _mp;
        maxHp = hp;
        typeperso = type;
        armePerso = null;
    }
    
    /**
     * Permet d'avoir la force d'attaque de l'arme ( sans triangle de modificateur de degat)
     * @return force de l'arme si présente
     *         0 Sinon
     */
    public int forceAttaque() {
        return armePerso != null ? armePerso.forceAttaque(TypeArme.Neutre) : 0;
    }
    
    /** 
     * Permet d'avoir le force d'attaque de l'arme portée en appliquant le triangle de modification de degat
     * @param ennemi Perso ennemi contre qui l'arme sera utilisée
     * @return la force de l'arme en ajoutant le modificateur de degat si le perso à une arme
     *         0 sinon
     */
    public int forceAttaque(Perso ennemi) {
        return armePerso != null ? armePerso.forceAttaque(ennemi.getTypeArme()) : 0;
    }
    
    /**
     * Permet d'appliquer les dégats au perso.
     * @param dommage Quantitée de dégats pris
     * @param attaqueur id de l'attaquant
     * @return un AttackResult
     */
    public AttaqueResult prendreDommage(int dommage,int attaqueur) {
        
        this.hp -= dommage;
        
        AttaqueResult ar = new AttaqueResult(dommage,false,this.cardID,attaqueur,estMort()); 
            
        return ar;
    }
    
    /**
     * Permet d'obtenir la liste des cartes qui était associée au personnage
     * @return Liste des cartes présente sur le perso
     */
    public List<Carte> libererCartes() {
        List<Carte> cartesMortes = new ArrayList<>();
        cartesMortes.addAll(armePerso.listEnchant);
        cartesMortes.addAll(armePerso.listEnchantStase);
        cartesMortes.add(armePerso);
        cartesMortes.add(this);
        return cartesMortes;
    }
    
    /**
     * Permet d'ajouter un enchant à l'arme du perso
     * @param ench Enchantement à appliquer
     * @return un EnchantResult si l'enchant fonctionne
     *         un RefusedResult sinon
     */
    public Resultat ajouterEnchant(Enchant ench) {
        return armePerso.ajouterEnchant(ench);
    }
    
    /**
     * Permet de placer une arme sur le perso
     * @param arme arme à donner au perso
     * @return true si l'arme est placée
     *         false sinon
     */
    public boolean placerArme(Arme arme) {
        boolean armeLibre = false;
        if  (this.armePerso == null && arme.peutUtiliserArme(this.typeperso)){
            this.armePerso = arme;
            armeLibre = true;
        }
        return armeLibre;
    }
    
    /**
     * Permet au perso de soigné un allié
     * @param allie Personnage allié à soigner
     * @return un SoinsResult si le soin à été fait
     *         un RefusedResult sinon
     */
    public Resultat soigner(Perso allie) {
        
        Resultat resultat;
        
        if ( this.mp > 0 && (this.getCardID() != allie.getCardID()) ) { /// tester que vie allie != Max ?
            --this.mp;
            allie.recevoirSoins();
            resultat = new SoinsResult(true,this.cardID,allie.cardID);
            // return good Result
        }
        else {
            resultat = new RefuseResult("Le soigneur n'a plus de magie.");
        }
        return resultat;
    }
    
    /**
     * Permet au personnage de recevoir le soin (autrement dit réinit ses points de vie)
     */
    private void recevoirSoins() {
        this.hp = this.maxHp;
    }
    
    /**
     * Permet de savoir si le personnage est mort
     * @return true si le personnage est mort
     *         false sinon
     */
    public boolean estMort() {
        return this.hp <= 0;
    }
    
    /**
     * Permet d'obtenir le type d'arme utilisée par le perso
     * @return le type de l'arme si une est équipée
     *         null sinon
     */
    public TypeArme getTypeArme() {
        return armePerso != null? armePerso.type: null;
    }
    
    /**
     * Permet d'obtenir le jSon associé au personnage
     * @return le JSon représentant le perso
     */
    @Override
    public JsonObject toJSON() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Id",this.cardID);
        obj.add("Type Personnage", typeperso.toString());
        obj.add("hp",hp );
        obj.add("mp",mp);
        if (armePerso != null)
            obj.add("Arme personnage",armePerso.toJSON());
        
        return obj.build();    
    }
    
    /** 
     * Permet de crée un JSon de test
     * @return le JSon représentant le perso lors de tests
     */
    @Override
    public JsonObject toJSONTest() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Type Personnage", typeperso.toString());
        obj.add("hp",hp );
        obj.add("mp",mp);
        if (armePerso != null)
            obj.add("Arme personnage",armePerso.toJSONTest());
        
        return obj.build();    
    }
    
}

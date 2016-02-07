/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.ResultUtils.AttackResult;
import cardgame.ResultUtils.RefusedResult;
import cardgame.ResultUtils.SoinsResult;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author kaldoran
 */
public class Perso extends Card {
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
    
    
    public int forceAttaque() {
        return armePerso != null ? armePerso.forceAttaque(TypeArme.Neutre) : 0;
    }
    
    public int forceAttaque(Perso ennemi) {
        return armePerso != null ? armePerso.forceAttaque(ennemi.getTypeArme()) : 0;
    }
    
    public AttackResult prendreDommage(int dommage,int attaqueur) {
        
        this.hp -= dommage;
        
        AttackResult ar = new AttackResult(dommage,false,this.cardID,attaqueur,estMort()); 
            
        return ar;
    }
    
    public List<Card> libererCartes() {
        List<Card> cartesMortes = new ArrayList<>();
        cartesMortes.addAll(armePerso.listEnchant);
        cartesMortes.addAll(armePerso.listEnchantStase);
        cartesMortes.add(armePerso);
        cartesMortes.add(this);
        return cartesMortes;
    }
    
    public Result ajouterEnchant(Enchant ench) {
        return armePerso.ajouterEnchant(ench);
    }
    
    public boolean placerArme(Arme arme) {
        boolean armeLibre = false;
        if  (this.armePerso == null && arme.peutUtiliserArme(this.typeperso)){
            this.armePerso = arme;
            armeLibre = true;
        }
        return armeLibre;
    }
    
    public Result soigner(Perso allie) {
        
        Result resultat;
        
        if ( this.mp > 0 && (this.getCardID() != allie.getCardID()) ) { /// tester que vie allie != Max ?
            --this.mp;
            allie.recevoirSoins();
            resultat = new SoinsResult(true,this.cardID,allie.cardID);
            // return good Result
        }
        else {
            resultat = new RefusedResult("Le soigneur n'a plus de magie.");
        }
        return resultat;
    }
    
    private void recevoirSoins() {
        this.hp = this.maxHp;
    }
    
    public boolean estMort() {
        return this.hp <= 0;
    }
    
    public TypeArme getTypeArme() {
        return armePerso != null? armePerso.type: null;
    }
    
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

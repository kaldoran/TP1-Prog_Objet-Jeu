/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.ResultUtils.AttackResult;
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
        return armePerso.forceAttaque(TypeArme.Neutre);
    }
    
    public int forceAttaque(Perso ennemi) {
        return forceAttaque() + armePerso.forceAttaque(ennemi.getTypeArme());
    }
    
    public AttackResult prendreDommage(int dommage) {
        AttackResult ar = new AttackResult(); 
        
        this.hp -= dommage;
        
        ar.attaqueATuer = estMort();
        ar.attaqueJoueur = false;
        ar.idCarte = this.getCardID();
        ar.dommageRecu = dommage;
            
        return ar;
    }
    
    public Result ajouterEnchant(Enchant ench) {
        return armePerso.ajouterEnchant(ench);
    }
    
    public void placerArme(Arme arme) {
        this.armePerso = arme;
    }
    
    public Result soigner(Perso allie) {
        
        if ( this.mp > 0 && !this.equals(allie) ) { /// tester que vie allie != Max ?
            --this.mp;
            allie.recevoirSoins();
            
            // return good Result
        }
        
        // return bad result
        
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public void recevoirSoins() {
        this.hp = this.maxHp;
    }
    
    public boolean estMort() {
        return this.hp <= 0;
    }
    
    public TypeArme getTypeArme() {
        return armePerso.type;
    }
    


    @Override
    public JsonObject toJSON() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Id",this.cardID);
        obj.add("hp",hp );
        obj.add("mp",mp);
        
        return obj.build();    
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.ResultUtils.AttackResult;

/**
 *
 * @author kaldoran
 */
public class Perso extends Card {
    private int hp;
    private int maxHp;
    private int mp;
    private Arme armePerso;
    private TypePerso typeperso;
    
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
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public void placerArme(Arme arme) {
        this.armePerso = arme;
    }
    
    public Result soigner(Perso allie) {
        /*
        Result r = null;
        
        if ( this.mp > 0 && !this.equals(allie) ) { /// tester que vie allie != Max ?
            --this.mp;
            allie.recevoirSoins();
        }
        
        return r;
        */
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
    
    public String toJSon() {
        throw new UnsupportedOperationException("Not implemented");
    }
}

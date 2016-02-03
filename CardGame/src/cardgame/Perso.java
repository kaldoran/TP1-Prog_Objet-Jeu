/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

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
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public int forceAttaque(Perso ennemi) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public AttackResult prendreDommage(int dommage) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result ajouterEnchant(Echant ench) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public void placerArme(Arme arme) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public Result soigner(Perso allie) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public void recevoirSoins() {
        this.hp = this.maxHp;
    }
    
    public boolean estMort() {
        return this.hp == 0;
    }
    
    public TypeArme getTypeArme() {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public String toJSon() {
        throw new UnsupportedOperationException("Not implemented");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Init;

import cardgame.Perso;
import cardgame.Regle;
import cardgame.TypePerso;

/**
 *
 * @author kaldoran
 */
public class PretreBuilder implements PersoBuilder {
    
    private int hp;
    private int mp;
    private final TypePerso tp;
    
    public PretreBuilder() {
        hp = Regle.PRETREHP;
        mp= Regle.PRETREMP;
        tp = TypePerso.Mage;
    }
    
     @Override
    public Perso buildNewPerso() {
        Perso personage = new Perso(hp,mp,tp);
        return personage;
    }

    @Override
    public void setHP(int vie) {
        hp = vie;
    }

    @Override
    public void setMP(int magie) {
        mp = magie;
    }
}

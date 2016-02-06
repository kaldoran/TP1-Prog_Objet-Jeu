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
 * @author mathieu
 */
public class PaladinBuilder implements PersoBuilder {

    private int hp;
    private int mp;
    private final TypePerso tp;
    
    public PaladinBuilder() {
        hp = Regle.PALADINHP;
        mp= Regle.PALADINMP;
        tp = TypePerso.Paladin;
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

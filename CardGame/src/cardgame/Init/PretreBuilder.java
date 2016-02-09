/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Init;

import cardgame.Cartes.Perso;
import cardgame.Regles.Regle;
import cardgame.Regles.TypePerso;

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
    
    /**
     * Permet d'instancier un nouveau perso Pretre
     * @return un nouveau personnage
     */
    @Override
    public Perso buildNewPerso() {
        Perso personage = new Perso(hp,mp,tp);
        return personage;
    }

    /** 
     * Permet de modifier la vie du Pretre
     * @param vie nouveau point de vie
     */
    @Override
    public void setHP(int vie) {
        hp = vie;
    }
    
    /**
     * Permet de modifier les points de magie du Pretre
     * @param magie nouveau point de magie
     */
    @Override
    public void setMP(int magie) {
        mp = magie;
    }
}

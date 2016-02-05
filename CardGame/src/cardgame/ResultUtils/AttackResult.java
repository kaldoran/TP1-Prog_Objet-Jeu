/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.ResultUtils;

import cardgame.Result;

/**
 *
 * @author kaldoran
 */
public class AttackResult implements Result {
    public int dommageRecu;
    public int attaqueur;
    public boolean attaqueJoueur;
    public int idCarte;
    public boolean attaqueATuer; 
    
    public int getDmgEffectue() {
        return dommageRecu;
    }
    
    public boolean attaqueTue() {
        return attaqueATuer;
    }

    @Override
    public boolean coupAMarcher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int coupJouerPar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

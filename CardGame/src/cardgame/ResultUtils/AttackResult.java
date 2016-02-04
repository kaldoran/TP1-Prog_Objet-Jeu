/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.ResultUtils;

/**
 *
 * @author kaldoran
 */
public class AttackResult {
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
}

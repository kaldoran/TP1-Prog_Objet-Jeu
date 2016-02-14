/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.JeuxCartes;

import cardgame.ResultUtils.SoinsResult;

/**
 *
 * @author mathieu
 */
public interface Soigneur {
    
   public abstract SoinsResult soigner (Perso p);
   
   public abstract boolean peutSoigner(Perso p);
   
}

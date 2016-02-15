/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.JeuxCartes;

import cardgame.ResultUtils.Resultat;

/**
 *
 * @author mathieu
 */
public interface Cible {
    
    public abstract boolean peutEtreAttaque();
    
    
    public abstract Resultat recoitAttaque(Combattant attaqueur);
    
    public abstract boolean estMort();
        
}

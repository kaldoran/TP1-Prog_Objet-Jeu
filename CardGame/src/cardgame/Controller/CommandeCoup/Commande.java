/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Controller.CommandeCoup;

import cardgame.ResultUtils.Resultat;

/**
 *
 * @author mathieu
 */
public interface Commande {
    
    public abstract Boolean coupPossible();
    
    public abstract Boolean coupFinitTour();
    
    public abstract Resultat placerCoup();
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Controller;

import cardgame.Controller.CommandeCoup.Commande;
import javax.swing.JFrame;

/**
 *
 * @author mathieu
 */
public interface Controller {
        
    public abstract void faireAction(Commande cmd);
    
    
    
}

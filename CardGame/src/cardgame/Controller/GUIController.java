/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Controller;

import cardgame.CommandeCoup.Commande;
import cardgame.ResultUtils.Resultat;
import javax.swing.JFrame;

/**
 *
 * @author mathieu
 */
public class GUIController implements Controller {

    @Override
    public void faireAction(Commande cmd, JFrame board) {

        
        if (cmd.coupPossible()) {
            Resultat res = cmd.placerCoup();
            //...
        }
        
    }
    
}

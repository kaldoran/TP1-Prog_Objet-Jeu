/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Controller;

import cardgame.API.Jeux;
import cardgame.Controller.CommandeCoup.Commande;
import cardgame.ResultUtils.Resultat;
import cardgame.Vue.GUI.GameBoard2Joueurs;

/**
 *
 * @author mathieu
 */
public class GUIController implements Controller {

    private final GameBoard2Joueurs vue;
    private Jeux api;
    
    public GUIController(GameBoard2Joueurs board){
        vue = board;
    }
    
    public void setAPI(Jeux jeu){
        api = jeu;
    }
    
    
    @Override
    public void faireAction(Commande cmd) {

        if (cmd.coupPossible()) {
            Resultat res = cmd.placerCoup();
            vue.ajouterLog(res.getDescription());
            vue.RefreshBoard();
        } else {
            vue.ajouterLog("Coup refusé");
        }
        vue.resetChoices();
        vue.verifierFinPartie();
    }
}

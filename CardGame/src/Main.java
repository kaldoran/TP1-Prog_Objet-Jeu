
import cardgame.API.Jeux;
import cardgame.Controller.Controller;
import cardgame.Controller.GUIController;
import cardgame.Vue.GUI.DialogNouvelPartie;
import cardgame.Vue.GUI.GameBoard;
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mathieu
 */
public class Main {

    public static void main(String args[]) throws Exception {
        
        DialogNouvelPartie diag = new DialogNouvelPartie(null, true);
        diag.setVisible(true);
        if (!diag.startClicked){
            return;
        }
        int nbPlayers = diag.nbPlayers;
        Jeux api = new Jeux();
        api.demarrerPartie(nbPlayers);
        GameBoard board = new GameBoard(api);
        Controller cont = new GUIController(board);
        board.setController(cont);
        board.setVisible(true);
        
        
    }

}

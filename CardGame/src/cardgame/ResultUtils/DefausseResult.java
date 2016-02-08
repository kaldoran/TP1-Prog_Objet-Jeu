/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.ResultUtils;

import cardgame.Cartes.Carte;
import cardGame.API.Resultat;
import java.util.List;

/**
 *
 * @author kaldoran
 */
public class DefausseResult implements Resultat {

    List<Carte> cartesDefausses;
    private int joueurId;
    private final String description;
    private final boolean coupAFonctionne;
    
    
    public DefausseResult(int idJoueur,boolean coupCorrect,List<Carte> cartes ) {
        cartesDefausses = cartes;
        joueurId = idJoueur;
        description = "Défaussage de cartes";
        coupAFonctionne = coupCorrect;
    }
    
    @Override
    public boolean coupAMarcher() {
        return coupAFonctionne;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int coupJouerPar() {
        return joueurId;
    }

    @Override
    public void setJoueur(int idJoueur) {
        joueurId  = idJoueur;
    }
    
}

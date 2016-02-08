/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.ResultUtils;

import cardGame.API.Resultat;

/**
 *
 * @author kaldoran
 */
public class FinDePartieResult implements Resultat {

    private final String description;
    private int joueurId;
    private final boolean coupAFonctionne;
    private final int joueurGagne;
    
    public FinDePartieResult(int jId, boolean coupCorrect, int idJoueurGagne) {
        joueurId = jId;
        coupAFonctionne = coupCorrect;
        joueurGagne = idJoueurGagne;
        description = "Le joueur" + idJoueurGagne + "vient de gagner la partie";
    }
    
    
       @Override
    public boolean coupAMarcher() {
        return coupAFonctionne;
    }

    @Override
    public String getDescription() {
        return description;
    }
    
    public int getJoueurQuiAGagne() {
        return joueurGagne;
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

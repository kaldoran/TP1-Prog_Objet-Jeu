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
public class RefuseResult implements Resultat {

     private final String description;
    private int joueurId;
    
    public RefuseResult(int idJoueur, String coupRefuse) {
        description = coupRefuse;
        joueurId = idJoueur;
    }
    
    public RefuseResult(String coupRefuse) {
        description = coupRefuse;
    }
    
       @Override
    public boolean coupAMarcher() {
        return false;
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

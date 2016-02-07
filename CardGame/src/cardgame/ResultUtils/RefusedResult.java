/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.ResultUtils;

import cardgame.Result;

/**
 *
 * @author kaldoran
 */
public class RefusedResult implements Result {

     private final String description;
    private int joueurId;
    
    public RefusedResult(int idJoueur, String coupRefuse) {
        description = coupRefuse;
        joueurId = idJoueur;
    }
    
    public RefusedResult(String coupRefuse) {
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

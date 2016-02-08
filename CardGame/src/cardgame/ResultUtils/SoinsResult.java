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
public class SoinsResult implements Resultat {

    private final String description;
    private int joueurId;
    private final boolean coupAFonctionne;
    private final int healerId;
    private final int persoSoigneeId;
    
    public SoinsResult(int jId,boolean coupCorrect, int hId, int cId) {
        description = "Coup de soins";
        joueurId = jId;
        coupAFonctionne = coupCorrect;
        healerId = hId;
        persoSoigneeId = cId;
    }
    
        public SoinsResult(boolean coupCorrect, int hId, int cId) {
        description = "Coup de soins";
        coupAFonctionne = coupCorrect;
        healerId = hId;
        persoSoigneeId = cId;
    }
        
        
    public int getHealerId(){
        return healerId;
    }
    
    public int getCarteSoigneeId(){
        return persoSoigneeId;
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

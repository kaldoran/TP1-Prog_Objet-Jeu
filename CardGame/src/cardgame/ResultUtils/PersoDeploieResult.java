/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.ResultUtils;

import cardgame.Resultat;

/**
 *
 * @author kaldoran
 */
public class PersoDeploieResult implements Resultat {

    private final String description;
    private int joueurId;
    private final boolean coupAFonctionne;
    private final int persoId;
    private final int armeId;
    
    public PersoDeploieResult(int jId,boolean coupCorrect,int pId,int aId){
        joueurId = jId;
        coupAFonctionne = coupCorrect;
        persoId = pId;
        armeId = aId;
        description = "Le joueur " + jId + "vient de déployer les cartes " + pId + " + " + aId;
    }
    
    public int getPersoId() {
        return joueurId;
    }
    
    public int getArmeId() {
        return armeId;
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

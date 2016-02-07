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
public class AttackResult implements Result {
    private final int dommageRecu;
    private int attaqueur;
    private final boolean attaqueJoueur;
    private final int idCarte;
    private final int idCarteAttack;
    private final boolean attaqueTuer; 
    private final String desc;
    
    public AttackResult(int dmg, int joueurId,boolean joueurAttaque,int carteId,int persoCoupId,boolean attaqueTue){
        dommageRecu = dmg;
        attaqueur = joueurId;
        attaqueJoueur = joueurAttaque;
        idCarte = carteId;
        attaqueTuer = attaqueTue;
        idCarteAttack = persoCoupId;
        desc = "Attaque d'un personnage";
    }
    
        public AttackResult(int dmg,boolean joueurAttaque,int carteId,int persoCoupId,boolean attaqueTue){
        dommageRecu = dmg;
        attaqueJoueur = joueurAttaque;
        idCarte = carteId;
        attaqueTuer = attaqueTue;
        idCarteAttack = persoCoupId;
        desc = "Attaque d'un personnage";
    }
    
    public int getDmgEffectue() {
        return dommageRecu;
    }
    
    @Override
    public boolean coupAMarcher() {
        return dommageRecu > 0;
    }

    @Override
    public String getDescription() {
        return desc;
    }

    @Override
    public int coupJouerPar() {
        return attaqueur;
    }
    
    public int getDommage(){
        return dommageRecu;
    }
    
    public int getAttaqueurPerso() {
        return idCarte;
    }
    
    public int getPersonneAttaque() {
        return idCarteAttack;
    }
    
    public boolean attaqueJoueur() {
        return attaqueJoueur;
    }
    
    public boolean attaqueATuer(){
        return attaqueTuer;
    }

    @Override
    public void setJoueur(int joueurId) {
        attaqueur = joueurId;
    }
}

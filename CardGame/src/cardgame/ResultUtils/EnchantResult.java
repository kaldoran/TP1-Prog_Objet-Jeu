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
public class EnchantResult implements Resultat {

    private final String description;
    private int joueurId;
    private final boolean coupAFonctionne;
    private final int enchantId;
    private final int carteEnchante;
    
    public EnchantResult(boolean coupCorrect, int enchId,int carteId) {
        coupAFonctionne = coupCorrect;
        enchantId = enchId;
        carteEnchante = carteId;
        description = "Enchantement d'une carte";
    }
    
    public EnchantResult(int jId, boolean coupCorrect, int enchId,int carteId) {
        joueurId = jId;
        coupAFonctionne = coupCorrect;
        enchantId = enchId;
        carteEnchante = carteId;
        description = "Enchantement d'une carte";
    }
    
    public int getCarteEnchantement() {
        return enchantId;
    }
    
    public int getCarteEnchante() {
        return carteEnchante;
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

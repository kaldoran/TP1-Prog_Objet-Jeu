/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Init;

import cardgame.Cartes.Perso;

/**
 *
 * @author kaldoran
 */
public interface PersoBuilder {

    public Perso buildNewPerso();
    
    public void setHP(int vie);
    
    public void setMP(int magie);
        
}

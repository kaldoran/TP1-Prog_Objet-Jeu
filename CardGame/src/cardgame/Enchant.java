/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;


/**
 *
 * @author kaldoran
 */
public abstract class Enchant extends Card {
    private String description;
    
    public abstract void placerEnchant(Arme arme);
    
    public String toJSon() {
        throw new UnsupportedOperationException("Not implemented");
    }
}

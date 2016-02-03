/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.List;

/**
 *
 * @author kaldoran
 */
public class Arme extends Card {
    protected TypeArme type;
    protected boolean estStast;
    protected int degat; 
    protected List<Enchant> listEnchant;
    protected List<TypePerso> listUtilisateurs;
    private int degatOrg;
    private TypeArme typeOrg;
    private List<TypePerso> listUtilisateursOrg;
    
    public int forceAttaque() {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public boolean peutUtiliserArme(TypePerso perso) {
        return listUtilisateurs.contains(perso);
    }
    
    public Result ajouterEnchant(Enchant ench) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    private void reset() {
        this.listUtilisateurs = this.listUtilisateursOrg;
        this.degat = this.degatOrg;
        this.type = this.typeOrg;
    }
    
    public String toJSon() {
        throw new UnsupportedOperationException("Not implemented");
    }
    
}

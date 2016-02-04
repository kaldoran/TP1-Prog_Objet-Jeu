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
    protected boolean estStase;
    protected int degat; 
    protected List<Enchant> listEnchant;
    protected List<TypePerso> listUtilisateurs;
    private int degatOrg;
    private TypeArme typeOrg;
    private List<TypePerso> listUtilisateursOrg;
    
    public int forceAttaque(TypeArme arme) {
        return degat + this.type.calculModificateur(arme); 
    }
    
    public boolean peutUtiliserArme(TypePerso perso) {
        return listUtilisateurs.contains(perso);
    }
    
    public Result ajouterEnchant(Enchant ench) {
        if ( !this.estStase ) {
            listEnchant.add(ench);
            ench.placerEnchant(this);
        }
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public void reset() {
        this.listEnchant = null;
        this.listUtilisateurs = this.listUtilisateursOrg;
        this.degat = this.degatOrg;
        this.type = this.typeOrg;
    }
    
    public String toJSon() {
        // Fast Implementaton
        // return "{typeArme:" + type.name() + ", degat:" + this.degat + "}";
        
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public int getDegat() {
        return this.degat;
    }
    
    public void setDegat(int degat) {
        this.degat = degat;
    }
    
    public void setType(TypeArme type) {
        this.type = type;
    }
    
    public void setStase(boolean stase) {
        this.estStase = stase;
    }
    
    public void setListUtilisateurs(List<TypePerso> listUtilisateurs) {
        this.listUtilisateurs = listUtilisateurs;
    }    
}

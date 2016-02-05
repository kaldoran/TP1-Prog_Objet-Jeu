/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
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
    private final int degatOrg;
    private final TypeArme typeOrg;
    private final List<TypePerso> listUtilisateursOrg;
    
    public Arme(TypeArme _type,int dmg)
    {
        super();
        type = _type;
        typeOrg = _type;
        degat = dmg;
        degatOrg = dmg;
        listUtilisateurs = new ArrayList<>(_type.getUtilisateurs());
        listUtilisateursOrg = new ArrayList<>(_type.getUtilisateurs());
        listEnchant = new ArrayList<>();
        estStase = false;
        
    }
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
    @Override
    public JsonObject toJSON() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Id",this.cardID);
        obj.add("type", type.name() );
        obj.add("degats",degat);
        
        return obj.build();
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

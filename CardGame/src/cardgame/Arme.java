/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.ResultUtils.EnchantResult;
import cardgame.ResultUtils.RefusedResult;
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
    protected List<Enchant> listEnchantStase;
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
        listEnchantStase = new ArrayList<>();
        estStase = false;
        
    }
    public int forceAttaque(TypeArme arme) {
        return degat + this.type.calculModificateur(arme); 
    }
    
    public boolean peutUtiliserArme(TypePerso perso) {
        return listUtilisateurs.contains(perso);
    }
    
    public Result ajouterEnchant(Enchant ench) {
        Result res;
        if ( !this.estStase ) {
            listEnchant.add(ench);
            ench.placerEnchant(this);
            res = new EnchantResult(true,ench.cardID,this.cardID);
        }
        else
        {
            res = new RefusedResult("L'enchantement ne peut pas être appliqué.");
        }
        return res;
    }
    
    public void reset() {
        this.listEnchantStase = new ArrayList<>(this.listEnchant);
        this.listEnchant = null;
        this.listUtilisateurs = this.listUtilisateursOrg;
        this.degat = this.degatOrg;
        this.type = this.typeOrg;
    }
    @Override
    public JsonObject toJSON() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Id",this.cardID);
        obj.add("Type d'arme", type.name());
        obj.add("Degats",degat);
        Iterator<Enchant> it = listEnchant.iterator();
        int enchNum = 1;
        while (it.hasNext()) {
                obj.add("Enchantement actif #" + enchNum, it.next().toJSON());
        }
        enchNum  = 1;
        it = listEnchantStase.iterator();
        while (it.hasNext()) {
                obj.add("Enchantement inactif #" + enchNum, it.next().toJSON());
        }
        
        
        return obj.build();
    }
    
     @Override
    public JsonObject toJSONTest() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Type d'arme", type.name());
        obj.add("Degats",degat);
        Iterator<Enchant> it = listEnchant.iterator();
        int enchNum = 1;
        while (it.hasNext()) {
                obj.add("Enchantement actif #" + enchNum, it.next().toJSONTest());
        }
        enchNum  = 1;
        it = listEnchantStase.iterator();
        while (it.hasNext()) {
                obj.add("Enchantement inactif #" + enchNum, it.next().toJSONTest());
        }
        
        
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
    
    public void staserArme() {
        this.estStase = true;
    }
    
    
    public boolean armeEstStase() {
        return estStase;
    }
    
    public void setListUtilisateurs(List<TypePerso> listUtilisateurs) {
        this.listUtilisateurs = listUtilisateurs;
    }    
}

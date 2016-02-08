/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Cartes;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;


/**
 *
 * @author kaldoran
 */
public abstract class Enchant extends Carte {
    private final String description;
    
    public Enchant(String desc){
        super();
        description = desc;
    }
    
    /**
     * Permet d'avoir la représentation de la carte d'enchant
     * @return le JSon représentant l'enchant de la carte
     */
    @Override
    public JsonObject toJSON() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Id",this.cardID);
        obj.add("Nom",this.getClass().getCanonicalName());
        obj.add("Description",description);
        
        return obj.build();    
    }
    
    /**
     * Permet d'avoir la représentation de la carte lors des tests
     * @return le JSon représentant l'enchant lors des tests
     */
    @Override
    public JsonObject toJSONTest() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Nom",this.getClass().getCanonicalName());
        obj.add("Description",description);
        
        return obj.build();    
    }
    
    /** 
     * Déclaration de la fonction placerEnchant, celle ci n'a aucun effet
     * @param arme Arme sur laquel placer enchant
     */
    public abstract void placerEnchant(Arme arme);
    
   
}

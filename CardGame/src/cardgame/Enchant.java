/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;


/**
 *
 * @author kaldoran
 */
public abstract class Enchant extends Card {
    private final String description;
    
    public Enchant(String desc){
        super();
        description = desc;
    }
    
    @Override
    public JsonObject toJSON() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Id",this.cardID);
        obj.add("Nom",this.getClass().getCanonicalName() );
        obj.add("Description",description);
        
        return obj.build();    
    }
    
    public abstract void placerEnchant(Arme arme);
    
    public String toJSon() {
        throw new UnsupportedOperationException("Not implemented");
    }
}

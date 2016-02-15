/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.JeuxCartes;

import cardgame.Regles.Regle;
import cardgame.Regles.TypeArme;
import cardgame.ResultUtils.Resultat;
import java.util.Arrays;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author mathieu
 */
public class Guerrier extends Combattant {

    public Guerrier() {
        super(Regle.GUERRIERHP, Regle.GUERRIERMP, Arrays.asList(TypeArme.values()));
    }

    
       @Override
    public JsonObject toJSON() {
        JsonObject json = super.toJSON();
        JsonObjectBuilder addition = Json.createObjectBuilder();
        addition.add("Type Personnage", "Guerrier");
        addition.add("General Info", json);
        
        return addition.build();
    }
    

}

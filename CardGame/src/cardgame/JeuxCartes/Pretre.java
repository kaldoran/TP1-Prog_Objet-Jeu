/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.JeuxCartes;

import cardgame.Regles.Regle;
import cardgame.Regles.TypeArme;
import cardgame.ResultUtils.SoinsResult;
import java.util.Arrays;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author mathieu
 */
public class Pretre extends Combattant implements Soigneur {

    public Pretre() {
        super(Regle.PRETREHP, Regle.PRETREMP, Arrays.asList(TypeArme.Contondant,TypeArme.Neutre));
    }

    
        /**
     * Permet au perso de soigner un allié.
     * Pour soigner, le perso a besoin d'avoir encore des points de magie.
     *
     * @param allie Personnage allié à soigner.
     * @return un SoinsResult si le soin à réussi, RefuseResult sinon.
     */
    @Override
    public SoinsResult soigner(Perso allie) {

        SoinsResult resultat;
        //Confirmation qu'on peut soigner et qu'on ne ce soigne pas soi-même.
            this.utiliserMagie();
            allie.recevoirSoins();
            resultat = new SoinsResult(true, this.cardID, allie.cardID);
            // return good Result

        return resultat;
    }

    @Override
    public boolean peutSoigner(Perso p) {
        return this.getMp() > 0 && (this.getCardID() != p.getCardID());
    }
    @Override
    public JsonObject toJSON() {
        JsonObject json = super.toJSON();
        JsonObjectBuilder addition = Json.createObjectBuilder();
        addition.add("Type Personnage", "Pretre");
        addition.add("General Info", json);
        
        return addition.build();
    }
}

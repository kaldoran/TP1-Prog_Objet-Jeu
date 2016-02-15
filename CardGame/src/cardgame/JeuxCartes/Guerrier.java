package cardgame.JeuxCartes;

import cardgame.Regles.*;
import java.util.Arrays;
import javax.json.*;

/**
 * Classe représentant la classe Guerrier du jeu. La classe est une extension de
 * Combattant, ce qui lui permet d'attaquer.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 * 12-Fév-2016 : 1.0 - Version initiale.
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

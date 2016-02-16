package cardgame.JeuxCartes;

import cardgame.Regles.*;
import cardgame.ResultUtils.SoinsResult;
import java.util.Arrays;
import javax.json.*;

/**
 * Classe représentant la classe Paladin du jeu. La classe est une extension de
 * Combattant et implémente soigneur, ce qui lui permet d'attaquer et soigner.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 * 12-Fév-2016 : 1.0 - Version initiale.
 */
public class Paladin extends Combattant implements Soigneur {

    public Paladin() {
        super(Regle.PALADINHP, Regle.PALADINMP, Arrays.asList(TypeArme.values()));
    }

    @Override
    public SoinsResult soigner(Perso allie) {
        SoinsResult resultat;
        this.utiliserMagie();
        allie.recevoirSoins();
        resultat = new SoinsResult(true, this.getCardID(), allie.getCardID());
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
        addition.add("Type Personnage", "Paladin");
        addition.add("General Info", json);
        return addition.build();
    }

}

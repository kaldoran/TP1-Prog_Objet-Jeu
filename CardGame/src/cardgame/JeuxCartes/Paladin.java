/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.JeuxCartes;

import cardgame.Regles.Regle;
import cardgame.Regles.TypeArme;
import cardgame.ResultUtils.Resultat;
import cardgame.ResultUtils.SoinsResult;
import java.util.Arrays;

/**
 *
 * @author mathieu
 */
public class Paladin extends Perso implements Attaquant,Soigneur {

    public Paladin() {
        super(Regle.GUERRIERHP, Regle.GUERRIERMP, Arrays.asList(TypeArme.values()));
    }

    @Override
    public Resultat attaque(Cible c) {
        return c.recoitAttaque(this);

    }
    
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
}

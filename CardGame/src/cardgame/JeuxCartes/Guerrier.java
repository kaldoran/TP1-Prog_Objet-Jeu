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

/**
 *
 * @author mathieu
 */
public class Guerrier extends Perso implements Attaquant {

    public Guerrier() {
        super(Regle.GUERRIERHP, Regle.GUERRIERMP, Arrays.asList(TypeArme.values()));
    }

    @Override
    public Resultat attaque(Cible c) {
        return c.recoitAttaque(this);

    }
}

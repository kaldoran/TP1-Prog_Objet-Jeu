/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.JeuxCartes;

import cardgame.Regles.TypeArme;
import cardgame.ResultUtils.Resultat;
import java.util.List;

/**
 *
 * @author mathieu
 */
public abstract class Combattant extends Perso {

    public Combattant(int _hp, int _mp, List<TypeArme> armes) {
        super(_hp, _mp, armes);
    }
    

    public int forceAttaque(TypeArme ta) {
        return this.getArme().forceAttaque(ta);
    }
    
    public Resultat Attaque(Cible c) {
        return c.recoitAttaque(this);
    }
     
}

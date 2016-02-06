/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Init;

import cardgame.Arme;
import cardgame.TypeArme;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kaldoran
 */
public class ArmeFactory {
    
    
    public List<Arme> creerSetArmes(int nbCopies,int degats) {
        List<Arme> armes = new ArrayList<>();
        
        for (int copieAct = 0; copieAct < nbCopies; copieAct++) {
            armes.add(new Arme(TypeArme.Contondant,degats));
            armes.add(new Arme(TypeArme.Perforant,degats));
            armes.add(new Arme(TypeArme.Tranchant,degats));
        }
        
        
        return armes;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Init;

import cardgame.Cartes.Arme;
import cardgame.Regles.TypeArme;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kaldoran
 */
public class ArmeFactory {
    
    /**
     * permet de crée une liste d'arme
     * @param nbCopies nombre de copie de l'arme
     * @param degats nombre de dégat que fera l'arme
     * @return une liste de nbCopie élements contenant les armes demandées.
     */
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

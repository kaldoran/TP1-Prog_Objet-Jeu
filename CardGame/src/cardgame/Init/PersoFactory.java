/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Init;

import cardgame.Cartes.Perso;
import cardgame.Regles.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe basé sur le patron Factory utilisé pour instancier des cartes personnages.
 * La classe est écrite de telle manière que tout ajouts de nouveaus personnages dans le
 * jeu a seulement besoin d'être ajouté comme fonction dans cette classe ainsi que
 * d'inscrire ces règles dans la classe Règle.
 * 
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reymaud REYN23119308
 * @version 1.0
 * Historique :
 * 8 Fév-2016 : 1.0 Version initiale.
 */
public class PersoFactory {

    /**
     * Retourne une liste de cartes Guerriers
     *
     * @param nbCopies nombre de copie de cartes guerriers
     * @return une liste de nbCopie élements contenant les guerriers demandées.
     */
    public List<Perso> creerSetGuerrier(int nbCopies) {
        List<Perso> guerriers = new ArrayList<>();

        for (int copieAct = 0; copieAct < nbCopies; copieAct++) {
            guerriers.add(new Perso(Regle.GUERRIERHP, Regle.GUERRIERMP, TypePerso.Guerrier));
        }

        return guerriers;
    }

    /**
     * Retourne une liste de cartes Prêtres.
     *
     * @param nbCopies nombre de copie de cartes prêtres.
     * @return une liste de nbCopie élements contenant les prêtres demandées.
     */
    public List<Perso> creerSetPretre(int nbCopies) {
        List<Perso> pretres = new ArrayList<>();

        for (int copieAct = 0; copieAct < nbCopies; copieAct++) {
            pretres.add(new Perso(Regle.PRETREHP, Regle.PRETREHP, TypePerso.Mage));
        }

        return pretres;
    }

    /**
     * Retourne une liste de cartes Paladins.
     *
     * @param nbCopies nombre de copie de cartes paladins.
     * @return une liste de nbCopie élements contenant les paladins demandées.
     */
    public List<Perso> creerSetPaladin(int nbCopies) {
        List<Perso> paladins = new ArrayList<>();

        for (int copieAct = 0; copieAct < nbCopies; copieAct++) {
            paladins.add(new Perso(Regle.PALADINHP, Regle.PALADINMP, TypePerso.Paladin));
        }

        return paladins;
    }

}

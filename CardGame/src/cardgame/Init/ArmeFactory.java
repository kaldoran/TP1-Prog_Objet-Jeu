package cardgame.Init;

import cardgame.JeuxCartes.Arme;
import cardgame.Regles.TypeArme;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilisé pour instancier correctement les cartes d'armes dans une
 * partie. ArmeFactory, classe basé sur le patron Factory, nous permet de
 * découpler et cacher la logique de création des cartes des classes reliés à
 * l'API.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class ArmeFactory {

    /**
     * Permet de créer une liste d'arme
     *
     * @param nbCopies Nombre d ecopies de chacunes des types d'armes à
     * instancier.
     * @param degats Nombre de dégats fait par ces armes.
     * @return Liste de nbCopie élements contenant les armes demandées.
     */
    public List<Arme> creerSetArmes(int nbCopies, int degats) {
        List<Arme> armes = new ArrayList<>();

        for (int copieAct = 0; copieAct < nbCopies; copieAct++) {
            armes.add(new Arme(TypeArme.Contondant, degats));
            armes.add(new Arme(TypeArme.Perforant, degats));
            armes.add(new Arme(TypeArme.Tranchant, degats));
        }

        return armes;
    }

}

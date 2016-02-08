package cardgame.Init;

import cardgame.Cartes.EnchantNeutre;
import cardgame.Cartes.EnchantFacile;
import cardgame.Cartes.EnchantStase;
import cardgame.Cartes.EnchantDegatMoins;
import cardgame.Cartes.EnchantDegatPlus;
import cardgame.Cartes.Enchant;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilisé pour instancier correctement les cartes d'enchantements dans
 * une partie. EnchantFactory, classe basé sur le patron Factory, nous permet de
 * découpler et cacher la logique de création des cartes de l'API.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reymaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class EnchantFactory {

    /**
     * Permet de créer un ensemble de tout les types d'enchantements du jeu.
     *
     * @param nbCopies nombre de copie des cartes Enchants à instancier.
     * @return Une liste de nbCopie élements qui contient tout les enchants.
     */
    public List<Enchant> creerSetEnchants(int nbCopies) {
        List<Enchant> enchantements = new ArrayList<>();

        for (int copieAct = 0; copieAct < nbCopies; copieAct++) {
            enchantements.add(new EnchantNeutre());
            enchantements.add(new EnchantStase());
            enchantements.add(new EnchantDegatPlus());
            enchantements.add(new EnchantDegatMoins());
            enchantements.add(new EnchantFacile());
        }

        return enchantements;
    }
}

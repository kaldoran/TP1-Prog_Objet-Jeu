package cardgame.JeuxCartes;

import javax.json.JsonObject;

/**
 * Classe abstraite, Carte sert d'interface commun pour tout les types de cartes
 * du jeu. (La raison derrière le choix de classe abstraite et non d'interface
 * réside dans l'identifiant unique. Celle-ci nous permet de lier une carte du
 * modèle aux demandes du controlleurs si nécessaire.)
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public abstract class Carte {

    //Int statique utilisé poour s'assurer que chaque carte ait un Id unique.
    static int SSID = 0;
    private final int cardID;

    /**
     * Description des fonctions représentant le JSon des cartes, non définie
     * ici
     *
     * @return null, fonction non définie ici
     */
    public abstract JsonObject toJSON();

    /**
     * Constructeur par défaut. Initialise l'identifiant de la carte.
     */
    public Carte() {
        cardID = SSID;
        ++Carte.SSID;
    }

    /**
     * Permet d'avoir l'id de la carte.
     *
     * @return l'identifiant unique associé à la carte
     */
    public int getCardID() {
        return cardID;
    }
}

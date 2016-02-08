package cardgame.Regles;

/**
 * Classe non instantiable, Regle sert de conteneur pour les valeurs
 * paramétrisables du jeu.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reymaud REYN23119308
 * @version 1.0
 *
 * Historique :
 *
 * -8 Fév-2016 : 1.0 Version initiale.
 */
public class Regle {

    private Regle() {
    }

    /**
     * Liste de toutes les règles de jeu.
     */
    public static final int GUERRIERHP = 5;
    public static final int GUERRIERMP = 0;
    public static final int PRETREHP = 3;
    public static final int PRETREMP = 3;
    public static final int PALADINHP = 4;
    public static final int PALADINMP = 1;
    public static final int CARTEGUERRIER = 4;
    public static final int CARTEPRETRE = 4;
    public static final int CARTEPALADIN = 2;
    public static final int CARTEARMEUN = 2;
    public static final int CARTEARMEDEUX = 2;
    public static final int CARTEENCHANTEMENT = 2;
    public static final int CARTEMAIN = 5;
}

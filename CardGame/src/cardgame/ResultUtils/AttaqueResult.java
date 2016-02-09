package cardgame.ResultUtils;

import cardGame.API.Resultat;

/**
 * Implémentation de Resultat pour décrire les conséquences d'une attaque
 * perso-perso ou perso-joueur.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class AttaqueResult implements Resultat {

    private final int dommageRecu;
    private int attaqueur;
    private final boolean attaqueJoueur;
    private final int idCarte;
    private final int idCarteAttack;
    private final boolean attaqueTuer;
    private final String desc;

    public AttaqueResult(int dmg, int joueurId, boolean joueurAttaque, int carteId, int persoCoupId, boolean attaqueTue) {
        dommageRecu = dmg;
        attaqueur = joueurId;
        attaqueJoueur = joueurAttaque;
        idCarte = carteId;
        attaqueTuer = attaqueTue;
        idCarteAttack = persoCoupId;
        desc = "Attaque d'un personnage";
    }

    public AttaqueResult(int dmg, boolean joueurAttaque, int carteId, int persoCoupId, boolean attaqueTue) {
        dommageRecu = dmg;
        attaqueJoueur = joueurAttaque;
        idCarte = carteId;
        attaqueTuer = attaqueTue;
        idCarteAttack = persoCoupId;
        desc = "Attaque d'un personnage";
    }

    /**
     * Getter
     *
     * @return le dommage reçu par l'attaque.
     */
    public int getDmgEffectue() {
        return dommageRecu;
    }

    /**
     * @return l'attaque a-t-elle fait des dégats réels?
     */
    @Override
    public boolean coupAMarcher() {
        return dommageRecu > 0;
    }

    /**
     * Getter
     *
     * @return Description de ce type de coup.
     */
    @Override
    public String getDescription() {
        return desc;
    }

    /**
     * Getter
     *
     * @return l'identifiant du joueur qui a joué de coup.
     */
    @Override
    public int coupJouerPar() {
        return attaqueur;
    }

    /**
     * Getter
     *
     * @return l'identifiant de la carte qui a attaqué.
     */
    public int getAttaqueurPerso() {
        return idCarte;
    }

    /**
     * Getter
     *
     * @return l'identifiant de la carte qui a reçu l'attaque ou -1 si le joeur
     * a pris le coup.
     *
     */
    public int getPersonneAttaque() {
        return idCarteAttack;
    }

    /**
     * Getter
     * @return True si l'attaque était Perso-Joueur,false sinon.
     */
    public boolean attaqueJoueur() {
        return attaqueJoueur;
    }

    /**
     * Getter
     * @return True si le perso est mort par cette attaque,false sinon.
     */
    public boolean attaqueATuer() {
        return attaqueTuer;
    }

    /**
     * Setter
     * @param joueurId l'identifiant du joueur qui a fait le coup.
     */
    @Override
    public void setJoueur(int joueurId) {
        attaqueur = joueurId;
    }
}

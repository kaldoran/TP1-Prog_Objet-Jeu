package cardgame.ResultUtils;

import cardGame.API.Resultat;

/**
 * Implémentation de Resultat pour décrire les conséquences d'une action
 * d'enchantement.
 *
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reymaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class EnchantResult implements Resultat {

    private final String description;
    private int joueurId;
    private final boolean coupAFonctionne;
    private final int enchantId;
    private final int carteEnchante;

    public EnchantResult(boolean coupCorrect, int enchId, int carteId) {
        coupAFonctionne = coupCorrect;
        enchantId = enchId;
        carteEnchante = carteId;
        description = "Enchantement d'une carte";
    }

    public EnchantResult(int jId, boolean coupCorrect, int enchId, int carteId) {
        joueurId = jId;
        coupAFonctionne = coupCorrect;
        enchantId = enchId;
        carteEnchante = carteId;
        description = "Enchantement d'une carte";
    }

    /**
     * Getter
     *
     * @return l'identifiant de la carte d'enchantement utilisé.
     */
    public int getCarteEnchantement() {
        return enchantId;
    }

    /**
     * Getter
     *
     * @return identifiant de la carte enchanté.
     */
    public int getCarteEnchante() {
        return carteEnchante;
    }

    /**
     * @return True si l'action a fonctionné,false sinon.
     */
    @Override
    public boolean coupAMarcher() {
        return coupAFonctionne;
    }

    /**
     * Getter
     *
     * @return Description de ce type de coup.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Getter
     *
     * @return l'identifiant du joueur qui a joué de coup.
     */
    @Override
    public int coupJouerPar() {
        return joueurId;
    }

    /**
     * Setter
     *
     * @param idJoueur l'identifiant du joueur qui a fait le coup.
     */
    @Override
    public void setJoueur(int idJoueur) {
        this.joueurId = idJoueur;
    }

}

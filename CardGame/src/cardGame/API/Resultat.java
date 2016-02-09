package cardGame.API;

/**
 * Interface utilisé pour communiquer aux joueurs les conséquences des coups joués/refusés.
 * Resultat est implémenté par une sous-classe pour chaque type de coup possible, afin de 
 * pouvoir transmettre la totalité des informations pertinentes.
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0 08-Fév-2016 : 1.0 - Version initiale.
 */
public interface Resultat {

    /**
     * Déclaration de coupAMarcher, Interface Result
     *
     * @return rien, non déclarée ici
     */
    public boolean coupAMarcher();

    /**
     * Déclaration de getDescription, Interface Result
     *
     * @return rien, non déclarée ici
     */
    public String getDescription();

    /**
     * Déclaration de coupJouerPar, Interface Result
     *
     * @return rien, non déclarée ici
     */
    public int coupJouerPar();

    /**
     * Déclaration de setJoueur, Interface Result
     * Ce setter existe seulement afin d'éviter d'envoyer aux classes
     * Cartes le Id du joueur.
     * @param idJoueur non définie ici
     */
    public void setJoueur(int idJoueur);
}

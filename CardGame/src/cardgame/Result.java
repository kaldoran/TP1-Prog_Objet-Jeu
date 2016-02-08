/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author kaldoran
 */
public interface Result {
    
    /**
     * Déclaration de coupAMarcher, Interface Result
     * @return rien, non déclarée ici
     */
    public boolean coupAMarcher();
    
    /**
     * Déclaration de getDescription, Interface Result
     * @return rien, non déclarée ici
     */
    public String getDescription();
    
    /**
     * Déclaration de coupJouerPar, Interface Result
     * @return rien, non déclarée ici
     */
    public int coupJouerPar();
    
    /**
     * Déclaration de setJoueur, Interface Result
     * @param idJoueur non définie ici
     * @return rien, non déclarée ici
     */
    public void setJoueur(int idJoueur);
}

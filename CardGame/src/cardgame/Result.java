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
    
    public boolean coupAMarcher();
    public String getDescription();
    public int coupJouerPar();
    public void setJoueur(int idJoueur);
}

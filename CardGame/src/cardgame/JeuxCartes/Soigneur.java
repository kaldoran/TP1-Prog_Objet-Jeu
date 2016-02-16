package cardgame.JeuxCartes;

import cardgame.ResultUtils.SoinsResult;

/**
 * Interface utilisé pour les fonctions reliés aux soins.
 * Cette interface nous permet de découpler la logique des sortilèges de soins
 * des Personnages, ce qui nous permettrai d'ajouter de nouveaux métiés axés sur
 * le support et les effets de status (Haste,Vitality etc...)
 * (L'idée provient de l'équipe Philippe Pépos PetitClerc et Mehdi Ait Younes) 
 * 
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 * 12-Fév-2016 : 1.0 - Version initiale.
 */
public interface Soigneur {
    
     /**
     * Permet au perso de soigner un allié.
     * Pour soigner, le perso a besoin d'avoir encore des points de magie.
     *
     * @param p Personnage allié à soigner.
     * @return un SoinsResult si le soin à réussi, RefuseResult sinon.
     */
   public abstract SoinsResult soigner (Perso p);
   
   /**
    * Vérifie si le soigneur est capable de faire le sort de soins.
    * @param p Le perso à soigner.
    * @return True si le sort de soins peut être éffectué.
    */
   public abstract boolean peutSoigner(Perso p);
   
}

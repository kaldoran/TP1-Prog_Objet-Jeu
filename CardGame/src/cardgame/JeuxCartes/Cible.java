package cardgame.JeuxCartes;

import cardgame.ResultUtils.Resultat;

/**
 * Interface utilisé pour les fonctions reliés au recoit de coups.
 * Cette interface nous permet de généraliser les appels d'attaques au xperso et Joueurs.
 * (L'idée provient de l'équipe Philippe Pépos PetitClerc et Mehdi Ait Younes) 
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 * 12-Fév-2016 : 1.0 - Version initiale.
 */
public interface Cible {
    
    /**
     * @return True si la cible peut actuellement être attaqué.
     * (Ex un Joueur doit avoir un jeu vide.)
     */
    public abstract boolean peutEtreAttaque();
    /**
     * recoitAttaque applique le dommage reçu, vérifie si le coup était
     * fatal et retourne le résultat du coup.
     * @param attaqueur Le combattant qui attaque la cible.
     * @return Le résultat du coup reçu (Soit AttackPerso ou AttackJoueur)
     */
    public abstract Resultat recoitAttaque(Combattant attaqueur);
    
    /**
     * @return True si la cible est morte.
     */
    public abstract boolean estMort();
        
}

package cardgame.JeuxCartes;

import cardgame.Regles.TypeArme;
import cardgame.ResultUtils.Resultat;
import java.util.List;

/**
 * Classe abstraite qui extend Perso, Combattant nous permet de
 * d.coupler la logique d'attaque hors des perso. Ceci nous permettrait
 * alors dans le futur d'ajouter des classes qui ne peuvent attaquer,
 * tel des troubadour ou Bardes.
 * 
 * (Inspiré par les travaux de Phillipe Pépos PetitClerc et Zerrouk Rahdia.)
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.2 
 * 
 * Historique : 
 * 14-Fév-2016 : 1.0 - Version initiale
 */
public abstract class Combattant extends Perso {

    public Combattant(int _hp, int _mp, List<TypeArme> armes) {
        super(_hp, _mp, armes);
    }
    
    /**
     * Fonction qui calcule le nombre de dégats fait à l'opposant armé.
     * @param ta Type d'arme de l'opposant.
     * @return Le nombre de dégats.
     */
    public int forceAttaque(TypeArme ta) {
        return this.getArme().forceAttaque(ta);
    }
    
    /**
     * Cette fonction effectue l'attaque d'une cible.
     * @param c Instance de la cible attaquée.
     * @return Soit unAttaquePersoResult 
     * ou AttaquePlayerResult décrivant le coup.
     */
    public Resultat Attaque(Cible c) {
        return c.recoitAttaque(this);
    }
     
}

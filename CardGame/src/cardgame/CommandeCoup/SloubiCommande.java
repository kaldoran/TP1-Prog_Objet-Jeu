package cardgame.CommandeCoup;

import cardgame.ResultUtils.RefuseResult;
import cardgame.ResultUtils.Resultat;

/**
 *
 * @author mathieu
 */
public class SloubiCommande implements Commande {

    @Override
    public Boolean coupPossible() {
        return false;
    }

    @Override
    public Boolean coupFinitTour() {
        return false;
    }

    @Override
    public Resultat placerCoup() {
        return new RefuseResult("Nice try.");
    }
    
}

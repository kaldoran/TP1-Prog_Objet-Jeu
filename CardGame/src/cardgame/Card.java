/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import javax.json.JsonObject;

/**
 *
 * @author kaldoran
 */
public abstract class Card {
    static int SSID = 0;
    protected int cardID;
    
    /**
     * Description des fonctions représentant le JSon des cartes, non définie ici
     * @return null, fonction non définie ici
     */
    public abstract JsonObject toJSON();
    
    /**
     * Description des fonctions représentant le JSon des cartes, non définie ici
     * @return null, fonction non définie ici
     */
    public abstract JsonObject toJSONTest();
    
    public Card() {
        cardID = SSID;
        ++Card.SSID;
    }
    
    /**
     * Permet d'avoir l'id de la carte
     * @return l'identifiant unique associé à la carte
     */
    public int getCardID() {
        return cardID;
    }
}

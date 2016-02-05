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
    
    public abstract JsonObject toJSON();
    
    public Card() {
        cardID = SSID;
        ++Card.SSID;
    }
    
    
    public int getCardID() {
        return cardID;
    }
}

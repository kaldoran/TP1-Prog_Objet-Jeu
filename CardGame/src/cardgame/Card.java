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
public abstract class Card {
    static int SSID = 0;
    private int cardID;
    
    public void creadID() {
        cardID = SSID;
        ++SSID;
    }
    
    public int getCardID() {
        return cardID;
    }
}

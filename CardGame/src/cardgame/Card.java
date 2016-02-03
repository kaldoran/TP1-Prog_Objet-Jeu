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
abstract class Card {
    static long SSID = 0;
    long cardID;
    
    public void creadID() {
        cardID = SSID;
        ++SSID;
    }
}

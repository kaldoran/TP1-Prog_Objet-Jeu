/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.ResultUtils;

import cardgame.Cartes.Carte;
import cardGame.API.Resultat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.json.JsonObject;

/**
 *
 * @author kaldoran
 */
public class PiocheResult implements Resultat {

    private final String description;
    private int joueurId;
    private final boolean coupAFonctionne;
    private final List<Integer> cartesId;
    private final List<JsonObject> cartesJSON;
    
    
    public PiocheResult(int jId,boolean coupCorrect,List<Carte> cartes) {
        description = "Résultat de pioches";
        joueurId = jId;
        coupAFonctionne = coupCorrect;
        cartesId = new ArrayList<>();
        cartesJSON = new ArrayList<>();
        for (Carte cartePioche : cartes) {
            cartesId.add(cartePioche.getCardID());
            cartesJSON.add(cartePioche.toJSON());
        }
    }
    
    
    public List<Integer> getCartesID(){
        return cartesId;
    }
    
    public List<JsonObject> getCartesJSON(){
        return cartesJSON;
    }
    
    
       @Override
    public boolean coupAMarcher() {
        return coupAFonctionne;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int coupJouerPar() {
        return joueurId;
    }

    @Override
    public void setJoueur(int idJoueur) {
        joueurId  = idJoueur;
    }
}

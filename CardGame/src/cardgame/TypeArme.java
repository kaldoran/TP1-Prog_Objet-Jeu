/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author kaldoran
 */
public enum TypeArme {
    
    Contondant("Contondant","Perforant ","Tranchant",TypePerso.values()),
    Perforant("Perforant ","Tranchant","Contondant",TypePerso.Guerrier,TypePerso.Paladin),
    Tranchant("Tranchant ","Contondant","Perforant",TypePerso.Guerrier,TypePerso.Paladin),
    Neutre("Neutre","","",TypePerso.values());
    
    private final String nom;
    private final String force;
    private final String faiblesse;
    private final List<TypePerso> users;
    
    TypeArme(String nom, String force, String faiblesse,TypePerso... t) {
        this.nom = nom;
        this.force = force;
        this.faiblesse = faiblesse;
        users = Arrays.asList(t);
    }
    
    public int calculModificateur(TypeArme armeEnnemi) {
        if ( this.force.equals(armeEnnemi.nom)) 
            return 1;
        else if ( this.faiblesse.equals(armeEnnemi.nom))
            return -1;
        
        return 0;
    }
    
    public List<TypePerso> getUtilisateurs(){
        return users;
    }
    
}

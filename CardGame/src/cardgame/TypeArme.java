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
public enum TypeArme {
    
    Contondant("Contondant","Perforant ","Tranchant"),
    Perforant("Perforant ","Tranchant","Contondant"),
    Tranchant("Tranchant ","Contondant","Perforant"),
    Neutre("Neutre","","");
    
    private final String nom;
    private final String force;
    private final String faiblesse;
    
    TypeArme(String nom, String force, String faiblesse) {
        this.nom = nom;
        this.force = force;
        this.faiblesse = faiblesse;
    }
    
    public int calculModificateur(TypeArme armeEnnemi) {
        if ( this.force.equals(armeEnnemi.nom)) 
            return 1;
        else if ( this.faiblesse.equals(armeEnnemi.nom))
            return -1;
        
        return 0;
    }
}

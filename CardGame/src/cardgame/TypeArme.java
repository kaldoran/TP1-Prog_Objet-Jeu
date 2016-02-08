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
    
    /**
     * Déclaration de l'enum des types d'armes possibles dans le jeu
     */
    Contondant("Contondant","Perforant","Tranchant",TypePerso.values()),
    Perforant("Perforant","Tranchant","Contondant",TypePerso.Guerrier,TypePerso.Paladin),
    Tranchant("Tranchant","Contondant","Perforant",TypePerso.Guerrier,TypePerso.Paladin),
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
    
    /**
     * Définie le triangle de modification de dégat
     * @param armeEnnemi Type d'arme de l'ennemi
     * @return 1 si l'arme armeEnnemi est plus forte de l'arme actuelle
     *        -1 si elle est moins forte
     *         0 Sinon
     */
    public int calculModificateur(TypeArme armeEnnemi) {
        if ( this.force.equals(armeEnnemi.nom)) 
            return 1;
        else if ( this.faiblesse.equals(armeEnnemi.nom))
            return -1;
        
        return 0;
    }
    
    /**
     * Liste des utilisateurs pouvant utiliser cette arme
     * @return 
     */
    public List<TypePerso> getUtilisateurs(){
        return users;
    }
    
}

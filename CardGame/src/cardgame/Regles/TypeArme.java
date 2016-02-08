package cardgame.Regles;

import java.util.Arrays;
import java.util.List;

/**
 * Enum, le type de données TypeArme contient chaque type d'armes possibles dans
 * le jeu, ainsi que leurs logiques personnelles, tel que leurs
 * forces/faiblesses et leur types d'utilisateurs. La classe détient aussi la
 * fonction de calcul du triangle d'attaque pour fins d'évolutions façiles.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reymaud REYN23119308
 * @version 1.0
 *
 * Historique :
 *
 * -8 Fév-2016 : 1.0 Version initiale.
 */
public enum TypeArme {

    /**
     * Déclaration de l'enum des types d'armes possibles dans le jeu. On décrit
     * en même temps leur forces,faiblesses et leurs utilisables possibles. Les
     * forces/faiblesses sont décrits en String pour fins de visibilité humaine.
     */
    Contondant("Contondant", "Perforant", "Tranchant", TypePerso.values()),
    Perforant("Perforant", "Tranchant", "Contondant", TypePerso.Guerrier, TypePerso.Paladin),
    Tranchant("Tranchant", "Contondant", "Perforant", TypePerso.Guerrier, TypePerso.Paladin),
    Neutre("Neutre", "", "", TypePerso.values());

    private final String nom;
    private final String force;
    private final String faiblesse;
    private final List<TypePerso> users;

    TypeArme(String nom, String force, String faiblesse, TypePerso... t) {
        this.nom = nom;
        this.force = force;
        this.faiblesse = faiblesse;
        users = Arrays.asList(t);
    }

    /**
     * Définit le calcul de modificateur du triangle d'armes.
     *
     * @param armeEnnemi Type d'arme de l'ennemi
     * @return 1 si l'arme actuelle est la faiblesse de l'arme ennemi, -1 si
     * l'arme ennemi est la faiblesse de l'arme actuelle, Sinon 0.
     */
    public int calculModificateur(TypeArme armeEnnemi) {
        if (this.force.equals(armeEnnemi.nom)) {
            return 1;
        } else if (this.faiblesse.equals(armeEnnemi.nom)) {
            return -1;
        }

        return 0;
    }

    /**
     * Liste des Perso pouvant utiliser cette arme.
     *
     * @return
     */
    public List<TypePerso> getUtilisateurs() {
        return users;
    }

}

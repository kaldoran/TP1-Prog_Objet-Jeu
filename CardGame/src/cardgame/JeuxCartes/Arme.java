package cardgame.JeuxCartes;

import cardgame.Regles.TypeArme;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.json.*;

/**
 * Classe représentant chacunes des cartes d'armes du jeu. Initialisé par
 * ArmeFactory (Afin d'attribuer les bons attributs pour chaque type d'armes),
 * Arme permet de traiter la logique de : - Force d'attaque - Équipage
 * d'enchantements - Vérification de l'arme pour un déploiement.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.2 
 * 
 * Historique : 08-Fév-2016 : 1.0 - Version initiale.
 * 11-Fév-2016 : 1.1 - Découpage de ListUtilisateurs pour le placer
 *                      dans Perso.
 *               1.2 - Ajout de fonctions pour vérifier si l'amr
 *                     est déployé.
 */
public class Arme extends Carte {

    protected TypeArme type;
    /**
     * Boolean notant explicitement si l'arme est stased pour fins
     * d'éfficiences.
     */
    private boolean estStase;
    private boolean armeUtilise;
    protected int degat;
    protected List<Enchant> listEnchant;
    protected boolean estFacile;
    
    /**
     * Liste utilisé pour noter les enchantements qui ont été Stased. Cette
     * liste sert seulement pour fins d'affichages.
     */
    protected List<Enchant> listEnchantStase;

    /*Variables notant les valeurs initiales de l'arme.
     Ceci nous permet de remettre l'arme à zéro si nécessaire.*/
    private final int degatOrg;
    private final TypeArme typeOrg;

    public Arme(TypeArme _type, int dmg) {
        super();
        type = _type;
        typeOrg = _type;
        degat = dmg;
        degatOrg = dmg;
        armeUtilise = false;
        estFacile = false;
        listEnchant = new ArrayList<>();
        listEnchantStase = new ArrayList<>();
        estStase = false;
    }

    /**
     * Permet de savoir la force d'attaque de l'arme en appliquant le triangle
     * des degats
     *
     * @param arme Type d'arme sur laquel faire le triangle de modificateur de
     * dégats
     * @return la force d'attaque de l'arme
     */
    public int forceAttaque(TypeArme arme) {
        return this.degat + this.type.calculModificateur(arme);
    }

    /**
     * Permet de savoir si un perso peut utiliser ou non une arme.
     *
     * @param p personnage à verifier
     * @return true si le perso peut porter l'arme false sinon
     */
    public boolean peutUtiliserArme(Perso p) {
        return (estFacile || p.getArmesUtilisables().contains(this.type));
    }

    /**
     * Permet d'ajouter un enchantement à l'arme courante
     *
     * @param ench Enchant à appliquer à l'arme
     */
    protected void ajouterEnchant(Enchant ench) {
        if (!this.estStase) {
            listEnchant.add(ench);
            ench.placerEnchant(this);
        }
    }

    /**
     * Permet de réinitaliser l'arme à son état d'origine.
     */
    protected void reset() {
        this.listEnchantStase = new ArrayList<>(this.listEnchant);
        this.listEnchant = new ArrayList<>();
        this.degat = this.degatOrg;
        this.type = this.typeOrg;
    }

    /**
     * Permet d'avoir la représentation JSon d'une arme.
     *
     * @return le jSon associé à une arme
     */
    @Override
    public JsonObject toJSON() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Id", this.getCardID());
        obj.add("Type d'arme", type.name());
        obj.add("Degats", degat);
        Iterator<Enchant> it = listEnchant.iterator();
        int enchNum = 1;
        while (it.hasNext()) {
            obj.add("Enchantement actif #" + enchNum, it.next().toJSON());
            ++enchNum;
        }
        enchNum = 1;
        it = listEnchantStase.iterator();
        while (it.hasNext()) {
            obj.add("Enchantement inactif #" + enchNum, it.next().toJSON());
            ++enchNum;
        }

        return obj.build();
    }

    /**
     * Getter
     * @return Type d'arme
     */
    public TypeArme getTypeArme(){
        return type;
    }
    
    /**
     * Permet de staser une Arme
     */
    protected void staserArme() {
        this.estStase = true;
    }
    
    /**
     * Setter notant que l'arme a été déployé.
     */
    protected void deployerArme() {
        armeUtilise = true;
    }

    /**
     * Permet de savoir si une arme est Stase
     *
     * @return true si l'arme est stase false sinon
     */
    public boolean peutAjouterEnchantement() {
        return !estStase;
    }

    /**
     * Getter
     * @return Bool dictant si l'arme est déployé sur un perso. 
     */
    public boolean armeEstDeploye() {
        return armeUtilise;
    }
}

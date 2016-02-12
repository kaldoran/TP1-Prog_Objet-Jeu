package cardgame.Cartes;

import cardgame.API.Resultat;
import cardgame.ResultUtils.EnchantResult;
import cardgame.ResultUtils.RefuseResult;
import cardgame.Regles.TypeArme;
import cardgame.Regles.TypePerso;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Classe représentant chacunes des cartes d'armes du jeu. Initialisé par
 * ArmeFactory (Afin d'attribuer les bons attributs pour chaque type d'armes),
 * Arme permet de traiter la logique de : - Force d'attaque - Équipage
 * d'enchantements - Vérification de l'arme pour un déploiement.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0 08-Fév-2016 : 1.0 - Version initiale.
 */
public class Arme extends Carte {

    protected TypeArme type;
    /**
     * Boolean notant explicitement si l'arme est stased pour fins
     * d'éfficiences.
     */
    private boolean estStase;
    protected int degat;
    protected List<Enchant> listEnchant;
    /**
     * Liste utilisé pour noter les enchantements qui ont été Stased. Cette
     * liste sert seulement pour fins d'affichages.
     */
    protected List<Enchant> listEnchantStase;
    protected List<TypePerso> listUtilisateurs;

    /*Variables notant les valeurs initiales de l'arme.
     Ceci nous permet de remettre l'arme à zéro si nécessaire.*/
    private final int degatOrg;
    private final TypeArme typeOrg;
    private final List<TypePerso> listUtilisateursOrg;

    public Arme(TypeArme _type, int dmg) {
        super();
        type = _type;
        typeOrg = _type;
        degat = dmg;
        degatOrg = dmg;
        listUtilisateurs = new ArrayList<>(_type.getUtilisateurs());
        listUtilisateursOrg = new ArrayList<>(_type.getUtilisateurs());
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
     * Permet de savoir si un perso peut utiliser ou non une arme
     *
     * @param perso personnage à verifier
     * @return true si le perso peut porter l'arme false sinon
     */
    public boolean peutUtiliserArme(TypePerso perso) {
        return listUtilisateurs.contains(perso);
    }

    /**
     * Permet d'ajouter un enchantement à l'arme courante
     *
     * @param ench Enchant à appliquer à l'arme
     * @return un EnchantResult si tout c'est bien passé un Refused Result sinon
     */
    public Resultat ajouterEnchant(Enchant ench) {
        Resultat res;
        if (!this.estStase) {
            listEnchant.add(ench);
            ench.placerEnchant(this);
            res = new EnchantResult(true, ench.cardID, this.cardID);
        } else {
            res = new RefuseResult("L'enchantement ne peut pas être appliqué.");
        }
        return res;
    }

    /**
     * Permet de réinitaliser l'arme à son état d'origine.
     */
    public void reset() {
        this.listEnchantStase = new ArrayList<>(this.listEnchant);
        this.listEnchant = null;

        this.listUtilisateurs.clear();
        this.listUtilisateurs.addAll(this.listUtilisateursOrg);
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
        obj.add("Id", this.cardID);
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
     * Permet de créer le JSon pour les tests.
     *
     * @return retourne le JSon associé à une arme
     */
    @Override
    public JsonObject toJSONTest() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Type d'arme", type.name());
        obj.add("Degats", degat);
        Iterator<Enchant> it = listEnchant.iterator();
        int enchNum = 1;
        while (it.hasNext()) {
            obj.add("Enchantement actif #" + enchNum, it.next().toJSONTest());
        }
        enchNum = 1;
        it = listEnchantStase.iterator();
        while (it.hasNext()) {
            obj.add("Enchantement inactif #" + enchNum, it.next().toJSONTest());
        }

        return obj.build();
    }

    /**
     * Permet de staser une Arme
     */
    public void staserArme() {
        this.estStase = true;
    }

    /**
     * Permet de savoir si une arme est Stase
     *
     * @return true si l'arme est stase false sinon
     */
    public boolean armeEstStase() {
        return estStase;
    }

}

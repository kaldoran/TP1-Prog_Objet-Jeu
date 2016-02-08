package cardgame.Cartes;

import cardGame.API.Resultat;
import cardgame.ResultUtils.AttaqueResult;
import cardgame.ResultUtils.RefuseResult;
import cardgame.ResultUtils.SoinsResult;
import cardgame.Regles.TypeArme;
import cardgame.Regles.TypePerso;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Classe représentant les cartes de type personnages du jeu. Instancié par
 * PersoFactory(Afin que la carte suive les bonnes règles), Perso permet de
 * calculer et d'appliquer les coups au niveau d'une carte. (Puisque les
 * différences entre chaque personnages sont minimes et sujet à évolution, Perso
 * peut être utilisé pour instancier chaque type de Perso du jeux. Les règles
 * sur leur paramétres peuvent être trouvé dans Regle.Java et TypePerso.java)
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reymaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class Perso extends Carte {

    private int hp;
    private final int maxHp;
    private int mp;
    private Arme armePerso;
    private final TypePerso typeperso;

    public Perso(int _hp, int _mp, TypePerso type) {
        super();
        hp = _hp;
        mp = _mp;
        maxHp = hp;
        typeperso = type;
        armePerso = null;
    }

    /**
     * Permet d'avoir la force d'attaque de l'arme (Sans triangle de
     * modificateur de dégats)
     *
     * @return force de l'arme si présente, 0 Sinon
     */
    public int forceAttaque() {
        return armePerso != null ? armePerso.forceAttaque(TypeArme.Neutre) : 0;
    }

    /**
     * Permet d'avoir la force d'attaque de l'arme utilisée en appliquant le
     * triangle de modification de dégats.
     *
     * @param ennemi Perso ennemi contre qui l'arme sera utilisée
     * @return la force de l'arme en ajoutant le modificateur de degat si le
     * perso à une arme, 0 sinon
     */
    public int forceAttaque(Perso ennemi) {
        return armePerso != null ? armePerso.forceAttaque(ennemi.getTypeArme()) : 0;
    }

    /**
     * Permet d'appliquer les dégats reçus au perso et d'avertir s'il a survécu.
     *
     * @param dommage Quantitée de dégats pris
     * @param attaqueur Identifiant de l'attaqueur.
     * @return un AttackResult décrivant le coup reçu au perso.
     */
    public AttaqueResult prendreDommage(int dommage, int attaqueur) {

        this.hp -= dommage;

        AttaqueResult resCoup = new AttaqueResult(dommage, false, this.cardID, attaqueur, estMort());

        return resCoup;
    }

    /**
     * Permet d'obtenir la liste des cartes qui était associée au personnage.
     * Ceci nous permet de les ajouter au cimetière à la mort du perso.
     *
     * @return Liste des cartes présente sur le perso
     */
    public List<Carte> libererCartes() {
        List<Carte> cartesMortes = new ArrayList<>();
        cartesMortes.addAll(armePerso.listEnchant);
        cartesMortes.addAll(armePerso.listEnchantStase);
        cartesMortes.add(armePerso);
        cartesMortes.add(this);
        return cartesMortes;
    }

    /**
     * Permet d'ajouter un enchantement à l'arme du perso.
     *
     * @param ench Enchantement à appliquer
     * @return un EnchantResult si l'enchant fonctionne un RefusedResult sinon
     */
    public Resultat ajouterEnchant(Enchant ench) {
        return armePerso.ajouterEnchant(ench);
    }

    /**
     * Permet de vérifier si le perso peut utiliser une arme et si oui,
     * la lui place.
     *
     * @param arme arme à donner au perso
     * @return true si l'arme est placée, false sinon
     */
    public boolean placerArme(Arme arme) {
        boolean armeLibre = false;
        if (this.armePerso == null && arme.peutUtiliserArme(this.typeperso)) {
            this.armePerso = arme;
            armeLibre = true;
        }
        return armeLibre;
    }

    /**
     * Permet au perso de soigner un allié.
     * Pour soigner, le perso a besoin d'avoir encore des points de magie.
     *
     * @param allie Personnage allié à soigner.
     * @return un SoinsResult si le soin à réussi, RefuseResult sinon.
     */
    public Resultat soigner(Perso allie) {

        Resultat resultat;
        //Confirmation qu'on peut soigner et qu'on ne ce soigne pas soi-même.
        if (this.mp > 0 && (this.getCardID() != allie.getCardID())) { 
            --this.mp;
            allie.recevoirSoins();
            resultat = new SoinsResult(true, this.cardID, allie.cardID);
            // return good Result
        } else {
            resultat = new RefuseResult("Le soigneur n'a plus de magie.");
        }
        return resultat;
    }

    /**
     * Permet au personnage de recevoir le soin (Autrement dit, réinit ses points
     * de vie).
     */
    private void recevoirSoins() {
        this.hp = this.maxHp;
    }

    /**
     * Permet de savoir si le personnage est mort.
     *
     * @return true si le personnage est mort, false sinon
     */
    public boolean estMort() {
        return this.hp <= 0;
    }

    /**
     * Permet d'obtenir le type d'arme utilisée par le perso
     *
     * @return le type de l'arme si une est équipée, null sinon
     */
    public TypeArme getTypeArme() {
        return armePerso != null ? armePerso.type : null;
    }

    /**
     * Permet d'obtenir le Json associé au personnage.
     *
     * @return le JSon représentant le perso
     */
    @Override
    public JsonObject toJSON() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Id", this.cardID);
        obj.add("Type Personnage", typeperso.toString());
        obj.add("hp", hp);
        obj.add("mp", mp);
        if (armePerso != null) {
            obj.add("Arme personnage", armePerso.toJSON());
        }

        return obj.build();
    }

    /**
     * Permet de créer un JSon pour fins de tests.
     *
     * @return le JSon représentant le perso lors de tests
     */
    @Override
    public JsonObject toJSONTest() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Type Personnage", typeperso.toString());
        obj.add("hp", hp);
        obj.add("mp", mp);
        if (armePerso != null) {
            obj.add("Arme personnage", armePerso.toJSONTest());
        }

        return obj.build();
    }

}

package cardgame.JeuxCartes;

import cardgame.Regles.TypeArme;
import cardgame.ResultUtils.AttaquePersoResult;
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
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */

public class Perso extends Carte implements Cible  {

    private int hp;
    private final int maxHp;
    private int mp;
    private final int maxMp;
    private Arme armePerso;
    private final List<TypeArme> armesUtilisables;

    public Perso(int _hp, int _mp, List<TypeArme> armes) {
        super();
        hp = _hp;
        mp = _mp;
        maxHp = hp;
        maxMp = _mp;
        armePerso = null;
        armesUtilisables = armes;
    }


    /**
     * Permet d'appliquer les dégats reçus au perso et d'avertir s'il a survécu.
     *
     * @param dommage Quantitée de dégats pris
     * @param attaqueur Identifiant de l'attaqueur.
     * @return un AttackResult décrivant le coup reçu au perso.
     */
    protected AttaquePersoResult prendreDommage(int dommage, int attaqueur) {

        this.hp -= dommage;

        AttaquePersoResult resCoup = new AttaquePersoResult(dommage, this.cardID, attaqueur, estMort());

        return resCoup;
    }

    /**
     * Permet d'obtenir la liste des cartes qui était associée au personnage.
     * Ceci nous permet de les ajouter au cimetière à la mort du perso.
     *
     * @return Liste des cartes présente sur le perso
     */
    protected List<Carte> libererCartes() {
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
     */
    protected void ajouterEnchant(Enchant ench) {
        armePerso.ajouterEnchant(ench);
    }

    /**
     * Permet de vérifier si le perso peut utiliser une arme et si oui,
     * la lui place.
     *
     * @param arme arme à donner au perso
     * @return true si l'arme est placée, false sinon
     */
    protected boolean equiperArme(Arme arme) {
        boolean armeLibre = false;
        if (this.armePerso == null && arme.peutUtiliserArme(this)) {
            this.armePerso = arme;
            armeLibre = true;
        }
        return armeLibre;
    }

    /**
     * Permet au personnage de recevoir le soin (Autrement dit, réinit ses points
     * de vie).
     */
    protected void recevoirSoins() {
        this.hp = this.maxHp;
    }
    
    public int getMp(){
        return mp;
    }
    
    protected void utiliserMagie(){
        Math.max(mp--,0);
    }

    /**
     * Permet de savoir si le personnage est mort.
     *
     * @return true si le personnage est mort, false sinon
     */
    @Override
    public boolean estMort() {
        return this.hp <= 0;
    }
    
    public List<TypeArme> getArmesUtilisables(){
        return armesUtilisables;
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
        //obj.add("Type Personnage", typeperso.toString());
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
        //obj.add("Type Personnage", typeperso.toString());
        obj.add("hp", hp);
        obj.add("mp", mp);
        obj.add("maxHp",maxHp);
        obj.add("maxMp",maxMp);
        if (armePerso != null) {
            obj.add("Arme personnage", armePerso.toJSONTest());
        }

        return obj.build();
    }
    
    public Arme getArme(){
        return armePerso;
    }

    @Override
    public boolean peutEtreAttaque() {
        return !estMort();
    }

    @Override
    public AttaquePersoResult recoitAttaque(Combattant attaqueur) {
        AttaquePersoResult res;
        assert (this.armePerso != null);
        int degat = attaqueur.forceAttaque(this.armePerso.getTypeArme());
        this.hp -= degat;
        res = new AttaquePersoResult(degat, this.getCardID(),attaqueur.getCardID(), estMort());
        return res;
    }

  
}

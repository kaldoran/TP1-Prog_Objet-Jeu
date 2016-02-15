package cardgame.JeuxCartes;

import cardgame.Regles.TypeArme;
import cardgame.ResultUtils.AttaquePersoResult;
import java.util.ArrayList;
import java.util.List;
import javax.json.*;

/**
 * Classe représentant les cartes de type personnages du jeu. La carte peut être
 * extend directement pour créer un perso non-combattant.
 *
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale. 12-Fév-2016 : 1.1 - Modification du
 * code pour marcher avec Cible. Guerrier,Pretre et Paladin. 14-Fév-2016 : 1.2 -
 * Modification du code pour marcher avec Combattant.
 */
public class Perso extends Carte implements Cible {

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
     * @return L'arme du personnage
     */
    public Arme getArme() {
        return armePerso;
    }

    public int getMp() {
        return mp;
    }

    public List<TypeArme> getArmesUtilisables() {
        return armesUtilisables;
    }

    /**
     * Utilise un point de magie.
     */
    protected void utiliserMagie() {
        Math.max(mp--, 0);
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
     * Permet de vérifier si le perso peut utiliser une arme et si oui, la lui
     * place.
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
     * Permet au personnage de recevoir le soin (Autrement dit, réinit ses
     * points de vie).
     */
    protected void recevoirSoins() {
        this.hp = this.maxHp;
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
     * Permet de savoir si le personnage est mort.
     *
     * @return true si le personnage est mort, false sinon
     */
    @Override
    public boolean estMort() {
        return this.hp <= 0;
    }

    /**
     * Permet d'obtenir le Json associé au personnage.
     *
     * @return le JSon représentant le perso
     */
    @Override
    public JsonObject toJSON() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Id", this.getCardID());
        //obj.add("Type Personnage", typeperso.toString());
        obj.add("hp", hp);
        obj.add("mp", mp);
        if (armePerso != null) {
            obj.add("Arme personnage", armePerso.toJSON());
        }

        return obj.build();
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
        res = new AttaquePersoResult(degat, this.getCardID(), attaqueur.getCardID(), estMort());
        return res;
    }

}

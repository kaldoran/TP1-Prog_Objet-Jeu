package cardgame.JeuxCartes;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Classe abstraite, Enchant sert d'interface commun pour tout les types
 * d'enchantements du jeu.
 *
 * Enchant, ainsi que ces sous-classes et Arme, fonctionne selon le principe du
 * patron de conception "Visiteur" afin de pouvoir ajouter dynamiquement des
 * nouveaux comportements aux armes sans devoir modifier leur code source. Ce
 * choix de design nous permet d'ajouter façilement des enchantements dans le
 * jeu dans devoir modifier Arme.
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public abstract class Enchant extends Carte {

    private final String description;

    public Enchant(String desc) {
        super();
        description = desc;
    }
    
    public boolean peutUtiliserEnchant(Arme arm) {
        return arm.peutAjouterEnchantement();
    }

    /**
     * Permet d'avoir la représentation de la carte d'enchantement.
     *
     * @return le JSon représentant l'enchant de la carte
     */
    @Override
    public JsonObject toJSON() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Id", this.cardID);
        obj.add("Nom", this.getClass().getCanonicalName());
        obj.add("Description", description);

        return obj.build();
    }

    /**
     * Permet d'avoir la représentation de la carte lors des tests
     *
     * @return le JSon représentant l'enchant lors des tests
     */
    @Override
    public JsonObject toJSONTest() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Nom", this.getClass().getCanonicalName());
        obj.add("Description", description);

        return obj.build();
    }

    /**
     * Déclaration de la fonction placerEnchant, celle ci n'a aucun effet
     *
     * @param arme Arme sur laquel placer enchant
     */
    public abstract void placerEnchant(Arme arme);
}

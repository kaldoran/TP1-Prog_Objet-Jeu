package cardgame.JeuxCartes;

import javax.json.*;
/**
 * Classe abstraite, Enchant sert d'interface commun pour tout les types
 * d'enchantements du jeu.
 *
 * Les implémentations de cette classes sont basés sur le patron Visiteur,
 * afin de pouvoir ajouter dynamiquement une nouvelle opération à arme
 * (ajout l'enchantement) sans toutefois modifier sa classe.
 * Ceci nous permet d'assurer que tout ajouts d'enchantements basés
 * sur des valeurs d'armes existantes n'auront pas besoin de modifier Arme.
 * (https://sourcemaking.com/design_patterns/visitor)
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
    
     /**
     * Déclaration de la fonction placerEnchant, celle ci n'a aucun effet
     *
     * @param arme Arme sur laquel placer enchant
     */
    protected abstract void placerEnchant(Arme arme);
    

    /**
     * Permet d'avoir la représentation de la carte d'enchantement.
     *
     * @return le JSon représentant l'enchant de la carte
     */
    @Override
    public JsonObject toJSON() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Id", this.getCardID());
        obj.add("Nom", this.getClass().getCanonicalName());
        obj.add("Description", description);

        return obj.build();
    }


   

}

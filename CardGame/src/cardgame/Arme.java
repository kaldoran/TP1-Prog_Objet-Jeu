/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.ResultUtils.EnchantResult;
import cardgame.ResultUtils.RefusedResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
/**
 *
 * @author kaldoran
 */
public class Arme extends Card {
    protected TypeArme type;
    protected boolean estStase;
    protected int degat; 
    protected List<Enchant> listEnchant;
    protected List<Enchant> listEnchantStase;
    protected List<TypePerso> listUtilisateurs;
    private final int degatOrg;
    private final TypeArme typeOrg;
    private final List<TypePerso> listUtilisateursOrg;
    
    public Arme(TypeArme _type,int dmg)
    {
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
     * Permet de savoir la force d'attaque de l'arme en appliquant le triangle des degats
     * @param arme Type d'arme sur laquel faire le triangle de modificateur de dégats
     * @return la force d'attaque de l'arme
     */
    public int forceAttaque(TypeArme arme) {
        return this.getDegat() + this.type.calculModificateur(arme); 
    }
    
    /** 
     * Permet de savoir si un perso peut utiliser ou non une arme
     * @param perso personnage à verifier
     * @return true si le perso peut porter l'arme
     *         false sinon
     */
    public boolean peutUtiliserArme(TypePerso perso) {
        return listUtilisateurs.contains(perso);
    }
    
    /**
     * Permet d'ajouter un enchantement à l'arme courante
     * @param ench Enchant à appliquer à l'arme
     * @return un EnchantResult si tout c'est bien passé
     *         un Refused Result sinon
     */
    public Result ajouterEnchant(Enchant ench) {
        Result res;
        if ( !this.estStase ) {
            listEnchant.add(ench);
            ench.placerEnchant(this);
            res = new EnchantResult(true,ench.cardID,this.cardID);
        }
        else
        {
            res = new RefusedResult("L'enchantement ne peut pas être appliqué.");
        }
        return res;
    }
    
    /**
     * Permet de réinitalisé l'arme à sont état d'origine
     */
    public void reset() {
        this.listEnchantStase = new ArrayList<>(this.listEnchant);
        this.listEnchant = null;
        
        this.setListUtilisateurs(this.listUtilisateursOrg);
        this.setDegat(this.degatOrg);
        this.setType(this.typeOrg);
    }
    
    /**
     * Permet d'avoir le JSon d'une arme
     * @return le jSon associé à une arme
     */
    @Override
    public JsonObject toJSON() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Id",this.cardID);
        obj.add("Type d'arme", type.name());
        obj.add("Degats",degat);
        Iterator<Enchant> it = listEnchant.iterator();
        int enchNum = 1;
        while (it.hasNext()) {
            obj.add("Enchantement actif #" + enchNum, it.next().toJSON());
            ++enchNum;    
        }
        enchNum  = 1;
        it = listEnchantStase.iterator();
        while (it.hasNext()) {
            obj.add("Enchantement inactif #" + enchNum, it.next().toJSON());
            ++enchNum;
        }
                
        return obj.build();
    }
    
    /**
     * Permet de crée le JSon pour les tests
     * @return retourne le JSon associé à une arme
     */
    @Override
    public JsonObject toJSONTest() {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("Type d'arme", type.name());
        obj.add("Degats",degat);
        Iterator<Enchant> it = listEnchant.iterator();
        int enchNum = 1;
        while (it.hasNext()) {
                obj.add("Enchantement actif #" + enchNum, it.next().toJSONTest());
        }
        enchNum  = 1;
        it = listEnchantStase.iterator();
        while (it.hasNext()) {
                obj.add("Enchantement inactif #" + enchNum, it.next().toJSONTest());
        }
        
        
        return obj.build();
    }
    
    /**
     * Permet d'avoir les degats de base d'une arme
     * @return les degats de l'arme
     */
    public int getDegat() {
        return this.degat;
    }
    
    /**
     * Permet de modifier la valeur de degat de l'arme
     * @param degat nouveau degats de l'arme
     */
    public void setDegat(int degat) {
        this.degat = degat;
    }
    
    /**
     * Permet de modifier le type de l'arme
     * @param type 
     */
    public void setType(TypeArme type) {
        this.type = type;
    }
    
    /**
     * Permet de staser une Arme
     */
    public void staserArme() {
        this.estStase = true;
    }
    
    /** 
     * Permet de savoir si une arme est Stase
     * @return true si l'arme est stase
     *         false sinon
     */
    public boolean armeEstStase() {
        return estStase;
    }
    
    /** 
     * 
     * @param listUtilisateurs 
     */
    public void setListUtilisateurs(List<TypePerso> listUtilisateurs) {
        this.listUtilisateurs = listUtilisateurs;
    }    
}

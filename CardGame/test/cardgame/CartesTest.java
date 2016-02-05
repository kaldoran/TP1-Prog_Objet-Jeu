/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.EnchantUtils.EnchantDegatPlus;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mathieu
 */
public class CartesTest {
    
    public CartesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of peutUtiliserArme method, of class Arme.
     */

    @Test
    public void TestInitCartes() {
        System.out.println("TestInitCartes");
        List<Card> deck = new ArrayList<>();
        deck.add(new Perso(5,0,TypePerso.Guerrier));
        deck.add(new Arme(TypeArme.Contondant, 2));
        deck.add(new EnchantDegatPlus());
        System.out.println(deck.get(0).toJSON().toString());
        System.out.println(deck.get(1).toJSON().toString());
        System.out.println(deck.get(2).toJSON().toString());  
         JsonObjectBuilder verifPers = Json.createObjectBuilder();
        JsonObjectBuilder verifArme = Json.createObjectBuilder();
        JsonObjectBuilder verifEnchant = Json.createObjectBuilder();

        verifPers.add("Type Personnage", TypePerso.Guerrier.toString());
        verifPers.add("hp", 5);
        verifPers.add("mp", 0);
        verifArme.add("Type d'arme", TypeArme.Contondant.toString());
        verifArme.add("Degats", 2);
        verifEnchant.add("Nom", EnchantDegatPlus.class.getCanonicalName());
        verifEnchant.add("Description", "Cette carte augmente les degats de l'arme choisi par un.");
        assertEquals(deck.get(0).toJSONTest(), verifPers.build());
        assertEquals(deck.get(1).toJSONTest(), verifArme.build());
        assertEquals(deck.get(2).toJSONTest(), verifEnchant.build());

    }
    
    @Test
    public void TestInitPerso() {
        System.out.println("TestInitPerso");
        Perso pers = new Perso(5,0,TypePerso.Guerrier);
        Arme armePerso = new Arme(TypeArme.Contondant, 2);
        Enchant ench = new EnchantDegatPlus();
        pers.placerArme(armePerso);
        pers.ajouterEnchant(ench);
        System.out.println(pers.toJSON().toString());
        JsonObjectBuilder verifPers = Json.createObjectBuilder();
        JsonObjectBuilder verifArme = Json.createObjectBuilder();
        JsonObjectBuilder verifEnchant = Json.createObjectBuilder();

        verifPers.add("Type Personnage", TypePerso.Guerrier.toString());
        verifPers.add("hp", 5);
        verifPers.add("mp", 0);
        verifArme.add("Type d'arme", TypeArme.Contondant.toString());
        verifArme.add("Degats", 3);
        verifEnchant.add("Nom", EnchantDegatPlus.class.getCanonicalName());
        verifEnchant.add("Description", "Cette carte augmente les degats de l'arme choisi par un.");
        verifArme.add("Enchantement actif #1", verifEnchant.build());
        verifPers.add("Arme personnage", verifArme.build());
        assertEquals(pers.toJSONTest(), verifPers.build());
    }
    
}

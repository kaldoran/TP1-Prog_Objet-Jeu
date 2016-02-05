/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.EnchantUtils.EnchantDegatPlus;
import java.util.ArrayList;
import java.util.List;
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
        assertEquals(true, true);
        System.out.print(deck.get(0).toJSON().toString());
        System.out.print(deck.get(1).toJSON().toString());
        System.out.print(deck.get(2).toJSON().toString());

        
    }
    
}

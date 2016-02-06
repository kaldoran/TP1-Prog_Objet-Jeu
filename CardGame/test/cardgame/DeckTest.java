/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.Iterator;
import java.util.List;
import javax.json.JsonObject;
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
public class DeckTest {
    
    public DeckTest() {
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
     * Test of piocherCarte method, of class Deck.
     */
    @Test
    public void testPiocherCarte() {
        System.out.println("piocherCarte");
        int nbCartes = 5;
        Deck instance = new Deck();
        List<Card> result = instance.piocherCarte(nbCartes);
        assertEquals(5, result.size());
        assertEquals(27, instance.carteRestantes());        
        result = instance.piocherCarte(3);
        assertEquals(3, result.size());
        assertEquals(24, instance.carteRestantes());
        System.out.println("Les cartes sont correctement pigés.");
        result = instance.piocherCarte(nbCartes);
        result = instance.piocherCarte(nbCartes);
        result = instance.piocherCarte(nbCartes);
        result = instance.piocherCarte(nbCartes);
        result = instance.piocherCarte(nbCartes);
        assertEquals(4, result.size());
        assertEquals(0, instance.carteRestantes());
        System.out.println("On peut piocher des cartes jusqu'à la dernière possible.");
        
    }

    /**
     * Test of dommageJoueur method, of class Deck.
     */
    @Test
    public void testDommageJoueur() {
        System.out.println("dommageJoueur");
        int nbCartes = 3;
        Deck instance = new Deck();
        List<Card> result = instance.dommageJoueur(nbCartes);
        assertEquals(3, result.size());
        assertEquals(29, instance.carteRestantes());
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("Le joueur prend correctement du dommage.");
    }

   

    /**
     * Test of toJSon method, of class Deck.
     */
    @Test
    public void testToJSon() {
        System.out.println("toJSon");
        Deck instance = new Deck();
        JsonObject expResult = null;
        JsonObject result = instance.toJSon();
        assertEquals(32, result.getInt("Nombre de cartes restantes a piger"));
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("Le JSON est correct.");
    }
    
    @Test
    public void testCartesPigees() {
         System.out.println("Test cartes pigées");
        Deck instance = new Deck();
        List<Card> result = instance.piocherCarte(5);
        int id = -1;
        for (Iterator<Card> it = result.iterator(); it.hasNext();) {
            Card cd = it.next();
            assertNotSame(id, cd.getCardID());
            System.out.println(cd.toJSON());
            id = cd.getCardID();
        }
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("Toutes les cartes pigées sont uniques.");
    }
    
}
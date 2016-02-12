package cardgame;

import cardgame.API.Deck;
import cardgame.Cartes.Carte;
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
 * Classes de tests Junit portant sur l'instantiation d'un Deck et son
 * utilisation.
 *
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
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

        // Création d'un nouveau deck comme celui d'une partie Normale
        Deck instance = new Deck();

        // On essai de piocher des 5 Cartes
        List<Carte> result = instance.piocherCarte(nbCartes);

        // Verification des méthodes de pioches
        assertEquals(5, result.size());
        assertEquals(27, instance.carteRestantes());

        // On essai de piocher 3 cartes
        result = instance.piocherCarte(3);
        assertEquals(3, result.size());

        // On verifie qu'il reste bien autant de carte qu'il devrait en avoir
        assertEquals(24, instance.carteRestantes());
        System.out.println("Les cartes sont correctement pigés.");

        // On pioche toutes les cartes réstante, en les piochants 5 par 5.
        result = instance.piocherCarte(nbCartes);
        result = instance.piocherCarte(nbCartes);
        result = instance.piocherCarte(nbCartes);
        result = instance.piocherCarte(nbCartes); // 20 cartes piochées
        result = instance.piocherCarte(nbCartes); // On Essai d'en piocher 5 ( il en reste que 4)
        assertEquals(4, result.size()); // On en recoit bien que 4
        assertEquals(0, instance.carteRestantes()); // La pioche est vide.
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
        List<Carte> result = instance.dommageJoueur(nbCartes);
        assertEquals(3, result.size());
        assertEquals(29, instance.carteRestantes());
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
        System.out.println("Le JSON est correct.");
    }

    @Test
    public void testCartesPigees() {
        System.out.println("Test cartes pigées");
        Deck instance = new Deck();
        List<Carte> result = instance.piocherCarte(5);
        int id = -1;
        for (Iterator<Carte> it = result.iterator(); it.hasNext();) {
            Carte cd = it.next();
            assertNotSame(id, cd.getCardID());
            System.out.println(cd.toJSON());
            id = cd.getCardID();
        }
        System.out.println("Toutes les cartes pigées sont uniques.");
    }

}

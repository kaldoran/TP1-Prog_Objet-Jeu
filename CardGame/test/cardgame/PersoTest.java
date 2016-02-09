package cardgame;

import cardgame.Regles.TypeArme;
import cardgame.Cartes.Enchant;
import cardgame.Cartes.Arme;
import cardgame.Cartes.Perso;
import cardgame.Cartes.EnchantDegatPlus;
import cardgame.Cartes.EnchantFacile;
import cardgame.Cartes.EnchantNeutre;
import cardgame.Init.GuerrierBuilder;
import cardgame.Init.PaladinBuilder;
import cardgame.Init.PretreBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classes de tests Junit portant sur les fonctions d'un personnages. Ces tests
 * permet en même temps de tester les fonctions de Enchant et d'Arme.
 *
 *
 * @author Mathieu Gravel GRAM02099206
 * @author Nicolas Reynaud REYN23119308
 * @version 1.0
 *
 * 08-Fév-2016 : 1.0 - Version initiale.
 */
public class PersoTest {

    public PersoTest() {
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
     * Test of forceAttaque method, of class Perso.
     */
    @Test
    public void testForceAttaque_0args() {
        System.out.println("forceAttaque");

        PaladinBuilder pal = new PaladinBuilder();
        PretreBuilder pre = new PretreBuilder();

        Perso paladin = pal.buildNewPerso();
        Perso pretre = pre.buildNewPerso();

        Arme arm1 = new Arme(TypeArme.Contondant, 1);
        Arme arm2 = new Arme(TypeArme.Contondant, 2);

        Enchant ench = new EnchantDegatPlus();

        paladin.placerArme(arm1);
        pretre.placerArme(arm2);

        int expResult = 1;
        int result = paladin.forceAttaque();
        assertEquals(expResult, result);

        expResult = 2;
        result = pretre.forceAttaque();
        assertEquals(expResult, result);

        pretre.ajouterEnchant(ench);
        expResult++;
        result = pretre.forceAttaque();
        assertEquals(expResult, result);

        System.out.println("Le calculs des dommages direct sont corrects.");
    }

    /**
     * Test of forceAttaque method, of class Perso.
     */
    @Test
    public void testForceAttaque_Perso() {
        System.out.println("forceAttaque sur un/des ennemi");

        PaladinBuilder pal = new PaladinBuilder();
        PretreBuilder pre = new PretreBuilder();
        GuerrierBuilder gue = new GuerrierBuilder();

        Perso paladin = pal.buildNewPerso();
        Perso pretre = pre.buildNewPerso();
        Perso guerrier = gue.buildNewPerso();

        Arme armC = new Arme(TypeArme.Contondant, 1);
        Arme armT = new Arme(TypeArme.Tranchant, 2);
        Arme armP = new Arme(TypeArme.Perforant, 2);

        paladin.placerArme(armT);
        pretre.placerArme(armC);
        guerrier.placerArme(armP);

        int expResult = 0;
        int result = pretre.forceAttaque(paladin);
        assertEquals(expResult, result);

        expResult = 2;
        result = pretre.forceAttaque(guerrier);
        assertEquals(expResult, result);

        expResult = 3;
        result = paladin.forceAttaque(pretre);
        assertEquals(expResult, result);

        expResult = 1;
        result = paladin.forceAttaque(guerrier);
        assertEquals(expResult, result);

        expResult = 3;
        result = guerrier.forceAttaque(paladin);
        assertEquals(expResult, result);

        expResult = 1;
        result = guerrier.forceAttaque(pretre);
        assertEquals(expResult, result);

        System.out.println("Le calculs des dommages sur les ennemis sont corrects.");
    }

    /**
     * Test of forceAttaque method, of class Perso.
     */
    @Test
    public void testForceAttaque_Perso_Ench() {
        System.out.println("forceAttaque sur un/des ennemi avec enchantements de dégats et neutralité.");
        PaladinBuilder pal = new PaladinBuilder();
        GuerrierBuilder gue = new GuerrierBuilder();

        Perso paladin = pal.buildNewPerso();
        Perso guerrier = gue.buildNewPerso();

        Arme armC = new Arme(TypeArme.Contondant, 1);
        Arme armT = new Arme(TypeArme.Tranchant, 2);

        paladin.placerArme(armC);
        guerrier.placerArme(armT);

        int expResult = 0;
        int result = paladin.forceAttaque(guerrier);
        assertEquals(expResult, result);
        expResult = 1;
        Enchant enchDeg = new EnchantDegatPlus();
        Enchant enchNeut = new EnchantNeutre();
        paladin.ajouterEnchant(enchDeg);
        result = paladin.forceAttaque(guerrier);
        assertEquals(expResult, result);
        expResult = 2;
        guerrier.ajouterEnchant(enchNeut);
        result = paladin.forceAttaque(guerrier);
        assertEquals(expResult, result);

        System.out.println("Le calculs des dommages sur les ennemis avec enchantements sont corrects.");
    }

    /**
     * Test of prendreDommage method, of class Perso.
     */
    @Test
    public void testPrendreDommage() {
        System.out.println("prendreDommage");
        PretreBuilder pre = new PretreBuilder();
        GuerrierBuilder gue = new GuerrierBuilder();

        Perso pretre = pre.buildNewPerso();
        Perso guerrier = gue.buildNewPerso();

        Arme armP = new Arme(TypeArme.Perforant, 2);
        Arme armC = new Arme(TypeArme.Contondant, 1);

        pretre.placerArme(armC);
        guerrier.placerArme(armP);

        guerrier.prendreDommage(pretre.forceAttaque(guerrier), pretre.getCardID());
        assertEquals(guerrier.estMort(), false);
        guerrier.prendreDommage(pretre.forceAttaque(guerrier), pretre.getCardID());
        assertEquals(guerrier.estMort(), false);
        guerrier.prendreDommage(pretre.forceAttaque(guerrier), pretre.getCardID());
        assertEquals(guerrier.estMort(), true);

        System.out.println("Le perso est mort seulement quand il devait mourir.");
    }

    @Test
    public void PlacerArmesTest() {
        System.out.println("Placer Armes");
        PretreBuilder pre = new PretreBuilder();
        GuerrierBuilder gue = new GuerrierBuilder();

        Perso pretre = pre.buildNewPerso();
        Perso pretre2 = pre.buildNewPerso();
        Perso guerrier = gue.buildNewPerso();

        Arme armT = new Arme(TypeArme.Tranchant, 2);
        Arme armP = new Arme(TypeArme.Perforant, 1);
        Arme armC = new Arme(TypeArme.Contondant, 1);

        assertEquals(guerrier.placerArme(armT), true);
        assertEquals(guerrier.getTypeArme(), TypeArme.Tranchant);
        assertEquals(guerrier.placerArme(armP), false);
        assertEquals(pretre.placerArme(armP), false);
        assertEquals(pretre.getTypeArme(), null);
        assertEquals(pretre.placerArme(armC), true);
        assertEquals(pretre.getTypeArme(), TypeArme.Contondant);
        Enchant ench = new EnchantFacile();
        armP.ajouterEnchant(ench);
        assertEquals(pretre2.placerArme(armP), true);
        assertEquals(pretre2.getTypeArme(), TypeArme.Perforant);

        System.out.println("Les personnages peuvent placer correctement leurs armes.");
    }

    /**
     * Test of soigner method, of class Perso.
     */
    @Test
    public void testSoigner() {
        System.out.println("soigner");
        System.out.println("prendreDommage");
        PaladinBuilder pal = new PaladinBuilder();
        GuerrierBuilder gue = new GuerrierBuilder();

        Perso paladin = pal.buildNewPerso();
        Perso guerrier = gue.buildNewPerso();

        Arme armT = new Arme(TypeArme.Tranchant, 2);
        Arme armP = new Arme(TypeArme.Perforant, 1);

        paladin.placerArme(armP);
        guerrier.placerArme(armT);

        guerrier.prendreDommage(paladin.forceAttaque(guerrier), paladin.getCardID());
        assertEquals(guerrier.estMort(), false);
        guerrier.prendreDommage(paladin.forceAttaque(guerrier), paladin.getCardID());
        assertEquals(guerrier.estMort(), false);
        paladin.soigner(guerrier);
        guerrier.prendreDommage(paladin.forceAttaque(guerrier), paladin.getCardID());
        assertEquals(guerrier.estMort(), false);

        System.out.println("Le guerrier a survecu a une attaque qu'il l'aurait tué sans soins.");
        guerrier.prendreDommage(paladin.forceAttaque(guerrier), paladin.getCardID());
        assertEquals(guerrier.estMort(), false);

        paladin.soigner(guerrier);
        guerrier.prendreDommage(paladin.forceAttaque(guerrier), paladin.getCardID());
        assertEquals(guerrier.estMort(), true);
        System.out.println("Le paladin n'a pas réussi à soigner sans points de mana.");

    }

}

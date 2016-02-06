/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.EnchantUtils.EnchantDegatPlus;
import cardgame.EnchantUtils.EnchantNeutre;
import cardgame.Init.ArmeFactory;
import cardgame.Init.GuerrierBuilder;
import cardgame.Init.PaladinBuilder;
import cardgame.Init.PretreBuilder;
import cardgame.ResultUtils.AttackResult;
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
        Perso instance1 = pal.buildNewPerso();
        Perso instance2 = pre.buildNewPerso();
        Arme arm1 = new Arme(TypeArme.Contondant,1);
        Arme arm2 = new Arme(TypeArme.Tranchant,2);
        Enchant ench = new EnchantDegatPlus();
        instance1.placerArme(arm1);
        instance2.placerArme(arm2);
        int expResult = 1;
        int result = instance1.forceAttaque();
        assertEquals(expResult, result);
        expResult = 2;
        result = instance2.forceAttaque();
        assertEquals(expResult, result);
        instance2.ajouterEnchant(ench);
        expResult++;
        result = instance2.forceAttaque();
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
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

        
        Arme armC = new Arme(TypeArme.Contondant,1);
        Arme armT = new Arme(TypeArme.Tranchant,2);
        Arme armP = new Arme(TypeArme.Perforant,2);
        
        paladin.placerArme(armC);
        pretre.placerArme(armT);
        guerrier.placerArme(armP);
        
        int expResult = 0;
        int result = paladin.forceAttaque(pretre);
        assertEquals(expResult, result);
        expResult = 2;
        result = paladin.forceAttaque(guerrier);
        assertEquals(expResult, result);
        expResult = 3;
        result = pretre.forceAttaque(paladin);
        assertEquals(expResult, result);
        expResult = 1;
        result = pretre.forceAttaque(guerrier);
        assertEquals(expResult, result);
        expResult = 3;
        result = guerrier.forceAttaque(pretre);
        assertEquals(expResult, result);
        expResult = 1;
        result = guerrier.forceAttaque(paladin);
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        System.out.println("Le calculs des dommages sur les ennemis sont corrects.");
    }
    
     /**
     * Test of forceAttaque method, of class Perso.
     */
    @Test
    public void testForceAttaque_Perso_Ench() {
        System.out.println("forceAttaque sur un/des ennemi avec enchantements de dégats et neutralité.");
        PaladinBuilder pal = new PaladinBuilder();
        PretreBuilder pre = new PretreBuilder();
        GuerrierBuilder gue = new GuerrierBuilder();
        
        Perso paladin = pal.buildNewPerso();
        Perso pretre = pre.buildNewPerso();

        
        Arme armC = new Arme(TypeArme.Contondant,1);
        Arme armT = new Arme(TypeArme.Tranchant,2);
        
        paladin.placerArme(armC);
        pretre.placerArme(armT);
        
        int expResult = 0;
        int result = paladin.forceAttaque(pretre);
        assertEquals(expResult, result);
        expResult = 1;
        Enchant enchDeg = new EnchantDegatPlus();
        Enchant enchNeut = new EnchantNeutre();
        paladin.ajouterEnchant(enchDeg);
        result = paladin.forceAttaque(pretre);
        assertEquals(expResult, result);
        expResult = 2;
        pretre.ajouterEnchant(enchNeut);
        result = paladin.forceAttaque(pretre);
        assertEquals(expResult, result);
        

        // TODO review the generated test code and remove the default call to fail.
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

        
        Arme armT = new Arme(TypeArme.Tranchant,2);
        Arme armP = new Arme(TypeArme.Perforant,1);
        
        pretre.placerArme(armP);
        guerrier.placerArme(armT);
        
        guerrier.prendreDommage(pretre.forceAttaque(guerrier));
        assertEquals(guerrier.estMort(),false);
        guerrier.prendreDommage(pretre.forceAttaque(guerrier));
        assertEquals(guerrier.estMort(),false);
        guerrier.prendreDommage(pretre.forceAttaque(guerrier));
        assertEquals(guerrier.estMort(),true);
        
        System.out.println("Le perso est mort seulement quand il devait mourir.");
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

        
        Arme armT = new Arme(TypeArme.Tranchant,2);
        Arme armP = new Arme(TypeArme.Perforant,1);
        
        paladin.placerArme(armP);
        guerrier.placerArme(armT);
        
        guerrier.prendreDommage(paladin.forceAttaque(guerrier));
        assertEquals(guerrier.estMort(),false);
        guerrier.prendreDommage(paladin.forceAttaque(guerrier));
        assertEquals(guerrier.estMort(),false);
        paladin.soigner(guerrier);
        guerrier.prendreDommage(paladin.forceAttaque(guerrier));
        assertEquals(guerrier.estMort(),false);
        
        System.out.println("Le guerrier a survecu a une attaque qu'il l'aurait tué sans soins.");
        guerrier.prendreDommage(paladin.forceAttaque(guerrier));
        assertEquals(guerrier.estMort(),false);

        paladin.soigner(guerrier);
        guerrier.prendreDommage(paladin.forceAttaque(guerrier));
        assertEquals(guerrier.estMort(),true);
        System.out.println("Le paladin n'a pas réussi à soigner sans points de mana.");
        
    }

  

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.EnchantUtils;

import cardgame.Arme;
import cardgame.Enchant;
import cardgame.Init.GuerrierBuilder;
import cardgame.Init.PretreBuilder;
import cardgame.Perso;
import cardgame.Result;
import cardgame.TypeArme;
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
public class EnchantTest {
    
    public EnchantTest() {
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
     * Test of placerEnchant method, of class EnchantDegatMoins.
     */
    @Test
    public void testPlacerEnchantDegatPlus() {
        System.out.println("placerEnchant Degat supplémentaire");
        Arme arme = new Arme(TypeArme.Contondant,1);
        Enchant ench = new EnchantDegatPlus();
        assertEquals(arme.forceAttaque(TypeArme.Neutre), 1);
        arme.ajouterEnchant(ench);
        assertEquals(arme.forceAttaque(TypeArme.Neutre), 2);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("Enchantement Degat Plus fonctionne");
    }
    
    /**
     * Test of placerEnchant method, of class EnchantDegatMoins.
     */
    @Test
    public void testPlacerEnchantDegatMoins() {
        System.out.println("placerEnchant Degat abaissé");
        Arme arme = new Arme(TypeArme.Contondant,1);
        Enchant ench = new EnchantDegatMoins();
        assertEquals(arme.forceAttaque(TypeArme.Neutre), 1);
        arme.ajouterEnchant(ench);
        assertEquals(arme.forceAttaque(TypeArme.Neutre), 0);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("Enchantement Degat Moins fonctionne");
    }
    
   
    /**
     * Test of placerEnchant method, of class EnchantDegatMoins.
     */
    @Test
    public void testPlacerEnchantFacile() {
        System.out.println("placerEnchant Facilité d'utilisation de l'arme");
        Arme arme = new Arme(TypeArme.Tranchant,1);
        PretreBuilder pre = new PretreBuilder();
        Perso pretre = pre.buildNewPerso();
        Enchant ench = new EnchantFacile();
        assertEquals(pretre.placerArme(arme), false);
        assertEquals(pretre.getTypeArme(), null);
        arme.ajouterEnchant(ench);
        assertEquals(pretre.placerArme(arme), true);
        assertEquals(pretre.getTypeArme(), TypeArme.Tranchant);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("Enchantement Facilité d'utilisation fonctionne");
    }
    
    /**
     * Test of placerEnchant method, of class EnchantDegatMoins.
     */
    @Test
    public void testPlacerEnchantNeutre() {
        System.out.println("placerEnchant Neutralité de l'arme");
        Arme armeT = new Arme(TypeArme.Tranchant,1);
        Arme armeP = new Arme(TypeArme.Perforant,1);
        Arme armeC = new Arme(TypeArme.Contondant,1);
        GuerrierBuilder gue = new GuerrierBuilder();
        Perso guerrier1 = gue.buildNewPerso();
        Perso guerrier2 = gue.buildNewPerso();
        Perso guerrier3 = gue.buildNewPerso();
        Enchant ench = new EnchantNeutre();
        guerrier1.placerArme(armeT);
        guerrier2.placerArme(armeP);
        guerrier3.placerArme(armeC);

        assertEquals(guerrier1.forceAttaque(guerrier2), 0);
        assertEquals(guerrier1.forceAttaque(guerrier3), 2);
        guerrier1.ajouterEnchant(ench);
        assertEquals(guerrier1.forceAttaque(guerrier2), 1);
        assertEquals(guerrier1.forceAttaque(guerrier3), 1);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("Enchantement Neutralité fonctionne");
    }
    
    /**
     * Test of placerEnchant method, of class EnchantDegatMoins.
     */
    @Test
    public void testPlacerEnchantStase() {
         System.out.println("placerEnchant Stases de l'arme");
        Arme armeT = new Arme(TypeArme.Tranchant,1);
        Arme armeC = new Arme(TypeArme.Contondant,1);
        GuerrierBuilder gue = new GuerrierBuilder();
        Perso guerrier1 = gue.buildNewPerso();
        Perso guerrier2 = gue.buildNewPerso();
        Enchant enchNeutre = new EnchantNeutre();
        Enchant enchDeg = new EnchantDegatPlus();
        Enchant enchStase = new EnchantStase();
        Enchant enchDegPlus = new EnchantDegatPlus();

        guerrier1.placerArme(armeC);
        guerrier2.placerArme(armeT);
        Result resultat;
        assertEquals(guerrier1.forceAttaque(guerrier2), 0);
        resultat = guerrier1.ajouterEnchant(enchNeutre);
        assertEquals(resultat.getClass().getName(),"cardgame.ResultUtils.EnchantResult");
        assertEquals(guerrier1.forceAttaque(guerrier2), 1);
        resultat = guerrier1.ajouterEnchant(enchDeg);
        assertEquals(resultat.getClass().getName(),"cardgame.ResultUtils.EnchantResult");        
        assertEquals(guerrier1.forceAttaque(guerrier2), 2);
        resultat = guerrier1.ajouterEnchant(enchStase);
        assertEquals(resultat.getClass().getName(),"cardgame.ResultUtils.EnchantResult");
        assertEquals(guerrier1.forceAttaque(guerrier2), 0);
        resultat = guerrier1.ajouterEnchant(enchDegPlus);
        assertEquals(resultat.getClass().getName(),"cardgame.ResultUtils.RefusedResult");
        assertEquals(guerrier1.forceAttaque(guerrier2), 0);

        // TODO review the generated test code and remove the default call to fail.
        System.out.println("Enchantement Stase fonctionne");
    }
    
}
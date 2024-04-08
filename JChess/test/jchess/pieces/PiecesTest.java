/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package jchess.pieces;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matteo.ambrosone
 */
public class PiecesTest {
    
    public PiecesTest() {
    }

    @org.junit.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.junit.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getX method, of class Pieces.
     */
    @org.junit.Test
    public void testGetX() {
        System.out.println("getX");
        Pieces instance = new PiecesImpl();
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setX method, of class Pieces.
     */
    @org.junit.Test
    public void testSetX() {
        System.out.println("setX");
        int x = 0;
        Pieces instance = new PiecesImpl();
        instance.setX(x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class Pieces.
     */
    @org.junit.Test
    public void testGetY() {
        System.out.println("getY");
        Pieces instance = new PiecesImpl();
        int expResult = 0;
        int result = instance.getY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setY method, of class Pieces.
     */
    @org.junit.Test
    public void testSetY() {
        System.out.println("setY");
        int y = 0;
        Pieces instance = new PiecesImpl();
        instance.setY(y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Pieces.
     */
    @org.junit.Test
    public void testGetName() {
        System.out.println("getName");
        Pieces instance = new PiecesImpl();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Pieces.
     */
    @org.junit.Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Pieces instance = new PiecesImpl();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSide method, of class Pieces.
     */
    @org.junit.Test
    public void testGetSide() {
        System.out.println("getSide");
        Pieces instance = new PiecesImpl();
        String expResult = "";
        String result = instance.getSide();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSide method, of class Pieces.
     */
    @org.junit.Test
    public void testSetSide() {
        System.out.println("setSide");
        String side = "";
        Pieces instance = new PiecesImpl();
        instance.setSide(side);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canMove method, of class Pieces.
     */
    @org.junit.Test
    public void testCanMove() {
        System.out.println("canMove");
        String move = "";
        Pieces instance = new PiecesImpl();
        boolean expResult = false;
        boolean result = instance.canMove(move);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class Pieces.
     */
    @org.junit.Test
    public void testFind() {
        System.out.println("find");
        String letter = "";
        Pieces instance = new PiecesImpl();
        int expResult = 0;
        int result = instance.find(letter);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class PiecesImpl extends Pieces {
    }
    
}

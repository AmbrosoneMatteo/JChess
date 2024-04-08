/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package jchess.pieces;

import jchess.Game;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author grimm
 */
public class PawnTest {
    Pawn pawn;
    public PawnTest() {
        pawn = new Pawn("a2","w");
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
     * Test of canMove method, of class Pawn.
     */
    @Test
    public void testCanMoveInitial_2Forward() {
        pawn = new Pawn("d2", "w");
        assertTrue(pawn.canMove("d4"));
    }
    @Test
    public void testBlackCanMoveInitial_2Forward() {
        pawn = new Pawn("d7", "b");
        assertTrue(pawn.canMove("d5"));
    }
    @Test
    public void testCanMoveInitialForward() {
        pawn.setX(1);
        pawn.setY(1);
        assertTrue(pawn.canMove("b3"));
    }
    @Test
    public void testCanTake() {
        pawn = new Pawn("d4","w");
        Game.setPiece(pawn);
        Pawn black_pawn = new Pawn("e5","b");
        Game.setPiece(black_pawn);
        assertTrue(pawn.canMove("e5"));
    }

    /**
     * Test of canMove method, of class Pawn.
     */
    @org.junit.Test
    public void testCanMove() {
        System.out.println("canMove");
        String move = "";
        Pawn instance = null;
        boolean expResult = false;
        boolean result = instance.canMove(move);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

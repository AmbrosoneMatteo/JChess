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
}

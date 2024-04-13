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
    Game game;
    Pieces[][] table;
    public PawnTest() {
        game = new Game("normal");
        pawn = new Pawn("a2","w");
        game.setPiece(pawn);
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
        game = new Game("normal");
        pawn = new Pawn("d2", "w");
        game.setPiece(pawn);
        assertTrue(pawn.canMove("d4",game.getTable()));
    }
    @Test
    public void testBlackCanMoveInitial_2Forward() {
        game = new Game("normal");
        pawn = new Pawn("d7", "b");
        game.setPiece(pawn);
        assertTrue(pawn.canMove("d5",game.getTable()));
    }
    @Test
    public void testCanMoveInitialForward() {
        game = new Game("normal");
        pawn = new Pawn("b1", "w");
        game.setPiece(pawn);
        assertTrue(pawn.canMove("b2",game.getTable()));
    }
    @Test
    public void testCanTake() {
        game = new Game("normal");
        pawn = new Pawn("d4","w");
        game.setPiece(pawn);
        Pawn black_pawn = new Pawn("e5","b");
        game.setPiece(black_pawn);
        assertTrue(pawn.canMove("e5",game.getTable()));
    }

    /**
     * Test of canMove method, of class Pawn.
     */
    @org.junit.Test
    public void testCanMove() {

    }
}

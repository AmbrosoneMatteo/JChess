/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package jchess.pieces;

import jchess.Game;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
//import static org.junit.Assert.*;

/**
 *
 * @author grimm
 */
public class BishopTest {
    Game game;
    Bishop bishop;
    public BishopTest() {
        game = new Game("test");
        bishop = new Bishop("e5","w");
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
     * Test of canMove method, of class Bishop.
     */
    @Test
    public void testCanMove() {
        String[] valid_moves= {"f4","g3","h2","d4","d6","f6","g7","a1"};
        String[] invalid_moves = {"z3","b1","c2","a8","c0"};
        Pieces[][] table = game.getTable();
        game.setPiece(bishop);
        for(int i =0; i<valid_moves.length; i++) {
            assertTrue(bishop.canMove(valid_moves[i],table));
        }
        for(int i = 0; i<invalid_moves.length;i++) {
            assertFalse(bishop.canMove(invalid_moves[i],table));
        }
    }
   @Test
    public void testIsSomeoneInTheMiddle() {
        game = new Game("nortmal");
        System.out.println("//-->Starting testMove()");
        String[] moves = {"d2d4","d7d5","b1c3","g8f6","c1f4", "b8c6","g1f3","e7e6","e2e3","f8b4"};
        for(int i = 0; i<moves.length;i++) {
            assertTrue("move ->"+moves[i]+" is invalid",game.move(moves[i]));
        }
        assertTrue(game.getTable()[3][1].isSomeoneInTheMiddle("e1", game.getTable()));
        System.out.println("//-->Ending testMove()");
    }
}

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

/**
 *
 * @author grimm
 */
public class QueenTest {
    Queen queen;
    Game game;
    public QueenTest() {
        queen = new Queen("e5","w");
        
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
     * Test of canMove method, of class Queen.
     */
    @Test
    public void testCanMove() {
        game = new Game("test");
        game.setPiece(queen);
        String[] valid_moves = {"f4","g3","h2","d4","d6","f6","g7","a1","e1","e8","a5","h5"};
        String[] invalid_moves = {"b1","c2","c4","c6","a8","b7"};
        for(int i =0; i<valid_moves.length; i++) {
            assertTrue(queen.canMove(valid_moves[i],game.getTable()));
        }
        for(int i = 0; i<invalid_moves.length;i++) {
            assertFalse(queen.canMove(invalid_moves[i],game.getTable()));
        }
    }    
}

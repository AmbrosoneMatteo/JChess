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

import java.beans.Transient;

/**
 *
 * @author matteo.ambrosone
 */
public class KingTest {
    King king;
    public KingTest() {
        king = new King("e4","w");
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
     * Test of canMove method, of class King.
     */
    @org.junit.Test
    public void testCanMove() {
        Game game = new Game("test");
        String[] valid_moves = {"e5","e3","d4","d3","d5","f3","f4","f5"};
        String[] invalid_moves = {"a5","b1","h6","g7","h8","v3","z4","g5"};
        for(int i = 0;i<valid_moves.length;i++){
            assertTrue("move -> "+valid_moves[i]+" is invalid",king.canMove(valid_moves[i],game.getTable()));
        }
    }
    @Test
    public void testCantMove() {
        Game game = new Game("test");
        Bishop bishop = new Bishop("e5","b");
        game.setPiece(king);
        game.setPiece(bishop);
        String[] invalid_moves = {"d4","f4"};
        for (int i = 0; i<invalid_moves.length; i++) {
            int x_dest = bishop.find(invalid_moves[i].substring(0,1));
            int y_dest = Integer.parseInt(invalid_moves[i].substring(1,2))-1;
            assertTrue(king.underCheck(x_dest,y_dest,game.getTable()));
        }
    }
}

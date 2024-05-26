package jchess.pieces;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 * */
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
public class RookTest {
    Rook rook;
    Game game;
    public RookTest() {
        rook = new Rook("d4","w");
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
    
    @Test
    public void testCanMove() {
        game = new Game("normal");
        game.setPiece(rook);
        String[] valid_moves = {"d5", "a4", "h4","d1","d8"};
        String[] invalid_moves = {"a1", "h5", "a8","b1","e8"};
        for(int i =0; i<valid_moves.length; i++) {
            assertTrue(rook.canMove(valid_moves[i],game.getTable()));
        }
        for(int i = 0; i<invalid_moves.length;i++) {
            assertFalse(rook.canMove(invalid_moves[i],game.getTable()));
        }
    }
}

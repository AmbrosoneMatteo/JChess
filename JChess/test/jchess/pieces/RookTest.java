package jchess.pieces;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 * */
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
    public RookTest() {
        rook = new Rook("d4","w");
    }
    
    @Test
    public void testCanMove() {
        String[] valid_moves = {"d5", "a4", "h4","d1","d8"};
        String[] invalid_moves = {"a1", "h5", "a8","b1","e8"};
        for(int i =0; i<valid_moves.length; i++) {
            assertTrue(rook.canMove(valid_moves[i]));
        }
        for(int i = 0; i<invalid_moves.length;i++) {
            assertFalse(rook.canMove(invalid_moves[i]));
        }
    }
}

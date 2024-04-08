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
        String[] valid_moves = {"e5","e3","d4","d3","d5","f3","f4","f5"};
        String[] invalid_moves = {"a5","b1","h6","g7","h8","v3","z4","g5"};
        for(int i = 0;i<valid_moves.length;i++){
            assertTrue(king.canMove(valid_moves[i]));
        }
    }

    
}

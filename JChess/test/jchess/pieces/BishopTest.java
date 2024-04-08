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
//import static org.junit.Assert.*;

/**
 *
 * @author grimm
 */
public class BishopTest {
    
    Bishop bishop;
    public BishopTest() {
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
        for(int i =0; i<valid_moves.length; i++) {
            assertTrue(bishop.canMove(valid_moves[i]));
        }
        for(int i = 0; i<invalid_moves.length;i++) {
            assertFalse(bishop.canMove(invalid_moves[i]));
        }
    }
    
}

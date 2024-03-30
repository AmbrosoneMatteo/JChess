/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package jchess.pieces;

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
        pawn = new Pawn();
        pawn.side="w";
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
        pawn.setX(1);
        pawn.setY(2);
        assertTrue(pawn.canMove("b4"));
    }
    
    @Test
    public void testCanMoveInitialForward() {
        pawn.setX(1);
        pawn.setY(2);
        assertTrue(pawn.canMove("b3"));
    }
    @Test
    public void testCanTake() throws NotImplementedException {
        /**
        pawn.setX(1);
        pawn.setY(4);
        Pawn black_pawn = new Pawn();
        black_pawn.setSide("b");
        black_pawn.setY(5);
        black_pawn.setX(2);
        */
        throw new NotImplementedException("Test not implemented");
    }
}

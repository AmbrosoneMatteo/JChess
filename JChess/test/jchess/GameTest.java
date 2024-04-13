/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package jchess;

import jchess.pieces.King;
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
public class GameTest {
    Game game;
    public GameTest() {
        game = new Game("normal");
    }

    //Test of method move 
    @Test
    public void testMove() {
        String[] moves = {"d2d4","d7d5","b1c3","g8f6","c1f4", "b8c6","g1f3","e7e6","e2e3","f8b4","f1b5","b4c3"};
        for(int i = 0; i<moves.length;i++) {
            assertTrue("move ->"+moves[i]+" is invalid",game.move(moves[i]));
        }
    }
    @Test
    public void testIsSomeoneInTheMiddle() {
        game = new Game("normal");
        assertFalse(game.isSomeoneInTheMiddle(new int[] {3,6}, new int[] {3,4}, "b"));
    }
    //This test is based on the fact that the king is under check after the testMove test, if you need to change the moves in
    //testMove() please make sure the game ends in a check or checkMate or create a new Game that ends with a check
    @Test
    public void testIsKingUnderCheck() {
        if(game.getTable()[0][4].getName()=="K") {
            King king = new King("e1","w");
            assertTrue(king.underCheck(-1,-1, game.getTable()));
        }
    }
    @Test
    public void testIsKingNotUnderCheck(){
           game = new Game("normal");
           String[] moves = {"d2d4","d7d5","b1c3","g8f6","c1f4", "b8c6","g1f3","e7e6","e2e3","f8b4","f1b5"};
           if(game.getTable()[0][4].getName()=="K") {
                King king = new King("e1","w");
                assertTrue(king.underCheck(-1,-1,game.getTable()));
           }
    }
    @Test 
    public void testKingCanCastle() {
        game = new Game("normal");
        String[] moves = {"e2e4","e7e5","f1c4","b8c6","g1f3","f8c5","e1g1"};
        for(int i = 0; i<moves.length;i++) {
            assertTrue("move ->"+moves[i]+" is invalid",game.move(moves[i]));
        }
        King king = (King) game.getTable()[0][4]; 
        assertTrue(king.canCastle(game.getTable(), 6));
    }
}

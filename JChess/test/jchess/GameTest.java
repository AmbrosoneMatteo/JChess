/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package jchess;

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
        game = new Game();
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
        game = new Game();
        assertFalse(Game.isSomeoneInTheMiddle(new int[] {3,6}, new int[] {3,4}, "b"));
    }
}

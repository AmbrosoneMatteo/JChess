package jchess.pieces;

import jchess.Game;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KnightTest {
    Knight knight;
    Game game;
    public KnightTest() {
        knight = new Knight("d4", "w");
    }

    @Test
    public void testKnightCanMove(){
        game = new Game("test");
        game.setPiece(knight);
        String[] valid_moves = {"e6", "c6", "f5", "b5"};
        String[] invalid_moves = {"e5", "h8", "f2", "c4"};
        for(String m : valid_moves){
            assertTrue("test failed at: "+m,knight.canMove(m,game.getTable()));
        }
        for(String m : invalid_moves){
            assertFalse("test failed at: "+m,knight.canMove(m,game.getTable()));
        }
    }

    @Test
    public void testCanTake(){
        game = new Game("test");
        game.setPiece(knight);
        Pawn p = new Pawn("e6", "b");
        game.setPiece(p);;
        game.setPiece(knight);
        assertTrue(knight.canMove("e6",game.getTable()));
    }

    @Test
    public void testCannotTake(){
        game = new Game("test");
        game.setPiece(knight);
        Pawn p = new Pawn("e6", "w");
        game.setPiece(p);;
        game.setPiece(knight);
        assertFalse(knight.canMove("e6",game.getTable()));
    }
}

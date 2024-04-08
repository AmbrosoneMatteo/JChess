package jchess.pieces;

import jchess.Game;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KnightTest {
    Knight knight;
    public KnightTest() {knight = new Knight("d4", "w");}

    @Test
    public void testKnightCanMove(){
        String[] valid_moves = {"e6", "c6", "f5", "b5"};
        String[] invalid_moves = {"e5", "h8", "f2", "c4"};
        for(String m : valid_moves){
            assertTrue(knight.canMove(m));
        }
        for(String m : invalid_moves){
            assertFalse(knight.canMove(m));
        }
    }

    @Test
    public void testCanTake(){
        Pawn p = new Pawn("e6", "b");
        Game.setPiece(p);;
        Game.setPiece(knight);
        assertTrue(knight.canMove("e6"));
    }

    @Test
    public void testCannotTake(){
        Pawn p = new Pawn("e6", "w");
        Game.setPiece(p);;
        Game.setPiece(knight);
        assertFalse(knight.canMove("e6"));
    }
}

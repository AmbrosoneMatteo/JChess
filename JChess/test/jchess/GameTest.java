/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package jchess;

import jchess.pieces.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.beans.Transient;

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
        System.out.println("//-->Starting testMove()");
        String[] moves = {"d2d4","d7d5","b1c3","g8f6","c1f4", "b8c6","g1f3","e7e6","e2e3","f8b4","f1b5","b4c3"};
        for(int i = 0; i<moves.length;i++) {
            assertTrue("move ->"+moves[i]+" is invalid",game.move(moves[i]));
        }
        System.out.println("//-->Ending testMove()");
    }

    @Test
    public void testIsSomeoneNotInTheMiddle() {
        System.out.println("//-->Starting testIsSomeoneNotInTheMiddle()");
        String[] positions = {"a3","h8","h8"};
        game = new Game("test");
        Pieces[] pieces = {
            new Rook("h3","w"),
            new Queen("a4","b"),
            new Bishop("g5","b")
        };
        for(var piece: pieces) {
            game.setPiece(piece);
        }
        for(var move: positions) {
            assertFalse(pieces[0].isSomeoneInTheMiddle(move,game.getTable()));
        }
        System.out.println("//-->Ending testIsSomeoneNotInTheMiddle()");
    }
        @Test
    public void testIsSomeoneInTheMiddle() {
        System.out.println("//-->Starting testIsSomeoneInTheMiddle()");
        String[] positions = {"a3","h8","h8"};
        game = new Game("test");
        Pieces[] pieces = {
            new Rook("h3","w"),
            new Queen("b3","b"),
            new Bishop("g5","b"),
            new Pawn("h6","b"),
            new Pawn("h2","w") 
        };
        for(var piece: pieces) {
            game.setPiece(piece);
        }
        for(var move: positions) {
            assertTrue(pieces[0].isSomeoneInTheMiddle(move,game.getTable()));
        }
        System.out.println("//-->Ending testIsSomeoneInTheMiddle()");
    }
    //This test is based on the fact that the king is under check after the testMove test, if you need to change the moves in
    //testMove() please make sure the game ends in a check or checkMate or create a new Game that ends with a check
    @Test
    public void testIsKingUnderCheck() {
        System.out.println("//-->Starting testIsKingUnderCheck()");
        if(game.getTable()[0][4].getName()=="K") {
            King king = new King("e1","w");
            assertTrue(king.underCheck(-1,-1, game.getTable()));
        }
        System.out.println("//-->Ending testIsKingUnderCheck()");
    }
    @Test
    public void testIsKingNotUnderCheck(){
         System.out.println("//-->Starting testIsKingNotUnderCheck()");
          game = new Game("normal");
           String[] moves = {"d2d4","d7d5","b1c3","g8f6","c1f4", "b8c6","g1f3","e7e6","e2e3","f8b4","f1b5"};
           for(var move: moves) {
               assertTrue("move -> "+move+" is invalid",game.move(move));
           }
           if(game.getTable()[0][4].getName().equals("K")) {
                King king = new King("e1","w");
                assertFalse(king.underCheck(-1,-1,game.getTable()));
           }
         System.out.println("//-->Ending testIsKingNotUnderCheck()");
    }
    @Test 
    public void testKingCanCastle() {
         System.out.println("//-->Starting testKingCanCastle()");
        game = new Game("normal");
        String[] moves = {"e2e4","e7e5","f1c4","b8c6","g1f3","f8c5"};
        for(int i = 0; i<moves.length;i++) {
            assertTrue("move ->"+moves[i]+" is invalid",game.move(moves[i]));
        }
        King king = (King) game.getTable()[0][4]; 
        assertTrue(game.move("e1g1"));
        assertTrue(game.getTable()[0][5].getName().equals("R"));
        System.out.println("//-->Ending testKingCanCastle()");
    }
    @Test
    public void testKingCanCastleLong() {
        System.out.println("//-->Starting testKingCanCastle()");
        game = new Game("normal");
        String[] moves = {"d2d4","d7d5","c1f4","c8f5","b1c3","g8f6","d1d2","b8c6"};
        for(int i = 0; i<moves.length;i++) {
            assertTrue("move ->"+moves[i]+" is invalid",game.move(moves[i]));
        }
        King king = (King) game.getTable()[0][4];
        //assertTrue(king.canCastle(game.getTable(), 2));
        assertTrue(game.move("e1c1"));
        assertTrue(game.getTable()[0][3].getName().equals("R"));
        System.out.println("//-->Ending testKingCanCastle()");
    }
    //this test checks that no piece can move where its move cause his own king to be under check
    @Test 
    public void testCheckAfterMove() {
      System.out.println("//-->Starting testCheckAfterMove()");
      game = new Game("test");
        Pieces[] pieces = {new King("a4", "w"), new Knight("d4", "w"), new Rook("h4", "b")};
        for(var piece: pieces) {
            game.setPiece(piece);
        }
        assertFalse(game.move("d4d6"));
      System.out.println("//-->Starting testCheckAfterMove()");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jchess;

import jchess.pieces.Bishop;
import jchess.pieces.King;
import jchess.pieces.Knight;
import jchess.pieces.Pawn;
import jchess.pieces.Pieces;
import jchess.pieces.Queen;
import jchess.pieces.Rook;

/**
 *
 * @author matteo.ambrosone
 */
public class Game {
    final public static String red = "\033[31;1m";
    final public static String blue = "\033[34;1m";
    final public static  String reset = "\033[0m";
    final static protected String[] alphabet = {"a","b","c","d","e","f","g","h"};
    private Pieces[][] table = new Pieces[8][8];
    public int[][] king_pos = {{0,4},{7,4}};
    public boolean white_under_check = false;
    public boolean black_under_check = false;    
    static private Pieces[] pieces = {
        new Pawn("a2","w"),
        new Pawn("b2","w"),
        new Pawn("c2","w"),
        new Pawn("d2","w"),
        new Pawn("e2","w"),
        new Pawn("f2","w"),
        new Pawn("g2","w"),
        new Pawn("h2","w"),
        new Pawn("a7","b"),
        new Pawn("b7","b"),
        new Pawn("c7","b"),
        new Pawn("d7","b"),
        new Pawn("e7","b"),
        new Pawn("f7","b"),
        new Pawn("g7","b"),
        new Pawn("h7","b"),
        new Rook("a1","w"),
        new Rook("h1","w"),
        new Rook("a8","b"),
        new Rook("h8","b"),
        new Knight("b1","w"),
        new Knight("g1","w"),
        new Knight("b8","b"),
        new Knight("g8","b"),
        new Bishop("c1","w"),
        new Bishop("f1","w"),
        new Bishop("c8","b"),
        new Bishop("f8","b"),
        new Queen("d1","w"),
        new Queen("d8","b"),
        new King("e1","w"),
        new King("e8","b")
    };
    public Game(String mode){
        switch(mode) {
                case "test": break;
                default:  initialize();
        }
    }
    public  void initialize() {
        for (var piece : pieces) {
            //System.out.println(pieces[i].getX()+" - "+pieces[i].getY());
            table[piece.getY()][piece.getX()] = piece;
        }
        /**
        move("d2d4");
        System.out.print(table[3][3].getName());
        for(int i = 0; i<table.length;i++) {
        for(int l = 0; l<table.length;l++) {
        if(table[i][l]==null) {
        System.out.print(" ");
        } else {
        System.out.print(table[i][l].getName());
        }
        }
        System.out.println();
        }
         */

        /**        
        move("d2d4");
        System.out.print(table[3][3].getName());
        for(int i = 0; i<table.length;i++) {
            for(int l = 0; l<table.length;l++) {
                if(table[i][l]==null) {
                    System.out.print(" ");
                } else {
                    System.out.print(table[i][l].getName());
                }
            }
            System.out.println();
        }
         */
    }
    public static int find(String letter) {
        int output = 0;
        for(int i = 0;i<alphabet.length;i++) {
            if(alphabet[i].equals(letter)) {
                output = i;
            }
        }
        return output;
    }
    public boolean move(String move) {
        boolean output = false;
        int x = find(move.substring(0,1));
        int y = Integer.parseInt(move.substring(1,2))-1;
        int x_dest = find(move.substring(2,3));
        int y_dest = Integer.parseInt(move.substring(3,4))-1;
        if(table[y][x].canMove(move.substring(2,4),table)) {
            //if the piece can move to the destination, 
            //it gets deleted from the old position and move to the new one
            Pieces piece = table[y][x];
            table[y][x] = null;               
            piece.setX(x_dest);
            piece.setY(y_dest);
            setPiece(piece);
            output= true;
            King king=new King("a1","w");
            boolean is_white = false;
            if(table[y_dest][x_dest].getSide().equals("w")) {
                 king = (King) table[king_pos[0][0]][king_pos[0][1]];
                 is_white=true;
            } else {
                 king = (King) table[king_pos[1][0]][king_pos[1][1]];
            }
            //it checks if are the towers or the kings that are tryng to move 
            //if that so it cancels the ability of the king to caste later on in the game
            //the piece that is controlled must be able to move otherwise is useless to check if the king can't blunder anymore
            if((((y==0)&&(is_white))||((y==7)&&(!is_white)))&&((king.can_castle_long)||(king.can_castle_short))&&output&&((piece.getName().equals("R"))||(piece.getName().equals("K")))) {
                if(x==0) {
                  if((piece.getName().equals("R"))) {
                    if(is_white) {
                            king.can_castle_long=false;
                        } else {
                            king.can_castle_short=false;
                        }
                    }  else if(piece.getName().equals("K")) {
                            king.can_castle_short=false;
                            king.can_castle_long=false;
                    }
                } else if(x==7) {
                 if((piece.getName().equals("R"))) {
                    if(is_white) {
                            king.can_castle_short=false;
                    } else {
                            king.can_castle_long=false;
                    }
                 }else if(piece.getName().equals("K")) {
                            king.can_castle_short=false;
                            king.can_castle_long=false;
                  }
               }
               //if after the move the player king is under check it means that the piece that the ue moved was
               //in between the king and its checker, so it's an invalid move, if the piece moved is the king no checks is needed
            } if((!piece.getName().equals("K"))&&(king.underCheck(-1, -1, table))) {
                    piece.setX(x_dest);
                    piece.setY(y_dest);
                    table[y_dest][x_dest] = null;      
                    setPiece(piece);
                    output= false;
            }
        }
        printTerminalChessboard();
        return output;
    }
    public Pieces[][] getStaticTable() {
        return table; 
    }
    public Pieces[][] getTable() {
        return table; 
    }
    public void setPiece(Pieces Piece) {
        table[Piece.getY()][Piece.getX()] = Piece;
    }
    public void printTerminalChessboard() {
        boolean white_cell = false;
        for(var i: table) {
            for(var l: i) {
                if(l!=null) {
                    if(l.getSide().equals("w")) {
                        System.out.print(blue+l.getName()+reset);
                    } else {
                        System.out.print(blue+l.getName()+reset);
                    }
                } else {
                    if(white_cell) {
                        System.out.print("#");
                    } else {
                        System.out.print(" ");
                    }
                }
                if(white_cell) {white_cell=false;} else {white_cell=true;}
            }
            System.out.println();
        }
        for (int c = 0; c < 3; c++)  {
            System.out.println();
        }
    }
}

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
    private boolean whiteUnderCheck = false;
    private boolean blackUnderCheck = false;
    private boolean white_moves = true;
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
    }
    public static int find(String letter) {
        letter = letter.toLowerCase();
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
        if(table[y][x]!=null) {
            Pieces piece = table[y][x];
            if((((piece.getSide().equals("w"))&&(white_moves))||((piece.getSide().equals("b"))&&(!white_moves)))&&(table[y][x].canMove(move.substring(2,4),table))) {
                //if the piece can move to the destination,
                //it gets deleted from the old position and move to the new one
                table[y][x] = null;
                piece.setX(x_dest);
                piece.setY(y_dest);
                setPiece(piece);
                output= true;
                boolean is_white = false;
                King king = new King("a1","w");
                if (piece.getName().equals("K")) {
                    king = (King) piece;
                }
                if (piece.getSide().equals("w")) {
                    is_white=true;
                }

                //it checks if are the towers or the kings that are tryng to move
                //if that so it cancels the ability of the king to caste later on in the game
                //the piece that is controlled must be able to move otherwise is useless to check if the king can't blunder anymore
                if((((y==0)&&(is_white))||((y==7)&&(!is_white)))&&((king.can_castle_long)||(king.can_castle_short))&&output&&((piece.getName().equals("R"))||piece.getName().equals("K"))) {
                    if((piece.getName().equals("R"))) {
                        if(x==0) {
                            if(is_white) {
                                king.can_castle_long = false;
                            } else {
                                king.can_castle_short=false;
                            }
                        } else if(x==7) {
                            if(is_white) {
                                king.can_castle_short=false;
                            } else {
                                king.can_castle_long = false;
                            }
                        }
                    }
                    else if(piece.getName().equals("K")) {
                        king.can_castle_short=false;
                        king.can_castle_long=false;
                        //Now that the king has moved it moves the rook completing
                        //the castling
                        if (Math.abs(y_dest-y)==0) {
                            if (x > x_dest) {
                                Rook rook = (Rook) table[y][0];
                                table[y][0] = null;
                                rook.setX(3);
                                setPiece(rook);
                            } else {
                                Rook rook = (Rook) table[y][7];
                                table[y][7] = null;
                                rook.setX(5);
                                setPiece(rook);
                            }
                        }
                    }
                }

                //if after the move the player king is under check it means that the piece that the ue moved was
                //in between the king and its checker, so it's an invalid move, if the piece moved is the king no checks is needed
                if((!piece.getName().equals("K"))&&(king.underCheck(-1, -1, table))) {
                    piece.setX(x_dest);
                    piece.setY(y_dest);
                    table[y_dest][x_dest] = null;
                    setPiece(piece);
                    output= false;
                }else {
                    //if the move is valid and the king is not under check it pass the move to the opponent
                    if(white_moves) {
                        white_moves=false;
                    } else {
                        white_moves=true;
                    }
                }
            }
            printTerminalChessboard();
        }
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
    public Pieces getPiece(int y ,int x) {
        return table[y][x];
    }
    public void printTerminalChessboard() {
        boolean white_cell = false;
        for(int i = table.length - 1; i >= 0; i--){
            for(int j = 0; j < table[i].length; j++){
                var l = table[i][j];
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
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public boolean isWhiteUnderCheck() {
        King k = null;
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                if(table[i][j].getName().equals("K") && table[i][j].getSide().equals("w")){
                    k = (King) table[i][j];
                }
            }
        }
        whiteUnderCheck = k.underCheck(-1, -1, this.table);
        return whiteUnderCheck;
    }

    public boolean isBlackUnderCheck() {
        King k = null;
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                if(table[i][j].getName().equals("K") && table[i][j].getSide().equals("b")){
                    k = (King) table[i][j];
                }
            }
        }
        blackUnderCheck = k.underCheck(-1, -1, this.table);
        return blackUnderCheck;
    }
}

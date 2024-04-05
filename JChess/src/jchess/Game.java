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
    final static protected String[] alphabet = {"a","b","c","d","e","f","g","h"};
    static private Pieces[][] table = new Pieces[8][8];
    static private Pieces[] pieces = {
        new Pawn("a2","w"),
        new Pawn("b2","w"),
        new Pawn("c2","w"),
        new Pawn("d2","w"),
        new Pawn("e2","w"),
        new Pawn("f2","w"),
        new Pawn("g2","w"),
        new Pawn("h2","w"),
        new Pawn("a7","w"),
        new Pawn("b7","w"),
        new Pawn("c7","w"),
        new Pawn("d7","w"),
        new Pawn("e7","w"),
        new Pawn("f7","w"),
        new Pawn("g7","w"),
        new Pawn("h7","w"),
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
    public static void main(String[] args) {
        for (int i = 0;i<pieces.length;i++) {
            //System.out.println(pieces[i].getX()+" - "+pieces[i].getY());
            table[pieces[i].getY()][pieces[i].getX()] = pieces[i];
        }
        
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
    }
    /*
     * It checks if someone is in the middle between the piece and its destination
     * We don't need to check the Knight since he's able to jump over pieces
     */
    public static boolean isSomeoneInTheMiddle(int[] position, int[] destination, String side) {
        boolean output = false;
        int[] cur = {0,0};
        //if the row doesn't change we just need to cycle the piece's column to see if ther's a piece in the middle
        if((position[0] == destination[0])&&(position[1] != destination[1])) {
            cur[0] = position[0];
            for(int i  = 0;i<Math.abs(position[1]-destination[1]);i++){
                if (position[1]<destination[1]) {
                    cur[1] = position[1]-i;
                } else {
                    cur[1] = position[1]+i;
                }
                if (table[cur[1]][cur[0]]==null) {//if the cell dosn't house a piece the condition it's true
                    output=true;
                } else {
                    output = false;
                    break;
                }
            }
        } 
        //if the column doesn't change we just need to cycle the piece's row to see if ther's a piece in the middle
        else if((position[1] == destination[1]&&(position[0] != destination[0]))) {
            cur[1] = position[1];
            for(int i  = 0;i<Math.abs(position[0]-destination[0]);i++){
                if (position[1]<destination[1]) { //the piece is moving down
                    cur[0] = position[0]-i;
                } else {//the piece is moving up
                    cur[0] = position[0]+i;
                }
                if (table[cur[1]][cur[0]]==null) {//if the cell dosn't house a piece the condition it's true
                    output=true;
                } else {
                    output = false;
                    break;
                }
            }
        }
        //if the row and the column change we need to check in the diagonal line if there's a piece in the middle
        else  {
            cur[0] = position[0];
            cur[1] = position[1];
            for(int i  = 0;i<Math.abs(position[1]-destination[1]);i++){
                if (position[1]<destination[1]) { //the piece is moving down
                    if(position[0]<destination[0]) {  //the piece is moving right
                        cur[0] = position[0]-i;
                    } else {//the piece is moving left
                        cur[0] = position[0]+i;
                    }
                    cur[1] = position[1]-i;
                } else { //the piece is moving up
                    if(position[0]<destination[0]) {//the piece is moving right
                        cur[0] = position[0]-i;
                    } else {//the piece is moving left
                        cur[0] = position[0]+i;
                    }
                    cur[1] = position[1]+i;
                }
                if (table[cur[1]][cur[0]]==null) { //if the cell dosn't house a piece the condition it's true
                    output=true;
                } else {
                    output = false;
                    break;
                }
            }
        }
        return output;
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
    public static boolean move(String move) {
        boolean output = false;
        int x = find(move.substring(0,1));
        int y = Integer.parseInt(move.substring(1,2))-1;
        int x_dest = find(move.substring(2,3));
        int y_dest = Integer.parseInt(move.substring(3,4))-1;
        if(table[y][x]==null) {
            System.out.println("Error piece not found");
        } else {
            if(table[y][x].canMove(move.substring(2,4))) {
                //if the piece can move to the destination, 
                //it gets deleted from the old position and move to the new one
                Pieces piece = table[y][x];
                table[y][x] = null;
                table[y_dest][x_dest] = piece;
                output= true;
            }
        }
        return output;
    }
    public static Pieces[][] getTable() {
        return table; 
    }
    public static void setPiece(Pieces Piece) {
        table[Piece.getY()][Piece.getX()] = Piece;
    }
}

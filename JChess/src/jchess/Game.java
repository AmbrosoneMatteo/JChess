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
    static Pieces[][] table = new Pieces[8][8];
    static Pieces[] pieces = {
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
     * We don't need to check the Knight since he's able to jump over pieces
     */
    public static boolean isSomeoneInTheMiddle(int[] position, int[] destination, String side) {
        boolean output = false;
        if (!table[destination[1]][destination[0]].getSide().equals(side)) {
            output=true;
        }
        if (output) {
            for(int i = 0; i<Math.abs(position[0]-destination[0]);i++) {

            }
        }
        return output;
    }
}

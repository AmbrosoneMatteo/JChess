/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jchess.pieces;

import jchess.Game;

/**
 *
 * @author matteo.ambrosone
 */
public class Pawn extends Pieces {

    //Constructor
    public Pawn(String position, String side) {
        this.name = "p";
        x = find(position.substring(0,1));
        y = Integer.parseInt(position.substring(1,2))-1;
        this.side = side;
    }
    //check if the piece can move
    public boolean canMove(String move, Pieces[][] table) {
        boolean output = false;
        int x_dest = find(move.substring(0,1));
        int y_dest = Integer.parseInt(move.substring(1,2))-1;
        //checks if the pawn has moved of one cell, in case it hasn't moved from the star he can move of 2 cell if there isn't someone in the middle
        if((Math.abs(y-y_dest)==1)||(((side=="w")&&(y==1))||((side=="b")&&(y==6))&&(Math.abs(y-y_dest)==2))) { 
            //if the piece hasn't moved horizontaly it's certainly a valid move
            if((x_dest==x)&&(table[y_dest][x_dest]==null)){
                if(((side.equals("w"))&&(y_dest>y))||((side.equals("b"))&&(y_dest<y))) {
                     if(((Math.abs(y-y_dest)==2)&&(table[y_dest-1][x_dest]==null)||(table[y_dest+1][x_dest]==null))||(Math.abs(y-y_dest)==1)) {
                          output=true;
                     }
                }
            } else if((Math.abs(x_dest-x)==1)&&(Math.abs(y_dest-y)==1)) {
                //if the pawn moved of one cell horizontally and advanced of 1cell
                //the only way that it's a valid move is that the destination house an enemy piece

                if(!table[y_dest][x_dest].side.equals(side)) {
                    output=true;

                    // Verifica se il movimento è diagonale
                    if(Math.abs(x_dest - x) == Math.abs(y_dest - y)) {
                        int x_step = (x_dest - x) / Math.abs(x_dest - x);
                        int y_step = (y_dest - y) / Math.abs(y_dest - y);
                        int x_current = x + x_step;
                        int y_current = y + y_step;
                        boolean canCapture = false;
                    }else{
                        // Controlla se ci sono pezzi avversari nella colonna a sinistra o a destra
                        int x_left = x - 1;
                        int x_right = x + 1;
                        boolean canCaptureLeft = (x_left >= 0 && table[y][x_left] != null && !table[y][x_left].getSide().equals(table[y][x].getSide()));
                        boolean canCaptureRight = (x_right < 8 && table[y][x_right] != null && !table[y][x_right].getSide().equals(table[y][x].getSide()));
                        boolean canCapture = true;

                    }
                }
            }
        }
        return output;
    }
}

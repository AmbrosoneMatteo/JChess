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
public class Knight extends Pieces {

    public Knight(String position, String side) {
        this.name = "N";
        x = find(position.substring(0,1));
        y = Integer.parseInt(position.substring(1,2))-1;
        this.side = side;
    }
    //it checks if the piece can move
    @Override
    public boolean canMove(String move,Pieces[][] table) {
        boolean output = false;
        int x_dest = find(move.substring(0,1));
        int y_dest = Integer.parseInt(move.substring(1,2))-1;
        /**
         * if the diference between the x and x_dest is 1 the ifference between y and y_dest must be 2 and viceversa
         * any other combination is an invalid move
        */
        if (((Math.abs(y_dest-y)==1)&&(Math.abs(x_dest-x)==2))||((Math.abs(y_dest-y)==2)&&(Math.abs(x_dest-x)==1))) {
            //if the destination is empty or has an enemy piece the move is valid
            if ((table[y_dest][x_dest]==null)||(!table[y_dest][x_dest].getSide().equals(side))) {
                output = true;
            } else if (table[y_dest][x_dest].getSide().equals(side)) { //if the destination has a friendly piece the move is invalid
                output=false;
            }
        }
        return output;
    }
}

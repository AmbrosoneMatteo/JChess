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
public class Queen extends Pieces {

    public Queen(String position, String side) {
        this.name = "Q";
        x = find(position.substring(0,1));
        y = Integer.parseInt(position.substring(1,2))-1;
        this.side = side;
    }
    @Override
    public boolean canMove(String move, Pieces[][] table) {
        boolean output =false;
        int x_dest = find(move.substring(0,1));
        int y_dest = Integer.parseInt(move.substring(1,2))-1;
        //the queen can move as a Bishop or as a Rook, so we can just copy the controls from the 2 pieces
        if ((table[y_dest][x_dest]== null)||(table[y_dest][x_dest].getSide()!= side)) {
            //we must make sure that the piece remains inside in the chesboard
            if((y_dest>=0)&&(y_dest<=7)&&(y_dest>=0)&&(y_dest<=7)) { //if it's inside the chessboard
                if((x_dest==x)||(y_dest==y)) { //if the moves corrispond to the same column or row
                    output = true;
                }else if((Math.abs(y_dest-y)==Math.abs(x_dest-x))){ //if the queen is mooving diagonally
                    output=true;
                }
                if((output)&&(isSomeoneInTheMiddle(move,table))) { //if there's someone in the middle the move is invalid
                    output=false;
                }
            }
        }
        return output;
    }
}

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
    @Override
    public boolean canMove(String move) {
        boolean output = false;
        int x_dest = find(move.substring(0,1));
        int y_dest = Integer.parseInt(move.substring(1,2))-1;
        //checks if the pawn has moved of one cell, in case it hasn't moved from the star he can move of 2 cell if there isn't someone in the middle
        if((y_dest == y+1)||((((side=="w")&&(y==2)&&(Math.abs(y_dest-y)==2))||((side=="b")&&(y==7)&&(Math.abs(y_dest-y)==2))&&(!Game.isSomeoneInTheMiddle(new int[] {x,y}, new int[] {x_dest,y_dest}, side))))) { 
            //if the piece hasn't moved horizontaly it's certainly a valid move
            if(x_dest==x){
                output=true;
            } else if((Math.abs(x_dest-x)==1)&&(Math.abs(y_dest-y)==1)) {
                /**
                *if the pawn moved of one cell horizontally and advanced of 1cell
                *the only way that it's a valid move is that the destination house an enemy piece
                */
                if(!Game.getTable()[y_dest][x_dest].side.equals(side)) {
                    output=true;
                }
            }
        }
        return output;
    }
    @Override
    public boolean givesCheck() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'givesCheck'");
    }
}

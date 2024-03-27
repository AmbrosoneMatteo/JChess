/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jchess.pieces;

/**
 *
 * @author matteo.ambrosone
 */
public class Pawn extends Pieces {

    @Override
    public boolean canMove(String move) {
        boolean output = false;
        int x_dest = Integer.parseInt(move.substring(0,1));
        int y_dest = Integer.parseInt(move.substring(1,2));
        if((y_dest == y+1)||(((side=="w")&&(y==2))||((side=="b")&&(y==7)))) {
            if(x_dest==x){
                output=true;
            } else if(((x_dest==x-1)||(x_dest==x+1))) {
                output=true;
            }
        }
        return output;
    }
}

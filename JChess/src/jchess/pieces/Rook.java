/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jchess.pieces;

/**
 *
 * @author matteo.ambrosone
 */
public class Rook extends Pieces {

    @Override
    public boolean canMove(String move) {
        boolean output = false;
        int x_dest = find(move.substring(0,1));
        int y_dest = Integer.parseInt(move.substring(1,2))-1;
        if((x_dest==x)&&((y_dest>=0)&(y_dest<=7))) {
            output = true;
        } else if((y_dest==y)&&((x_dest>=0)&(x_dest<=7))) {
            output = true;
        }
        return output;
    }
}

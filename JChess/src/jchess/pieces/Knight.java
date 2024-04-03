/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jchess.pieces;

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
    @Override
    public boolean canMove(String move) {
        boolean output = false;
        int x_dest = find(move.substring(0,1));
        int y_dest = Integer.parseInt(move.substring(1,2))-1;
        if ((Math.abs(y_dest-y)==2)&&(Math.abs(x_dest-x)==1)) {
            output=true;
        }else if ((Math.abs(y_dest-y)==2)&&(Math.abs(x_dest-x)==1)) {
            output = true;
        } 
        return output;
    }
}

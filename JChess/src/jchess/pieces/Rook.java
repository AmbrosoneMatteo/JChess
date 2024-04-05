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

    public Rook(String position, String side) {
        this.name = "R";
        x = find(position.substring(0,1));
        y = Integer.parseInt(position.substring(1,2))-1;
        this.side = side;
    }
    
    @Override
    public boolean canMove(String move) {
        boolean output = false;
        int x_dest = find(move.substring(0,1));
        int y_dest = Integer.parseInt(move.substring(1,2))-1;
        //we must check that only a line chenges and that the rook remains inside the chessboard
        if((x_dest==x)&&((y_dest>=0)&(y_dest<=7))) {
            output = true;
        } else if((y_dest==y)&&((x_dest>=0)&(x_dest<=7))) {
            output = true;
        }
        return output;
    }
    @Override
    public boolean givesCheck() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'givesCheck'");
    }
}

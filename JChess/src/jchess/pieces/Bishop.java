/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jchess.pieces;

/**
 *
 * @author matteo.ambrosone
 */
public class Bishop extends Pieces {

    public Bishop(String position, String side) {
        this.name = "B";
        x = find(position.substring(0,1));
        y = Integer.parseInt(position.substring(1,2))-1;
        this.side = side;
    }
    //it checks if the difference between x and x_dest corrisponds to the difference between y and y_dest and
    //if the Bishop remains inside the chessboard
    @Override
    public boolean canMove(String move) {
        boolean output = false;
        int x_dest = find(move.substring(0,1));
        int y_dest = Integer.parseInt(move.substring(1,2))-1;
        if((Math.abs(y_dest-y)==Math.abs(x_dest-x)&&(((x_dest>=0)&&(x_dest<=7))&&((y_dest>=0)&&(y_dest>=0))))){
            output=true;
        }
        return output;
    }
    @Override
    public boolean givesCheck() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'givesCheck'");
    }
    
}

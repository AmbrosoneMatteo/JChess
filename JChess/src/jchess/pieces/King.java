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
public class King extends Pieces {

    public King(String position, String side) {
        this.name = "K";
        x = find(position.substring(0,1));
        y = Integer.parseInt(position.substring(1,2))-1;
        this.side = side;
    }
    @Override
    public boolean canMove(String move) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //it checks around if a piece is attacking the king
    public boolean underCheck() {
        boolean output = false;
        Pieces[][] table = Game.getTable();
        if (y>=2) {
            if((x>=1)&&(table[y-2][x-1]!=null)&&(table[y-2][x-1].getName().equals("N"))&&(!table[y-2][x-1].getSide().equals(side))) {
                output=true;
            } else if((x<=6)&&(table[y-2][x+1]!=null)&&(table[y-2][x+1].getName().equals("N"))&&(!table[y-2][x+1].getSide().equals(side))) {
                output=true;
            }
        } else if(y<=5) {
            if((x>=1)&&(table[y+2][x-1]!=null)&&(table[y+2][x-1].getName().equals("N"))&&(!table[y-2][x-1].getSide().equals(side))) {
                output=true;
            }else if((x<=6)&&(table[y+2][x+1]!=null)&&(table[y+2][x+1].getName().equals("N"))&&(!table[y+2][x+1].getSide().equals(side))) {
                output=true;
            }
        }
        return output;
    }
}

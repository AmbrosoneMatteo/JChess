/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jchess.pieces;

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
}

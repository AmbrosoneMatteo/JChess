/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jchess.pieces;

import static jchess.Game.find;

/**
 *
 * @author matteo.ambrosone
 */
public abstract class Pieces implements Control {
    protected int x;
    protected int y;
    protected String name;
    protected String side;
    final protected String[] alphabet = {"a","b","c","d","e","f","g","h"};
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
    /*
     * It checks if someone is in the middle between the piece and its destination
     * We don't need to check the Knight since he's able to jump over pieces
     * or the King since he's not able to move over 2 cells
     */
    public  boolean isSomeoneInTheMiddle(String destination, Pieces[][] table) {
        boolean output = false;
        int x_dest = find(destination.substring(0,1));
        int y_dest = Integer.parseInt(destination.substring(1,2))-1;
        //if the row doesn't change we just need to cycle the piece's column to see if ther's a piece in the middle
        while((x_dest!=x)&&(y_dest!=y)) {
            if(x_dest<x) {
                x_dest++;
            } else if (x_dest>x) {
                x_dest--;
            }
             if(y_dest<y) {
                y_dest++;
            } else if (y_dest>y) {
                y_dest--;
            }
            if((table[y_dest][x_dest]!=null)&&((x_dest!=x)&&(y_dest!=y))) {
                output = true;
                break;
            }
        } 
        return output;
    }
    @Override
    public boolean canMove(String move,Pieces[][] table) {
        return false;
    }
    public int find(String letter) {
        int output = 0;
        for(int i = 0;i<alphabet.length;i++) {
            if(alphabet[i].equals(letter)) {
                output = i;
            }
        }
        return output;
    }
}

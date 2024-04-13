/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jchess.pieces;

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

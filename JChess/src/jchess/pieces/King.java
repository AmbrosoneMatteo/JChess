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

    public boolean can_castle_short  = true;
    public boolean can_castle_long  = true;
    public King(String position, String side) {
        this.name = "K";
        x = find(position.substring(0,1));
        y = Integer.parseInt(position.substring(1,2))-1;
        this.side = side;
    }
    @Override
    public boolean canMove(String move,Pieces[][] table) {
        boolean output=false;
        int x_dest = find(move.substring(0,1));
        int y_dest = Integer.parseInt(move.substring(1,2))-1;
        if((table[y_dest][x_dest]==null)||(!table[y_dest][x_dest].getSide().equals(side))) {
            if((Math.abs(x_dest-x)<=1)&&(Math.abs(y_dest-y)<=1)) {
                if(!underCheck(x_dest,y_dest, table)){
                    output = true;
                }
            } else if ((Math.abs(x_dest-x)<=2)&&(Math.abs(y_dest-y)<=0)) {
                if(canCastle(table, x_dest)) {
                    output=true;
                }
            }
        }
        return output;
    }
    //it checks around if a piece is attacking the king
    public boolean underCheck(int vx, int vy, Pieces[][] table) {
        //if vx or vy is set to -1 it means it is tryng to see if the king is under check at the local position
        //otherwise it checks if the king can move to another cell without being under check
        if((vy==-1)||(vy==-1)) {
            vy=y;
            vx=x;
        }
        String move = alphabet[vx]+(vy+1);
        boolean output = false;
        for(int i = 0; i<table.length;i++) {
            if(!output) {
                for(int l = 0; l<table.length;l++) {
                    if((table[i][l]!=null)&&(!table[i][l].getSide().equals(side))) {
                        //if the enemy piece can move where the king is it means is attacking it
                        if(table[i][l].canMove(move,table)){
                            output=true;
                            break;
                        }
                    }
                }                
            } else {
                break;
            }
        }
        return output;
    }
    public boolean canCastle(Pieces[][] table, int x_dest) {
        boolean output = false;
        if((((side.equals("w"))&&(y==0))||((side.equals("b"))&&(y==7)))&&(x==4)&&(!underCheck(-1, -1, table))) {
            if((x_dest<x)&&((((side.equals("w")))&&(can_castle_long))||(((side.equals("b")))&&(can_castle_short)))) {
                for(int i = x-1; i>0;i--) {
                    if((table[y][i]!=null)||(underCheck(i,y,table))) {
                        output = false;
                        break;
                    } else {
                        output = true;
                    } 
                }
            } else if((((side.equals("w")))&&(can_castle_short))||(((side.equals("b")))&&(can_castle_long)))  {
                for(int i = x+1; i<7;i++) {
                    if((table[y][i]!=null)||(underCheck(i,y,table))) {
                        output = false;
                        break;
                    } else {
                        output = true;
                    } 
                }
            }
        }
        return output;
    }
}

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
        boolean output=false;
        int x_dest = find(move.substring(0,1));
        int y_dest = Integer.parseInt(move.substring(1,2))-1;
        if((Math.abs(x_dest-x)<=1)&&(Math.abs(y_dest-y)<=1)) {
            output = true;
        }
        /**
        if(output) {
            output = !underCheck(x_dest,y_dest);
        }*/
        return output;
    }
    //it checks around if a piece is attacking the king
    public boolean underCheck(int vx, int vy) {
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
        } else if (x>=2) {
            if((y>=1)&&(table[y-1][x-2]!=null)&&(table[y-1][x-2].getName().equals("N"))&&(!table[y-1][x-2].getSide().equals(side))) {
                output=true;
            } else if((x<=6)&&(table[y-1][x+2]!=null)&&(table[y-1][x+2].getName().equals("N"))&&(!table[y-1][x+2].getSide().equals(side))) {
                output=true;
            }
        } else if(x<=5) {
            if((x>=1)&&(table[y+1][x-2]!=null)&&(table[y+1][x-2].getName().equals("N"))&&(!table[y-1][x-2].getSide().equals(side))) {
                output=true;
            }else if((x<=6)&&(table[y+1][x+2]!=null)&&(table[y+1][x+2].getName().equals("N"))&&(!table[y+1][x+2].getSide().equals(side))) {
                output=true;
            }
        }
        if(!output) {
            for(int i = x; i<table.length;i++) {
                String piece_name = table[i][x].getName();
                if(table[y][i]!=null) {
                    if(((piece_name.equals("R"))||(piece_name.equals("Q")))&&(!table[y][i].getSide().equals(side))) {
                        output = true;
                        break;
                    }
                } 
            }
            if(!output) {
                for(int i = x; i>=0;i--) {
                    String piece_name = table[i][x].getName();
                    if(table[y][i]!=null) {
                        if(((piece_name.equals("R"))||(piece_name.equals("Q")))&&(!table[y][i].getSide().equals(side))) {
                            output = true;
                            break;
                        }
                    } 
                }
                if(!output) {
                    for(int i = y; i<table.length;i++) {
                        String piece_name = table[i][x].getName();
                        if(table[i][x]!=null) {
                            if(((piece_name.equals("R"))||(piece_name.equals("Q")))&&(!table[i][x].getSide().equals(side))) {
                                output = true;
                                break;
                            }
                        } 
                    }
                    if(!output) {
                        for(int i = y; i>=0;i--) {
                           String piece_name = table[i][x].getName();
                           if(table[i][x]!=null) {
                                if(((piece_name.equals("R"))||(piece_name.equals("Q")))&&(!table[i][x].getSide().equals(side))) {
                                    output = true;
                                    break;
                                }
                            } 
                        }
                    }
                }
            }
        }
        if(!output) {
            int virt_y =y;
            while (y>=0) {
                for(int i=x;i>=0;i++) {
                    String piece_name = table[i][x].getName();
                    virt_y--;
                    if((((piece_name.equals("B"))||(piece_name.equals("Q")))||(piece_name.equals("Q")))&&(!table[i][x].getSide().equals(side))) {
                        output = true;
                        break;
                    }
                }
            }
            if(!output) {
                while(y<table.length) {
                    for(int i=x;i>=0;i++) {
                        String piece_name = table[i][x].getName();
                        virt_y++;
                        if((table[i][x].getName().equals("R"))&&(!table[i][x].getSide().equals(side))) {
                            output = true;
                            break;
                        }
                    }
                }
                if(!output) {
                    while(y>=0) {
                        for(int i=x;i>=0;i--) {
                            String piece_name = table[i][x].getName();
                            virt_y--;
                            if((table[i][x].getName().equals("R"))&&(!table[i][x].getSide().equals(side))) {
                                output = true;
                                break;
                            }
                        }
                    }
                    if(!output) {
                        while(y<table.length) {
                            for(int i=x;i>=0;i--) {
                                String piece_name = table[i][x].getName();
                                virt_y++;
                                if((table[i][x].getName().equals("R"))&&(!table[i][x].getSide().equals(side))) {
                                    output = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return output;
    }
}

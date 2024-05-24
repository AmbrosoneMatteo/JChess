package jchess.net;

import jchess.pieces.Pieces;

import java.io.*;
import java.net.Socket;

public class JChessClient {
    final public static String blue = "\033[34;1m";
    final public static  String reset = "\033[0m";
    private static final String SERVER_ADDR = "127.0.0.1";
    private static final int SERVER_PORT = 3000;
    private static Socket server;
    private static OutputStream out;
    private static ObjectInputStream inObject;
    private static Pieces[][] board;
    public static void main(String[] args) {
        try {
            server = new Socket(SERVER_ADDR, SERVER_PORT);
            out = server.getOutputStream();
            inObject = new ObjectInputStream(server.getInputStream());

            receiveUpdatedBoard();
            printTerminalChessboard();
            while(true){
                sendPlayerMove();
                receiveUpdatedBoard();
                printTerminalChessboard();

                // Receive the updated board after the opponent move
                receiveUpdatedBoard();
                printTerminalChessboard();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void sendPlayerMove() throws IOException {
        // Temporary logic for reading the move from keyboard input
        // when the GUI is ready we should read the move from it
        System.out.print("Insert move: ");

        int read = 0;
        while((read = System.in.read()) != 10){
            out.write(read);
        }
        out.write((byte)10); // End of message
    }

    public static void receiveUpdatedBoard() throws IOException, ClassNotFoundException {
        board = (Pieces[][]) inObject.readObject();
    }

    public static void printTerminalChessboard() {
        boolean white_cell = false;
        for(var i: board) {
            for(var l: i) {
                if(l!=null) {
                    if(l.getSide().equals("w")) {
                        System.out.print(blue+l.getName()+reset);
                    } else {
                        System.out.print(blue+l.getName()+reset);
                    }
                } else {
                    if(white_cell) {
                        System.out.print("#");
                    } else {
                        System.out.print(" ");
                    }
                }
                if(white_cell) {white_cell=false;} else {white_cell=true;}
            }
            System.out.println();
        }
        for (int c = 0; c < 3; c++)  {
            System.out.println();
        }
    }
}

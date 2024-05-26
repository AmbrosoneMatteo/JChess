package jchess.net;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class JChessClient {
    private static final String SERVER_ADDR = "127.0.0.1";
    private static final int SERVER_PORT = 3000;
    private static Socket server;
    private static OutputStream out;
    private static InputStream in;
    public static void main(String[] args) {
        try {
            server = new Socket(SERVER_ADDR, SERVER_PORT);
            in = server.getInputStream();
            out = server.getOutputStream();

            while(true){
                // Receiving the message from the server
                String receivedString = getStringFromBytes(receiveRequest());
                if(receivedString.equals("MOVE")){ // The server requested the player move
                    sendPlayerMove();
                }else{ // The server is sending something else (opponent move)
                    System.out.println("Opponent move: " + receivedString);
                }
            }
        } catch (IOException e) {
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

    public static ArrayList<Byte> receiveRequest() throws IOException {
        ArrayList<Byte> msg = new ArrayList<Byte>();
        int read = 0;
        while((read = in.read()) != 10){
            msg.add((byte) read);
        }
        return msg;
    }

    private static String getStringFromBytes(ArrayList<Byte> bytes){
        String out = "";
        for(byte b : bytes){
            out += (char)b;
        }
        return out;
    }
}

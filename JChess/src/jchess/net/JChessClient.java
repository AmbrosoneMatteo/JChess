package jchess.net;


import jchess.gui.MainGameGUI;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class JChessClient implements Runnable{
    private final String SERVER_ADDR = "127.0.0.1";
    private final int SERVER_PORT = 3000;
    private Socket server;
    private OutputStream out;
    private InputStream in;
    private MainGameGUI gui;

    public JChessClient(MainGameGUI gui){
        this.gui = gui;
    }

    @Override
    public void run() {
        try {
            MainGameGUI game = new MainGameGUI();
            server = new Socket(SERVER_ADDR, SERVER_PORT);
            in = server.getInputStream();
            out = server.getOutputStream();

            while(true){
                // Receiving the message from the server
                String receivedString = getStringFromBytes(receiveRequest());
                if(!receivedString.equals("MOVE")){ // The server requested the player move
                    String start = receivedString.substring(0,2);
                    String end = receivedString.substring(2,4);
                    gui.movePiece(start, end);
                    gui.repaint();
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void sendPlayerMove(String move) throws IOException {
        ArrayList<Byte> msg = getBytesFromString(move);

        for(byte b : msg){
            out.write(b);
        }

        out.write((byte)10); // End of message
    }

    public ArrayList<Byte> receiveRequest() throws IOException {
        ArrayList<Byte> msg = new ArrayList<Byte>();
        int read = 0;
        while((read = in.read()) != 10){
            msg.add((byte) read);
        }
        return msg;
    }

    private String getStringFromBytes(ArrayList<Byte> bytes){
        String out = "";
        for(byte b : bytes){
            out += (char)b;
        }
        return out;
    }

    public static ArrayList<Byte> getBytesFromString(String string) {
        ArrayList<Byte> byteArrayList = new ArrayList<>();
        for (char c : string.toCharArray()) {
            byteArrayList.add((byte) c);
        }
        return byteArrayList;
    }
}

package jchess.net;


import jchess.Game;
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
    private boolean turn;
    private String side;
    private Game game;

    public JChessClient(MainGameGUI gui, Game game){
        this.gui = gui;
        this.game = game;
    }

    @Override
    public void run() {
        try {
            server = new Socket(SERVER_ADDR, SERVER_PORT);
            in = server.getInputStream();
            out = server.getOutputStream();

            System.out.println("Waiting for another player");
            String receiveStart = getStringFromBytes(receiveRequest());
            this.side = receiveStart.split(" ")[1];
            System.out.println("Game started, side: " + this.side);

            while(true){
                // Receiving the message from the server
                String receivedString = getStringFromBytes(receiveRequest());
                if(!receivedString.equals("MOVE")){ // The server requested the player move
                    String start = receivedString.substring(0,2);
                    String end = receivedString.substring(2,4);
                    int blackCountStart = game.getCountBlackPieces();
                    int whiteCountStart = game.getCountWhitePieces();

                    game.move(receivedString);

                    int blackCountEnd = game.getCountBlackPieces();
                    int whiteCountEnd = game.getCountWhitePieces();
                    if(blackCountStart != blackCountEnd || whiteCountStart != whiteCountEnd){
                        // A piece has been taken, so it has to be removed from the board
                        gui.removePieceFromBoard(end);
                    }

                    gui.movePiece(start, end);
                    gui.repaint();

                    if(this.side.equals("w")){
                        game.setWhite_moves(true);
                    }else{
                        game.setWhite_moves(false);
                    }
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

    private ArrayList<Byte> receiveRequest() throws IOException {
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

    private ArrayList<Byte> getBytesFromString(String string) {
        ArrayList<Byte> byteArrayList = new ArrayList<>();
        for (char c : string.toCharArray()) {
            byteArrayList.add((byte) c);
        }
        return byteArrayList;
    }

    public boolean isTurn() {
        return turn;
    }

    public String getSide() {
        return side;
    }
}

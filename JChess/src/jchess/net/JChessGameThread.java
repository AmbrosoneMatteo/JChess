package jchess.net;

import jchess.Game;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class JChessGameThread implements Runnable{
    private Socket player1;
    private Socket player2;
    private Game game;
    private InputStream player1Input;
    private InputStream player2Input;
    private OutputStream player1Output;
    private OutputStream player2Output;
    public JChessGameThread(Socket player1, Socket player2, Game game) throws IOException {
        this.player1 = player1;
        this.player2 = player2;
        this.game = game;

        this.player1Input = player1.getInputStream();
        this.player2Input = player2.getInputStream();
        this.player1Output = player1.getOutputStream();
        this.player2Output = player2.getOutputStream();
    }

    @Override
    public void run() {
        try {
            while(true){
                sendRequestMove(player1Output);
                ArrayList<Byte> player1Move = getPlayerMove(player1Input);
                game.move(getStringFromBytes(player1Move));

                // Sending the opponent move (player 1 move) to player2
                sendMove(player1Move, player2Output);

                sendRequestMove(player2Output);
                ArrayList<Byte> player2Move = getPlayerMove(player2Input);
                game.move(getStringFromBytes(player2Move));

                // Sending the opponent move (player 2 move) to player 1
                sendMove(player2Move, player1Output);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Byte> getPlayerMove(InputStream playerInput) throws IOException {
        ArrayList<Byte> msg = new ArrayList<Byte>();
        int read = 0;
        while((read = playerInput.read()) != 10){
            msg.add((byte) read);
        }
        return msg;
    }

    private void sendMove(ArrayList<Byte> msg, OutputStream playerOutput) throws IOException {
        for(byte b : msg){
            playerOutput.write(b);
        }
        playerOutput.write(10); // End of message
    }

    private void sendRequestMove(OutputStream playerOutput) throws IOException {
        ArrayList<Byte> msg = getBytesFromString("MOVE");
        sendMove(msg, playerOutput);
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

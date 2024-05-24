package jchess.net;

import jchess.Game;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class JChessGameThread implements Runnable{
    private Socket player1;
    private Socket player2;
    private Game game;
    private InputStream player1Input;
    private InputStream player2Input;
    private ObjectOutputStream player1OutputObject;
    private ObjectOutputStream player2OutputObject;
    public JChessGameThread(Socket player1, Socket player2, Game game) throws IOException {
        this.player1 = player1;
        this.player2 = player2;
        this.game = game;

        this.player1Input = player1.getInputStream();
        this.player2Input = player2.getInputStream();
        this.player1OutputObject = new ObjectOutputStream(player1.getOutputStream());
        this.player2OutputObject = new ObjectOutputStream(player2.getOutputStream());
    }

    @Override
    public void run() {
        try {
            sendBoard(player1OutputObject);
            while(true){
                String player1Move = getPlayerMove(player1Input);
                game.move(player1Move);

                // Sending back the updated board to player 1 and player 2
                sendBoard(player1OutputObject);
                sendBoard(player2OutputObject);

                String player2Move = getPlayerMove(player2Input);
                game.move(player2Move);

                // Sending back the updated board to player 1 and player 2
                sendBoard(player1OutputObject);
                sendBoard(player2OutputObject);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public String getPlayerMove(InputStream playerInput) throws IOException {
        ArrayList<Byte> msg = new ArrayList<Byte>();
        int read = 0;
        while((read = playerInput.read()) != 10){
            msg.add((byte) read);
        }
        return getStringFromBytes(msg);
    }

    private void sendBoard(ObjectOutputStream playerOutputObject) throws IOException {
        playerOutputObject.reset();
        playerOutputObject.writeObject(this.game.getTable());
        playerOutputObject.flush();
    }

    private String getStringFromBytes(ArrayList<Byte> bytes){
        String out = "";
        for(byte b : bytes){
            out += (char)b;
        }
        return out;
    }
}

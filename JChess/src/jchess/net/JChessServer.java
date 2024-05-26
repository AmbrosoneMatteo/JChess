package jchess.net;

import jchess.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class JChessServer {
    private static final int PORT = 3000;
    private static ArrayList<JChessGameThread> gameThreads = new ArrayList<JChessGameThread>();

    public static void main(String[] args) {
        Logger.log("JChess Server Started");
        try {
            ServerSocket server = new ServerSocket(3000);
            Logger.log("Server listening on " + server.getLocalSocketAddress());

            while(true){
                Socket clientConnection1 = server.accept();
                Logger.log("A client has connected from " + clientConnection1.getRemoteSocketAddress());
                Logger.log("Waiting for another client before starting the game");
                Socket clientConnection2 = server.accept();
                Logger.log("A client has connected from " + clientConnection2.getRemoteSocketAddress());

                Logger.log("Creating new game thread");
                Game game = new Game("prod");
                JChessGameThread gameThread = new JChessGameThread(clientConnection1, clientConnection2, game);
                new Thread(gameThread).start();
                gameThreads.add(gameThread);
                Logger.log("Game thread started");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

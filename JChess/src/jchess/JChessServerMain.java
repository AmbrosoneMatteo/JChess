package jchess;

import jchess.net.JChessServer;


/**
 * Main class to run for starting a new JChessServer
 */
public class JChessServerMain {
    public static void main(String[] args) {
        new Thread(new JChessServer()).start();
    }
}

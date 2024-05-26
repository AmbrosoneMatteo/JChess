package jchess;

import jchess.gui.MainGameGUI;
import jchess.net.JChessClient;

/**
 * Main class to run for starting a new game
 *
 * This class generate the game GUI and opens a socket connection to the server
 */
public class JChessClientMain {
    public static void main(String[] args) {
        MainGameGUI gui = new MainGameGUI();
        JChessClient client = new JChessClient(gui);
        gui.setClient(client);

        new Thread(client).start();
        new Thread(gui).start();
    }
}

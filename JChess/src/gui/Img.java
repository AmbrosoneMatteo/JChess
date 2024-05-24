package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import jchess.Game;
import jchess.pieces.Pieces;

import java.util.Hashtable;

public class Img extends Application {
    Hashtable<String, String> nametopiece = new Hashtable<>() {{
        put("K","king");
        put("Q","queen");
        put("R","rook");
        put("N","knight");
        put("B","bishop");
        put("p","pawn");
    }};
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game("normal");
        Pieces[][] table = game.getTable();

        //Loading the MainGame GUI prom the fxml file
        Image piece = new Image("file:assets/rook_w.png",250, 300, false, false);
        ImageView view = new ImageView(piece);
        view.setX(50);
        view.setY(50);
        Group root = new Group(view);
        Scene main = new Scene(root);
        primaryStage.setScene(main);
        primaryStage.show();
    }
}
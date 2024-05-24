package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import jchess.Game;
import jchess.pieces.*;
import java.io.IOException;
import java.util.Hashtable;

public class MainGameGUI extends Application {
    Game game;
    final protected String[] alphabet = {"a","b","c","d","e","f","g","h"};
    public static void main(String[] args) {
        launch(args);
    }
    String move = "";
    Scene scene;
    GridPane board;
    @Override
    public void start(Stage primaryStage) {
        try {
            game = new Game("normal"); //instanciate a new game in normal mode
            //Loading the MainGame GUI prom the fxml file
            Parent root = FXMLLoader.load(getClass().getResource("./MainGameGUI.fxml")); //load the FXML scene
            primaryStage.setTitle("JChess");
            primaryStage.setScene(new Scene(root));
            scene = primaryStage.getScene();
            board = (GridPane) scene.lookup("#board");
            //takes all the cells in the chessboard by id and assigns it the onMouseClicked event
            //that calls the method move() with the cell id
            for(int column = 1; column<8;column++) {
                for (int row =1; row<=8;row++) {
                    Rectangle rect = (Rectangle) scene.lookup("#"+alphabet[column].toUpperCase()+row);
                    //assign new method to the cell
                    rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            move(rect.getId());
                        }
                    });
                }
            }
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //this method gets activated when the player touches a cell on the chessboard
    @FXML
    public void move(String action) {
        Rectangle rect = (Rectangle) scene.lookup("#"+action);
        int y = Integer.parseInt(action.substring(1,2))-1;
        int x =  game.find(action.substring(0,1));
        if ((this.move!="")&&(action!=move)) {
            //if move!=null it means that a cell has already been selected
            boolean piece_taken = false;
            if (game.getTable()[y][x]!=null) {
                piece_taken=true;
            }
            if(this.game.move(this.move+action)) {
                y = 7-y;
                //if the move is valid the table moves the piece from the first cell clicked
                //to the last one, then reset the variable move
                ImageView piece = (ImageView) scene.lookup("#p"+move.substring(0,2));
                board.setRowIndex(piece, y); //set the row index of the piece
                board.setColumnIndex(piece,x); //set the column index of the piece
                if(piece_taken) {
                   ImageView view = (ImageView) scene.lookup("#p"+action);
                   view.setImage(null);
                   view.disableProperty().setValue(true);
                   view.setVisible(false);
                }
                piece.idProperty().setValue("p"+action);
                //System.out.println("#p"+move.substring(0,2));
            } else {
                //colors the selected cells in red to tell the player that his move was invalid
                move+=action;
                for(int i = 0; i<2;i++) {
                    Rectangle rectangle = (Rectangle) scene.lookup("#"+move.substring(0+i*2,2+i*2));
                    rectangle.setFill(new Color(1.0, 0.2,0.2,1));
                }
            }
            this.move = ""; //eliminate the variable that contains the selected cells
        }else if(game.getPiece(y,x)!=null) {
            //if move==null it means that no cell has been selected yet
            this.move = action;
        } else {
            Rectangle rectangle = (Rectangle) scene.lookup("#"+action);
            rectangle.setStyle("fill: #efefdf");
        }
    }
}

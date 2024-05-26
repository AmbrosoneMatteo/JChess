package jchess.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import jchess.Game;

import java.io.IOException;

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
            game = new Game("normal"); //creates a new game in normal mode
            //Loading the MainGame GUI prom the fxml file
            Parent root = FXMLLoader.load(getClass().getResource("MainGameGUI.fxml")); //load the FXML scene
            primaryStage.setTitle("JChess");
            primaryStage.setScene(new Scene(root));
            scene = primaryStage.getScene();
            board = (GridPane) scene.lookup("#board");
            //takes all the cells in the chessboard by id and assigns it the onMouseClicked event
            //that calls the method move() with the cell id so it knows what cell the user selected
            for(int column = 0; column<8;column++) {
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
        Rectangle rect = (Rectangle) scene.lookup("#"+action);//takes the selected cell
        //Converts the action string into coordinates
        int y = Integer.parseInt(action.substring(1,2))-1;
        int x =  game.find(action.substring(0,1));
        if ((this.move!="")&&(action!=move)) {
            //if move!=null it means that a cell has already been selected
            boolean piece_taken = false;
            if (game.getTable()[y][x]!=null) {
                //if the destination cell contains a piece, it means that the player is taking a piece that must be removed from the chessboard
                //this condition must be checked before the game moves the piece at its destination
                piece_taken=true;
            }
            if(this.game.move(this.move+action)) {
                //if the move is valid the table moves the piece from the first cell clicked
                //to the last one, then reset the variable move
                ImageView piece = (ImageView) scene.lookup("#p"+move); //takes the selected piece on the board
                //in case of castling it cheks where that happened and if the piece moved is a king, then moves the rook to its position
                if((move.substring(0,1).equals("E"))&&(game.getTable()[y][x].getName()=="K")) {
                    String row = move.substring(1,2);
                    if (action.substring(0,1).equals("G")) {
                        movePiece("H"+row,"F"+row);
                    } else if (action.substring(0,1).equals("C")) {
                        movePiece("A"+row,"D"+row);
                    }
                }else if(piece_taken) {
                   //if the player's taking a piece the program removes it from the board
                   ImageView view = (ImageView) scene.lookup("#p"+action);
                   view.setImage(null);
                   view.disableProperty().setValue(true);
                   view.setVisible(false);
                   view.idProperty().setValue("eliminated"); //set the id to eliminated so it doesn't get picked again
                }
                movePiece(move,action);
                repaint(); //reset board colors
            } else {
                repaint(); //reset board colors
                //colors the selected cells in red to tell the player that his move was invalid
                move+=action;
                for(int i = 0; i<2;i++) {
                    Rectangle rectangle = (Rectangle) scene.lookup("#c"+move.substring(0+i*2,2+i*2));
                    rectangle.setFill(new Color(1.0, 0.2,0.2,1));
                }
            }
            this.move = ""; //eliminate the variable that contains the selected cells
        }else if(game.getPiece(y,x)!=null) {
            //if move==null it means that no cell has been selected yet
            //so the program paints the selected cell
            Rectangle rectangle = (Rectangle) scene.lookup("#c"+action);
            rectangle.setFill(new Color(0.8, 0.8,0.5,1));
            this.move = action;
        } else {
            //if the cell doesn't contain a piece it gets colored to let the user know that he selected an empty cell
            repaint();
            Rectangle rectangle = (Rectangle) scene.lookup("#c"+action);
            rectangle.setFill(new Color(0.8, 0.8,0.2,1));
        }
    }

    private void repaint() {
        boolean white = false;
        for(int column = 0; column<8;column++) {
            for (int row =1; row<=8;row++) {
                Rectangle rect = (Rectangle) scene.lookup("#c"+alphabet[column].toUpperCase()+row);
                //takes all the cells and repaints them to cancel previosly painted cells
                //like empty ones, invalid moves and cells with selected pieces on it
                if(white) {
                    //paints the cell with white
                    rect.setFill(new Color(0.93,0.93,0.835,1));
                    white =false;
                } else {
                    //paint the cell with green
                    rect.setFill(new Color(0.4901,0.5803,0.3647,1));
                    white = true;
                }
            }
            if(white) {white=false; } else {white=true;} //alter the color order
        }
    }

    public void movePiece(String position, String destination) {
        ImageView piece = (ImageView) scene.lookup("#p"+position);
        int y = Integer.parseInt(destination.substring(1,2))-1;
        int x =  game.find(destination.substring(0,1));
        board.setRowIndex(piece,7-y);
        board.setColumnIndex(piece,x);
        piece.idProperty().setValue("p"+destination);
    }
}

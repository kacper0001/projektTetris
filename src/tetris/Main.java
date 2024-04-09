package tetris;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import static tetris.Piece.*;
import static tetris.Type.*;


public class Main extends Application {
     public static int HEIGHT =1000;
     public static int WIDTH = 500;
     public static int SIZE = 20;
    public static GridPane ROOT = new GridPane();
    public static Piece [][] PIECES_A = new Piece[HEIGHT/SIZE][WIDTH/SIZE];
    public static List<Piece> PIECES = new ArrayList<>();

    public static Type CURRENT_TYPE = EMPTY;


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(ROOT,WIDTH,HEIGHT);


        for (int r = 0; r <HEIGHT/SIZE ; r++) {
            for (int c = 0; c <WIDTH/SIZE; c++) {
                Piece rectangle = new Piece(CURRENT_TYPE,r, c);
                PIECES_A[r][c] = rectangle;
                ROOT.add(rectangle, c*SIZE, r*SIZE);
            }

        }

        Piece middle = PIECES_A[2][PIECES_A[0].length/2];// sprawdzenie
        middle.setType(PIECE);
        PIECES.add(middle);
        Piece.create(middle,CURRENT,4);




       Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),event -> {
           if(PIECES.isEmpty()!= true){
              // Piece.moveDown(PIECES);
          }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        primaryStage.setScene(scene);
        primaryStage.show();



    }

    }

package tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
     public static int HEIGHT =1000;
     public static int WIDTH = 500;
     public static int SIZE = 20;
    public static GridPane ROOT = new GridPane();
    public static Rectangle [][] RECTANGLES = new Rectangle[HEIGHT/SIZE][WIDTH/SIZE];



    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(ROOT,WIDTH,HEIGHT);


        for (int r = 0; r <HEIGHT/SIZE ; r++) {
            for (int c = 0; c <WIDTH/SIZE; c++) {
                Rectangle rectangle = new Rectangle(SIZE,SIZE, Color.BLACK);
                RECTANGLES[r][c] = rectangle;
                ROOT.add(rectangle, c*SIZE, r*SIZE);
            }

        }

        Rectangle middle = RECTANGLES[2][RECTANGLES[0].length/2];// sprawdzenie
        Color current = Piece.chooseColor();
        middle .setFill(current);
        Piece.create(middle,current,4);


        primaryStage.setScene(scene);
        primaryStage.show();



    }
}
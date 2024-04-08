package tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    public static int HEIGHT = 800;
    public static int WIDTH = 600;
    public static int SIZE = 20;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();
        Scene scene = new Scene(root,WIDTH,HEIGHT);
        Rectangle[][] rectangles = new Rectangle[HEIGHT/SIZE][WIDTH/SIZE];

        for (int r = 0; r <HEIGHT/SIZE ; r++) {
            for (int c = 0; c <WIDTH/SIZE; c++) {
                Rectangle rectangle = new Rectangle(SIZE,SIZE, Color.BLACK);
                rectangles[r][c] = rectangle;
                root.add(rectangle, c*SIZE, r*SIZE);
            }
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
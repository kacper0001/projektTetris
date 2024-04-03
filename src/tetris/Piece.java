package tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static tetris.Main.*;


public class Piece extends Rectangle {

    public static void create( Rectangle middle, Color current){
        int count = 5;
        middle.setX(WIDTH/2);
        middle.setY(HEIGHT - 3*SIZE);
        while(count>=0){
            for (int r = HEIGHT - 3*SIZE ; r < HEIGHT / SIZE; r++) {
                for (int c = WIDTH/2; c < WIDTH/SIZE ; c++) {
                    double random = Math.random();
                    if(random < 0.25){
                        Rectangle rectangle = new Rectangle( WIDTH/2 - SIZE, HEIGHT - 3*SIZE, SIZE,SIZE );
                        rectangle.setFill(current);
                        create(rectangle, current);
                        count--;



                    }
                    
                }
            }
            
            
        }
    }

    private static Color ChooseColor() {
        double random = Math.random();
        Color color = Color.GREEN;
        if(random<= 0.2){
            color = Color.YELLOW;
        }
        else if(random> 0.2 && random<=0.4){
            color = Color.BLUE;
        }
        else if(random> 0.4 && random<=0.6){
            color = Color.RED;
        }
        else if(random> 0.6 && random<=0.8){
            color = Color.PINK;
        }
        
        return color;
    }

}

package tetris;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import jdk.nashorn.api.tree.ForInLoopTree;

import java.util.List;
import java.util.Objects;

import static tetris.Main.*;
import static tetris.Type.*;


public class Piece extends Rectangle {
    int r,c;
    Type type;
    public static Color CURRENT = Piece.chooseColor();

    public Piece(Type type, int r, int c) {
        super(r,c, SIZE, SIZE);
        setType(type);
    }

    public void setType(Type type) {
        this.type = type;
        if (type == Type.EMPTY) {
            setFill(Color.BLACK);
        }
        if(type == PIECE){
            setFill(CURRENT);
        }


    }
    public static void create(Piece middle, Color current, int count) { // tworzy piece (count to ilość kwadratów która ma być w jednym piece)
        for (int r =0; r < HEIGHT/SIZE; r ++) {
            for (int c = 0; c < WIDTH/SIZE; c ++) {
                if(PIECES_A[r][c] == middle) {
                    int random = (int) (Math.random() *4);

                    if (random == 1  && r - 1 >=0 ) {
                        PIECES_A[r-1][c].setFill(current);
                        middle = PIECES_A[r-1][c];
                        PIECES.add(middle);
                        if (count-- >0) {
                            create(middle, current, count--);
                        }
                        else {
                            return;
                        }
                    }
                    else if (random == 2 && r + 1 < HEIGHT/SIZE) {
                        PIECES_A[r+1][c].setFill(current);
                        middle = PIECES_A[r+1][c];
                        PIECES.add(middle);
                        if(count-->0) {
                            create(middle, current, count--);
                        }
                        else {
                            return;
                        }


                    }
                    else if (random ==3 && c +1 < WIDTH/SIZE) {
                        PIECES_A[r][c + 1].setFill(current);
                        middle = PIECES_A[r][c + 1];
                        PIECES.add(middle);
                        if (count-- > 0){
                            create(middle, current, count--);
                        }
                        else {
                            return;
                        }

                    }
                    else if (random ==4 && c - 1 > 0) {
                        PIECES_A[r][c-1].setFill(current);
                        middle = PIECES_A[r][c-1];
                        PIECES.add(middle);
                        if ( count-- >0) {
                            create(middle, current, count--);
                        }
                        else {
                            return;
                        }

                    }
                    else {
                        if(count-->0) {
                            create(middle, current, count--);
                        }
                        else {
                            return;
                        }

                    }
                }
            }


        }
    }
    public static void moveDown(List<Piece> pieces) {
        boolean canFall = true;

            for (int i = 0; i < pieces.size(); i++) {
                Piece piece = pieces.get(i);
                if (piece.r <= HEIGHT / SIZE) {
                    canFall = false;
                }
                if (piece.r < HEIGHT / SIZE && PIECES_A[piece.r + 1][piece.c].type == FALLEN) {
                    canFall=false;
                }
                if(canFall== true) {
                    pieces.remove(piece);
                    pieces.add(PIECES_A[piece.r + 1][piece.c]);
                    draw(pieces);


                }
            }

    }

    private static void draw(List<Piece> pieces) {
        for (int i = 0; i < pieces.size(); i++) {
        pieces.get(i).setType(PIECE);

        }
    }

    static Color chooseColor() { //wybiera kolor
        double random = Math.random();
        Color color = Color.GREEN;
        if (random <= 0.2) {
            color = Color.YELLOW;
        } else if (random > 0.2 && random <= 0.4) {
            color = Color.BLUE;
        } else if (random > 0.4 && random <= 0.6) {
            color = Color.RED;
        } else if (random > 0.6 && random <= 0.8) {
            color = Color.PINK;
        }

        return color;
    }
}


    /*



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return r == piece.r && c == piece.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }


    public static void moveLeft (List<Rectangle> pieces){

    }
    public static void moveRight (List<Rectangle> pieces){

    }

}
*/




package tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

import static tetris.Main.*;


public class Piece extends Rectangle {



    public static void create(Rectangle middle, Color current, int count) { // tworzy piece (count to ilość kwadratów która ma być w jednym piece)
        for (int r =0; r < HEIGHT/SIZE; r ++) {
                for (int c = 0; c < WIDTH/SIZE; c ++) {
                    if(RECTANGLES[r][c] == middle) {
                        int random = (int) (Math.random() *4);

                        if (random == 1  && r - 1 >=0 ) {
                            RECTANGLES[r-1][c].setFill(current);
                            middle = RECTANGLES[r-1][c];
                            PIECES.add(middle);
                            if (count-- >0) {
                                create(middle, current, count--);
                            }
                            else {
                                return;
                            }
                        }
                        else if (random == 2 && r + 1 < HEIGHT/SIZE) {
                            RECTANGLES[r+1][c].setFill(current);
                            middle = RECTANGLES[r+1][c];
                            PIECES.add(middle);
                            if(count-->0) {
                                create(middle, current, count--);
                            }
                            else {
                                return;
                            }


                        }
                        else if (random ==3 && c +1 < WIDTH/SIZE) {
                            RECTANGLES[r][c + 1].setFill(current);
                            middle = RECTANGLES[r][c + 1];
                            PIECES.add(middle);
                            if (count-- > 0){
                                create(middle, current, count--);
                        }
                        else {
                            return;
                        }

                        }
                        else if (random ==4 && c - 1 > 0) {
                            RECTANGLES[r][c-1].setFill(current);
                            middle = RECTANGLES[r][c-1];
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

        public static void move(List<Rectangle> pieces){
            for (int r =0; r < HEIGHT/SIZE; r ++) {
                for (int c = 0; c < WIDTH / SIZE; c++) {
                    Rectangle current = RECTANGLES[r][c];
                        Rectangle piecePart = pieces.get(r);
                        if(current == piecePart && r+1 < HEIGHT/SIZE){
                            RECTANGLES[r][c].setFill(Color.BLUE);
                            RECTANGLES[r+1][c].setFill(CURRENT);
                            pieces.set(r, RECTANGLES[r+1][c]);

                            move(pieces);

                            }




                    }
                }
            }

        }




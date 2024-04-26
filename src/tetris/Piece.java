package tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.*;

import static tetris.Main.*;
import static tetris.Type.*;


public class Piece extends Rectangle {
    int r, c;
    Type type;
    public static Color CURRENT = Piece.chooseColor();

    public Piece(Type type, int r, int c) {
        super(r, c, SIZE, SIZE);
        this.r = r;
        this.c = c;
        setType(type);
    }

    public void setType(Type type) {
        this.type = type;
        if (type == Type.EMPTY) {
            setFill(Color.BLACK);
        }
        if (type == PIECE) {
            setFill(CURRENT);
        }


    }

    public static void create(Piece middle, Color current, int count) {// tworzy piece (count to ilość kwadratów która ma być w jednym piece)--> błąd is here!!!!
        if (count == 0) {
            return;
        }
        List<Piece> free = new ArrayList<>();
        if (middle.r + 1 < PIECES_A.length && PIECES_A[middle.r + 1][middle.c].type == EMPTY) {
            free.add(PIECES_A[middle.r + 1][middle.c]);
        }
        if (middle.r - 1 >= 0 && PIECES_A[middle.r - 1][middle.c].type == EMPTY) {
            free.add(PIECES_A[middle.r - 1][middle.c]);
        }
        if (middle.c - 1 >= 0 && PIECES_A[middle.r][middle.c - 1].type == EMPTY) {
            free.add(PIECES_A[middle.r][middle.c - 1]);
        }
        if (middle.c + 1 < PIECES_A[0].length && PIECES_A[middle.r][middle.c + 1].type == EMPTY) {
            free.add(PIECES_A[middle.r][middle.c + 1]);
        }
        if(!PIECES.isEmpty()) {
            Collections.shuffle(free);
            Piece next = free.get(0);
            next.setType(PIECE);
            PIECES.add(next);
            create(next, CURRENT, count - 1);
        }
    }

    public static void moveDown(List<Piece> pieces) {
        boolean canFall = true;
        for (int i = 0; i < pieces.size(); i++) {
            Piece piece = pieces.get(i);
            if (piece.r + 1 >= HEIGHT / SIZE) {
                canFall = false;
                for (int j = 0; j < pieces.size(); j++) {
                    pieces.get(j).setType(FALLEN);
                    PIECES.remove(pieces.get(j));

                }
                PIECES.clear();
            }
            if (piece.r + 1 < HEIGHT / SIZE && PIECES_A[piece.r + 1][piece.c].type == FALLEN) {
                canFall = false;
                for (int j = 0; j < pieces.size(); j++) {
                    pieces.get(j).setType(FALLEN);
                    PIECES.remove(pieces.get(j));
                }
                PIECES.clear();


            }
        }

            if (canFall == true) {
                List<Piece> newPieces = new ArrayList<>();
                pieces.sort(Comparator.comparingInt(value -> value.r));
                for (int i = pieces.size() - 1; i >= 0; i--) {
                    PIECES_A[pieces.get(i).r][pieces.get(i).c].setType(EMPTY);
                    Piece temp = new Piece(PIECE, pieces.get(i).r++,pieces.get(i).c);
                    PIECES_A[temp.r][temp.c].setType(PIECE);
                    PIECES.remove(pieces.get(i));
                    newPieces.add(temp);
                }
                for (int i = 0; i < newPieces.size() ; i++) {
                    PIECES.add(newPieces.get(i));

                }

            }


        }

    private static void draw(List<Piece> pieces) {
        for (int i = 0; i < pieces.size(); i++) {
            pieces.get(i).setFill(CURRENT);

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


    public static void moveLeft(List<Piece> pieces) {
        boolean canMove = true;

        for (int i = 0; i < pieces.size(); i++) {
            Piece piece = pieces.get(i);
            if (piece.c -1 < 0) {
                canMove = false;
            }
            if (piece.c -1 >= 0 && PIECES_A[piece.r][piece.c - 1].type == FALLEN) {
                canMove = false;
            }
        }

        if (canMove == true) {
            pieces.sort(Comparator.comparingInt(value -> value.c));
            for (int i = 0; i< pieces.size(); i++) {
                PIECES_A[pieces.get(i).r][pieces.get(i).c].setType(EMPTY);
                pieces.get(i).c--;
                PIECES_A[pieces.get(i).r][pieces.get(i).c].setType(PIECE);
            }

        }




    }

    public static void moveRight(List<Piece> pieces) {
        boolean canMove = true;

        for (int i = 0; i < pieces.size(); i++) {
            Piece piece = pieces.get(i);
            if (piece.c +1 >= PIECES_A[0].length) {
                canMove = false;

            }
            if (piece.c +1 < PIECES_A[0].length && PIECES_A[piece.r][piece.c + 1].type == FALLEN) {
                canMove = false;
            }
        }

        if (canMove == true) {
            pieces.sort(Comparator.comparingInt(value -> value.c));
            for (int i = pieces.size() - 1; i >= 0; i--) {
                PIECES_A[pieces.get(i).r][pieces.get(i).c].setType(EMPTY);
                pieces.get(i).c++;
                PIECES_A[pieces.get(i).r][pieces.get(i).c].setType(PIECE);
            }

        }

    }

    public static void generate(){
            Piece newMiddle =PIECES_A[2][PIECES_A[0].length/2];
            PIECES.add(newMiddle);
            Color newColor = chooseColor();
            CURRENT = newColor;
            Piece.create(newMiddle, CURRENT, 4);


    }
}







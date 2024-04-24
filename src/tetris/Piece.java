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
        Collections.shuffle(free);
        Piece next = free.get(0);
        next.setType(PIECE);
        PIECES.add(next);
        create(next, CURRENT, count - 1);

    }

    public static void moveDown(List<Piece> pieces) {
        boolean canFall = true;

        for (int i = 0; i < pieces.size(); i++) {
            Piece piece = pieces.get(i);
            if (piece.r + 1 >= HEIGHT / SIZE) {
                canFall = false;
            }
            if (piece.r + 1 < HEIGHT / SIZE && PIECES_A[piece.r + 1][piece.c].type == FALLEN) {
                canFall = false;
            }
        }

        if (canFall == true) {
            pieces.sort(Comparator.comparingInt(value -> value.r));
            for (int i = pieces.size() - 1; i >= 0; i--) {
                PIECES_A[pieces.get(i).r][pieces.get(i).c].setType(EMPTY);
                pieces.get(i).r++;
                PIECES_A[pieces.get(i).r][pieces.get(i).c].setType(PIECE);
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
}







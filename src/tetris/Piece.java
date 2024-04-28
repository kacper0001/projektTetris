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

    public static void moveDown(List<Piece> pieces) {//ruch w dół
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
                    PIECES.remove(pieces.get(i));
                    newPieces.add(PIECES_A[pieces.get(i).r++][pieces.get(i).c]);
                }
               for (int i = 0; i < newPieces.size() ; i++) {
                    PIECES.add(newPieces.get(i));
                   PIECES_A[pieces.get(i).r][pieces.get(i).c].setType(PIECE);

                }
            }


        }

    private static void draw(boolean[][] toBoolean) {//zmienia odwróconą tablicę booleanów spowrotem na piecy
        int minRow = PIECES_A.length;
        int minColumn = PIECES_A[0].length;
        int maxRow = 0;
        int maxColum =0;
        for (int r = 0; r < PIECES_A.length; r++) {
            for (int c = 0; c < PIECES_A[0].length; c++) {
                if(PIECES_A[r][c].type == PIECE){
                    if(r<minRow){
                        minRow=r;
                    }
                    if(c<minColumn){
                        minColumn= c;
                    }
                    if(r>maxRow){
                        maxRow=r;
                    }
                    if(c>maxColum){
                        maxColum=c;
                    }
                }

            }
        }
        for (int r = 0; r < toBoolean.length; r++) {
            for (int c = 0; c < toBoolean[0].length; c++) {
                if(toBoolean[r][c] == true){
                    PIECES_A[minRow][minColumn].setType(PIECE);
                    int count =0;
                    for (int i = 0; i < PIECES.size(); i++) {
                        if(PIECES.get(i) == PIECES_A[minRow][minColumn]){
                            count++;
                        }
                    }
                    if(count ==0) {
                        PIECES.add(PIECES_A[minRow][minColumn]);
                    }
                }
                else {
                    PIECES_A[minRow][minColumn].setType(EMPTY);
                    int count =0;
                    for (int i = 0; i < PIECES.size(); i++) {
                        if(PIECES.get(i) == PIECES_A[minRow][minColumn]){
                            count++;
                        }
                    }
                    if(count !=0) {
                        PIECES.remove(PIECES_A[minRow][minColumn]);
                    }
                }

            }

        }
        minColumn++;
        minRow++;

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


    public static void moveLeft(List<Piece> pieces) {//ruch w lewo
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

    public static void moveRight(List<Piece> pieces) {//ruch w prawo
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

    public static void generate(){//generowanie spadającego kawałka
            Piece newMiddle =PIECES_A[2][PIECES_A[0].length/2];
            PIECES.add(newMiddle);
            Color newColor = chooseColor();
            CURRENT = newColor;
            Piece.create(PIECES_A[2][PIECES_A[0].length/2], CURRENT, 4);


    }
    public static void turnLeft (boolean[][] toBoolean){//obraca w lewo wytworzoną wcześniej tablice booleanów
        boolean[][] temp = new boolean[toBoolean.length][toBoolean[0].length];
        int i = 0;
            for (int r = toBoolean.length - 1; r >= 0; r--) {
                for (int c = 0; c < toBoolean[0].length; c++) {
                    temp[r][c] = toBoolean[i][c];
                    i++;
                }
            }
        for (int r = 0; r < toBoolean.length; r++) {
            for (int c = 0; c < toBoolean[0].length; c++) {
                toBoolean[r][c] = temp[r][c];

            }
        }
        draw(toBoolean);
        }
        public static boolean[][] toBoolean(){ //tworzy boolean[][] na naszym spadającym kawałku
        int minRow = PIECES_A.length;
        int minColumn = PIECES_A[0].length;
        int maxRow = 0;
        int maxColum =0;
            for (int r = 0; r < PIECES_A.length; r++) {
                for (int c = 0; c < PIECES_A[0].length; c++) {
                    if(PIECES_A[r][c].type == PIECE){
                        if(r<minRow){
                            minRow=r;
                        }
                        if(c<minColumn){
                            minColumn= c;
                        }
                        if(r>maxRow){
                            maxRow=r;
                        }
                        if(c>maxColum){
                            maxColum=c;
                        }
                    }

                }
            }
            boolean[][] toBoolean = new boolean[maxRow-minRow][maxColum-minColumn];
            for (int r = 0; r < toBoolean.length; r++) {
                for (int c = 0; c < toBoolean[0].length; c++) {
                    if(PIECES_A[minRow][minColumn].type==PIECE){
                        toBoolean[r][c] = true;
                    }
                    else{
                        toBoolean[r][c] = false;
                    }
                    minRow++;
                    minColumn++;
                }
            }
            return toBoolean;
        }
    }








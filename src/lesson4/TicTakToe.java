package lesson4;

import java.util.Random;
import java.util.Scanner;

public class TicTakToe {
    public static final Scanner SCANNER = new Scanner(System.in);
    private static final int SIZE = 5; 
    private static final int DOTE_TO_WIN = 4;
    private static final char DOT_EMPTY = '•';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static char[][] map = new char[SIZE][SIZE];

    public static void main(String[] args) {
        initializeGame();
        while (true) {
            humanTurn();
            printMap();
            if (checkEndGame(DOT_X)) {
                break;
            }
            computerTurn();
            printMap();
            if (checkEndGame(DOT_O)) {
                break;
            }
        }
    }

    private static boolean checkEndGame(char symbol) {
        if (checkWin(symbol)) {
            System.out.println(isHumanTurn(symbol) ? "Человек(X) победил!" : "Компьютер(O) победил!");
            return true;
        }
        if (isMapFull()) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }

    private static boolean isMapFull() {
        for (int rowIndex = 0; rowIndex < map.length; rowIndex++) {
            char[] row = map[rowIndex];
            for (int colIndex = 0; colIndex < row.length; colIndex++) {
                if (row[colIndex] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkWin(char symbol) {
        int diagonalA = 0;
        int diagonalB = 0;
        for (int i = 0; i <= SIZE - 1; i++) {
            int gorizontal = 0;
            int vertical = 0;
            for (int j = 0; j <= SIZE - 1; j++) {
                if (map[i][j] == symbol) {
                    gorizontal++;
                    if (gorizontal == DOTE_TO_WIN) {
                        return true;
                    }
                }
                if (map[j][i] == symbol) {
                    vertical++;
                    if (vertical == DOTE_TO_WIN) {
                        return true;
                    }
                }
            }
        }
        for (int i = 0; i < SIZE; i++) {
            diagonalA = (map[i][i] == symbol) ? diagonalA + 1 : 0;
            diagonalB = (map[i][map.length - 1 - i] == symbol) ? diagonalB + 1 : 0;
            if ((diagonalA == DOTE_TO_WIN) || (diagonalB == DOTE_TO_WIN)) {
                return true;
            }
        }
        return false;
    }

    private static void humanTurn() {
        int rowIndex;
        int colIndex;
        do {
            System.out.print("Введите номер строки: ");
            rowIndex = SCANNER.nextInt() - 1;
            System.out.print("Введите номер столбца: ");
            colIndex = SCANNER.nextInt() - 1;
        } while (!isCellVaid(rowIndex, colIndex, DOT_X));
        map[rowIndex][colIndex] = DOT_X;
    }

    private static void computerTurn() {
        int rowIndex = -1;
        int colIndex = -1;
        Random rand = new Random();
        do {
            rowIndex = rand.nextInt(SIZE);
            colIndex = rand.nextInt(SIZE);
        } while (!isCellVaid(rowIndex, colIndex, DOT_O));
        map[rowIndex][colIndex] = DOT_O;
    }

    private static boolean isCellVaid(int rowIndex, int colIndex, char symbol) {
        if (!isArrayIndexValid(rowIndex) || !isArrayIndexValid(colIndex)) {
            System.out.println("Индексы колонки должны быть в пределе от 0 " + SIZE);
            return false;
        }
        if (map[rowIndex][colIndex] != DOT_EMPTY) {
            if (isHumanTurn(symbol)) {
                System.out.println("Данная ячейка уже занята");
            }
            return false;
        }
        return true;
    }

    private static boolean isHumanTurn(char symbol) {
        return symbol == DOT_X;
    }

    private static boolean isArrayIndexValid(int index) {
        return index >= 0 && index < SIZE;
    }

    private static void printMap() {
        printHeader();
        printMapState();
        System.out.println();
    }

    private static void printMapState() {
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            System.out.print((rowIndex + 1) + " ");
            for (int colIndex = 0; colIndex < SIZE; colIndex++) {
                System.out.print(map[rowIndex][colIndex] + " ");
            }
            System.out.println();
        }
    }

    private static void printHeader() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void initializeGame() {
        for (int rowIndex = 0; rowIndex < map.length; rowIndex++) {
            for (int colIndex = 0; colIndex < map.length; colIndex++) {
                map[rowIndex][colIndex] = DOT_EMPTY;
            }
        }
    }


}

package com.avon;

import java.util.Scanner;

public class ConnectFourGame {
    private final static char PLAYER_G = 'G';
    private final static char PLAYER_R = 'R';
    private final static char EMPTY = ' ';
    private final static int COLUMNS = 7;
    private final static int ROWS = 6;
    private char[][] gameTable;

    ConnectFourGame() {
        gameTable = new char[COLUMNS][ROWS];
    }

    public void play() {
        initGameTable();
        printTable();
        while (true) {
            turn(PLAYER_R);
            if (checkWin(PLAYER_R)) {
                System.out.println("Player 1 [RED] wins!");
                break;
            }
            if (isTableFull()) {
                System.out.println("It's a draw, try another!");
                break;
            }
            turn(PLAYER_G);
            if (checkWin(PLAYER_G)) {
                System.out.println("Player 2 [GREEN] wins!");
                break;
            }
            if (isTableFull()) {
                System.out.println("It's a draw, try another!");
                break;
            }
        }

    }

    private void initGameTable() {
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < ROWS; row++) {
                gameTable[col][row] = EMPTY;
            }
        }
    }

    private void printTable() {
        for (int row = ROWS - 1; row >= 0; row--) {
            for (int col = 0; col < COLUMNS; col++) {
                if (col == 0) System.out.print("|");
                System.out.print(gameTable[col][row] + "|");
            }
            System.out.println();
        }
    }

    private void turn(char playerChar) {
        int n;
        int place;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println( playerChar == PLAYER_R ? "Player 1 [RED] - choose column (1-7)" : "Player 2 [GREEN] - choose column (1-7)");
            n = scanner.nextInt() - 1;
            place = validCell(n);
        } while (place < 0);
        gameTable[n][place] = playerChar;
        printTable();
    }

    private int validCell(int n) {
        if (n < 0 || n >= 7) {
            return -1;
        }
        for (int i = 0; i < ROWS; i++ ) {
            if (gameTable[n][i] == EMPTY) return i;
        }
        return -1;
    }

    private boolean checkWin(char playerChar) {
        for (int col = 0; col < COLUMNS-3; col++) {
            for (int row = 0; row < ROWS-3; row++) {
                if (gameTable[col][row] == playerChar) {
                    int counterHor = 1;
                    int counterVert = 1;
                    int counterDiag1 = 1;
                    for (int i = 1; i < 4; i++) {
                        if (gameTable[col + i][row] == playerChar) {
                            counterHor++;
                        } if (gameTable[col][row + i] == playerChar) {
                            counterVert++;
                        } if (gameTable[col + i][row + i] == playerChar) {
                            counterDiag1++;
                        }
                    }
                    if (counterVert == 4 || counterHor == 4 || counterDiag1 == 4) return true;
                }
            }
            for (int row = ROWS-1; row >= 3; row--) {
                if (gameTable[col][row] == playerChar) {
                    int counterDiag2 = 1;
                    for (int i = 1; i < 4; i++) {
                        if (gameTable[col + i][row - i] == playerChar) {
                            counterDiag2++;
                        }
                    }
                    if (counterDiag2 == 4) return true;
                }
            }
        }
        return false;
    }

    private boolean isTableFull() {
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < ROWS; row++) {
                if (gameTable[col][row] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}

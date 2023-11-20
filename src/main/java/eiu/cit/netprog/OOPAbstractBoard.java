/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eiu.cit.netprog;

/**
 *
 */
public abstract class OOPAbstractBoard {

    static char[] board = new char[9];
    static int[][] winners = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7},
    {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    // initialize
    public void initialize() {
        for (int i = 0; i < board.length; i++) {
            board[i] = '-';
        }
    }

    // check validity of move
    public boolean checkMove(int cell) {
        return board[cell] == '-';
    }

    // check status for a player
    // 0. player has not won yet
    // 1. player won
    public int checkStatus(char player) {
        int status = 0;
        //
        for (int[] winner : winners) {
            if (checkWinner(winner, player)) {
                status = 1;
                break;
            }
        }
        return status;

    }

    public int checkBoard() {
        int status = 1;
        //
        for (char c : board) {
            if (c == '-') {
                status = 0;
                break;
            }
        }
        return status;

    }

    public boolean checkWinner(int[] winner, char player) {
        boolean check = true;
        for (int cell : winner) {
            if (board[cell] != player) {
                check = false;
                break;
            }
        }

        return check;

    }

    // update board
    public void updateBoard(int cell) {
        board[cell] = 'o';
        // return board;

    }

    // make move
    public abstract void makeMove();

    public String encodeBoard() {
        StringBuilder builder = new StringBuilder();
        for (char c : board) {
            builder.append(c).append('.');
        }
        return builder.toString();

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eiu.cit.netprog;

import java.io.*;
import java.net.*;

/**
 *
 */
public class TicTacToeServer {

    public final static int PORT = 13;
    public static char[] board = {'-', '-', '-', '-', '-', '-', '-', '-', '-'};

    public static void main(String[] args) {
        try (ServerSocket socket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket connection = socket.accept()) {
                    //Input
                    Reader in = new InputStreamReader(connection.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(in);

                    //Output
                    Writer out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
                    BufferedWriter writer = new BufferedWriter(out);

                    //Session
                    sendBoard(writer);
                    writer.write("\r\n");
                    writer.flush();

                    String move = reader.readLine();
                    while (!move.equals("quit")) {

                        int cell = Integer.parseInt(move);
                        if (board[cell] != '-') {
                            writer.write("Invalid move" + "\r\n");
                            writer.flush();
                        } else {
                            board[cell] = 'X';
                            if (!checkDraw()) {
                                if (checkWinner(writer)) {
                                    break;
                                }
                                makeMove();
                                if (checkWinner(writer)) {
                                    break;
                                }
                                sendBoard(writer);
                                writer.write("\r\n");
                                writer.flush();
                            } else {
                                sendBoard(writer);
                                writer.write("***It's a draw***");
                                writer.write("\r\n");
                                writer.flush();
                            }
                        }
                        move = reader.readLine();

                    }
                    connection.close();
                    socket.close();
                    break;
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {

        }
    }

    private static void sendBoard(BufferedWriter writer) {
        try {
            for (int i = 0; i < board.length; i++) {
                writer.write(board[i]);
            }
//            writer.write(board[0] + " " + board[1] + " " + board[2] + "\r\n");
//            writer.write(board[3] + " " + board[4] + " " + board[5] + "\r\n");
//            writer.write(board[6] + " " + board[7] + " " + board[8]);
        } catch (IOException e) {

        }
    }

    private static void makeMove() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == '-') {
                board[i] = 'O';
                break;
            }
        }
    }

    private static boolean checkDraw() {
        boolean result = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == '-') {
                result = false;
                break;
            }
        }
        return result;
    }

    private static boolean checkWinner(BufferedWriter writer) {
        try {
            int[][] line = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

            //check every lines/rows
            for (int i = 0; i < line.length; i++) {
                // Eliminate the case dash=dash=dash and check if all three cells match each other
                if (board[line[i][0]] != '-' && board[line[i][0]] == board[line[i][1]] && board[line[i][1]] == board[line[i][2]]) {
                    //check X or O
                    if (board[line[i][0]] == 'X') {
                        sendBoard(writer);
                        writer.write(" X wins!");
                        writer.write("\r\n");
                        writer.flush();
                    } else {
                        sendBoard(writer);
                        writer.write(" O wins!");
                        writer.write("\r\n");
                        writer.flush();
                    }
                    return true;
                }
            }
        } catch (IOException e) {
        }
        return false;
    }
}

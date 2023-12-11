package eiu.cit.netprog;

import java.io.*;
import java.net.*;

public class Task extends Thread {

    private final Socket connection;
    private final OOPAbstractBoard board;
    private final Writer out;
    private final BufferedReader in;
    private final String move;

    public Task(Socket connection, OOPAbstractBoard board, Writer out, BufferedReader in, String move) {
        this.connection = connection;
        this.board = board;
        this.out = out;
        this.in = in;
        this.move = move;
    }

    @Override
    public void run() {
        try {
            if (move.equals("quit")) {
                out.write("");
                out.flush();
                connection.close();
            } else {
                int cell = Character.getNumericValue(move.charAt(0));
                if (cell >= 0 && cell < 9) {
                    boolean empty = board.checkMove(cell);
                    if (empty) {
                        board.updateBoard(cell);
                        if (board.checkStatus('o') == 0) {
                            if (board.checkBoard() == 0) {
                                board.makeMove();
                                if (board.checkStatus('x') == 0) {
                                    if (board.checkBoard() == 0) {
                                        out.write(board.encodeBoard() + "\r\n");
                                        out.flush();
                                    } else {
                                        out.write(board.encodeBoard() + " *** ");
                                        out.write("It's a draw!" + " *** ");
                                        out.write("Let's play again!" + " *** " + "\r\n");
                                        out.flush();
                                        board.initialize();
                                    }
                                } else {
                                    out.write(board.encodeBoard() + " *** ");
                                    out.write("I won!" + " *** ");
                                    out.write("Let's play again!" + " *** " + "\r\n");
                                    out.flush();
                                    board.initialize();
                                }
                            } else {
                                out.write(board.encodeBoard() + " *** ");
                                out.write("It's a draw!" + " *** ");
                                out.write("Let's play again!" + " *** " + "\r\n");
                                out.flush();
                                board.initialize();
                            }
                        } else {
                            out.write(board.encodeBoard() + " *** ");
                            out.write("You won!" + " *** ");
                            out.write("Let's play again!" + " *** " + "\r\n");
                            out.flush();
                            board.initialize();
                        }
                    } else {
                        out.write("Occupied cell!" + "\r\n");
                        out.flush();
                    }
                } else {
                    out.write("Wrong input!" + "\r\n");
                    out.flush();
                }
            }
            return;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

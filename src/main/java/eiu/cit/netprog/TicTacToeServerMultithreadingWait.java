package eiu.cit.netprog;

import java.io.*;
import java.net.*;

public class TicTacToeServerMultithreadingWait extends Thread {

    public OOPAbstractBoard b;
    private final Socket connection;

    public TicTacToeServerMultithreadingWait(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            Writer out = new OutputStreamWriter(connection.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String strategy = in.readLine();
            if (strategy.equals("left")) {
                b = new BoardLeft();
            } else {
                b = new BoardRight();
            }

            String board = in.readLine();
            b.decodeBoard(board);
            while (connection.isConnected()) {
                startGame(out, in);
            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void startGame(Writer out, BufferedReader in) throws IOException {
        String move = in.readLine();
        if (move.equals("quit")) {
            connection.close();
        } else {
            // get move
            /*
             * This method returns the numeric value of the character, as a non-negative int
             * value; -2 if the character has a numeric value that is not a non-negative
             * integer; -1 if the character has no numeric value.
             */
            int cell = Character.getNumericValue(move.charAt(0));
            // check that the move is within range
            if (cell >= 0 && cell < 9) {
                // check that the move is to an empty cell
                boolean empty = b.checkMove(cell);
                // System.out.println(empty);
                if (empty) {
                    /// update board
                    b.updateBoard(cell);
                    // check status for player 'o'
                    // 0. player has not won yet
                    // 1. player won
                    if (b.checkStatus('o') == 0) {
                        if (b.checkBoard() == 0) {
                            b.makeMove();
                            // check status for player 'x'
                            if (b.checkStatus('x') == 0) {
                                // check status of board
                                // 0. no draw yet
                                // 1. draw
                                if (b.checkBoard() == 0) {
                                    // return new board
                                    out.write(b.encodeBoard() + "\r\n");
                                    out.flush();
                                    connection.close();
                                } else {
                                    // return new board
                                    out.write(b.encodeBoard() + " *** ");
                                    out.write("It's a draw!" + " *** ");
                                    out.write("Let's play again!" + " *** " + "\r\n");
                                    out.flush();
                                    b.initialize();
                                }
                            } else {
                                // return new board
                                out.write(b.encodeBoard() + " *** ");
                                out.write("I won!" + " *** ");
                                out.write("Let's play again!" + " *** " + "\r\n");
                                out.flush();
                                b.initialize();
                            }

                        } else {
                            // return new board
                            out.write(b.encodeBoard() + " *** ");
                            out.write("It's a draw!" + " *** ");
                            out.write("Let's play again!" + " *** " + "\r\n");
                            out.flush();
                            b.initialize();
                        }

                    } else {
                        // return new board
                        out.write(b.encodeBoard() + " *** ");
                        out.write("You won!" + " *** ");
                        out.write("Let's play again!" + " *** " + "\r\n");
                        out.flush();
                        b.initialize();
                    }

                } else {
                    // return new board
                    out.write("Occupied cell!" + "\r\n");
                    out.flush();
                }

            } else {
                // return new board
                out.write("Wrong input!" + "\r\n");
                out.flush();
            }
        }
    }
}

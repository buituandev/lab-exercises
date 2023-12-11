package eiu.cit.netprog;

import java.net.*;
import java.io.*;

public class TicTacToeServerMultithreading extends Thread {

    private final static int PORT = 10;
    private OOPAbstractBoard b;
    private final Socket connection;

    public TicTacToeServerMultithreading(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            Writer out = new OutputStreamWriter(connection.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            playGame(in, out);
            connection.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                Socket connection = server.accept();
                Thread clientThread = new TicTacToeServerMultithreading(connection);
                clientThread.start();
            }
        }
    }

    private void playGame(BufferedReader in, Writer out) throws IOException {
        String tragedy = in.readLine();
        if (tragedy.equals("left")) {
            b = new BoardLeft();
            out.write("I'LL PLAY LEFT STRATEGY" + "\r\n");
            out.flush();
        } else if (tragedy.equals("right")) {
            b = new BoardRight();
            out.write("I'LL PLAY RIGHT STRATEGY" + "\r\n");
            out.flush();
        } else {
            b = new BoardRandom();
            out.write("I'LL PLAY RANDOM STRATEGY" + "\r\n");
            out.flush();
        }
        // initialize
        b.initialize();

        while (true) {
            String move = in.readLine();
            if (move.equals("quit")) {
                break;
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
}

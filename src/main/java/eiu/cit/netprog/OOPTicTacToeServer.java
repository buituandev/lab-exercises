package eiu.cit.netprog;

import java.net.*;
import java.io.*;

public class OOPTicTacToeServer {

    private final static int PORT = 10;
    private static OOPAbstractBoard b;

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            try (Socket connection = server.accept()) {
                if (args[0].equals("left")) {
                    b = new BoardLeft();
                } else if (args[0].equals("right")) {
                    b = new BoardRight();
                } else {
                    b = new BoardRandom();
                }
                // initialize
                b.initialize();

                Writer out = new OutputStreamWriter(connection.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

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
                connection.close();
            }
            server.close();
        }
    }
}

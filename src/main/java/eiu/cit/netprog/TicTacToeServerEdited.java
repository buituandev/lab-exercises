package eiu.cit.netprog;

import java.net.*;
import java.io.*;

public class TicTacToeServerEdited {
	private final static int PORT = 10;
	private final static int[][] winners = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 },
			{ 2, 5, 8 }, { 0, 4, 8 }, { 2, 4, 6 } };

	public static void main(String[] args) throws IOException {
		try (ServerSocket server = new ServerSocket(PORT)) {
			try (Socket connection = server.accept()) {
				char[] board = new char[9];
				// initialize
				initialize(board);

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
							boolean empty = checkMove(board, cell);
							// System.out.println(empty);
							if (empty) {
								/// update board
								updateBoard(board, cell);
								// check status for player 'o'
								// 0. player has not won yet
								// 1. player won
								if (checkStatus(board, 'o') == 0) {
									if (checkBoard(board) == 0) {
										makeMove(board);
										// check status for player 'x'
										if (checkStatus(board, 'x') == 0) {
											// check status of board
											// 0. no draw yet
											// 1. draw
											if (checkBoard(board) == 0) {
												// return new board
												out.write(encodeBoard(board) + "\r\n");
												out.flush();
											} else {
												// return new board
												out.write(encodeBoard(board) + " *** ");
												out.write("It's a draw!" + " *** ");
												out.write("Let's play again!" + " *** " + "\r\n");
												out.flush();
												initialize(board);
											}
										} else {
											// return new board
											out.write(encodeBoard(board) + " *** ");
											out.write("I won!" + " *** ");
											out.write("Let's play again!" + " *** " + "\r\n");
											out.flush();
											initialize(board);
										}

									} else {
										// return new board
										out.write(encodeBoard(board) + " *** ");
										out.write("It's a draw!" + " *** ");
										out.write("Let's play again!" + " *** " + "\r\n");
										out.flush();
										initialize(board);
									}

								} else {
									// return new board
									out.write(encodeBoard(board) + " *** ");
									out.write("You won!" + " *** ");
									out.write("Let's play again!" + " *** " + "\r\n");
									out.flush();
									initialize(board);
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

	// initialize
	private static void initialize(char[] board) {
		for (int i = 0; i < board.length; i++) {
			board[i] = '-';
		}
	}

	// check validity of move
	private static boolean checkMove(char[] board, int cell) {
		return board[cell] == '-';
	}

	// check status for a player
	// 0. player has not won yet
	// 1. player won

	private static int checkStatus(char[] board, char player) {
		int status = 0;
		//
		for (int[] winner : winners) {
			if (checkWinner(winner, board, player)) {
				status = 1;
				break;
			}
		}
		return status;

	}

	private static int checkBoard(char[] board) {
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

	private static boolean checkWinner(int[] winner, char[] board, char player) {
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
	private static void updateBoard(char[] board, int cell) {
		board[cell] = 'o';
		// return board;

	}

	// make move
	private static void makeMove(char[] board) {
		for (int i = 0; i < board.length; i++) {
			if (board[i] == '-') {
				board[i] = 'x';
				break;
			}
		}

	}

	// encoding the current board
	private static String encodeBoard(char[] board) {
		StringBuilder builder = new StringBuilder();
		for (char c : board) {
			builder.append(c).append('.');
		}
		return builder.toString();

	}
}
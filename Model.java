import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Random;
import java.util.Scanner;

public class Model {

	final int PORT = 50348;
	
	//A enum just for niceness
	private enum Piece {
		Nought,
		Cross,
		None;

		@Override
		public String toString() {
			switch(this) {
				case Nought: return "O";
				case Cross: return "X";
				case None: return " ";
				default: throw new IllegalArgumentException();
			}
		}

		public char toChar() {
			switch(this) {
				case Nought: return 'O';
				case Cross: return 'X';
				case None: return ' ';
				default: throw new IllegalArgumentException();
			}
		}


		public static Piece fromString(String value) {
			switch(value) {
				case "O": return Piece.Nought;
				case "X": return Piece.Cross;
				case " ": return Piece.None;
				default: throw new IllegalArgumentException();
			}
		}
	}

	//Used to report errors to the view
	public interface ErrorCallback {
		public void reportError(String error);
	}


	//Internal representation of the board
	private Piece[][] board = new Piece[3][3];

	//Which piece is this player
	private Piece me = Piece.None;

	//The socket used to talk to the other player
	private Socket client_sock = new Socket();

	//Used to accept connections
	private ServerSocket server_sock;

	//Used to report errors to the view
	private ErrorCallback errorCallback;


	//Add a error reporting callback
	public void addErrorReporter(ErrorCallback c) {
		errorCallback = c;
	}

	public Model(boolean server) {
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				board[row][col] = Piece.None;
			}
		}

		if(server) {
			try {
				//Create the socket. ServerSockets throw but sockets don't for some reason
				server_sock = new ServerSocket();

				//Reuse the address. This doesn't seem to work very well though
				server_sock.setReuseAddress(true);

				//Bind the server address
				server_sock.bind(new InetSocketAddress(PORT));

				//Nothing to do but print the error and crash. There is no reporting callback yet
			} catch (SocketException e) {
				e.printStackTrace();
				System.exit(1);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		} else {
			try {
				client_sock.setReuseAddress(true);
			} catch (SocketException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}


	//UML

	//Makes a move and sends it to the other player. Returns true if it's a valid move, false otherwise
	public boolean move(int row, int column) {
		if(row < 0 || row > 2 || column < 0 || column > 2) {
			errorCallback.reportError("Incorrect row and/or column");
			return false;
		}
		if(!board[row][column].equals(Piece.None)) {
			errorCallback.reportError("Piece overwrite attempted");
			return false;
		}
		sendMove(row, column);
		board[row][column] = me;
		return true;
	}


	//Used to check if the game has ended.
	public boolean hasEnded() {
		return winner() != ' ' || draw();
	}

	//Who the winner is. 'X' if its crosses, 'O' if its noughts and ' ' if it's neither (draw)
	public char winner() {
		for(int i = 0; i < 3; i++) {
			Piece rw = rowWin(i);
			if(!rw.equals(Piece.None)) {
				return rw.toChar();
			}

			Piece cw = colWin(i);
			if(!cw.equals(Piece.None)) {
				return cw.toChar();
			}
		}
		Piece tl = diaWin(true);
		if(!tl.equals(Piece.None)) {
			return tl.toChar();
		}
		Piece tr = diaWin(false);
		if(!tr.equals(Piece.None)) {
			return tr.toChar();
		}

		return Piece.None.toChar();

	}


	private Piece diaWin(boolean topleft) {
		if(topleft) {
			Piece w = board[0][0];
			for(int i = 1; i < 3; i++) {
				if(!board[i][i].equals(w)) {
					return Piece.None;
				}
			}
			return w;
		} else {
			Piece w = board[0][2];
			for(int i = 1; i < 3; i++) {
				if(!board[i][3-i].equals(w)) {
					return Piece.None;
				}
			}
			return w;

		}
	}
	private Piece colWin(int col) {
		Piece w = board[0][col];
		for(int i = 1; i < 3; i++) {
			if(!board[i][col].equals(w)) {
				return Piece.None;
			}
		}
		return w;
	}
	private Piece rowWin(int row) {
		Piece w = board[row][0];
		for(int i = 1; i < 3; i++) {
			if(!board[row][i].equals(w)) {
				return Piece.None;
			}
		}
		return w;
	}

	public boolean draw() {
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				if(board[row][col].equals(Piece.None)) {
					return false;
				}
			}
		}
		return true;
	}

	public char[][] getBoard() {
		char[][] retval = new char[3][3];
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				retval[row][col] = board[row][col].toChar();
			}
		}
		return retval;
	}

	private void sendMove(int row, int col) {
		try {
			PrintWriter w = new PrintWriter(client_sock.getOutputStream());
			w.print(row * 3 + col);
		} catch(IOException e) {
			errorCallback.reportError(e.toString());
		}
	}

	public int recvMove() {
		try {
			Scanner r = new Scanner(client_sock.getInputStream());
			int move = r.nextInt();
			if(move == 10) {
				quit();
				return 10;
			}

			return move;
		} catch(IOException e) {
			errorCallback.reportError(e.toString());
			return 11;
		}
	}

	public boolean connect(String address) {
		try {
			client_sock.connect(new InetSocketAddress(InetAddress.getByName(address), PORT), 500);
			
			Scanner in = new Scanner(client_sock.getInputStream());
			String first = in.nextLine();
			System.out.println(first);
			if (first.equals("true")) {
				me = Piece.Cross;
			} else {
				me = Piece.Nought;
			}
			return true;
		} catch (SocketTimeoutException e) {
			errorCallback.reportError(e.toString());
			return false;
		} catch(IOException e) {
			errorCallback.reportError(e.toString());
			return false;
		}
	}
	public boolean accept() {
		try {
			client_sock = server_sock.accept();
			amIFirstPlayer();
			return true;
		} catch (IOException e) {
			errorCallback.reportError(e.toString());
			return false;
		}
	}


	public boolean amIFirstPlayer() {
		if(!me.equals(Piece.None)) {
			return me.equals(Piece.Cross);
		}
		Random r = new Random();
		Boolean amIFirst = r.nextBoolean();
		if(amIFirst) {
			me = Piece.Cross;
		} else {
			me = Piece.Nought;
		}
		try {
			PrintWriter out = new PrintWriter(client_sock.getOutputStream());
			out.println(!amIFirst);
			out.flush();
		} catch (IOException e) {
			errorCallback.reportError(e.toString());
			return false;
		}


		return amIFirst;
	}

	public void quit() {
		try {
			PrintWriter w = new PrintWriter(client_sock.getOutputStream());
			w.print(10);
			client_sock.close();
		} catch(IOException e) {
			System.err.println("Failed to quit");
			return;
		}
	}
}

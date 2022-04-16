import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;

public class Model {

	final int PORT = 50348;

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

	public interface ErrorCallback {
		public void reportError(String error);
		public void reportWarning(String warning);
	}

	private Piece[][] board = new Piece[3][3];
	private Piece me = Piece.None;
	private Socket sock = new Socket();
	private ErrorCallback errorCallback;


	public void addErrorReporter(ErrorCallback c) {
		errorCallback = c;
	}

	public Model() {
	}


	//UML

	public boolean move(int row, int column) {
		if(row < 0 || row > 3 || column < 0 || column > 3) {
			errorCallback.reportWarning("Incorrect row and/or column");
			return false;
		}
		if(!board[row][column].equals(Piece.None)) {
			errorCallback.reportWarning("Piece overwrite attempted");
			return false;
		}
		sendMove(row, column);
		board[row][column] = me;
		return true;
	}

	public boolean hasEnded() {
		return winner() != ' ' || draw();
	}

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
			PrintWriter w = new PrintWriter(sock.getOutputStream());
			w.print(row * 3 + col);
		} catch(IOException e) {
			errorCallback.reportError(e.toString());
		}
	}

	public int recvMove() {
		try {
			Scanner r = new Scanner(sock.getInputStream());
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
			sock.connect(new InetSocketAddress(InetAddress.getByName(address), PORT), 120);
			return true;
		} catch(IOException e) {
			errorCallback.reportError(e.toString());
			return false;
		}
	}

	public boolean amIFirstPlayer() {
		Random r = new Random();
		Boolean amIFirst = r.nextBoolean();
		if(amIFirst) {
			me = Piece.Cross;
		} else {
			me = Piece.Nought;
		}
		return amIFirst;
	}

	public void quit() {
		try {
			PrintWriter w = new PrintWriter(sock.getOutputStream());
			w.print(10);
		} catch(IOException e) {
			System.err.println("Failed to quit");
			System.exit(1);
		}
	}
}

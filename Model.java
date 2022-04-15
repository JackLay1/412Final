import java.net.ServerSocket;
import java.io.IOException;

public class Model {
	
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

	public interface Callback {
		public void reportError(String error);
		public void reportWarning(String warning);
	}

	private Piece[][] board = new Piece[3][3];
	private Piece me = Piece.None;
	private ServerSocket sock;
	private Callback errorCallback;


	public void addErrorReporter(Callback c) {
		errorCallback = c;
	}

	public Model() {
		try {
			sock = new ServerSocket(556745);
		} catch(IOException ex) {
			System.err.println("Socket couldn't open.");
			System.exit(1);
		}
	}


	//UML
	
	public boolean move(int row, int col, char piece) {
		//TODO
		return false;
	}

	public boolean hasEnded() {
		return winner() != ' ' || draw();
	}

	public char winner() {
		//TODO
		return ' ';
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

	public void sendMove(int row, int col) {
		//TODO
	}

	public int recvMove() {
		//TODO
		return -1;
	}

	boolean connect(String address) {
		//TODO
		return false;
	}

	public char firstPlayer() {
		//TODO
		return ' ';
	}

	public void quit() {
		//TODO
	}
}

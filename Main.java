public class Main {
	Controller conn;
	public static void main(String[] args) {
		if(args.length == 1) {
			if(args[0] == "--help") {
				System.err.println("Usage: ttt [port]\n" + 
						   "If no port is specified, the program functions as a server instead");
				return;
			} else {
				new Main().client(args[0]);
			}
		} else if(args.length == 0) {
			new Main().server();
		} else {
			System.err.println("Too many arguments");
			System.exit(1);
		}
	}

	private void client(String ip) {
		conn = new Controller();
		conn.client(ip);
	}

	private void server() {
		conn = new Controller();
		conn.server();
	}
}

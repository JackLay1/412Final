import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Controller {
	Model model;
	View view;


	volatile private int _____private_move = 10;
	private void setupbuttons() {
		view.addButtonListener1(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_____private_move = 0;
			}
		});
		view.addButtonListener2(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_____private_move = 1;
			}
		});
		view.addButtonListener3(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_____private_move = 2;
			}
		});
		view.addButtonListener4(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_____private_move = 3;
			}
		});
		view.addButtonListener5(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_____private_move = 4;
			}
		});
		view.addButtonListener6(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_____private_move = 5;
			}
		});
		view.addButtonListener7(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_____private_move = 6;
			}
		});
		view.addButtonListener8(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_____private_move = 7;
			}
		});
		view.addButtonListener9(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_____private_move = 8;
			}
		});
	}

	private int getMove() {
		while(_____private_move == 10) {
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {
				break;
			}
		}
		return _____private_move;
	}



	public Controller() {
		model = new Model();
		view = new View();
		
		view.start();
		model.addErrorReporter(new Model.ErrorCallback() {
			@Override
			public void reportError(String error) {
				System.err.println(error);
				view.showError(error);
			}
		});

		view.addConnectButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String t = view.getIP();
				client(t);
			}
		});

		view.addQuitButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.quit();
			}
		});


		if(!model.accept()) {
			System.exit(1);
		}

		run();


	}

	private void client(String ip) {

		if(!model.connect(ip)) {
			view.showError("Couldn't Connect");
			return;
		};
		run();
	}

	private void run() {
		System.out.println("HERE");

		view.goToGame();
		boolean first = model.amIFirstPlayer();
		if(!first) {
			view.WaitForOtherPlayer();
			model.recvMove();
			view.update(model.getBoard());
		}
		while(true) {
			if(!model.hasEnded()) {
				view.ItsYourMove();
				int move = getMove();
				int row = move / 3;
				int col = move % 3;
				model.move(row, col);
				view.update(model.getBoard());
			} else {
				break;
			}

			if(!model.hasEnded()) {
				view.WaitForOtherPlayer();
				int move = model.recvMove();
				int row = move / 3;
				int col = move % 3;
				model.move(row, col);
				view.update(model.getBoard());
			} else {
				break;
			}
		}
		model.quit();
		char winner = model.winner();

		view.displayGameResults(winner);
	}
}

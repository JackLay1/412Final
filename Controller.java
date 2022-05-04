import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Controller {
	Model model;
	View view;


	volatile private int private_move = 10;
	private void setupbuttons() {
		view.addButtonListener1(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				private_move = 0;
			}
		});
		view.addButtonListener2(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				private_move = 1;
			}
		});
		view.addButtonListener3(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				private_move = 2;
			}
		});
		view.addButtonListener4(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				private_move = 3;
			}
		});
		view.addButtonListener5(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				private_move = 4;
			}
		});
		view.addButtonListener6(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				private_move = 5;
			}
		});
		view.addButtonListener7(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				private_move = 6;
			}
		});
		view.addButtonListener8(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				private_move = 7;
			}
		});
		view.addButtonListener9(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				private_move = 8;
			}
		});
	}

	private int getMove() {
		while(private_move == 10) {
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {
				break;
			}
		}
		int move = private_move;
		private_move = 10;
		return move;
	}

	public Controller() {
		view = new View();
		
		view.addQuitButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.quit();
			}
		});

	}


	private void setupErrorCallback() {
		model.addErrorReporter(new Model.ErrorCallback() {
			@Override
			public void reportError(String error) {
				System.err.println(error);
				view.showError(error);
			}
		});
	}
	
	public void server() {
		model = new Model(true);
		setupErrorCallback();
		view.waiting();
		if(!model.accept()) {
			view.showError("Couldn't accept connection");
			return;
		}
		run();
	}

	public void client(String ip) {
		model = new Model(false);
		setupErrorCallback();
		if(!model.connect(ip)) {
			view.showError("Couldn't Connect");
			return;
		};
		run();
	}

	private void run() {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				model.quit();
			}
		}));
		
		view.goToGame();
		setupbuttons();
		boolean first = model.amIFirstPlayer();
		if(!first) {
			view.WaitForOtherPlayer();
			if(!model.recvMove()) {
				return;
			}
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
				if(!model.recvMove()) {
					return;
				}
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

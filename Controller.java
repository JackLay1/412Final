import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Controller {
	Model model;
	View view;

	public Controller() {
		model = new Model();
		view = new View();
		view.start();
		
		model.addErrorReporter(new Model.ErrorCallback() {
			@Override
			public void reportError(String error) {
				view.showError(error);
			}
		});

		view.addConnectButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String t = view.getIP();
				view.goToGame();
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


	}

	private void client(String ip) {
		model.connect(ip);
		run();
	}

	private void run() {
		boolean first = model.amIFirstPlayer();
		if(!first) {
			view.WaitForOtherPlayer();
			model.recvMove();
			//view.update(model.getBoard());
		}
		while(true) {
			if(!model.hasEnded()) {
				view.ItsYourMove();
				//int move = view.getMove();
				//int row = move / 3;
				//int col = move % 3;
				//model.move(row, col);
				//view.update(model.getBoard());
			} else {
				break;
			}

			if(!model.hasEnded()) {
				view.WaitForOtherPlayer();
				int move = model.recvMove();
				int row = move / 3;
				int col = move % 3;
				model.move(row, col);
				//view.update(model.getBoard());
			} else {
				break;
			}
		}
		model.quit();
		char winner = model.winner();

		view.displayGameResults(winner);
	}
}

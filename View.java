import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;

public class View {
	JFrame frame = new JFrame();
	JButton[] blist = new JButton[9];
	JButton quit = new JButton("QUIT");

	public void addButtonListener1(ActionListener al){ blist[0].addActionListener(al); }
	public void addButtonListener2(ActionListener al){ blist[1].addActionListener(al); }
	public void addButtonListener3(ActionListener al){ blist[2].addActionListener(al); }
	public void addButtonListener4(ActionListener al){ blist[3].addActionListener(al); }
	public void addButtonListener5(ActionListener al){ blist[4].addActionListener(al); }
	public void addButtonListener6(ActionListener al){ blist[5].addActionListener(al); }
	public void addButtonListener7(ActionListener al){ blist[6].addActionListener(al); }
	public void addButtonListener8(ActionListener al){ blist[7].addActionListener(al); }
	public void addButtonListener9(ActionListener al){ blist[8].addActionListener(al); }

	public void addQuitButtonListener(ActionListener al){ 
		quit.addActionListener(al);
	}

	public void waiting() {
		frame.getContentPane().removeAll();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JLabel wait = new JLabel("WAITING");
		wait.setFont(new Font("Arial", Font.BOLD,56));
		JLabel IP = new JLabel();
		try {
			Socket test_sock = new Socket("example.com", 80);
			String local_host = test_sock.getLocalAddress().getHostAddress().toString();
			test_sock.close();
			IP.setText(local_host);
            IP.setFont(new Font("Arial",Font.BOLD, 28));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setSize(600,600);
		frame.add(wait, BorderLayout.CENTER);
		frame.add(IP, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	public void goToGame() {
		frame.setSize(600,600);
		for(int i=0; i<9; i++) {
			blist[i] = new JButton("") ;
		}

		frame.getContentPane().removeAll();
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(3, 3));
		JPanel buttonss = new JPanel();

		container.add(blist[0]);
		container.add(blist[1]);
		container.add(blist[2]);
		container.add(blist[3]);
		container.add(blist[4]);
		container.add(blist[5]);
		container.add(blist[6]);
		container.add(blist[7]);
		container.add(blist[8]);
		for(JButton b : blist){
			b.setFont(new Font("Arial", Font.BOLD, 56));
		}

		frame.add(container, BorderLayout.CENTER);
		buttonss.add(quit);
		frame.add(buttonss, BorderLayout.SOUTH);
		buttonss.setVisible(true);
		frame.setVisible(true);
		frame.repaint();
		frame.validate();


		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	public void update(char[][] f){
		blist[0].setText(Character.toString(f[0][0]));
		blist[1].setText(Character.toString(f[0][1]));
		blist[2].setText(Character.toString(f[0][2]));
		blist[3].setText(Character.toString(f[1][0]));
		blist[4].setText(Character.toString(f[1][1]));
		blist[5].setText(Character.toString(f[1][2]));
		blist[6].setText(Character.toString(f[2][0]));
		blist[7].setText(Character.toString(f[2][1]));
		blist[8].setText(Character.toString(f[2][2]));

	}

	public void showError(String s) {
		JLabel error = new JLabel(s);
		frame.getContentPane().removeAll();
		frame.add(error, BorderLayout.CENTER);
		frame.repaint();
		frame.validate();

	}

	public void ItsYourMove() {
		for(int i=0;i<9;i++) {
			if(blist[i].getText().equals(" ")) {
				blist[i].setEnabled(true);
			}
		}
	}

	public void WaitForOtherPlayer() {
		for(int i=0;i<9;i++) {
			blist[i].setEnabled(false);
		}
	}

	public void displayGameResults(char w) {
		frame.getContentPane().removeAll();
		JLabel win = new JLabel();
        win.setFont(new Font("Arial",Font.BOLD, 28));
		if(w == 'X') {
			win.setText("X wins");
		} else if(w == 'O') {
			win.setText("O WINS");
		} else {
			win.setText("TIE GANME");
		}

		frame.add(win, BorderLayout.CENTER);
		frame.repaint();
		frame.validate();
	}
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class View {
    JFrame frame = new JFrame();
    JButton[] blist = new JButton[9];
    JTextField ip = new JTextField();
    JLabel prompt = new JLabel("ENTER YOUR OPPONENT'S IP");
    JButton send = new JButton("SEND REQUEST");
    JButton quit = new JButton("QUIT");
    JButton again = new JButton("PLAY AGAIN");
    JButton URTURN = new JButton("YOUR TURN MDOE");
    JButton NOTU = new JButton("NOT YOUR TURN");

    JPanel container21 = new JPanel(new GridLayout(2,1));

    JButton[][] buttons = new JButton[3][3];
    char game[][] = new char[3][3];
    int count = 1;

    public void addButtonListener1(ActionListener al){blist[0].addActionListener(al);}
    public void addButtonListener2(ActionListener al){blist[1].addActionListener(al);}
    public void addButtonListener3(ActionListener al){blist[2].addActionListener(al);}
    public void addButtonListener4(ActionListener al){blist[3].addActionListener(al);}
    public void addButtonListener5(ActionListener al){blist[4].addActionListener(al);}
    public void addButtonListener6(ActionListener al){blist[5].addActionListener(al);}
    public void addButtonListener7(ActionListener al){blist[6].addActionListener(al);}
    public void addButtonListener8(ActionListener al){blist[7].addActionListener(al);}
    public void addButtonListener9(ActionListener al){blist[8].addActionListener(al);}

    public void addConnectButtonListener(ActionListener al){
        send.addActionListener(al);
    };
    public void addQuitButtonListener(ActionListener al){quit.addActionListener(al);}
    /*public void start(){
        frame.setSize(600,600);
        container21.add(prompt);
        container21.add(ip);
        frame.add(send, BorderLayout.EAST);
        frame.add(container21, BorderLayout.CENTER);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    };*/
    public String getIP(){
        return ip.getText();
    };
    
        public void waiting(){
        frame.getContentPane().removeAll();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel wait = new JLabel("WAITING");
        JLabel IP = new JLabel();
            try {
                IP.setText(InetAddress.getLocalHost().toString());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            frame.setSize(600,600);
            frame.add(wait, BorderLayout.CENTER);
            frame.add(IP, BorderLayout.SOUTH);
            frame.setVisible(true);
    }
    
    public void goToGame() {
        for(int i=0; i<9; i++){
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

        frame.add(container, BorderLayout.CENTER);
        buttonss.add(again);
        buttonss.add(quit);
        frame.add(buttonss, BorderLayout.SOUTH);
        buttonss.setVisible(true);
        frame.setVisible(true);
        frame.repaint();
        frame.validate();


        again.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 9; i++) {
                    blist[i].setText("");
                    blist[i].setEnabled(true);
                    count = 1;
                }
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        game[i][j] = '\u0000';
                    }
                }
            }
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
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

        for(JButton b : blist ){
            if(!b.getText().equals("")){
                b.setEnabled(false);
            }
        }
    }

    public void showError(String s){
        JLabel error = new JLabel(s);
        frame.getContentPane().removeAll();
        frame.add(error, BorderLayout.CENTER);
        frame.repaint();
        frame.validate();

    }


    public void DISPLAYBOARD(){
        System.out.println(game[0][0] + "|" + game[0][1] + "|" + game[0][2]);
        System.out.println(game[1][0] + "|" + game[1][1] + "|" + game[1][2]);
        System.out.println(game[2][0] + "|" + game[2][1] + "|" + game[2][2]);
    }
    public void ItsYourMove(){

        for(int i=0;i<9;i++) {
            if (blist[i].getText() == "") {
                blist[i].setEnabled(true);
            }
        }
    }
    public void WaitForOtherPlayer(){
        for(int i=0;i<9;i++){
            blist[i].setEnabled(false);}
    }
    public void displayGameResults(char w){
        frame.getContentPane().removeAll();
        JLabel win = new JLabel();
        if(w == 'X'){
            win.setText("X wins");
        }
        else if(w == 'O'){
            win.setText("O WINS");
        }
        else{win.setText("TIE GANME");}

        frame.add(win, BorderLayout.CENTER);
    }
    public void halt(){
        for(int i=0; i<9; i++){
            blist[i].setEnabled(false);
        }
    }
    public void clicked(JButton b){
        if (count % 2 == 1) {
            b.setText("x");
            b.setFont(new Font("ARIAL", Font.BOLD, 70));
            b.setEnabled(false);
            count++;
        }
        else {
            b.setText("O");
            b.setFont(new Font("COMIC SANS", Font.BOLD, 69));
            b.setEnabled(false);
            count++;
        }
    }

    public static void main(String[] args) {

    }
}

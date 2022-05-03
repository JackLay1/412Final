import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {
    JFrame frame = new JFrame();

    JButton blist[] = new JButton[9];
    JTextField ip = new JTextField();
    JLabel prompt = new JLabel("ENTER YOUR OPPONENT'S IP");
    JButton send = new JButton("SEND REQUEST");
    JButton quit = new JButton("QUIT");
    JButton again = new JButton("PLAY AGAIN");
    JButton URTURN = new JButton("YOUR TURN MDOE");
    JButton NOTU = new JButton("NOT YOUR TURN");

    JButton b1 = new JButton();
    JButton b2 = new JButton();
    JButton b3 = new JButton();
    JButton b4 = new JButton();
    JButton b5 = new JButton();
    JButton b6 = new JButton();
    JButton b7 = new JButton();
    JButton b8 = new JButton();
    JButton b9 = new JButton();

    JPanel container21 = new JPanel(new GridLayout(2,1));

    JButton[][] buttons = new JButton[3][3];
    char game[][] = new char[3][3];
    int count = 1;


    public void addListener(ActionListener al){
        send.addActionListener(al);
    };
    public void start(){
        frame.setSize(600,600);
        container21.add(prompt);
        container21.add(ip);
        frame.add(send, BorderLayout.EAST);
        frame.add(container21, BorderLayout.CENTER);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    };
    public String getIP(){
        return ip.getText();
    };
    public void goToGame() {
        frame.getContentPane().removeAll();
        JPanel container = new JPanel();
        container.setLayout(new GridLayout(3, 3));
        JPanel buttonss = new JPanel();


        container.add(b1);
        container.add(b2);
        container.add(b3);
        container.add(b4);
        container.add(b5);
        container.add(b6);
        container.add(b7);
        container.add(b8);
        container.add(b9);

        frame.add(container, BorderLayout.CENTER);
        buttonss.add(again);
        buttonss.add(quit);
        frame.add(buttonss, BorderLayout.SOUTH);
        buttonss.setVisible(true);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicked(b1);
                container.setEnabled(false);
                if (count % 2 == 0) {
                    game[0][0] = 'x';
                } else {
                    game[0][0] = 'O';
                }
                WaitForOtherPlayer();
                DISPLAYBOARD();
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicked(b2);
                if (count % 2 == 0) {
                    game[0][1] = 'x';
                } else {
                    game[0][1] = 'O';
                }
                WaitForOtherPlayer();
                DISPLAYBOARD();
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicked(b3);
                if (count % 2 == 0) {
                    game[0][2] = 'x';
                } else {
                    game[0][2] = 'O';
                }
                WaitForOtherPlayer();
                DISPLAYBOARD();
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicked(b4);
                if (count % 2 == 0) {
                    game[1][0] = 'x';
                } else {
                    game[1][0] = 'O';
                }
                WaitForOtherPlayer();
                DISPLAYBOARD();
            }
        });
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicked(b5);
                if (count % 2 == 0) {
                    game[1][1] = 'x';
                } else {
                    game[1][1] = 'O';
                }
                WaitForOtherPlayer();
                DISPLAYBOARD();
            }
        });
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicked(b6);
                if (count % 2 == 0) {
                    game[1][2] = 'x';
                } else {
                    game[1][2] = 'O';
                }
                WaitForOtherPlayer();
                DISPLAYBOARD();
            }
        });
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicked(b7);
                if (count % 2 == 0) {
                    game[2][0] = 'x';
                } else {
                    game[2][0] = 'O';
                }
                WaitForOtherPlayer();
                DISPLAYBOARD();
            }
        });
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicked(b8);
                if (count % 2 == 0) {
                    game[2][1] = 'x';
                } else {
                    game[2][1] = 'O';
                }
                WaitForOtherPlayer();
                DISPLAYBOARD();
            }
        });
        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicked(b9);
                if (count % 2 == 0) {
                    game[2][2] = 'x';
                } else {
                    game[2][2] = 'O';
                }
                WaitForOtherPlayer();
                DISPLAYBOARD();
            }
        });
        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicked(b8);
                if (count % 2 == 0) {
                    game[2][1] = 'x';
                } else {
                    game[2][1] = 'O';
                }
                WaitForOtherPlayer();
            }
        });
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
                frame.getContentPane().removeAll();
                container21.add(prompt);
                container21.add(ip);
                frame.add(send, BorderLayout.EAST);
                frame.add(container21, BorderLayout.CENTER);
                frame.repaint();
                frame.validate();
            }
        });
    }
    public void update(char[][] f){
        b1.setText(Character.toString(f[0][0]));
        b2.setText(Character.toString(f[0][1]));
        b3.setText(Character.toString(f[0][2]));
        b4.setText(Character.toString(f[1][0]));
        b5.setText(Character.toString(f[1][1]));
        b6.setText(Character.toString(f[1][2]));
        b7.setText(Character.toString(f[2][0]));
        b8.setText(Character.toString(f[2][1]));
        b9.setText(Character.toString(f[2][2]));
    }



    public void DISPLAYBOARD(){
        System.out.println(game[0][0] + "|" + game[0][1] + "|" + game[0][2]);
        System.out.println(game[1][0] + "|" + game[1][1] + "|" + game[1][2]);
        System.out.println(game[2][0] + "|" + game[2][1] + "|" + game[2][2]);
    }
    public void change(int b){
        blist[b].setEnabled(false);
        if(count%2 == 1){
            blist[b].setText("X");
        }
        else{blist[b].setText("O");}
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
        new View().start();
        new View().goToGame();
    }
}

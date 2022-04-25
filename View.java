import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class View {
    JTextField ip = new JTextField();
    JLabel prompt = new JLabel("ENTER YOUR OPPONENT'S IP");
    JButton send = new JButton("SEND REQUEST");
    JButton quit = new JButton("QUIT");
    JButton again = new JButton("PLAY AGAIN");
    JPanel container21 = new JPanel(new GridLayout(2,1));

    JButton[][] buttons = new JButton[3][3];
    char game[][] = new char[3][3];
    int count = 1;
    JButton blist[] = new JButton[9];

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
    public void finalgo(){
        JFrame frame = new JFrame("Tx3");
        frame.setSize(600,600);
        container21.add(prompt);
        container21.add(ip);
        frame.add(send, BorderLayout.EAST);
        frame.add(container21, BorderLayout.CENTER);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pip = ip.getText();
                System.out.println(pip);
                frame.getContentPane().removeAll();
                JPanel container = new JPanel();
                container.setLayout(new GridLayout(3,3));
                JPanel buttonss = new JPanel();
                JButton b1 = new JButton();
                blist[0] = b1;
                buttons[0][0] = b1;
                JButton b2 = new JButton();
                blist[1] = b2;
                buttons[0][1] = b2;
                JButton b3 = new JButton();
                blist[2] = b3;
                buttons[0][2] = b3;
                JButton b4 = new JButton();
                blist[3] = b4;
                buttons[1][0] = b4;
                JButton b5 = new JButton();
                blist[4] = b5;
                buttons[1][1] = b5;
                JButton b6 = new JButton();
                blist[5] = b6;
                buttons[1][2] = b6;
                JButton b7 = new JButton();
                blist[6] = b7;
                buttons[2][0] = b7;
                JButton b8 = new JButton();
                blist[7] = b8;
                buttons[2][1] = b8;
                JButton b9 = new JButton();
                blist[8] = b9;
                buttons[2][2] = b9;
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
                frame.add(buttonss, BorderLayout.EAST);

                b1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clicked(b1);
                        container.setEnabled(false);
                        if (count % 2 == 0) {
                            game[0][0] = 'x';
                        }
                        else {
                            game[0][0] = 'O';
                        }
                        DISPLAYBOARD();
                    }
                });
                b2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clicked(b2);
                        if (count % 2 == 0) {
                            game[0][1] = 'x';
                        }
                        else {
                            game[0][1] = 'O';
                        }
                        DISPLAYBOARD();
                    }
                });
                b3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clicked(b3);
                        if (count % 2 == 0) {
                            game[0][2] = 'x';
                        }
                        else {
                            game[0][2] = 'O';
                        }
                        DISPLAYBOARD();
                    }
                });
                b4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clicked(b4);
                        if (count % 2 == 0) {
                            game[1][0] = 'x';
                        }
                        else {
                            game[1][0] = 'O';
                        }
                        DISPLAYBOARD();
                    }
                });
                b5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clicked(b5);
                        if (count % 2 == 0) {
                            game[1][1] = 'x';
                        }
                        else {
                            game[1][1] = 'O';
                        }
                        DISPLAYBOARD();
                    }
                });
                b6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clicked(b6);
                        if (count % 2 == 0) {
                            game[1][2] = 'x';
                        }
                        else {
                            game[1][2] = 'O';
                        }
                        DISPLAYBOARD();
                    }
                });
                b7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clicked(b7);
                        if (count % 2 == 0) {
                            game[2][0] = 'x';
                        }
                        else {
                            game[2][0] = 'O';
                        }
                        DISPLAYBOARD();
                    }
                });
                b8.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clicked(b8);
                        if (count % 2 == 0) {
                            game[2][1] = 'x';
                        }
                        else {
                            game[2][1] = 'O';
                        }
                        DISPLAYBOARD();
                    }
                });
                b9.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clicked(b9);
                        if (count % 2 == 0) {
                            game[2][2] = 'x';
                        }
                        else {
                            game[2][2] = 'O';
                        }
                        DISPLAYBOARD();
                    }
                });
                frame.repaint();
                frame.validate();
            }
        });
        again.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0; i<9; i++){
                    blist[i].setText("");
                    blist[i].setEnabled(true);
                    count=1;
                }
                for (int i=0; i<3; i++){
                    for(int j=0; j<3; j++){
                        game[i][j]='\u0000';
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
        for(int i=0;i<9;i++){blist[i].setEnabled(true);}
    }
    public void WaitForOtherPlayer(){for(int i=0;i<9;i++){blist[i].setEnabled(false);}}
    public void displayGameResults(){

    }
    public static void main(String[] args) {
        new View().DISPLAYBOARD();
    }
}

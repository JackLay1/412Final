import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {
    JButton[][] buttons = new JButton[3][3];
    char game[][] = new char[3][3];
    int count = 0;
    JButton blist[] = new JButton[9];
    public void clicked(JButton b){
        if (count % 2 == 0) {
            b.setText("x");
            b.setFont(new Font("WINGDINGS", Font.BOLD, 70));
            b.setEnabled(false);
            count++;
        }
        else {
            b.setText("O");
            b.setFont(new Font("COMIC SANS", Font.BOLD, 69));
            b.setEnabled(false);
            count++;
        }
        System.out.println(game[0][0] + "|" + game[0][1] + "|" + game[0][2]);
        System.out.println(game[1][0] + "|" + game[1][1] + "|" + game[1][2]);
        System.out.println(game[2][0] + "|" + game[2][1] + "|" + game[2][2]);
    }
    public void mmenu(){
        JFrame frame = new JFrame();
        frame.setSize(600,600);
        JPanel container = new JPanel(new GridLayout(2,1));
        JTextField ip = new JTextField();
        JLabel prompt = new JLabel("ENTER YOUR OPPONENT'S IP");
        JButton send = new JButton("SEND REQUEST");
        container.add(prompt);
        container.add(ip);
        frame.add(send, BorderLayout.EAST);
        frame.add(container, BorderLayout.CENTER);
        frame.setVisible(true);

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pip = ip.getText();
                System.out.println(pip);
            }
        });
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void go(){
        JFrame frame = new JFrame();
        frame.setSize(600,600);
        JPanel container = new JPanel();
        container.setLayout(new GridLayout(3,3));
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
        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /*b1.addActionListener(new ActionListener() {
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
            }
        });*/



    }

    public static void main(String[] args) {
        new View().mmenu();
    }
}

package fr.chess.launch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChessClock implements KeyListener, Runnable, ActionListener {

    private JFrame frame1,frame2;
    private Panel p;
    private Thread counter;
    private JLabel label1,label2,label3,label4,label5,label6;
    private JTextField player1,player2,time;
    private JButton start;
    private String p1,p2;
    private int timelimit,p1remaining,p2remaining;
    private boolean started,isCurrentPlayerPlayerOne;

    public static void main(String args[]) throws InterruptedException
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        ChessClock o = new ChessClock();
        o.m1();
    }
    public void m1()
    {
        frame1 = new JFrame();
        frame1.setTitle("ChessClock");
        frame1.setSize(700,500);
        frame1.setResizable(false);
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p = new Panel();
        p.setLayout(null);
        frame1.add(p);
        label1 = new JLabel("<html><p style='color: black; font-size:15px'>White</p></html>");
        player1 = new JTextField("");
        label2 = new JLabel("<html><p style='color: black; font-size:15px'>Black</p></html>");
        player2 = new JTextField("");
        label4 = new JLabel("<html><p style='color: red; font-size:25px'>VS</p></html>");
        label3 = new JLabel("<html><p style='color: black; font-size:15px'>Time</p></html>");
        time = new JTextField("5");
        start = new JButton("<html><p style='color: black; font-size:15px'>Play</p></html>");
        start.addActionListener(this);
        label1.setBounds(210, 100, 150, 35);
        player1.setBounds(170, 140, 150, 35);
        label2.setBounds(410, 100, 150, 35);
        player2.setBounds(370, 140, 150, 35);
        label4.setBounds(324,140,50,35);
        label3.setBounds(320, 180, 150, 35);
        time.setBounds(320, 220, 50, 35);
        start.setBounds(280, 280, 150, 35);
        p.add(label1);
        p.add(player1);
        p.add(label2);
        p.add(player2);
        p.add(label4);
        p.add(label3);
        p.add(time);
        p.add(start);
        frame1.setVisible(true);
    }
    public void m2()
    {
        frame2 = new JFrame();
        frame2.setTitle("ChessClock");
        frame2.setSize(700,500);
        frame2.setResizable(false);
        frame2.setLocationRelativeTo(null);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.addKeyListener(this);
        p = new Panel();
        p.setLayout(null);
        frame2.add(p);
        counter = new Thread(this);
        label5 = new JLabel();
        label6 = new JLabel();
        started=false;
        label1.setText("<html><p style='font-size:30px'>"+p1+"</p></html>");
        label2.setText("<html><p style='font-size:30px'>"+p2+"</p></html>");
        timelimit*=10;
        p1remaining=timelimit;
        p2remaining=timelimit;
        label3.setText("<html><p style='font-size:50px'>"+timelimit/600+":"+timelimit%600+":"+(p1remaining%600)%10+"</p></html>");
        label4.setText("<html><p style='font-size:50px'>"+timelimit/600+":"+timelimit%600+":"+(p2remaining%600)%10+"</p></html>");
        label6 = new JLabel("<html><p style='color: black; font-size:50px'>VS</p></html>");
        label5.setText("<html><p style='color: black; font-size:10px'>*Press space to start match</p></html>");
        label1.setBounds(250-(p1.length()*22), 140, p1.length()*22, 35);
        label2.setBounds(440, 140, p2.length()*22, 35);
        label3.setBounds(130, 200, 250, 70);
        label4.setBounds(380, 200, 250, 70);
        label5.setBounds(20, 300, 1000, 150);
        label6.setBounds(304,130,150,60);
        p.add(label1);
        p.add(label3);
        p.add(label2);
        p.add(label4);
        p.add(label5);
        p.add(label6);
        started=false;
        frame2.setVisible(true);
        frame1.setVisible(false);
    }
    public void actionPerformed(ActionEvent ae)
    {
        String str = ae.getActionCommand();
        if(str.equals("<html><p style='color: black; font-size:15px'>Play</p></html>"))
        {
            p1=player1.getText();
            p2=player2.getText();
            timelimit=60*Integer.parseInt(time.getText());
            m2();
        }
    }
    public void keyPressed(KeyEvent ke){}
    public void keyReleased(KeyEvent ke){}
    public void keyTyped(KeyEvent ke)
    {
        char ch = ke.getKeyChar();
        if(ch == ' ')
        {
            if(!started)
            {
                label5.setText("");
                isCurrentPlayerPlayerOne=true;
                started = true;
                counter.start();
            }
            else
            {
                if(isCurrentPlayerPlayerOne)
                    isCurrentPlayerPlayerOne = false;
                else isCurrentPlayerPlayerOne = true;
            }
        }
    }
    public void run()
    {
        while(true)
        {
            System.out.print("");//unnecessary but necessary
            try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException e){}
            if(isCurrentPlayerPlayerOne)
            {
                p1remaining--;
                label3.setText("<html><p style='color:red; font-size:50px'>"+p1remaining/600+":"+(p1remaining%600)/10+":"+(p1remaining%600)%10+"</p></html>");
                label4.setText("<html><p style='color:black; font-size:50px'>"+p2remaining/600+":"+(p2remaining%600)/10+":"+(p2remaining%600)%10+"</p></html>");
                if(p1remaining == 0)
                {
                    label5.setText("<html><p style='color: orange; font-size:40px'>"+p2+" won the match"+"</p></html>");
                    break;
                }
            }
            else
            {
                p2remaining--;
                label3.setText("<html><p style='color:black; font-size:50px'>"+p1remaining/600+":"+(p1remaining%600)/10+":"+(p1remaining%600)%10+"</p></html>");
                label4.setText("<html><p style='color:red; font-size:50px'>"+p2remaining/600+":"+(p2remaining%600)/10+":"+(p2remaining%600)%10+"</p></html>");
                if(p2remaining==0)
                {
                    label5.setText("<html><p style='color: orange; font-size:40px'>"+p1+" won the match"+"</p></html>");
                    break;
                }
            }
        }
    }
}

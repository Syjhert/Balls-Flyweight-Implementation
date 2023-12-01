package Testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.*;
import java.util.Iterator;

public class Balls extends JFrame{
    public JPanel panel;
    public JButton btnStart;
    private JButton btnStop;
    private JCheckBox cbFlyweight;

    ArrayList<JPanel> balls = new ArrayList<>();
    int colorInd = 0;
    Color colors[] = {Color.gray, Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.CYAN};
    Timer timer = null;
    Balls(){
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer = new Timer();
                if(cbFlyweight.isSelected()){
                    timer.scheduleAtFixedRate(FWTask(), 0, 100);
                }else{
                    timer.scheduleAtFixedRate(nonFWTask(), 0, 100);
                }
                btnStart.setVisible(false);
                btnStop.setVisible(true);
            }
        });
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.cancel();
                for(JPanel ball : balls){
                    remove(ball);
                }
                revalidate();
                repaint();
                balls = new ArrayList<>();
                colorInd = 0;
                btnStart.setVisible(true);
                btnStop.setVisible(false);
            }
        });
    }
    TimerTask nonFWTask(){
        return new TimerTask() {
            @Override
            public void run() {
                if(balls.size() < 100){
                    Ball newBall = new Ball(colors[colorInd]);
                    colorInd = ((++colorInd)%6);
                    balls.add(newBall);
                    add(newBall);
                }
                System.out.println("This is a new run");
                for(JPanel ballPanel : balls){
                    Ball ball = (Ball)ballPanel;
                    ball.x += ball.xVel * ball.xDir;
                    ball.y += ball.yVel * ball.yDir;
                    if(ball.x <= 0 || ball.x >= 600){
                        ball.xDir *= -1;
                        ball.xVel = (++(ball.xVel))%31;
                        ball.xVel++;
                        if(ball.xVel >= 31) ball.xVel = 20;
                        if(ball.x <= 0) ball.x = 0;
                        else ball.x = 600;
                    }
                    if(ball.y <= 0 || ball.y >= 600){
                        ball.yDir *= -1;
                        ball.yVel = (++(ball.yVel))%31;
                        if(ball.y <= 0) ball.y = 0;
                        else ball.y = 600;
                    }
                    System.out.println("this ball is in (" + ball.x + ", " + ball.y + ")");
                    ball.setLocation(ball.x, ball.y);
                }
                System.out.println("BALL COUNT: " + balls.size());
            }
        };
    }
    TimerTask FWTask(){
        return new TimerTask() {
            @Override
            public void run() {
                if(balls.size() < 100){
                    BallFW ballFW = BallFWFactory.getBallFW(colors[colorInd]);
                    colorInd = ((++colorInd)%6);
                    MovingBall newBall = new MovingBall(ballFW);
                    balls.add(newBall);
                    add(newBall);
                }
                System.out.println("This is a new run");
                for(JPanel ballPanel : balls){
                    MovingBall ball = (MovingBall) ballPanel;
                    ball.x += ball.xVel * ball.xDir;
                    ball.y += ball.yVel * ball.yDir;
                    if(ball.x <= 0 || ball.x >= 600){
                        ball.xDir *= -1;
                        ball.xVel = (++(ball.xVel))%31;
                        if(ball.x <= 0) ball.x = 0;
                        else ball.x = 600;
                    }
                    if(ball.y <= 0 || ball.y >= 600){
                        ball.yDir *= -1;
                        ball.yVel = (++(ball.yVel))%31;
                        if(ball.y <= 0) ball.y = 0;
                        else ball.y = 600;
                    }
                    System.out.println("this ball is in (" + ball.x + ", " + ball.y + ")");
                    ball.setLocation(ball.x, ball.y);
                }
                System.out.println("BALL COUNT: " + balls.size());
            }
        };
    }

    public static void main(String[] args) {
        Balls frame = new Balls();

        frame.panel.setLayout(null);

        frame.panel.setSize(600, 600);

        frame.panel.add(frame.btnStart);
        frame.panel.add(frame.btnStop);
        frame.panel.add(frame.cbFlyweight);
        frame.panel.setBackground(Color.WHITE);
        frame.btnStart.setBounds(300, 500, 100, 30);
        frame.btnStop.setBounds(300, 500, 100, 30);
        frame.cbFlyweight.setBounds(310, 550, 80, 30);
        frame.btnStop.setVisible(false);
        frame.setLayout(null);
        frame.setContentPane(frame.panel);
        frame.setSize(640, 650);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle("Balls");
        frame.setVisible(true);
    }
}
package Testing;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MovingBall extends JPanel {
    int x, y, xDir, yDir, xVel, yVel;
    final public BallFW ballFW;
    MovingBall(BallFW ballFW){
        super(null);
        this.ballFW = ballFW;
        x = 300;
        y = 300;
        setBounds(x, y, 20, 20);
        Random random = new Random();
        if(random.nextInt(2) == 0){
            xDir = 1;
        }
        else{
            xDir = -1;
        }
        if(random.nextInt(2) == 0){
            yDir = 1;
        }else{
            yDir = -1;
        }
        xVel = random.nextInt(30) + 1;
        yVel = random.nextInt(30) + 1;
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(ballFW.color);
        g.fillOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        g.drawImage(ballFW.sprite, 0, 0, this);
    }
}
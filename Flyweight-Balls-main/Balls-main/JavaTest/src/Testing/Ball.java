package Testing;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Ball extends JPanel{
    public int x, y, xDir, yDir, xVel, yVel;
    Color color;
    Image sprite;
    Ball(Color color){
        super(null);
        this.color = color;
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
        BufferedImage image = null;
        try{
            InputStream is = Ball.class.getResourceAsStream("/assets/soccerball.png");
            image = ImageIO.read(is);
            sprite = image.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        g.drawImage(sprite, 0, 0, this);
    }

    public void disposeImage(){
        sprite.flush();
    }
}

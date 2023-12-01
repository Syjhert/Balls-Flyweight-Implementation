package Testing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BallFWFactory {
    private static final Map<Color, BallFW> ballFWs = new HashMap<>();
    public static BallFW getBallFW(Color color){
        Image sprite = null;
        if(ballFWs.get(color) == null){
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("JavaTest\\src\\Testing\\soccerball.png"));
                sprite = image.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
            }catch(Exception e){
            }
            ballFWs.put(color, new BallFW(color, sprite));
        }
        return ballFWs.get(color);
    }
}

package Testing;

import java.awt.*;

public final class BallFW {
    final Color color;
    final Image sprite;
    BallFW(Color color, Image sprite){
        this.color = color;
        this.sprite = sprite;
    }
    public void disposeImage(){
        sprite.flush();
    }
}
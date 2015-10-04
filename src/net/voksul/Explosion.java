package net.voksul;

import java.awt.*;
import java.net.URL;

/**
 * Created by Chris Bitler on 10/4/15.
 */
public class Explosion {
    int frame = 0;
    int x = 0, y = 0;
    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        frame+=10;
        String fName = "explosion1.png";
        if(frame > 0 && frame <= 250) {
            fName = "explosion1.png";
        }else if(frame > 250 && frame <= 500) {
            fName = "explosion2.png";
        }else if(frame > 500 && frame <= 750) {
            fName = "explosion3.png";
        }else if(frame > 750 && frame <= 1000) {
            fName = "explosion4.png";
        }else if(frame > 1000 && frame <= 1250) {
            fName = "explosion5.png";
        }
        URL url = getClass().getResource(fName);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage(url);
        g.drawImage(img,x,y,null);
    }
}

package net.voksul;

import java.awt.*;
import java.net.URL;

/**
 * Created by Chris Bitler on 10/4/15.
 */
public class Shield {
    int x = 100;

    public void draw(Graphics g) {
        /*Color initColor = g.getColor();
        g.setColor(Color.PINK);
        g.fillRect(x,Main.instance.getHeight()-100, 150, 20);
        g.setColor(initColor);*/
        URL url = getClass().getResource("shield.png");
        Toolkit tk = Toolkit.getDefaultToolkit();
        g.drawImage(tk.getImage(url),x,Main.instance.getHeight()-100,null);
    }

    public BoundingBox getBoundingBox() {
        return new BoundingBox(x,Main.instance.getHeight()-100,150,20);
    }
}

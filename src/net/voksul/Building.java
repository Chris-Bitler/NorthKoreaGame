package net.voksul;

import java.awt.*;
import java.net.URL;

/**
 * Created by Chris Bitler on 10/4/15.
 */
public class Building {
    int x,y;
    int width, height;
    Image img;
    public Building() {
        width = 80;
        height = 30;
        URL url = getClass().getResource("building.png");
        Toolkit tk = Toolkit.getDefaultToolkit();
        img = tk.getImage(url);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void draw(Graphics g) {
        /*Color initColor = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x,y,width,height);
        g.setColor(initColor);*/
        g.drawImage(img,x,y,null);
    }

    public BoundingBox getBoundingBox() {
        return new BoundingBox(x,y,width,height);
    }
}

package net.voksul;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;

/**
 * Created by Chris Bitler on 10/4/15.
 */
public class Nuke {
    int velocity = 0;
    int width = 0,height = 0;
    double xpos = 0, ypos = 0;
    double angle = 0;
    Image image ;
    public Nuke(double xpos, double ypos, double angle) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.angle = angle;
        this.velocity = 2;
        width = 30;
        height = 60;
        URL url = getClass().getResource("nuke.png");
        Toolkit tk = Toolkit.getDefaultToolkit();
        image = tk.getImage(url);
    }

    public void setVel(int speed)
    {
        this.velocity = speed;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        /*Color initColor = g2d.getColor();
        g2d.setColor(Color.CYAN);
       // AffineTransform initForm = g2d.getTransform();
       // g2d.rotate(angle+90,xpos,ypos);
        g2d.fillRect((int)xpos,(int)ypos,width,height);
        g2d.setColor(initColor);*/
       // g2d.setTransform(initForm);
        g.drawImage(image,(int)xpos,(int)ypos,null);
    }

    public BoundingBox getBoundingBox() {
        return new BoundingBox((int)xpos,(int)ypos,width,height);
    }
}

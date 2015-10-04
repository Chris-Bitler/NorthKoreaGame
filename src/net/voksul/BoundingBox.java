package net.voksul;

/**
 * Created by Chris Bitler on 10/4/15.
 */
public class BoundingBox {
    int x,y,width,height;

    public BoundingBox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean collides(BoundingBox other) {
        if(this.x < other.x + other.width &&
                this.x + this.width > other.x &&
                this.y < other.y + other.height &&
                this.y + this.height > other.y) {
            return true;
        }else{
            return false;
        }
    }
}

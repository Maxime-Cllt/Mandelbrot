package server.obj;

import java.awt.*;
import java.io.Serializable;

public class Point implements Serializable {

    private int x, y;
    private Color color;

    public Point() {
        this(0, 0, Color.black);
    }

    public Point(int x, int y) {
        this(x, y, Color.black);
    }

    public Point(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /********************
     * GETTER AND SETTER
     *******************/

    public int getX() {
        return this.x;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color newcolor) {
        this.color = newcolor;
    }

    @Override
    public String toString() {
        return "(" + this.x + ";" + this.y + ")";
    }

}
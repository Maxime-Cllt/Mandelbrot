package server.obj;

import java.awt.*;
import java.io.Serializable;

public class Point implements Serializable {

    private final int x;
    private final int y;
    private Color color;
    private short divergence;

    /***************
     * CONSTRUCTORS
     **************/

    public Point(int x, int y) {
        this(x, y, Color.black);
    }

    public Point(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.divergence = 0;
    }

    /********************
     * GETTER AND SETTER
     *******************/

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getDivergence() {
        return this.divergence;
    }

    public void setDivergence(final short newDivergence) {
        this.divergence = newDivergence;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(final Color newcolor) {
        this.color = newcolor;
    }

    @Override
    public String toString() {
        return "(" + this.x + ";" + this.y + ")" + " Color: " + this.color;
    }

}
package server.obj;

import java.awt.*;
import java.io.Serializable;

public class Point implements Serializable {

    private final int x, y;
    private Color color;
    private short divergence;

    /***************
     * CONSTRUCTORS
     **************/

    /**
     * Constructeur de Point avec couleur et divergence par défaut
     *
     * @param x
     * @param y
     */
    public Point(final int x, final int y) {
        this(x, y, Color.black);
    }

    /**
     * Constructeur de Point avec couleur et divergence par défaut
     *
     * @param x
     * @param y
     * @param color
     */
    public Point(final int x, final int y, final Color color) {
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
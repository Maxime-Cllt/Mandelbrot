package server;

import java.awt.*;
import java.io.Serializable;

public class Point implements Serializable {

    private int x, y;
    private Color color;

    public Point() {
        this.x = 0;
        this.y = 0;
        this.color = Color.black;
    }

    public Point(int newX, int newY) {
        this.x = newX;
        this.y = newY;
        this.color = Color.black;
    }

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
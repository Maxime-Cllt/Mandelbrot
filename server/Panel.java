package server;

import server.obj.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel {

    public ArrayList<Point> listePointMandelbrot;

    /**
     * Constructeur de la classe Panneau qui initialise le fond en noir de la fenêtre de dessin
     */
    public Panel() {
        this.setBackground(Color.black);
        this.listePointMandelbrot = new ArrayList<>();
    }

    /*
     * Méthode qui dessine les points de la liste de points Mandelbrot
     */
    public void paintComponent(Graphics graphics) {
        graphics.clearRect(0, 0, Constantes.WIDTH, Constantes.HEIGHT); // On efface tout sur le panneau
        for (Point p : listePointMandelbrot) {
            graphics.setColor(p.getColor());
            graphics.drawLine(p.getX(), p.getY(), p.getX(), p.getY());
        }
    }
}
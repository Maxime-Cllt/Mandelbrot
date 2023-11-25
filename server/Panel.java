package server;

import server.obj.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Panel extends JPanel {

    private final ArrayList<Point> listePointMandelbrot;
    private final BufferedImage image;

    public Panel() {
        this.setBackground(Color.WHITE);
        this.listePointMandelbrot = new ArrayList<>();
        this.image = new BufferedImage(Constantes.WIDTH, Constantes.HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

    public void setListePointMandelbrot(List<Point> listePointMandelbrot) {
        this.listePointMandelbrot.clear();
        this.listePointMandelbrot.addAll(listePointMandelbrot); // On copie la liste pour éviter les problèmes de concurrence
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        for (Point p : listePointMandelbrot) {
            g2d.setColor(p.getColor());
            g2d.drawLine(p.getX(), p.getY(), p.getX(), p.getY());
        }

        g.drawImage(image, 0, 0, this);
        g2d.dispose();
    }
}

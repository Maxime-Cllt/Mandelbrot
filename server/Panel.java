package server;

import server.obj.Point;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Panel extends javax.swing.JPanel {

    private final BufferedImage image;
    private List<Point> listePointMandelbrot;

    public Panel() {
        this.setBackground(Color.WHITE);
        this.listePointMandelbrot = new ArrayList<>();
        this.image = new BufferedImage(Constantes.WIDTH, Constantes.HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void setListePointMandelbrot(List<Point> listePointMandelbrot) {
        this.listePointMandelbrot = new ArrayList<>(listePointMandelbrot);
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

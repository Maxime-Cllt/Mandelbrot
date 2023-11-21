package server;

import server.obj.Point;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private final Panel panel = new Panel();

    public Frame(int width, int height) {

        this.setTitle("Mandelbrot");
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(panel, BorderLayout.CENTER);
        this.setContentPane(container);
        this.setVisible(true);


        container.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                System.out.println("FROM X: " + evt.getX() + " Y: " + evt.getY());
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                System.out.println(" TO X: " + evt.getX() + " Y: " + evt.getY());
            }

        });


    }

    public Panel getPanel() {
        return this.panel;
    }

    /**
     * Convertit un point en son équivalent complexe dans l'intervalle donné
     *
     * @param p Point à convertir
     * @return Complexe correspondant au point
     */
    public Complexe convert(Point p) {
        return new Complexe(
                p.getX() * (Constantes.INTERVALLE_FRAME_WIDTH / (double) Constantes.WIDTH) + Constantes.DECAL_IMAGE_X,
                p.getY() * (Constantes.INTERVALLE_FRAME_HEIGHT / (double) Constantes.HEIGHT) + Constantes.DECAL_IMAGE_Y);
    }
}
package server;

import server.obj.Point;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private final Panel panel = new Panel();
    public server.obj.Point pressed = new server.obj.Point();
    public server.obj.Point released = new server.obj.Point();
    public Complexe complexe1 = new Complexe();
    public Complexe complexe2 = new Complexe();

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
                pressed = new Point(evt.getX(), evt.getY());
                complexe1 = convert(pressed);
                System.out.println(" FROM Complexe1: " + complexe1);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                System.out.println("TO X: " + evt.getX() + " Y: " + evt.getY());
                released = new Point(evt.getX(), evt.getY());
                complexe2 = convert(released);
                System.out.println("Complexe2: " + complexe2);

                //on modifie les coordonnées de l'intervalle complexe lors d'un zoom
                Constantes.WIDTH_COMPLEXE = complexe1;
                Constantes.HEIGHT_COMPLEXE = complexe2;
                //On modifie l'intervalle d'affichage de l'image dans le plan complexe
                Constantes.calculCoordPlan();

                try {
                    Serveur.drawImage();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        });


    }

    public Panel getPanel() {
        return this.panel;
    }


    public void setStateFrame(boolean state) {
        this.setEnabled(state);
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
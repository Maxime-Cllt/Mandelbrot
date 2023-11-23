package server;

import server.obj.Point;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Frame extends javax.swing.JFrame {

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
//        this.setResizable(false);

        initMenu();

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
                System.out.println("FROM Complexe 1: " + complexe1);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                System.out.println("TO X: " + evt.getX() + " Y: " + evt.getY());
                released = new Point(evt.getX(), evt.getY());
                complexe2 = convert(released);
                System.out.println("Complexe 2: " + complexe2);

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

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu_option = new JMenu("Options");
        JMenuItem reset = new JMenuItem("Reset");
        JMenuItem zoom = new JMenuItem("Zoom");
        JMenuItem exec = new JMenuItem("Exec");


        /*
         * Reset du zoom sur l'image de base
         */
        reset.addActionListener(e -> {
            System.out.println("RESET ZOOM");
            Constantes.WIDTH_COMPLEXE.setA(-2);
            Constantes.WIDTH_COMPLEXE.setB(1);
            Constantes.HEIGHT_COMPLEXE.setA(1);
            Constantes.HEIGHT_COMPLEXE.setB(-1);
            Constantes.calculCoordPlan();
            try {
                Serveur.drawImage();
            } catch (Exception exe) {
                exe.printStackTrace();
            }
        });

        /*
         * Zoom sur la zone aléatoire
         */
        zoom.addActionListener(e -> {
            Constantes.WIDTH_COMPLEXE = convert(new Point(new Random().nextInt(Constantes.WIDTH), new Random().nextInt(Constantes.HEIGHT)));
            Constantes.HEIGHT_COMPLEXE = convert(new Point(new Random().nextInt(Constantes.WIDTH), new Random().nextInt(Constantes.HEIGHT)));
            //On modifie l'intervalle d'affichage de l'image dans le plan complexe
            Constantes.calculCoordPlan();

            try {
                Serveur.drawImage();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        /*
          Exécution du script bash pour lancer les clients
         */
        exec.addActionListener(e -> {
            try {
                String scriptPath = System.getProperty("user.dir") + "/exec.sh";
                ProcessBuilder processBuilder = new ProcessBuilder("bash", scriptPath);
                Process process = processBuilder.start();
                exec.setBackground(process.waitFor() == 0 ? Color.GREEN : Color.RED);
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        menu_option.add(exec);
        menu_option.add(reset);
        menu_option.add(zoom);

        menuBar.add(menu_option);
        this.setJMenuBar(menuBar);
    }

    public void setStateFrame(boolean state) {
        this.panel.setEnabled(state);
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
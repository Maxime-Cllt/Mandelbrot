package server;

import server.obj.ImpMandelbrot;
import server.obj.Point;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe Serveur
 * args[0] : largeur de l'image
 * args[1] : hauteur de l'image
 * args[2] : nombre de limite de divergence
 * args[3] : partie réelle de la borne inférieure du plan complexe
 * args[4] : partie imaginaire de la borne inférieur du plan complexe
 * args[5] : partie réelle de la borne supérieure du plan complexe
 * args[6] : partie imaginaire de la borne supérieur du plan complexe
 * <p>
 * exemple : java Serveur 800 600 1000 -1.5 -1.5 1.5 1.5
 */

public class Serveur {

    private static final double randomColor = (Math.random() * 12);
    public static long numberOfTaskDone = 0; // Nombre de tâches effectuées
    private static Frame frame; // Fenêtre
    private static ImpMandelbrot bagOfTask; // Liste de points

    public static void main(String[] args) {
        try {
            ArrayList<String> arrArgs = new ArrayList<>(Arrays.asList(args));

            if (arrArgs.size() == 3 || arrArgs.size() == 7) {
                Constantes.WIDTH = Integer.parseInt(args[0]);
                Constantes.HEIGHT = Integer.parseInt(args[1]);
                Constantes.LIMIT = Integer.parseInt(args[2]);
            }
            if (arrArgs.size() == 7) {
                Constantes.WIDTH_COMPLEXE = new Complexe(Double.parseDouble(args[3]), Double.parseDouble(args[6]));
                Constantes.HEIGHT_COMPLEXE = new Complexe(Double.parseDouble(args[4]), Double.parseDouble(args[5]));
                Constantes.calculCoordPlan();
            }
            if (arrArgs.isEmpty()) {
                System.out.println("Aucun paramètre n'a été renseigné. Le programme accepte 3 ou 7 arguments en paramètres");
            } else if (arrArgs.size() != 3 && arrArgs.size() != 7) {
                System.out.println("Le nombre de paramètre est incorrect. Le programme accepte 3 ou 7 arguments en paramètres");
                return;
            }

            arrArgs = null;

            Constantes.displayInfo();
            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            frame = new Frame(Constantes.WIDTH, Constantes.HEIGHT);


            bagOfTask = new ImpMandelbrot();
            initPoints(bagOfTask);
            registry.rebind("Mandelbrot", bagOfTask);
            drawImage();

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Méthode initPoints qui permet d'initialiser les points de la liste.
     *
     * @param bagOfTask : la liste de points
     */
    private static void initPoints(ImpMandelbrot bagOfTask) {
        try {
            final int width = Constantes.WIDTH;
            final int height = Constantes.HEIGHT;
            for (int i = 0; i <= width; i++) {
                for (int j = 0; j <= height; j++) bagOfTask.addTask(new Point(i, j));
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Méthode drawImage qui permet de dessiner l'image de la fractale de Mandelbrot en fonction des points de la liste.
     */
    public static void drawImage() {
        bagOfTask.taskDone.clear();
        bagOfTask.sizeOfTask = 0;
        frame.setStateFrame(false);

        System.out.println("Serveur prêt, connectez-vous au client pour commencer le calcul de l'image...");

        final long startTime = System.currentTimeMillis();

        try {

            while (bagOfTask.dataToDo.size() > bagOfTask.sizeOfTask) {
                frame.setTitle("[ " + bagOfTask.sizeOfTask + " / " + bagOfTask.dataToDo.size() + " ]");
                frame.getPanel().setListePointMandelbrot(bagOfTask.dataToDo);
                frame.getPanel().repaint();
                Thread.sleep(500);
            }

            numberOfTaskDone += bagOfTask.sizeOfTask;
            final int maxDivergence = bagOfTask.getMax();

            bagOfTask.dataToDo.parallelStream().forEach(point -> {
                point.setColor(getColorForDivergence(point.getDivergence(), maxDivergence));
            });

            frame.getPanel().setListePointMandelbrot(bagOfTask.dataToDo);

            System.out.println("Début calcul de l'image...");
            Thread.sleep(15);
            frame.getPanel().repaint();
            System.out.println("Image terminé");
            frame.setTitle("Mandelbrot");
            frame.setStateFrame(true);
            System.out.println("Temps d'exécution: " + (System.currentTimeMillis() - startTime) + "ms");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Méthode getColorForDivergence qui permet de retourner une couleur en fonction de la divergence.
     *
     * @param divergence    : la divergence
     * @param maxDivergence : la divergence maximale
     * @return une couleur en fonction de la divergence
     */
    private static Color getColorForDivergence(final int divergence, final int maxDivergence) {
        if (divergence == maxDivergence) {
            return new Color(0, 0, 0);
        } else {
            final int r = Math.toIntExact(Math.min((divergence + 1) * Math.round(randomColor / 2), 255));
            final int g = Math.toIntExact(Math.min((divergence + 1) * Math.round(randomColor / 4), 255));
            final int b = Math.toIntExact(Math.min((divergence + 1) * Math.round(randomColor / 3), 255));
            return new Color(r, g, b);
        }
    }

}


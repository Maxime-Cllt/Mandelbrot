package server;

import server.obj.ImpMandelbrot;
import server.obj.Point;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;

public class Serveur {

    private static Frame frame;
    private static ImpMandelbrot bagOfTask;
    public static long numberOfTaskDone = 0;

    public static void main(String[] args) throws RemoteException {

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

        try {
            bagOfTask = new ImpMandelbrot();
            initPoints(bagOfTask);
            registry.rebind("Mandelbrot", bagOfTask);
            drawImage();

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

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

    private static Color getColorForDivergence(final int divergence, final int maxDivergence) {
        if (divergence == maxDivergence) {
            return new Color(182, 255, 214);
        } else {
            final int r = Math.min((divergence + 1) * 8, 255);
            final int g = Math.min((divergence + 1) * 6, 255);
            final int b = Math.min((divergence + 1) * 4, 255);
            return new Color(r, g, b);
        }
    }


    private static Color getColorForDivergenceV2(final int divergence, final int maxDivergence) {
        final int p = (int) (divergence / (double) maxDivergence * 100);
        return new Color(p, p, p);
    }


}


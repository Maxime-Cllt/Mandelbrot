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

    public static Frame frame;
    private static ImpMandelbrot bagOfTask;

    /**
     * Méthode main du serveur qui lance le serveur et la frame
     *
     * @param args args[0] = largeur de la frame
     *             args[1] = hauteur de la frame
     *             args[2] = limite de calcul
     *             args[3] = partie réelle de l'intervalle de calcul
     *             args[4] = partie imaginaire de l'intervalle de calcul
     *             args[5] = partie réelle de l'intervalle de l'image
     *             args[6] = partie imaginaire de l'intervalle de l'image
     *             ex: Server 300 200 100 -2 1 1 -1
     */

    public static void main(String[] args) throws RemoteException {

        ArrayList<String> arrArgs = new ArrayList<>(Arrays.asList(args));
        System.out.println(arrArgs.size());
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

        System.out.println("-----------------------------------");
        System.out.println("Résolution de la frame: " + Constantes.WIDTH + "x" + Constantes.HEIGHT + "\nLimite de calcul: " + Constantes.LIMIT);
        System.out.print("Les intervalles complexe sont: (" + Constantes.WIDTH_COMPLEXE.getA() + ";" + Constantes.HEIGHT_COMPLEXE.getA() + ") sur l'axe des réels");
        System.out.println(" et (" + Constantes.HEIGHT_COMPLEXE.getB() + ";" + Constantes.WIDTH_COMPLEXE.getB() + ") sur l'axe des imaginaires");
        System.out.println("-----------------------------------");

        Registry registry = LocateRegistry.createRegistry(1099);

        frame = new Frame(Constantes.WIDTH, Constantes.HEIGHT);

        try {
            // Initialisation de la liste des points à traiter pour la frame
            bagOfTask = new ImpMandelbrot();
            for (int i = 0; i <= Constantes.WIDTH; i++) {
                for (int j = 0; j <= Constantes.HEIGHT; j++) {
                    bagOfTask.addTask(new Point(i, j));
                }
            }


            //Envois des données au registre
//            Naming.rebind("Mandelbrot", bagOfTask);
            registry.rebind("Mandelbrot", bagOfTask);

            drawImage();

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Dessine l'image dans la frame et attend que les clients aient traité tous les points pour l'afficher
     *
     * @throws Exception
     */
    public static void drawImage() throws Exception {

        //Reset des données pour que le client traite les points.
        bagOfTask.taskDone.clear();
        bagOfTask.sizeOfTask = -1;
        frame.setStateFrame(false);

        System.out.println("Serveur prêt, connectez-vous au client pour commencer le calcul de l'image...");

        //Le serveur attend que les clients aient traité tous les points
        while (bagOfTask.dataToDo.size() > bagOfTask.sizeOfTask) {
            System.out.println("Progression :  [" + bagOfTask.sizeOfTask + "/" + bagOfTask.dataToDo.size() + "] points traités");
            frame.setTitle("[" + bagOfTask.sizeOfTask + "/" + bagOfTask.dataToDo.size() + "]");
            frame.getPanel().listePointMandelbrot = bagOfTask.dataToDo;
            frame.getPanel().repaint();
            Thread.sleep(1500);
        }

        final int maxDivergence = bagOfTask.getMax(); // Change this according to the maximum divergence you want to handle

        for (int i = 0; i < bagOfTask.dataToDo.size(); i++) {
            bagOfTask.dataToDo.get(i).setColor(getColorForDivergence(bagOfTask.dataToDo.get(i).getDivergence(), maxDivergence));
        }

        frame.getPanel().listePointMandelbrot = bagOfTask.dataToDo;

        //Début du calcul de l'image
        System.out.println("Début calcul de l'image...");
        Thread.sleep(10);
        frame.getPanel().repaint();
        System.out.println("Image terminé");
        frame.setTitle("Mandelbrot");
        frame.setStateFrame(true);

    }

    private static Color getColorForDivergence(final int divergence, final int maxDivergence) throws RemoteException {

        if (divergence == maxDivergence) {
            // Lighter color for maximum divergence
            return new Color(182, 255, 214); // You can adjust these values based on your preference
        } else {
            // Use a formula to calculate RGB components based on divergence
            int r = ((divergence + 1) * 200 / maxDivergence) % 256; // Adjust the multiplier and divisor for red component
            int g = ((divergence + 1) * 150 / maxDivergence) % 256; // Adjust the multiplier and divisor for green component
            int b = ((divergence + 1) * 100 / maxDivergence) % 256; // Adjust the multiplier and divisor for blue component
            return new Color(r, g, b);
        }
    }


} // class Serveur
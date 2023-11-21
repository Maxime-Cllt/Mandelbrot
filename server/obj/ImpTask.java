package server.obj;

import server.Complexe;
import server.Constantes;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImpTask extends UnicastRemoteObject implements Task {

    public Point pointToDo;
    public int divergence;

    public ImpTask(Point point) throws RemoteException {
        super();
        pointToDo = point;
        divergence = 0;
    }

    public void run() throws RemoteException {

        Complexe z = new Complexe(0, 0);
        Complexe complexe = convert(pointToDo);

        //Verification d'appartenance au cercle de rayon 1/2.
        double verif1 = Math.pow(complexe.getA() + 1., 2) + Math.pow(complexe.getB(), 2);

        //Verification d'appartenance à la cardioïde (le coeur)
        double p = Math.sqrt(Math.pow(complexe.getA() - (1. / 4.), 2) + Math.pow(complexe.getB(), 2));
        double verif2 = p - (2 * Math.pow(p, 2)) + (1. / 4.);

        if (verif1 >= (1. / 16.) || complexe.getA() >= verif2) {

            for (int n = 0; n < Constantes.LIMIT; n++) {

                if (z.module() > 2) {

                    /*
                     * Le cas où z0 a un module supérieur à 2 fait que le point c a une divergence de 0,
                     * comme les points appartenant à Mandelbrot, cependant, comme z0 vaut 0+0i le module ne peut pas être supérieur à 2
                     */
                    divergence = n;
                    break;
                }
                z = z.multiply(z);
                z = z.add(complexe);
            }
        }
        // On attribue une couleur en fonction de la divergence.
        pointToDo.setColor(getColorForDivergence(divergence));
    }


    /**
     * Retourne la couleur en fonction de la divergence du point. Plus la divergence est grande, plus la couleur est rouge.
     *
     * @param divergence la divergence du point
     * @return la couleur du point
     */
    private Color getColorForDivergence(int divergence) {

        return switch (divergence) {
            case 0 -> new Color(0, 0, 0);
            case 1 -> new Color(97, 229, 52);
            case 2 -> new Color(8, 239, 158);
            case 3 -> new Color(8, 12, 239);
            case 4 -> new Color(239, 8, 235);
            case 5 -> new Color(66, 239, 8);
            case 6 -> new Color(239, 158, 8);
            case 7 -> new Color(8, 238, 209);
            case 8 -> new Color(8, 239, 158);
            case 9 -> new Color(8, 12, 239);
            case 10 -> new Color(239, 8, 235);
            case 11 -> new Color(66, 239, 8);
            case 12 -> new Color(239, 158, 8);
            case 13 -> new Color(8, 238, 209);
            default -> new Color(255, 0, 0);
        };
    }


    public int getDivergence() throws RemoteException {
        return this.divergence;
    }

    @Override
    public void affiche() throws RemoteException {
        System.out.println("Point: " + pointToDo.getX() + " " + pointToDo.getY() + " Divergence: " + divergence);
    }

    public Point getPointToDo() throws RemoteException {
        return this.pointToDo;
    }

    /**
     * Convertit un point de l'image en un point du plan complexe.
     *
     * @param p le point de l'image
     * @return le point du plan complexe
     */
    public Complexe convert(Point p) {
        return new Complexe(p.getX() * (Constantes.INTERVALLE_FRAME_WIDTH / (double) Constantes.WIDTH) + Constantes.DECAL_IMAGE_X,
                p.getY() * (Constantes.INTERVALLE_FRAME_HEIGHT / (double) Constantes.HEIGHT) + Constantes.DECAL_IMAGE_Y);
    }
}
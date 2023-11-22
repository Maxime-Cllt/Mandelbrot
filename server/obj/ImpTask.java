package server.obj;

import server.Complexe;
import server.Constantes;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImpTask extends UnicastRemoteObject implements Task {

    private static final double ONE_SIXTEENTH = 1.0 / 16.0;
    private static final double ONE_FOURTH = 1.0 / 4.0;
    private final Point pointToDo;
    private int divergence;
    private final Color BLACK = new Color(0, 0, 0);
    private final Color WHITE = new Color(255, 255, 255);

    public ImpTask(Point point) throws RemoteException {
        super();
        pointToDo = point;
        divergence = 0;
    }

    public void run() throws RemoteException {
        Complexe z = new Complexe(0, 0);
        Complexe complexe = convert(pointToDo);

        final double complexeASquared = complexe.getA() * complexe.getA();
        final double complexeBSquared = complexe.getB() * complexe.getB();
        final double p = Math.sqrt(Math.pow(complexe.getA() - (ONE_FOURTH), 2) + Math.pow(complexe.getB(), 2));
        final double pSquared = Math.pow(p, 2);
        final double verif1 = (complexeASquared + 1.0) + complexeBSquared;
        final double verif2 = Math.sqrt((complexeASquared - ONE_FOURTH) + complexeBSquared) - (2 * pSquared) + ONE_FOURTH;

        /*if (verif1 >= ONE_SIXTEENTH || complexe.getA() >= verif2) {
            for (int n = 0; n < Constantes.LIMIT; n += 2) {
                if (z.module() > 2) {
                    divergence = n;
                    break;
                }

                z = z.multiply(z);
                z = z.add(complexe);

                if (z.module() > 2) {
                    divergence = n + 1;
                    break;
                }

                z = z.multiply(z);
                z = z.add(complexe);
            }
        }*/

        if (verif1 >= ONE_SIXTEENTH || complexe.getA() >= verif2) {
            final int limit = Constantes.LIMIT;
            int n = 0;

            // Batch processing
            while (n < limit) {
                if (z.module() > 2) {
                    divergence = n;
                    break;
                }

                z = z.multiply(z);
                z = z.add(complexe);

                if (z.module() > 2) {
                    divergence = n + 1;
                    break;
                }

                z = z.multiply(z);
                z = z.add(complexe);

                n += 2;
            }
        }

        pointToDo.setColor(getColorForDivergence(divergence));
        pointToDo.setDivergence(divergence);
    }

    private Color getColorForDivergence(int divergence) {
        return (divergence == 0) ? WHITE : BLACK;
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

    public Complexe convert(Point p) {
        return new Complexe(p.getX() * (Constantes.INTERVALLE_FRAME_WIDTH / (double) Constantes.WIDTH) + Constantes.DECAL_IMAGE_X, p.getY() * (Constantes.INTERVALLE_FRAME_HEIGHT / (double) Constantes.HEIGHT) + Constantes.DECAL_IMAGE_Y);
    }
}

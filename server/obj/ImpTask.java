package server.obj;

import server.Complexe;
import server.Constantes;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImpTask extends UnicastRemoteObject implements Task {
    private static final double ONE_SIXTEENTH = 1.0 / 16.0;
    private static final double ONE_FOURTH = 1.0 / 4.0;
    private static final Color BLACK = new Color(0, 0, 0);
    private static final Color WHITE = new Color(255, 255, 255);
    private final Point pointToDo;

    public ImpTask(final Point point) throws RemoteException {
        super();
        pointToDo = point;
    }

    private static Color getColorForDivergence(final int divergence) {
        return (divergence == 0) ? WHITE : BLACK;
    }


    /**
     * Fonction qui exécute la tâche (calcul de la fractale)
     * @throws RemoteException
     */
    public void run() throws RemoteException {
        Complexe z = new Complexe();
        Complexe complexe = convert(pointToDo);
        final double complexeASquared = complexe.getA() * complexe.getA();
        final double complexeBSquared = complexe.getB() * complexe.getB();
        final double pSquared = Math.pow(complexe.getA() - ONE_FOURTH, 2) + complexeBSquared;
        final double verif1 = complexeASquared + 1.0 + complexeBSquared;
        final double verif2 = Math.sqrt(complexeASquared - ONE_FOURTH + complexeBSquared) - 2 * pSquared + ONE_FOURTH;
        int divergence = 0;

        if (verif1 >= ONE_SIXTEENTH || complexe.getA() >= verif2) {
            final int limit = Constantes.LIMIT;

            for (int n = 0; n < limit; n++) {
                if (z.module() > 2) {
                    divergence = n;
                    break;
                }
                z = z.multiply(z).add(complexe);
            }
        }

        pointToDo.setColor(getColorForDivergence(divergence));
        pointToDo.setDivergence((short) divergence);
    }

    /**
     * Convertit un point en complexe pour le calcul de la fractale
     *
     * @param p Point à convertir
     * @return Complexe
     */
    public Complexe convert(final Point p) {
        return new Complexe(
                p.getX() * (Constantes.INTERVALLE_FRAME_WIDTH / Constantes.WIDTH) + Constantes.DECAL_IMAGE_X,
                p.getY() * (Constantes.INTERVALLE_FRAME_HEIGHT / Constantes.HEIGHT) + Constantes.DECAL_IMAGE_Y
        );
    }
}

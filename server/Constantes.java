package server;

public class Constantes {

    //Taille de la frame où on affiche l'image
    public static int WIDTH = 500;
    public static int HEIGHT = 250;
    //Intervalle de calcul du module de mandelbrot (en x et en y)
    public static Complexe WIDTH_COMPLEXE = new Complexe(-2, 1);
    public static Complexe HEIGHT_COMPLEXE = new Complexe(1, -1);
    //Limite de calcul du module de mandelbrot
    public static int LIMIT = 100;
    //Intervalle de l'image en x et en y
    public static double INTERVALLE_FRAME_WIDTH = (Math.max(WIDTH_COMPLEXE.getA(), HEIGHT_COMPLEXE.getA()) - Math.min(WIDTH_COMPLEXE.getA(), HEIGHT_COMPLEXE.getA()));
    public static double INTERVALLE_FRAME_HEIGHT = (Math.max(WIDTH_COMPLEXE.getB(), HEIGHT_COMPLEXE.getB()) - Math.min(WIDTH_COMPLEXE.getB(), HEIGHT_COMPLEXE.getB()));

    //Décalage de l'image en x et en y
    public static double DECAL_IMAGE_X = Math.min(WIDTH_COMPLEXE.getA(), HEIGHT_COMPLEXE.getA());
    public static double DECAL_IMAGE_Y = Math.min(WIDTH_COMPLEXE.getB(), HEIGHT_COMPLEXE.getB());

    /**
     * Constructeur de la classe Constantes qui initialise les constantes de l'application
     *
     * @param width          Largeur de la frame
     * @param height         Hauteur de la frame
     * @param widthComplexe  Intervalle de calcul en x
     * @param heightComplexe Intervalle de calcul en y
     * @param limit          Limite de calcul du module de mandelbrot
     */
    public Constantes(int width, int height, Complexe widthComplexe, Complexe heightComplexe, int limit) {
        WIDTH = width;
        HEIGHT = height;
        WIDTH_COMPLEXE = widthComplexe;
        HEIGHT_COMPLEXE = heightComplexe;
        LIMIT = limit;
    }


    /**
     * Calcul les coordonnées de l'image dans le plan complexe en fonction de l'intervalle de calcul
     */
    public static void calculCoordPlan() {
        INTERVALLE_FRAME_WIDTH = (Math.max(WIDTH_COMPLEXE.getA(), HEIGHT_COMPLEXE.getA()) - Math.min(WIDTH_COMPLEXE.getA(), HEIGHT_COMPLEXE.getA()));
        INTERVALLE_FRAME_HEIGHT = (Math.max(WIDTH_COMPLEXE.getB(), HEIGHT_COMPLEXE.getB()) - Math.min(WIDTH_COMPLEXE.getB(), HEIGHT_COMPLEXE.getB()));
        DECAL_IMAGE_X = Math.min(WIDTH_COMPLEXE.getA(), HEIGHT_COMPLEXE.getA());
        DECAL_IMAGE_Y = Math.min(WIDTH_COMPLEXE.getB(), HEIGHT_COMPLEXE.getB());
    }
}
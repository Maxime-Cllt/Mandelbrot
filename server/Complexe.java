package server;

public final class Complexe {

    private double a, b;

    /**
     * Constructeur de la classe Complexe qui initialise les deux doubles à 0
     */
    public Complexe() {
        this.a = 0;
        this.b = 0;
    }

    /**
     * Constructeur de la classe Complexe qui prend en paramètre deux doubles
     *
     * @param newA (double) les deux doubles qui vont initialiser les deux doubles de la classe
     * @param newB (double) les deux doubles qui vont initialiser les deux doubles de la classe
     */
    public Complexe(double newA, double newB) {
        this.a = newA;
        this.b = newB;
    }

    /**
     * Méthode qui calcule le produit de deux complexes (a+ib)*(c+id) = (ac-bd)+i(ad+bc) et qui retourne un nouveau complexe
     *
     * @param complexe (Complexe) le complexe avec lequel on veut multiplier
     * @return un nouveau complexe qui est le produit des deux complexes (Complexe)
     */
    public Complexe multiply(Complexe complexe) {
        final double a1 = this.getA();
        final double b1 = this.getB();
        final double a2 = complexe.getA();
        final double b2 = complexe.getB();
        return new Complexe((a1 * a2 - b1 * b2), (a1 * b2 + b1 * a2));
    }

    /**
     * Méthode qui calcule la somme de deux complexes
     *
     * @param complexe
     * @return un nouveau complexe qui est la somme des deux complexes (Complexe)
     */
    public Complexe add(Complexe complexe) {
        return new Complexe(this.getA() + complexe.getA(), this.getB() + complexe.getB());
    }

    /**
     * Méthode qui calcule la différence de deux complexes
     *
     * @return le module du complexe en double arrondi à 2 chiffres après la virgule (double)
     */
    public double module() {
        return Math.sqrt(Math.pow(this.getA(), 2) + Math.pow(this.getB(), 2));
    }

    /********************
     * GETTERS ET SETTERS
     ********************/

    public double getA() {
        return this.a;
    }

    public void setA(double newA) {
        this.a = newA;
    }

    public double getB() {
        return this.b;
    }

    public void setB(double newB) {
        this.b = newB;
    }

    @Override
    public String toString() {
        return this.getA() + "+i" + this.getB();
    }
}
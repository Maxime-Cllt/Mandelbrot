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
     * @param newA
     * @param newB
     */
    public Complexe(double newA, double newB) {
        this.a = newA;
        this.b = newB;
    }

    /**
     * Constructeur de la classe Complexe qui prend en paramètre un complexe
     *
     * @param complexe
     */
    public Complexe(Complexe complexe) {
        this.a = complexe.getA();
        this.b = complexe.getB();
    }

    /**
     * Méthode qui calcule le produit de deux complexes (a+ib)*(c+id) = (ac-bd)+i(ad+bc) et qui retourne un nouveau complexe
     *
     * @param complexe (Complexe) le complexe avec lequel on veut multiplier
     * @return un nouveau complexe qui est le produit des deux complexes (Complexe)
     */
    public Complexe multiply(Complexe complexe) {
        double a1 = this.getA();
        double b1 = this.getB();
        double a2 = complexe.getA();
        double b2 = complexe.getB();
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
     * Méthode qui calcule le module d'un complexe
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
package poo;

import java.awt.Rectangle;

public class Paleta extends ObjetoGrafico {
    final double paletaVel = 500.0;

    public Paleta() {

    }

    public Paleta(String filename, double x, double y) {
        super(filename, (int) x, (int) y);
    }

    public void moverPaletaarriba(double delta) {
        setY(getY() - paletaVel * delta);
    }

    public void moverPaletabajo(double delta) {
        setY(getY() + paletaVel * delta);
    }

    public double getPaletaVel() {
        return paletaVel;
    }

    public Rectangle getColiton() {
        return this;
    }

}

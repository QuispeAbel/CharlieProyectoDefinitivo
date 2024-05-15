package poo;

import java.awt.Rectangle;

public class Paleta extends ObjetoGrafico {
    final double paletaVel = 500.0;
    // private Rectangle rectangulo;

    public Paleta() {

    }

    public Paleta(String filename, double x, double y) {
        super(filename, (int) x, (int) y);
        // rectangulo = new Rectangle((int) x, (int) y, getWidthIm(), getHeightIm());
        // rectangulo.getBounds();
    }

    public void moverPaletaarriba(double delta) {
        setY(getY() - paletaVel * delta);
        // rectangulo.setLocation((int) getX(), (int) getY());
    }

    public void moverPaletabajo(double delta) {
        setY(getY() + paletaVel * delta);
        // rectangulo.setLocation((int) getX(), (int) getY());
    }

    public double getPaletaVel() {
        return paletaVel;
    }

    public Rectangle getColiton() {
        return this;
    }

}

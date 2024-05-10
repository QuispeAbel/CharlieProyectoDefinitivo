package poo;

public class Paleta extends ObjetoGrafico {
    final double paletaVel = 500.0;

    public Paleta() {

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

}

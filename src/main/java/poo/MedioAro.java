package poo;

public class MedioAro extends obstaculo {
    long currentTime = System.currentTimeMillis();

    MedioAro(String filename, double alturaY) {
        super(filename, alturaY);
    }

    public MedioAro(String filename, double x, double y) {
        super(filename, (int) x, (int) y);
    }

    void desplazamientoX(double delta) {
        setX(getX() - 100 * delta);
    }

    void ubicaAropost(double xheroe) {
        this.setPosicion(xheroe, alturaY);
    }
}

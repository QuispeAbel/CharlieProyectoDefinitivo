package poo;

public class MedioAro extends obstaculo {
    long currentTime = System.currentTimeMillis();

    MedioAro(String filename, double alturaY) {
        super(filename, alturaY);
    }

    void desplazamientoX(double delta) {
        setX(getX() - 100 * delta);
    }

    void ubicaAropost(double xheroe) {
        this.setPosicion(xheroe, alturaY);
    }

    void ubicaArosup(double xheroe) {
        this.setPosicion((xheroe + 20), alturaY);
    }
}

package poo;

import java.io.IOException;

import javax.imageio.ImageIO;

public class MedioAro extends obstaculo {
    long currentTime = System.currentTimeMillis();

    MedioAro(String filename, double alturaY) {
        super(filename, alturaY);
        /*
         * try {
         * this.imagen = ImageIO.read(getClass().getResource(filename));
         * this.alturaY = alturaY;
         * } catch (IOException e) {
         * System.out.println(e);
         * }
         */
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

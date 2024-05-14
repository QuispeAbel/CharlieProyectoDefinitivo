package poo;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Caldera extends ObjetoGrafico {

    double alturaY = 390;

    Caldera(String filename) {
        try {
            this.imagen = ImageIO.read(getClass().getResource(filename));

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

package poo;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Caldera extends obstaculo {

    Caldera(String filename) {
        try {
            this.imagen = ImageIO.read(getClass().getResource(filename));
            this.alturaY = 390;
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

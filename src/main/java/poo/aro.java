package poo;

import java.io.IOException;

import javax.imageio.ImageIO;

public class aro extends obstaculo {
    long currentTime = System.currentTimeMillis();
    double altura=190, altura_solapa=200;
    
    aro(String filename) {
        try {
            this.imagen = ImageIO.read(getClass().getResource(filename));

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    void desplazamientoX (double delta){
        setX(getX()- 100 * delta);
    }

    void ubicaAropost (double xheroe){
        this.setPosicion(xheroe,altura);
    }

    void ubicaArosup (double xheroe){
        this.setPosicion((xheroe+20),altura_solapa);
    }
}

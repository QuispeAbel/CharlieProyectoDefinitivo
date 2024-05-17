package poo;
import java.awt.*;
import java.awt.geom.*;

abstract class obstaculo extends ObjetoGrafico {
    protected double velocidadDesplazamientoX = 100;
    protected double alturaY;
    protected double x;

    obstaculo(String filename, double alturaY) {
        super(filename);
        this.alturaY = alturaY;
    }

    obstaculo(String filename,double x, double alturaY) {
        super(filename,(int) x,(int) alturaY);
    }

    obstaculo(String filename,double x, double alturaY,int width, int height) {
        super(filename,(int) x,(int) alturaY,width,height);
    }


    obstaculo() {}

    void desplazamientoX() {
    };

    /*
     * private void reiniciarObjeto() {
     * // Establece la posición de reinicio del objeto delante del personaje
     * double posX = heroe.getX() + offsetSpawnX;
     * double posY = y; // Define la posición adecuada en Y según sea necesario
     * objetoEnJuego.setPosicion(posX, posY);
     * }
     */

}

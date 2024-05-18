package poo;

import java.awt.*;
//import java.awt.geom.*;

abstract class obstaculo extends ObjetoGrafico implements Hiteable {
    protected double velocidadDesplazamientoX = 100;
    protected double alturaY;
    protected double x;
    protected Rectangle hitbox = new Rectangle();

    obstaculo(String filename, double alturaY) {
        super(filename);
        this.alturaY = alturaY;
    }

    obstaculo(String filename, double x, double alturaY) {
        super(filename, (int) x, (int) alturaY);
    }

    obstaculo() {
    }

    void desplazamientoX() {
    };

    public void setHitbox(int x, int y, int width, int height) {
        hitbox.x = x;
        hitbox.y = y;
        hitbox.width = width;
        hitbox.height = height;
        // hitbox = new Rectangle(x, y, width, height);
    }

    public void setHitbox(int width, int height) {
        hitbox.width = width;
        hitbox.height = height;
        // hitbox = new Rectangle(x, y, width, height);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setX(double x, int posXhitbox) {
        super.setX(x);
        hitbox.x = (int) x + posXhitbox;
    }

    public void setY(double y, int posYhitbox) {
        super.setY(y);
        hitbox.y = (int) y + posYhitbox;
    }
    /*
     * private void reiniciarObjeto() {
     * // Establece la posición de reinicio del objeto delante del personaje
     * double posX = heroe.getX() + offsetSpawnX;
     * double posY = y; // Define la posición adecuada en Y según sea necesario
     * objetoEnJuego.setPosicion(posX, posY);
     * }
     */

}

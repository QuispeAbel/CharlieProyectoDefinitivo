package poo;

import java.awt.*;
//import java.awt.geom.*;

abstract class Obstaculo extends ObjetoGrafico implements Hiteable {
    protected double velocidadDesplazamientoX = 100;
    // protected double alturaY;
    protected double x;
    protected Rectangle hitbox = new Rectangle();

    Obstaculo(String filename, double alturaY) {
        super(filename, alturaY);
        // this.alturaY = alturaY;
    }

    Obstaculo(String filename, double x, double alturaY) {
        super(filename, (int) x, (int) alturaY);
    }

    Obstaculo() {
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

    // NUEVOS DRAW PARA VER LAS HITBOX
    // public void draw(Graphics2D g) {
    // super.draw(g);
    // g.draw(hitbox);
    // }

    // public void display(Graphics2D g) {
    // super.display(g);
    // g.draw(hitbox);
    // }

}

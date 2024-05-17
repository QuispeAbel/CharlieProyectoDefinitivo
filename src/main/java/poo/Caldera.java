package poo;

import java.awt.*;
import java.awt.geom.*;
import java.awt.Rectangle;

public class Caldera extends obstaculo {

    private int posXhitbox = 5;
    private int posYhitbox = 40;
    private int widthHitbox = 44;
    private int heigthHitbox = 80;

    Caldera(String filename) {
        super(filename, 575);
        super.setHitbox(heigthHitbox, widthHitbox);
    }

    public Caldera(String filename, double x, double alturaY) {
        super(filename, (int) x, (int) alturaY);

    }

    public Rectangle getColiton() {
        return this;
    }

    public void setX(double x) {
        super.setX(x, posXhitbox);
    }

    public void setY(double y) {
        super.setY(y, posYhitbox);
    }

    public void setPosicion(double x, double y) {
        this.setX(x, posXhitbox);
        this.setY(y, posYhitbox);
    }

    @Override
    void desplazamientoX() {
    }// anula
}

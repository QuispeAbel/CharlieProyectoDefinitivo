package poo;

import java.awt.Rectangle;

public class Caldera extends Obstaculo {

    private int posXhitbox = 15;
    private int posYhitbox = 39;
    private int widthHitbox = 45;
    private int heigthHitbox = 45;

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

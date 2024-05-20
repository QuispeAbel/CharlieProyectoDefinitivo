package poo;

import java.awt.Rectangle;

public class Bonus extends obstaculo {
    private int posXhitbox = 0;
    private int posYhitbox = 0;
    private int widthHitbox = 32;
    private int heigthHitbox = 32;

    //private double altura_bonus = 253;

    Bonus(String filename) {
        super(filename, 315);
        super.setHitbox(heigthHitbox, widthHitbox);
    }

    public Bonus(String filename, double x, double alturaY) {
        super(filename, (int) x, (int) alturaY);

    }

    public void Movimientobonus(double delta) {
        setX(getX() - 100 * delta, posXhitbox);
    }

    void spawn(double xheroe) {
        this.setX(xheroe, posXhitbox);
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

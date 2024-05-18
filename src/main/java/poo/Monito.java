package poo;

import java.awt.Rectangle;

public class Monito  extends obstaculo {
    private int posXhitbox = 19;
    private int posYhitbox = 190;
    private int widthHitbox = 20;
    private int heigthHitbox = 19;

    Monito(String filename, double alturaY) {
        super(filename, alturaY);
        super.setHitbox(widthHitbox, heigthHitbox);
    }
    public Monito(String filename, double x, double alturaY) {
        super(filename, (int) x, (int) alturaY);

    }

    void MovimientoMono(double delta) {
        setX(getX() - 100 * delta, posXhitbox);
    }

    void ubicaMono(double xheroe) {
        this.setX(xheroe, posXhitbox);
        this.setY(alturaY, posYhitbox);
        // this.setPosicion(xheroe, alturaY);
    }

    public void spawn(double xheroe) {
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


}

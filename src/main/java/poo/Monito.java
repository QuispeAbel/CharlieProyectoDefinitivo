package poo;

import java.awt.Rectangle;

public class Monito extends Obstaculo {
    private int posXhitbox = 0;
    private int posYhitbox = 0;
    private int widthHitbox = 50;
    private int heigthHitbox = 45;

    protected double gravity = 10.0;

    protected int alturaY = 340;

    // public int POSICION_Y_PISO = 340;

    Monito(String filename) {
        super(filename, 340);
        super.setHitbox(heigthHitbox, widthHitbox);
    }

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
        this.setY(340, posYhitbox);
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

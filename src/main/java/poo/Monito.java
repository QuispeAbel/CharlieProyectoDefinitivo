package poo;

import java.awt.Rectangle;

public class Monito extends obstaculo {
    private int posXhitbox = 0;
    private int posYhitbox = 0;
    private int widthHitbox = 50;
    private int heigthHitbox = 45;

    protected double gravity = 10.0;

    private int alturaY = 340;

    public int POSICION_Y_PISO = 340;

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

    public void update(double delta) {
        if (this.getY() < POSICION_Y_PISO)
            this.setY(getY() + gravity);
    }

    void MovimientoMono(double delta) {
        setX(getX() - 100 * delta, posXhitbox);
    }

    void SaltoMono(double delta) {
        setX(getX() - 2000 * delta, posXhitbox);
        setY(getY() - 2000 * delta, posYhitbox);
        setX(getX() - 1500 * delta, posXhitbox);
    }

    public void setPiso(int PosNueva) {
        POSICION_Y_PISO = PosNueva;
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

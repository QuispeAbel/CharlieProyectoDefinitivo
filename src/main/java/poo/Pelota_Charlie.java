package poo;

import java.awt.Rectangle;

public class Pelota_Charlie extends Personaje {
    private int posXhitbox = 15;
    private int posYhitbox = 0;
    private int widthHitbox = 40;
    private int heigthHitbox = 40;

    protected double gravity = 10.0;

    private int alturaY = 575;

    public int POSICION_Y_PISO = 575;
    

    public Pelota_Charlie(String filename, double x, double y) {
        super(filename, x, y);
        super.setHitbox((int) x + posXhitbox, (int) y + posYhitbox, widthHitbox, heigthHitbox);
      }
    
    
      public void ganar(int x, int y) {
        this.setX(x);
            POSICION_Y_PISO = y;
        }

    void MovimientoPelota(double delta) {
        setX(getX() - 100 * delta, posXhitbox);
    }

    void DisparadaIzq(double delta) {
        setX(getX() - 200 * (delta*2), posXhitbox);
    }

    void Disparadader(double delta) {
        setX(getX() + 200 * (delta*2), posXhitbox);
    }

    public void setPiso(int PosNueva) {
        POSICION_Y_PISO = PosNueva;
    }

    void ubicarPelota(double xheroe) {
        this.setX(xheroe, posXhitbox);
        this.setY(alturaY, posYhitbox);
        // this.setPosicion(xheroe, alturaY);
    }

    public void spawn(double xheroe) {
        this.setX(xheroe, posXhitbox);
        this.setY(575, posYhitbox);
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

    public void update(double delta) {

    }

}

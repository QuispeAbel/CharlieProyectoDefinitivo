package poo;

import java.awt.Rectangle;

public class Pelota_Charlie extends Personaje {
    private int posXhitbox = 10;
    private int posYhitbox = 0;
    private int widthHitbox = 47;
    private int heigthHitbox = 60;

    protected double gravity = 10.0;

    private int alturaY = 575;

    public int POSICION_Y_PISO = 575;
    

    Pelota_Charlie(String filename) {
        super(filename, 575);
        this.setHitbox(heigthHitbox, widthHitbox);
    }
    
    public void setHitbox(int x, int y, int width, int height) {
        hitbox.x = x;
        hitbox.y = y;
        hitbox.width = width;
        hitbox.height = height;
        // hitbox = new Rectangle(x, y, width, height);
    }

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

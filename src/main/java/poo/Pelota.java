package poo;

import java.awt.Rectangle;

public class Pelota extends ObjetoGrafico {
    private int velocidadPelotaX = 5;
    private int velocidadPelotaY = 5;
    private boolean pelotaFueraIzq = false;
    private boolean pelotaFueraDer = false;
    // private Rectangle rectangulo;

    public Pelota() {
        super();
    }

    public Pelota(String filename, double x, double y) {
        super(filename, (int) x, (int) y);
    }

    public void moverPelota(int maxX, int maxY, boolean gameover) {
        super.setX(getX() + velocidadPelotaX);
        super.setY(getY() + velocidadPelotaY);

        if (getY() >= maxY - 30) {
            velocidadPelotaY = -velocidadPelotaY;
            if (!gameover)
                playSound("src\\main\\resources\\poo\\sonidos\\golpe.wav");
        }

        if (getY() <= 25) {
            velocidadPelotaY = -velocidadPelotaY;
            playSound("src\\main\\resources\\poo\\sonidos\\golpe.wav");
        }

        if (getX() <= 0)
            pelotaFueraIzq = true;

        if (getX() >= maxX)
            pelotaFueraDer = true;

    }

    public boolean getPelotaFueraIzq() {
        return pelotaFueraIzq;
    }

    public boolean getPelotaFueraDer() {
        return pelotaFueraDer;
    }

    public void setPelotaFueraIzq() {
        pelotaFueraIzq = false;
    }

    public void setPelotaFueraDer() {
        pelotaFueraDer = false;
    }

    public void invertirVelX() {
        velocidadPelotaX = -velocidadPelotaX;
    }

    public void invertirVelY() {
        velocidadPelotaY = -velocidadPelotaY;
    }

    public void detenerPelota() {
        velocidadPelotaX = 0;
        velocidadPelotaY = 0;
    }

    public Rectangle getColiton() {
        return this;
    }

}

package poo;

public class Pelota extends ObjetoGrafico {
    private int velocidadPelotaX = 10;
    private int velocidadPelotaY = 10;

    public void moverPelota(int maxX, int maxY) {
        super.setX(getX() + velocidadPelotaX);
        super.setY(getY() + velocidadPelotaY);

        if (getY() >= maxY)
            velocidadPelotaY = -velocidadPelotaY;

        if (getY() <= 0)
            velocidadPelotaY = -velocidadPelotaY;

        if (getX() >= maxX)
            velocidadPelotaX = -velocidadPelotaX;

        if (getX() <= 0)
            velocidadPelotaX = -velocidadPelotaX;

    }
}

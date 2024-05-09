package poo;

public class Pelota extends ObjetoGrafico {
    private int velocidadPelotaX = 10;
    private int velocidadPelotaY = 10;
    private boolean pelotaFueraIzq = false;
    private boolean pelotaFueraDer = false;

    public void moverPelota(int maxX, int maxY) {
        super.setX(getX() + velocidadPelotaX);
        super.setY(getY() + velocidadPelotaY);

        if (getY() >= maxY - 20)
            velocidadPelotaY = -velocidadPelotaY;

        if (getY() <= 25)
            velocidadPelotaY = -velocidadPelotaY;

        if (getX() <= 0)
            pelotaFueraIzq = true;

        if (getX() >= maxX)
            pelotaFueraDer = true;

    }

    public boolean getPelotaFueraIzq(){
        return pelotaFueraIzq;
    }

    public boolean getPelotaFueraDer(){
        return pelotaFueraDer;
    }

    public void setPelotaFueraIzq(){
        pelotaFueraIzq = false;
    }

    public void setPelotaFueraDer(){
        pelotaFueraDer = false;
    }

    public void invertirVelX(){
        velocidadPelotaX = -velocidadPelotaX;
    }

    
    
}

package poo;

public class MedioAro extends obstaculo {

    private int posXhitbox = 19;
    private int posYhitbox = 190;
    private int widthHitbox = 20;
    private int heigthHitbox = 100;

    MedioAro(String filename, double altura) {
        super(filename, altura);
        super.setHitbox(widthHitbox, heigthHitbox);
    }

    public void setPosXhitbox(int posXhitbox) {
        this.posXhitbox = posXhitbox;
    }

    public void setPosYhitbox(int posYhitbox) {
        this.posYhitbox = posYhitbox;
    }

    public void setWidthHitbox(int widthHitbox) {
        hitbox.width = widthHitbox;
    }

    public void setHeigthHitbox(int heigthHitbox) {
        hitbox.height = heigthHitbox;
    }

    void desplazamientoX(double delta) {
        setX(getX() - 100 * delta, posXhitbox);
    }

    void respawnMedioAro(double xheroe) {
        this.setX(xheroe, posXhitbox);
        this.setY(getY(), posYhitbox);
        // this.setPosicion(xheroe, alturaY);
    }
}

package poo;

public class MedioAro extends obstaculo {

    private int posXhitbox = 19;
    private int posYhitbox = 190;
    private int widthHitbox = 20;
    private int heigthHitbox = 19;

    MedioAro(String filename, double alturaY) {
        super(filename, alturaY);
        super.setHitbox(widthHitbox, heigthHitbox);
    }

    // public MedioAro(String filename, double x, double y) {
    // super(filename, (int) x, (int) y);
    // }

    void desplazamientoX(double delta) {
        setX(getX() - 100 * delta, posXhitbox);
    }

    void ubicaAropost(double xheroe) {
        this.setX(xheroe, posXhitbox);
        this.setY(alturaY, posYhitbox);
        // this.setPosicion(xheroe, alturaY);
    }
}

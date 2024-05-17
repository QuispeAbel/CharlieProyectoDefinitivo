package poo;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class aro {
    private MedioAro AroDelante;
    private MedioAro AroDetras;
    private double altura_AroDelante = 253;
    private double altura_AroDetras = 272;

    aro(String filename, String filename2) {
        AroDelante = new MedioAro(filename, altura_AroDelante);
        AroDetras = new MedioAro(filename2, altura_AroDetras);
    }

    public void spawn(double xheroe) {
        AroDelante.ubicaAropost(xheroe);
        AroDetras.ubicaAropost(xheroe + 30);
    }

    // PROVISORIO
    public double getX() {
        return AroDelante.getX();
    }

    public void MovimientoAro(double delta) {
        AroDelante.desplazamientoX(delta);
        AroDetras.desplazamientoX(delta);
    }

    public Rectangle getHitbox() {
        return AroDelante.getHitbox();
    }

    public void displayDelante(Graphics2D g2) {
        g2.drawImage(AroDelante.imagen, (int) AroDelante.getX(), (int) AroDelante.getY(), null);
    }

    public void displayDetras(Graphics2D g2) {
        g2.drawImage(AroDetras.imagen, (int) AroDetras.getX(), (int) AroDetras.getY(), null);
    }

}

package poo;

import java.awt.Graphics2D;

public class aro {
    private MedioAro AroDelante;
    private MedioAro AroDetras;
    private double altura_AroDelante = 190;
    private double altura_AroDetras = 200;

    aro(String filename, String filename2) {
        AroDelante = new MedioAro(filename, altura_AroDelante);
        AroDetras = new MedioAro(filename2, altura_AroDetras);
    }

    public void spawn(double xheroe) {
        AroDelante.ubicaAropost(xheroe);
        AroDetras.ubicaAropost(xheroe + 20);
    }

    public void MovimientoAro(double delta) {
        AroDelante.desplazamientoX(delta);
        AroDetras.desplazamientoX(delta);
    }

    public void display(Graphics2D g2) {
        g2.drawImage(AroDelante.imagen, (int) AroDelante.getX(), (int) AroDelante.getY(), null);
        g2.drawImage(AroDetras.imagen, (int) AroDetras.getX(), (int) AroDetras.getY(), null);
    }
}

package poo;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Aro {
    private MedioAro AroDelante;
    private MedioAro AroDetras;
    private double altura_AroDelante = 253;
    private double altura_AroDetras = 272;
    // protected static final BufferedImage adelante = null;
    // protected static final BufferedImage atras = null;

    Aro(String filename, String filename2) {

        // setAltura_AroDetras(272);
        AroDelante = new MedioAro(filename, altura_AroDelante);
        AroDetras = new MedioAro(filename2, altura_AroDetras);
    }

    public void setAltura_AroDetras(double altura_AroDetras) {
        this.altura_AroDetras = altura_AroDetras;
    }

    public void spawn(double X) {
        AroDelante.respawnMedioAro(X);
        AroDetras.respawnMedioAro(X + 30);
    }

    public void spawnAroGrande(double X) {
        AroDelante.respawnMedioAro(X);
        AroDetras.respawnMedioAro(X + 40);
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
        AroDelante.draw(g2);
        // g2.drawImage(AroDelante.imagen, (int) AroDelante.getX(), (int)
        // AroDelante.getY(), null);
    }

    public void displayDetras(Graphics2D g2) {
        AroDetras.draw(g2);
        // g2.drawImage(AroDetras.imagen, (int) AroDetras.getX(), (int)
        // AroDetras.getY(), null);
    }

    public void aroGrande() {
        AroDelante.setPosXhitbox(10);
        AroDelante.setPosYhitbox(253);
        AroDelante.setWidthHitbox(25);
        AroDelante.setHeigthHitbox(70);
    }

}

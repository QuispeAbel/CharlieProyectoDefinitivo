package poo;

import java.awt.image.*; //imagenes
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.*; //Point2d

class ObjetoGrafico extends Rectangle {

    BufferedImage imagen = null;
    private Point2D.Double posicion = new Point2D.Double();

    public ObjetoGrafico() {

    }

    public void setImagen(BufferedImage img) {
        this.imagen = img;

    }

    public void setPosicion(double x, double y) {
        posicion.setLocation(x, y);
    }

    public void setX(double x) {
        posicion.x = x;
    }

    public void setY(double y) {
        posicion.y = y;
    }

    public double getX() {
        return posicion.getX();
    }

    public double getY() {
        return posicion.getY();
    }

    public void update(double delta) {

    }

    public void draw(Graphics2D g) {

        g.drawImage(imagen, (int) posicion.getX() - (imagen.getWidth() / 2), (int) posicion.getY(), null);
    }
}

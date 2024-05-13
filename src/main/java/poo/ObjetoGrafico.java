package poo;

import java.awt.image.*; //imagenes
import java.io.*;
import javax.imageio.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.Graphics2D;
import java.awt.geom.*; //Point2d

class ObjetoGrafico {

    protected BufferedImage imagen = null;
    protected Point2D.Double posicion = new Point2D.Double();

    public void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObjetoGrafico(String filename) {
        try {
            this.imagen = ImageIO.read(getClass().getResource(filename));

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public ObjetoGrafico() {

    }

    public int getHeight() {
        return imagen.getHeight();
    }

    public int getWidth() {
        return imagen.getWidth();
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

    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.getX(), (int) this.getY(), null);
    }
}

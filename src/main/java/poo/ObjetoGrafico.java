package poo;

import java.awt.*;
import java.awt.image.*; //imagenes
import java.io.*;
import javax.imageio.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

class ObjetoGrafico extends Rectangle {

    protected BufferedImage imagen = null;

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

    public ObjetoGrafico(String filename, int x, int y) {
        try {

            this.imagen = ImageIO.read(getClass().getResource(filename));
            this.x = x;
            this.y = y;
            this.width = getWidthIm();
            this.height = getHeightIm();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public ObjetoGrafico(String filename, int x, int y, int weight, int height) {
        try {
            this.imagen = ImageIO.read(getClass().getResource(filename));
            this.x = x;
            this.y = y;
            this.width = weight;
            this.height = height;
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public ObjetoGrafico(String filename) {
        try {
            this.imagen = ImageIO.read(getClass().getResource(filename));
            // posiblemente se saque
            this.width = getWidthIm();
            this.height = getHeightIm();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public ObjetoGrafico() {

    }

    public int getHeightIm() {
        return imagen.getHeight();
    }

    public int getWidthIm() {
        return imagen.getWidth();
    }

    public void setImagen(BufferedImage img) {
        this.imagen = img;

    }

    public void setPosicion(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public void setX(double x) {
        this.x = (int) x;
    }

    public void setY(double y) {
        this.y = (int) y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void update(double delta) {

    }

    public void draw(Graphics2D g) {
        g.drawImage(imagen, (int) this.getX() - (imagen.getWidth() / 2), (int) this.getY(), null);
    }

    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.getX(), (int) this.getY(), null);
    }
}

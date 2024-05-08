package poo;

/**
Compilar
javac -cp ".;bucleJuego.jar" DemoJuego02.java 

Ejecutar
java -cp ".;bucleJuego.jar" DemoJuego02
  */

import com.entropyinteractive.*;

import java.awt.*;
import java.awt.event.*; //eventos

import java.awt.image.*; //imagenes
import javax.imageio.*; //imagenes

import java.awt.Graphics2D;

import java.util.LinkedList;

import java.util.*;
import java.text.*;

public class DemoJuego02 extends JGame {

    Date dInit = new Date();
    Date dAhora;
    SimpleDateFormat ft = new SimpleDateFormat("mm:ss");
    final double NAVE_DESPLAZAMIENTO = 150.0;
    final double NAVE_DESPLAZAMIENTO2 = 150.0;
    BufferedImage img_fondo = null;

    ObjetoGrafico ovni = new ObjetoGrafico();
    
    ObjetoGrafico ovni2 = new ObjetoGrafico();

    Pelota pelota = new Pelota();

    public static void main(String[] args) {

        DemoJuego02 game = new DemoJuego02();
        game.run(1.0 / 60.0);
        System.exit(0);
    }

    public DemoJuego02() {

        super("DemoJuego02", 800, 600);

        System.out.println(appProperties.stringPropertyNames());

    }

    public void gameStartup() {

        try {
            img_fondo = ImageIO.read(getClass().getResource("imagenes/Pong-Fondo.jpg"));
            ovni.setImagen(ImageIO.read(getClass().getResource("imagenes/paleta.jpg")));
            ovni.setPosicion(19, getHeight() / 2);
            ovni2.setImagen(ImageIO.read(getClass().getResource("imagenes/paleta.jpg")));
            ovni2.setPosicion(getWidth() -19 ,getHeight() / 2);
            pelota.setImagen(ImageIO.read(getClass().getResource("imagenes/pelota.jpg")));
            pelota.setPosicion(getWidth()/2 ,getHeight() / 2);
        } catch (Exception e) {

        }

    }

    public void gameUpdate(double delta) {

        Keyboard keyboard = this.getKeyboard();

        pelota.moverPelota();

        // Procesar teclas de direccion
        if (keyboard.isKeyPressed(KeyEvent.VK_UP)) {
            ovni.setY(ovni.getY() - NAVE_DESPLAZAMIENTO * delta);
            // shipY -= NAVE_DESPLAZAMIENTO * delta;
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_DOWN)) {
            // shipY += NAVE_DESPLAZAMIENTO * delta;
            ovni.setY(ovni.getY() + NAVE_DESPLAZAMIENTO * delta);
        }
 
         // Procesar teclas de direccion
         if (keyboard.isKeyPressed(KeyEvent.VK_W)) {
            ovni2.setY(ovni2.getY() - NAVE_DESPLAZAMIENTO2 * delta);
            // shipY -= NAVE_DESPLAZAMIENTO * delta;
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_S)) {
            // shipY += NAVE_DESPLAZAMIENTO * delta;
            ovni2.setY(ovni2.getY() + NAVE_DESPLAZAMIENTO2 * delta);
        }



       

        // Esc fin del juego
        LinkedList<KeyEvent> keyEvents = keyboard.getEvents();
        for (KeyEvent event : keyEvents) {
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                    (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                stop();
            }
        }

        ovni.update(delta);

    }

    public void gameDraw(Graphics2D g) {

        dAhora = new Date();
        long dateDiff = dAhora.getTime() - dInit.getTime();
        long diffSeconds = dateDiff / 1000 % 60;
        long diffMinutes = dateDiff / (60 * 1000) % 60;

        g.drawImage(img_fondo, 0, 0, null);// imagen de fondo

        g.setColor(Color.black);
        g.drawString("Tiempo de Juego: " + diffMinutes + ":" + diffSeconds, 12, 42);
        g.drawString("Tecla ESC = Fin del Juego ", 592, 42);

        g.setColor(Color.white);
        g.drawString("Tiempo de Juego: " + diffMinutes + ":" + diffSeconds, 10, 40);
        g.drawString("Tecla ESC = Fin del Juego ", 590, 40);

        ovni.draw(g);
        
        ovni2.draw(g);

        pelota.draw(g);
    }

    public void gameShutdown() {
        Log.info(getClass().getSimpleName(), "Shutting down game");
    }
}

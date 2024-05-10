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

public class Pong extends JGame {

    Date dInit = new Date();
    Date dAhora;
    SimpleDateFormat ft = new SimpleDateFormat("mm:ss");

    final double velocidadPaleta = 500.0; // Velocidad Pelota
    BufferedImage img_fondo = null; // Crear Imágen

    Paleta paleta1 = new Paleta();

    Paleta paleta2 = new Paleta();

    Pelota pelota = new Pelota();

    Jugador jugadorIzq = new Jugador();

    Jugador jugador2Der = new Jugador();

    public static void main(String[] args) {

        Pong game = new Pong();
        game.run(1.0 / 60.0);
        System.exit(0);
    }

    public Pong() {

        super("DemoJuego02", 800, 600);

        System.out.println(appProperties.stringPropertyNames());

    }

    public void gameStartup() {

        try {
            img_fondo = ImageIO.read(getClass().getResource("imagenes/Pong-Fondo.jpg"));
            paleta1.setImagen(ImageIO.read(getClass().getResource("imagenes/paleta.jpg")));
            paleta1.setPosicion(19, getHeight() / 2);
            paleta2.setImagen(ImageIO.read(getClass().getResource("imagenes/paleta.jpg")));
            paleta2.setPosicion(getWidth() - 19, getHeight() / 2);
            pelota.setImagen(ImageIO.read(getClass().getResource("imagenes/pelota.jpg")));
            pelota.setPosicion(getWidth() / 2, getHeight() / 2);
        } catch (Exception e) {

        }

    }

    public void gameUpdate(double delta) {

        Keyboard keyboard = this.getKeyboard();

        pelota.moverPelota(getWidth(), getHeight()); // Movimiento continuo de la pelota

        // Reiniciar Pelota
        if (pelota.getPelotaFueraDer()) { // Pregunta si la pelota se salio a la derecha
            pelota.invertirVelX(); // La pelota ahora sale para el otro lado de la cancha
            pelota.setPelotaFueraDer(); // se vuelve a poner falso que la pelota salio a la derecha
            pelota.setPosicion(getWidth() / 2, getHeight() / 2); // setea la posición al medio
        } else if (pelota.getPelotaFueraIzq()) { // lo mismo que arriba con izquierda
            pelota.invertirVelX();
            pelota.setPelotaFueraIzq();
            pelota.setPosicion(getWidth() / 2, getHeight() / 2);
        }

        if (pelota.getY() <= paleta2.getY() + 100 && pelota.getY() >= paleta2.getY()
                && pelota.getX() >= getWidth() - 35) // Golpe Paleta Derecha
            pelota.invertirVelX();

        if (pelota.getY() <= paleta1.getY() + 100 && pelota.getY() >= paleta1.getY()
                && pelota.getX() <= getWidth() - 765) // Golpe Paleta Derecha
            pelota.invertirVelX();
        // Movimiento de Paletas

        if (keyboard.isKeyPressed(KeyEvent.VK_W) && paleta1.getY() >= 25) { // agregué que no haya llegado a su límite
                                                                            // superior
            paleta1.setY(paleta1.getY() - velocidadPaleta * delta);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_S) && paleta1.getY() <= getHeight() - 100) { // agregué que no haya
                                                                                           // llegado a su límite
                                                                                           // inferior
            paleta1.setY(paleta1.getY() + velocidadPaleta * delta);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_UP) && paleta2.getY() >= 25) { // agregué que no haya llegado a su límite
                                                                             // superior
            paleta2.setY(paleta2.getY() - velocidadPaleta * delta);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_DOWN) && paleta2.getY() <= getHeight() - 100) { // agregué que no haya
                                                                                              // llegado a su límite
                                                                                              // inferior
            paleta2.setY(paleta2.getY() + velocidadPaleta * delta);
        }

        // Esc fin del juego
        LinkedList<KeyEvent> keyEvents = keyboard.getEvents();
        for (KeyEvent event : keyEvents) {
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                    (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                stop();
            }
        }

        paleta1.update(delta);
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

        paleta1.draw(g);

        paleta2.draw(g);

        pelota.draw(g);
    }

    public void gameShutdown() {
        Log.info(getClass().getSimpleName(), "Shutting down game");
    }
}

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

        super("Pong", 800, 600);

        System.out.println(appProperties.stringPropertyNames());

    }

    public void gameStartup() {

        try {
            img_fondo = ImageIO.read(getClass().getResource("imagenes/FondoNegro.png"));
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
            jugadorIzq.sumarPunto();
        } else if (pelota.getPelotaFueraIzq()) { // lo mismo que arriba con izquierda
            pelota.invertirVelX();
            pelota.setPelotaFueraIzq();
            pelota.setPosicion(getWidth() / 2, getHeight() / 2);
            jugador2Der.sumarPunto();
        }

        if (pelota.getY() <= paleta2.getY() + 100 && pelota.getY() >= paleta2.getY()
                && pelota.getX() >= getWidth() - 35) // Golpe Paleta Derecha
            pelota.invertirVelX();

        if (pelota.getY() <= paleta1.getY() + 100 && pelota.getY() >= paleta1.getY()
                && pelota.getX() <= 35) // Golpe Paleta Derecha
            pelota.invertirVelX();
        // Movimiento de Paletas

        if (keyboard.isKeyPressed(KeyEvent.VK_W) && paleta1.getY() >= 25) { // agregué que no haya llegado a su límite
                                                                            // superior
            paleta1.moverPaletaarriba(delta);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_S) && paleta1.getY() <= getHeight() - 100) { // agregué que no haya
                                                                                           // llegado a su límite
                                                                                           // inferior
            paleta1.moverPaletabajo(delta);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_UP) && paleta2.getY() >= 25) { // agregué que no haya llegado a su límite
                                                                             // superior
            paleta2.moverPaletaarriba(delta);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_DOWN) && paleta2.getY() <= getHeight() - 100) { // agregué que no haya
                                                                                              // llegado a su límite
                                                                                              // inferior
            paleta2.moverPaletabajo(delta);
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

        // Dibujar una línea punteada en la mitad de la ventana
        g.setColor(Color.WHITE);
        Graphics2D g2d = (Graphics2D) g;
        Stroke dashed = new BasicStroke(10, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0);
        g2d.setStroke(dashed);
        g.drawLine(getWidth() / 2 - 5, 0, getWidth() / 2 - 5, getHeight());

        g.setColor(Color.white);
        g.drawString("Tiempo de Juego: " + diffMinutes + ":" + diffSeconds, 10, 40);
        g.drawString("Tecla ESC = Fin del Juego ", getWidth() - 160, 40);

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("" + jugadorIzq.getPuntos(), getWidth() / 2 - 100, 100);

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("" + jugador2Der.getPuntos(), getWidth() / 2 + 50, 100);

        paleta1.draw(g);

        paleta2.draw(g);

        pelota.draw(g);
    }

    public void gameShutdown() {
        Log.info(getClass().getSimpleName(), "Shutting down game");
    }
}

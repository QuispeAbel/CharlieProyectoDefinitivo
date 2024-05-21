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
    String img_pelota;
    String img_paleta1;
    String img_paleta2;

    boolean gameover = false;

    int sounds = 1;

    Paleta paleta1;

    Paleta paleta2;

    Pelota pelota;

    Jugador jugadorIzq = new JugadorPong();

    Jugador jugador2Der = new JugadorPong();

    public static void main(String[] args) {

    }

    public Pong(String img_pelota, String img_paleta1, String img_paleta2) {

        super("Pong", 1024, 768);

        this.img_pelota = img_pelota;
        this.img_paleta1 = img_paleta1;
        this.img_paleta2 = img_paleta2;

        System.out.println(appProperties.stringPropertyNames());

    }

    public void gameStartup() {

        try {

            img_fondo = ImageIO.read(getClass().getResource("imagenes/FondoNegro.png"));

            paleta1 = new Paleta(img_paleta1, 19, getHeight() / 2);
            paleta2 = new Paleta(img_paleta2, getWidth() - 19, getHeight() / 2);

            pelota = new Pelota(img_pelota, getWidth() / 2, getHeight() / 2);

        } catch (Exception e) {

        }

    }

    public void gameUpdate(double delta) {

        Keyboard keyboard = this.getKeyboard();

        pelota.moverPelota(getWidth(), getHeight(), gameover); // Movimiento continuo de la pelota

        if (jugador2Der.getPuntos() == 10 || jugadorIzq.getPuntos() == 10) {
            pelota.detenerPelota();
            pelota.setPosicion(getWidth() / 2, getHeight());
            gameover = true;
        }

        // Reiniciar Pelota
        if (pelota.getPelotaFueraDer()) { // Pregunta si la pelota se salio a la derecha
            pelota.invertirVelX(); // La pelota ahora sale para el otro lado de la cancha
            pelota.setPelotaFueraDer(); // se vuelve a poner falso que la pelota salio a la derecha
            pelota.setPosicion(getWidth() / 2, getHeight() / 2); // setea la posición al medio
            jugadorIzq.sumarPuntos();
        } else if (pelota.getPelotaFueraIzq()) { // lo mismo que arriba con izquierda
            pelota.invertirVelX();
            pelota.setPelotaFueraIzq();
            pelota.setPosicion(getWidth() / 2, getHeight() / 2);
            jugador2Der.sumarPuntos();
        }

        if (!gameover) {
            if (paleta2.intersects(pelota)) { // Golpe Paleta Derecha
                pelota.invertirVelX();
                // pelota.invertirVelY();
                pelota.playSound("src\\main\\resources\\poo\\sonidos\\golpe.wav");
            }

            if (paleta1.intersects(pelota)) { // Golpe Paleta Derecha
                pelota.invertirVelX();
                // pelota.invertirVelY();
                pelota.playSound("src\\main\\resources\\poo\\sonidos\\golpe.wav");
            }

            if (keyboard.isKeyPressed(KeyEvent.VK_W) && paleta1.getY() >= 25) // agregué
                                                                              // que no
                                                                              // haya
                                                                              // llegado
                                                                              // a su
                                                                              // límite
                // superior
                paleta1.moverPaletaarriba(delta);

            if (keyboard.isKeyPressed(KeyEvent.VK_S) && paleta1.getY() <= getHeight() - 100) // agregué que no haya
                // llegado a su límite
                // inferior
                paleta1.moverPaletabajo(delta);

            if (keyboard.isKeyPressed(KeyEvent.VK_UP) && paleta2.getY() >= 25) // agregué
                                                                               // que no
                                                                               // haya
                                                                               // llegado
                                                                               // a su
                // límite
                // superior
                paleta2.moverPaletaarriba(delta);

            if (keyboard.isKeyPressed(KeyEvent.VK_DOWN) && paleta2.getY() <= getHeight() - 100) // agregué que no haya
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

        g.setFont(new Font("Arial", Font.BOLD, 70));
        g.drawString("" + jugadorIzq.getPuntos(), getWidth() / 2 - 100, 100);
        g.drawString("" + jugador2Der.getPuntos(), getWidth() / 2 + 50, 100);

        paleta1.draw(g);

        paleta2.draw(g);

        pelota.draw(g);

        if (gameover) {
            g.setFont(new Font("Arial", Font.BOLD, 30));
            String winner = jugadorIzq.getPuntos() > jugador2Der.getPuntos() ? "Player 1" : "Player 2";
            g.drawString("Game Over!  " + winner + " wins!", getWidth() / 2 - 180, getHeight() / 2 - 10);
            g.setColor(Color.GRAY);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("[press ESC to quit]", getWidth() / 2 - 90, getHeight() / 2 + 130);
        }

    }

    public void gameShutdown() {
        Log.info(getClass().getSimpleName(), "Shutting down game");
    }
}

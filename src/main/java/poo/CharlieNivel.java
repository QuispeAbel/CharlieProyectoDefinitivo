package poo;

/**
Compilar
javac -cp ".;bucleJuego.jar" DemoJuego03.java

Ejecutar
java -cp ".;bucleJuego.jar" DemoJuego03
  */

import com.entropyinteractive.*;

import java.awt.*;
import java.awt.event.*; //eventos

import java.awt.image.*; //imagenes
import javax.imageio.*; //imagenes

import java.util.*;
import java.text.*;

public class CharlieNivel extends JGame {

    Date dInit = new Date();
    Date dAhora;
    SimpleDateFormat ft = new SimpleDateFormat("mm:ss");

    Camara cam;
    Fondo fondo;
    Personaje heroe;
    final double HEROE_DESPLAZAMIENTO = 150.0;

    public static void main(String[] args) {

        CharlieNivel game = new CharlieNivel();
        game.run(1.0 / 60.0);
        System.exit(0);
    }

    public CharlieNivel() {
        // call game constructor
        super("DemoCamaraHeroe ", 640, 480);

    }

    public void gameStartup() {
        // Log.info(getClass().getSimpleName(), "Starting up game");
        Mundo m = Mundo.getInstance();
        heroe = new Personaje("imagenes/geometry-01.png");
        heroe.setPosicion(320.0, 240.0);

        cam = new Camara(0, 0);

        cam.setRegionVisible(640, 480);

        fondo = new Fondo("imagenes/fondox84.png");
        m.setLimitesMundo(fondo.getWidth(), fondo.getHeight());
        heroe.quieto();

    }

    public void gameUpdate(double delta) {
        Keyboard keyboard = getKeyboard();

        // Procesar teclas de direccion
        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
            /// shipX -= NAVE_DESPLAZAMIENTO * delta;
            heroe.setX(heroe.getX() - HEROE_DESPLAZAMIENTO * delta);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
            // shipX += NAVE_DESPLAZAMIENTO * delta;
            heroe.setX(heroe.getX() + HEROE_DESPLAZAMIENTO * delta);
        }
        /*
         * if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)){
         * 
         * heroe.left();
         * }
         * if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)){
         * 
         * heroe.right();
         * }
         */

        // check the list of key events for a pressed escape key
        LinkedList<KeyEvent> keyEvents = keyboard.getEvents();
        for (KeyEvent event : keyEvents) {
            if ((event.getID() == KeyEvent.KEY_RELEASED)) {
                heroe.quieto();
            }
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                    (event.getKeyCode() == KeyEvent.VK_SPACE)) {
                heroe.jump();
            }
            /*
             * if ((event.getID() == KeyEvent.KEY_RELEASED) &&
             * (event.getKeyCode() == KeyEvent.VK_SPACE)) {
             * heroe.jumpEnd();
             * }
             */
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                    (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                stop();
            }
        }

        heroe.update(delta);

        // heroe.applyForce(gravedad);

        cam.seguirPersonaje(heroe); /// la camara sigue al Personaje

    }

    public void gameDraw(Graphics2D g) {

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Mundo m = Mundo.getInstance();

        g.translate(cam.getX(), cam.getY());

        fondo.display(g);
        m.display(g);
        heroe.display(g);

        g.translate(-cam.getX(), -cam.getY());

        g.setColor(Color.red);

        g.drawString("Tecla ESC = Fin del Juego ", 490, 20);
    }

    public void gameShutdown() {
        // Log.info(getClass().getSimpleName(), "Shutting down game");
    }
}
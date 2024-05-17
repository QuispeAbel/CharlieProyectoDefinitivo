package poo;

import com.entropyinteractive.*;

import java.awt.*;
import java.awt.event.*; //eventos
import java.util.*;
import java.text.*;

public class CharlieNivel extends JGame {

    Date dInit = new Date();
    Date dAhora;
    SimpleDateFormat ft = new SimpleDateFormat("mm:ss");

    Camara cam;
    Fondo fondo;
    Marcador_Puntaje marcador;
    Leon leoncito;
    Personaje Charlie;
    tarima tarima;
    Caldera calderass;
    int espacioEntreCalderas = 800;
    Jugador j1;
    Jugador j2;
    boolean gameover = false;
    boolean ganaste = false;
    // private long lastSpawnTime; // Guarda el tiempo del último spawn
    // private long spawnInterval = 5000; // Intervalo de tiempo entre spawns en
    // milisegundos
    aro arito = new aro("imagenes/aroMitad2Peque.png", "imagenes/aroMitad1Peque.png");

    private double DistanciaNuevoSpawnX = 700; // Offset en X para asegurar que el objeto aparezca adelante del
                                               // personaje

    final double HEROE_DESPLAZAMIENTO = 350.0;

    public static void main(String[] args) {

        CharlieNivel game = new CharlieNivel();
        game.run(1.0 / 60.0);
        System.exit(0);
    }

    public CharlieNivel() {
        // call game constructor
        super("DemoCamaraHeroe ", 1024, 720);

    }

    public void gameStartup() {

        Mundo m = Mundo.getInstance();

        leoncito = new Leon("imagenes/leoncito.png", 320, 575);
        Charlie = new Personaje("imagenes/Charlie/CharlieCaminando3.gif", 350, 515);
        Charlie.setPiso(515);

        marcador = new Marcador_Puntaje("imagenes/marcador.jpg");
        marcador.setPosicion(4, 30);

        calderass = new Caldera("imagenes/caldera1.png");

        cam = new Camara(0, 0);

        cam.setRegionVisible(640, 480);

        fondo = new Fondo("imagenes/FondoCharliLevel1.png");
        m.setLimitesMundo(fondo.getWidthIm(), fondo.getHeightIm());

        Charlie.quieto();
        leoncito.quieto();

        tarima = new tarima("imagenes/tarima.png", 8100, 327);

    }

    public void gameUpdate(double delta) {
        Keyboard keyboard = getKeyboard();

        // Procesar teclas de direccion
        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
            /// shipX -= NAVE_DESPLAZAMIENTO * delta;
            if (gameover || ganaste) {
                leoncito.quieto();
                Charlie.quieto();
            } else {
                leoncito.right(HEROE_DESPLAZAMIENTO * delta);
                Charlie.right(HEROE_DESPLAZAMIENTO * delta);
            }
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
            // shipX += NAVE_DESPLAZAMIENTO * delta;
            if (gameover || ganaste) {
                leoncito.quieto();
                Charlie.quieto();
            } else
                leoncito.left(HEROE_DESPLAZAMIENTO * delta);
            Charlie.left(HEROE_DESPLAZAMIENTO * delta);

        }
        /*
         * if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)){
         * 
         * leoncito.left();
         * }
         * if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)){
         * 
         * leoncito.right();
         * }
         */

        // check the list of key events for a pressed escape key
        LinkedList<KeyEvent> keyEvents = keyboard.getEvents();
        for (KeyEvent event : keyEvents) {
            if ((event.getID() == KeyEvent.KEY_RELEASED)) {
                leoncito.quieto();
                Charlie.quieto();
            }
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                    (event.getKeyCode() == KeyEvent.VK_SPACE)) {
                if (gameover || ganaste) {
                    leoncito.quieto();
                    Charlie.quieto();
                } else {
                    leoncito.jump();
                    Charlie.jump();
                }
            }

            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                    (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                stop();
            }
        }

        if (!gameover)
            leoncito.update(delta);
        Charlie.update(delta);

        // Desplazar el aro hacia la izquierda
        arito.MovimientoAro(delta);

        // leoncito.applyForce(gravedad);

        cam.seguirPersonaje(leoncito); /// la camara sigue al Personaje

        // long currentTime = System.currentTimeMillis();
        if (!gameover) {
            if (leoncito.getX() > calderass.getX() + 250) {

                calderass.setPosicion(leoncito.getX() + espacioEntreCalderas, 553);
            }
            // editar arito: proximamente el setX tomara el x del hitbox y no el de uno de
            // los medios aros
            if (leoncito.getX() > arito.getX() + 350) {

                arito.spawn(leoncito.getX() + DistanciaNuevoSpawnX);

            }

            if (leoncito.getHitbox().intersects(arito.getHitbox()))
                gameover = true;
            if (leoncito.getHitbox().intersects(calderass.getHitbox()))
                gameover = true;
            if (leoncito.getHitbox().intersects(tarima))
                ganaste = true;
        }

    }

    public void gameDraw(Graphics2D g) {

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Mundo m = Mundo.getInstance();

        g.translate(cam.getX(), cam.getY());

        fondo.display(g);
        m.display(g);
        tarima.display(g);

        calderass.display(g);

        arito.displayDelante(g);

        leoncito.display(g);

        Charlie.display(g);

        arito.displayDetras(g);

        g.translate(-cam.getX(), -cam.getY());

        g.setColor(Color.red);

        g.drawString("Tecla ESC = Fin del Juego ", 490, 20);

        marcador.display(g);

        if (gameover) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 70));
            g.drawString("GAME OVER!", 100, 250);
        }

        if (ganaste) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 70));
            g.drawString("GANASTE!", 100, 250);
        }
    }

    public void gameShutdown() {
        // Log.info(getClass().getSimpleName(), "Shutting down game");
    }
}

/*
 * private void reiniciarObjeto() {
 * // Establece la posición de reinicio del objeto delante del personaje
 * double posX = leoncito.getX() + offsetSpawnX;
 * double posY = y; // Define la posición adecuada en Y según sea necesario
 * objetoEnJuego.setPosicion(posX, posY);
 * }
 */

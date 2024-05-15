package poo;

import com.entropyinteractive.*;

import java.awt.*;
import java.awt.event.*; //eventos
import java.awt.Rectangle;
//import java.awt.image.*; //imagenes
//import javax.imageio.*; //imagenes

import java.util.*;
import java.text.*;

public class CharlieNivel extends JGame {

    Date dInit = new Date();
    Date dAhora;
    SimpleDateFormat ft = new SimpleDateFormat("mm:ss");

    Camara cam;
    Fondo fondo;
    Marcador_Puntaje marcador;
    Personaje heroe;
    ObjetoGrafico tarima;
    Caldera calderass;
    int espacioEntreCalderas = 800;
    Jugador j1;
    Jugador j2;
    boolean gameover = false;
    int cont = 0;

    private long lastSpawnTime; // Guarda el tiempo del último spawn
    private long spawnInterval = 5000; // Intervalo de tiempo entre spawns en milisegundos
    aro arito = new aro("imagenes/aroMitad2.png", "imagenes/aroMitad1.png");

    private double offsetSpawnX = 350; // Offset en X para asegurar que el objeto aparezca adelante del personaje

    final double HEROE_DESPLAZAMIENTO = 250.0;

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

        Mundo m = Mundo.getInstance();
        heroe = new Personaje("imagenes/squareImage.png", getHeight(), getWidth());
        heroe.setPosicion(320.0, 380.0);

        marcador = new Marcador_Puntaje("imagenes/marcador.jpg");
        marcador.setPosicion(4, 30);

        calderass = new Caldera("imagenes/caldera1.png", getHeight(), 345);

        cam = new Camara(0, 0);

        cam.setRegionVisible(640, 480);

        fondo = new Fondo("imagenes/fondox84.png");
        m.setLimitesMundo(fondo.getWidthIm(), fondo.getHeightIm());
        heroe.quieto();

        tarima = new ObjetoGrafico("imagenes/tarima.png", 8100, 327);

    }

    public void gameUpdate(double delta) {
        Keyboard keyboard = getKeyboard();

        // Procesar teclas de direccion
        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
            /// shipX -= NAVE_DESPLAZAMIENTO * delta;
            if (!gameover)
                heroe.right(HEROE_DESPLAZAMIENTO * delta);
            else
                heroe.quieto();
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
            // shipX += NAVE_DESPLAZAMIENTO * delta;
            if (!gameover)
                heroe.left(HEROE_DESPLAZAMIENTO * delta);
            heroe.quieto();
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
                if (!gameover)
                    heroe.jump();
                else
                    heroe.quieto();
            }

            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                    (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                stop();
            }
        }

        heroe.update(delta);

        // Desplazar el aro hacia la izquierda
        arito.MovimientoAro(delta);

        // heroe.applyForce(gravedad);

        cam.seguirPersonaje(heroe); /// la camara sigue al Personaje

        long currentTime = System.currentTimeMillis();
        if (!gameover) {
            if (currentTime - lastSpawnTime > spawnInterval) {
                // Reinicia la posición del objeto al borde derecho de la pantalla
                arito.spawn(heroe.getX() + offsetSpawnX);

                calderass.setPosicion(heroe.getX() + espacioEntreCalderas, 345);
                // Actualizar el tiempo del último spawn
                lastSpawnTime = currentTime;
                cont++;
            }
            if (heroe.intersects(calderass))
                gameover = true;
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

        heroe.display(g);

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
    }

    public void gameShutdown() {
        // Log.info(getClass().getSimpleName(), "Shutting down game");
    }
}

/*
 * private void reiniciarObjeto() {
 * // Establece la posición de reinicio del objeto delante del personaje
 * double posX = heroe.getX() + offsetSpawnX;
 * double posY = y; // Define la posición adecuada en Y según sea necesario
 * objetoEnJuego.setPosicion(posX, posY);
 * }
 */

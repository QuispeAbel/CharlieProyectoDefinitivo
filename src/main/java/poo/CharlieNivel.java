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

//import java.awt.image.*; //imagenes
//import javax.imageio.*; //imagenes

import java.util.*;
import java.text.*;
//import java.util.List;

public class CharlieNivel extends JGame {

    Date dInit = new Date();
    Date dAhora;
    SimpleDateFormat ft = new SimpleDateFormat("mm:ss");

    Camara cam;
    Fondo fondo;
    Marcador_Puntaje marcador;
    Personaje heroe;
    ObjetoGrafico tarima;
    ObjetoGrafico calderass;
    int espacioEntreCalderas = 800;
    Jugador j1;
    Jugador j2;

    private long lastSpawnTime; // Guarda el tiempo del último spawn
    private long spawnInterval = 5000; // Intervalo de tiempo entre spawns en milisegundos
    aro arito = new aro("imagenes/aroMitad2.png", "imagenes/aroMitad1.png");

    private double offsetSpawnX = 350; // Offset en X para asegurar que el objeto aparezca adelante del personaje

    final double HEROE_DESPLAZAMIENTO = 300.0;

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
        heroe = new Personaje("imagenes/squareImage.png");
        heroe.setPosicion(320.0, 380.0);

        marcador = new Marcador_Puntaje("imagenes/marcador.jpg");
        marcador.setPosicion(4, 30);

        calderass = new ObjetoGrafico("imagenes/caldera1.png");

        cam = new Camara(0, 0);

        cam.setRegionVisible(640, 480);

        fondo = new Fondo("imagenes/fondox84.png");
        m.setLimitesMundo(fondo.getWidth(), fondo.getHeight());
        heroe.quieto();

        tarima = new ObjetoGrafico("imagenes/tarima.png");
        tarima.setPosicion(8100, 327);

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

        // Desplazar el aro hacia la izquierda
        arito.MovimientoAro(delta);

        // heroe.applyForce(gravedad);

        cam.seguirPersonaje(heroe); /// la camara sigue al Personaje

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSpawnTime > spawnInterval) {
            // Reinicia la posición del objeto al borde derecho de la pantalla
            arito.spawn(heroe.getX() + offsetSpawnX);

            calderass.setPosicion(heroe.getX() + espacioEntreCalderas, 345);
            // Actualizar el tiempo del último spawn
            lastSpawnTime = currentTime;
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

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
    Personaje heroe;
    ObjetoGrafico tarima;
    // List<ObjetoGrafico> calderas= new ArrayList<>();
    ObjetoGrafico calderass = new ObjetoGrafico("imagenes/caldera1.png");
    int espacioEntreCalderas = 800;
    int numCalderas = 10;

    private long lastSpawnTime; // Guarda el tiempo del último spawn
    private long spawnInterval = 5000; // Intervalo de tiempo entre spawns en milisegundos
    aro aros1 = new aro("imagenes/aroMitad2.png"); // ,new aro("imagenes/aroMitad2.png")};
    aro aros2 = new aro("imagenes/aroMitad1.png"); // ,new aro("imagenes/aroMitad1.png")};
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
        aros1.desplazamientoX(delta);
        aros2.desplazamientoX(delta);

        // heroe.applyForce(gravedad);

        cam.seguirPersonaje(heroe); /// la camara sigue al Personaje

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSpawnTime > spawnInterval) {
            // Reinicia la posición del objeto al borde derecho de la pantalla
            aros1.ubicaAropost(heroe.getX() + offsetSpawnX); // .setPosicion(posX, 190);
            aros2.ubicaArosup(heroe.getX() + offsetSpawnX);// .setPosicion(posX+420, 200);

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

        aros1.display(g);

        heroe.display(g);

        aros2.display(g);

        g.translate(-cam.getX(), -cam.getY());

        g.setColor(Color.red);

        g.drawString("Tecla ESC = Fin del Juego ", 490, 20);
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

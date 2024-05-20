package poo;

import com.entropyinteractive.*;

import java.awt.*;
import java.awt.event.*; //eventos
import java.util.*;
import java.text.*;

public class CharlieNivel {

    Date dInit;
    Date dAhora;
    SimpleDateFormat ft;

    Camara cam;
    Fondo fondo;
    Marcador_Puntaje marcador;
    Leon leoncito;
    Charlie Charlie;
    tarima tarima;
    Caldera calderass;
    Bonus bolsa;
    int espacioEntreCalderas = 800;
    int posicion_aro;
    Jugador j1;
    Jugador j2;
    boolean gameover = false;
    boolean ganaste = false;
    int cont = 0;
    int contbon = 0;
    // private long lastSpawnTime; // Guarda el tiempo del último spawn
    // private long spawnInterval = 5000; // Intervalo de tiempo entre spawns en
    // milisegundos
    Aro arito;
    Aro aro;

    private double DistanciaNuevoSpawnXarito = 4500; // Offset en X para asegurar que el objeto aparezca adelante del
    // personaje
    private double DistanciaNuevoSpawnXbonus = 4515;
    private double DistanciaNuevoSpawnXaro = 700;

    final double HEROE_DESPLAZAMIENTO = 350.0;

    CharlieNivel() {
    }

    public void Start() {

        ft = new SimpleDateFormat("mm:ss");

        dInit = new Date();

        Mundo m = Mundo.getInstance();

        j1 = new Jugador();

        bolsa = new Bonus("imagenes/ufo.png");

        arito = new Aro("imagenes/aroMitad2Peque.png", "imagenes/aroMitad1Peque.png");
        aro = new Aro("imagenes/aroGrande1.png", "imagenes/aroGrande2.png");

        leoncito = new Leon("imagenes/leoncito.png", 320, 575);
        Charlie = new Charlie("imagenes/Charlie/CharlieCaminando3.gif", 350, 515);
        Charlie.setPiso(515);

        marcador = new Marcador_Puntaje("imagenes/marcadorCopia.jpg");
        marcador.setPosicion(4, 30);

        calderass = new Caldera("imagenes/caldera1.png");

        aro.aroGrande();

        cam = new Camara(0, 0);

        cam.setRegionVisible(1024, 720);

        fondo = new Fondo("imagenes/FondoCharliLevel1.png");
        m.setLimitesMundo(fondo.getWidthIm(), fondo.getHeightIm());

        Charlie.quieto();
        leoncito.quieto();

        tarima = new tarima("imagenes/tarima.png", 10000, 530);

    }

    public void Update(double delta, Keyboard keyboard) {

        // Puntos
        if (leoncito.getX() > aro.getX()) {
            // if(j1_jugando)
            j1.sumarPuntos(100);
            // else
            // j2.sumarPuntosPasados(100);
        }

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
            } else {
                leoncito.left(HEROE_DESPLAZAMIENTO * delta);
                Charlie.left(HEROE_DESPLAZAMIENTO * delta);
            }

        }

        if (keyboard.isKeyPressed(KeyEvent.KEY_RELEASED)) {
            leoncito.quieto();
            Charlie.quieto();
        }

        if ((keyboard.isKeyPressed(KeyEvent.VK_SPACE))) {
            if (gameover || ganaste) {
                leoncito.quieto();
                Charlie.quieto();
            } else {
                leoncito.jump();
                Charlie.jump();
            }
        }

        // if ((event.getID() == KeyEvent.KEY_PRESSED) &&
        // (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
        // stop();
        // }

        if (!gameover) {
            arito.MovimientoAro(delta);
            bolsa.Movimientobonus(delta);
            aro.MovimientoAro(delta);
            leoncito.update(delta);
            Charlie.update(delta);
        }

        // Desplazar el aro hacia la izquierda
        // arito.MovimientoAro(delta);

        // leoncito.applyForce(gravedad);

        cam.seguirPersonaje(leoncito); /// la camara sigue al Personaje

        // long currentTime = System.currentTimeMillis();
        if (!gameover) {
            if (leoncito.getX() > calderass.getX() + 250 && !ganaste) {

                calderass.setPosicion(leoncito.getX() + espacioEntreCalderas, 553);
            }
            // editar arito: proximamente el setX tomara el x del hitbox y no el de uno de
            // los medios aros
            if (leoncito.getX() > aro.getX() + 350 && !ganaste) {
                aro.spawnAroGrande(leoncito.getX() + DistanciaNuevoSpawnXaro);
                cont++;
                if (cont % 5 == 0) {
                    arito.spawn(leoncito.getX() + DistanciaNuevoSpawnXarito);
                    bolsa.spawn(leoncito.getX() + DistanciaNuevoSpawnXbonus);
                }
            }

            if (leoncito.getHitbox().intersects(aro.getHitbox()) || leoncito.getHitbox().intersects(arito.getHitbox()))
                gameover = true;
            if (Charlie.getHitbox().intersects(aro.getHitbox()) || Charlie.getHitbox().intersects(arito.getHitbox()))
                gameover = true;
            if (leoncito.getHitbox().intersects(calderass.getHitbox()))
                gameover = true;
            if (leoncito.getHitbox().intersects(tarima)) {
                ganaste = true;
                Charlie.ganar(10050, 440);
                leoncito.ganar(10020, 490);
            }
            if (Charlie.getHitbox().intersects(bolsa)) {
                contbon++;
            }
        }

    }

    public void Draw(Graphics2D g) {

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Mundo m = Mundo.getInstance();

        g.translate(cam.getX(), cam.getY());

        fondo.display(g);
        m.display(g);
        tarima.display(g);

        calderass.display(g);

        aro.displayDelante(g);

        arito.displayDelante(g);

        if (!Charlie.getHitbox().intersects(bolsa) && contbon == 0) {
            bolsa.display(g);
        }

        leoncito.display(g);

        Charlie.display(g);

        aro.displayDetras(g);

        arito.displayDetras(g);

        g.translate(-cam.getX(), -cam.getY());

        g.setColor(Color.red);

        g.drawString("Tecla ESC = Fin del Juego ", 490, 20);

        marcador.display(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        j1.sumarPuntos(100);
        marcador.draw(g);

        g.setFont(new Font("Arial", Font.BOLD, 70));

        if (gameover) {
            g.setColor(Color.RED);
            g.drawString("GAME OVER!", 100, 250);
        }

        if (ganaste) {
            g.setColor(Color.GREEN);
            g.drawString("GANASTE!", 100, 250);
        }
    }

    public void Sdown() {
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

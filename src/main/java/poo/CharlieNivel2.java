package poo;

import com.entropyinteractive.*;

import java.awt.*;
import java.awt.event.*; //eventos
import java.util.*;
//import java.text.*;

import java.text.SimpleDateFormat;

public class CharlieNivel2 extends CharlieNivel {
    Date dInit;
    Date dAhora;
    SimpleDateFormat ft;

    Camara cam;
    Fondo fondo;
    Marcador_Puntaje marcador;
    Charlie Charlie;
    tarima tarima;

    Jugador j1;
    Jugador j2;
    boolean gameover = false;
    boolean ganaste = false;
    // private long lastSpawnTime; // Guarda el tiempo del último spawn
    // private long spawnInterval = 5000; // Intervalo de tiempo entre spawns en
    // milisegundos
    // aro arito = new aro("imagenes/aroMitad2Peque.png",
    // "imagenes/aroMitad1Peque.png");
    Monito mono, monoazul;
    int cont = 0;

    private double DistanciaNuevoSpawnX = 700; // Offset en X para asegurar que el objeto aparezca adelante del
                                               // personaje

    final double HEROE_DESPLAZAMIENTO = 350.0;

    public CharlieNivel2() {
    }

    public void Start() {

        ft = new SimpleDateFormat("mm:ss");

        dInit = new Date();

        Mundo m = Mundo.getInstance();

        Charlie = new Charlie("imagenes/Charlie/CharlieCaminando3.gif", 350, 640);
        Charlie.setPiso(322);

        mono = new Monito("imagenes/mono.png");

        monoazul = new Monito("imagenes/mono_azul.png");

        marcador = new Marcador_Puntaje("imagenes/marcador.jpg");
        marcador.setPosicion(4, 30);

        cam = new Camara(0, 0);

        cam.setRegionVisible(1024, 720);

        fondo = new Fondo("imagenes/FondoCharliLevel2.png");
        m.setLimitesMundo(fondo.getWidthIm(), fondo.getHeightIm());

        Charlie.quieto();

        tarima = new tarima("imagenes/tarima_columna.gif", 10000, 320);

    }

    public void Update(double delta, Keyboard keyboard) {
        // Keyboard keyboard = getKeyboard();

        // Procesar teclas de direccion
        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
            /// shipX -= NAVE_DESPLAZAMIENTO * delta;
            if (gameover || ganaste) {
                Charlie.quieto();
            } else {
                Charlie.right(HEROE_DESPLAZAMIENTO * delta);
            }
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
            // shipX += NAVE_DESPLAZAMIENTO * delta;
            if (gameover || ganaste) {
                Charlie.quieto();
            } else {
                Charlie.left(HEROE_DESPLAZAMIENTO * delta);
            }

        }
        if (keyboard.isKeyPressed(KeyEvent.KEY_RELEASED)) {
            Charlie.quieto();
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
            if (gameover || ganaste) {
                Charlie.quieto();
            } else {
                Charlie.jump();
            }
        }

        if (!gameover) {
            // arito.MovimientoAro(delta);
            mono.MovimientoMono(delta);
            monoazul.MovimientoMono(delta * 2);
            Charlie.update(delta);

            if (!mono.getHitbox().intersects(monoazul.getHitbox())) {
                mono.update(delta);
                monoazul.update(delta);
            }
        }

        // Desplazar el aro hacia la izquierda
        // arito.MovimientoAro(delta);

        cam.seguirPersonaje(Charlie); /// la camara sigue al Personaje

        // long currentTime = System.currentTimeMillis();
        if (!gameover) {

            // editar arito: proximamente el setX tomara el x del hitbox y no el de uno de
            // los medios aros
            if (Charlie.getX() > mono.getX() + 350 && !ganaste) {

                mono.spawn(Charlie.getX() + DistanciaNuevoSpawnX);
                cont++;
                if (cont % 5 == 0)
                    monoazul.spawn(Charlie.getX() + DistanciaNuevoSpawnX * 1.5);

            }

            if (mono.getHitbox().intersects(monoazul.getHitbox())) // && mono.getY()==monoazul.getY() )
                monoazul.SaltoMono(delta);

            // if (!mono.getHitbox().intersects(monoazul.getHitbox())) // && mono.getY() !=
            // monoazul.getY() )
            // monoazul.update(delta);

            if (Charlie.getHitbox().intersects(mono.getHitbox()))
                gameover = true;
            if (Charlie.getHitbox().intersects(monoazul.getHitbox()))
                gameover = true;
            if (Charlie.getHitbox().intersects(tarima)) {
                ganaste = true;
                Charlie.ganar(10050, 270);
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

        // arito.displayDelante(g);

        // arito.displayDelante(g);

        Charlie.display(g);

        mono.display(g);
        monoazul.display(g);

        // arito.displayDetras(g);

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

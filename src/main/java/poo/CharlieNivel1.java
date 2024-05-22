package poo;

import com.entropyinteractive.*;

import java.awt.*;
import java.awt.event.*; //eventos
import java.util.*;
import java.text.*;
import javax.swing.Timer;
import java.util.ArrayList;

public class CharlieNivel1 {

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
    Jugador j1;
    Jugador j2;
    boolean gameover = false;
    boolean ganaste = false;
    boolean bonus = false;
    Timer bonusTimer;
    int cont = 0;
    int contbon = 0;
    // private long lastSpawnTime; // Guarda el tiempo del último spawn
    // private long spawnInterval = 5000; // Intervalo de tiempo entre spawns en
    // milisegundos
    Aro arito;

    private int CantidadArosGrandes = 10;
    private int DistanciaEntreAros = 1000;
    ArrayList<Aro> arosgrandes = new ArrayList<Aro>();

    private int CantidadArosChicos = 4;
    private int DistanciaEntreArosChicos = 2000;
    ArrayList<Aro> aroschicos = new ArrayList<Aro>();

    private double DistanciaNuevoSpawnXarito = 4500; // Offset en X para asegurar que el objeto aparezca adelante del
    // personaje
    private double DistanciaNuevoSpawnXbonus = 4515;

    final double HEROE_DESPLAZAMIENTO = 350.0;

    CharlieNivel1() {
    }

    public void Start() {

        ft = new SimpleDateFormat("mm:ss");

        dInit = new Date();

        Mundo m = Mundo.getInstance();

        j1 = new Jugador();

        // bolsa = new Bonus("imagenes/ufo.png");

        // primer aro chico necesario porque la diferencia entre aros es distinta al
        // primer spawn
        aroschicos.add(new Aro("imagenes/aroMitad2Peque.png", "imagenes/aroMitad1Peque.png"));
        aroschicos.get(0).spawn(2500);

        // arito = new Aro("imagenes/aroMitad2Peque.png",
        // "imagenes/aroMitad1Peque.png");
        for (int i = 0; i < CantidadArosGrandes; i++) {
            arosgrandes.add(new Aro("imagenes/aroGrande1.png", "imagenes/aroGrande2.png"));
            arosgrandes.get(i).aroGrande();
            arosgrandes.get(i).spawnAroGrande(DistanciaEntreAros * (i + 1));

            // aros chicos
            if (i < CantidadArosChicos && 0 < i) {
                aroschicos.add(new Aro("imagenes/aroMitad2Peque.png", "imagenes/aroMitad1Peque.png"));
                aroschicos.get(i).spawn(DistanciaEntreArosChicos * (i + 1));
            }
        }

        leoncito = new Leon("imagenes/leoncito.png", 320, 575);
        Charlie = new Charlie("imagenes/Charlie/CharlieCaminando3.gif", 350, 515);
        Charlie.setPiso(515);

        marcador = new Marcador_Puntaje("imagenes/marcadorCopia.jpg");
        marcador.setPosicion(4, 30);

        calderass = new Caldera("imagenes/caldera1.png");

        cam = new Camara(0, 0);

        cam.setRegionVisible(1024, 720);

        fondo = new Fondo("imagenes/FondoCharliLevel1.png");
        m.setLimitesMundo(fondo.getWidthIm(), fondo.getHeightIm());

        Charlie.quieto();
        leoncito.quieto();

        tarima = new tarima("imagenes/tarima.png", 10000, 530);

        // cuenta el tiempo cuando aparece el carterl "Bonus"
        bonusTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bonus = false;
                bonusTimer.stop();
            }
        });
        bonusTimer.setRepeats(false);

    }

    public void Update(double delta, Keyboard keyboard) {

        cam.seguirPersonaje(leoncito); /// la camara sigue al Personaje
        // // Puntos
        // if (leoncito.getX() > aro.getX()) {
        // // if(j1_jugando)
        // j1.sumarPuntos(100);
        // // else
        // // j2.sumarPuntosPasados(100);
        // }

        // marcador.setNroJugador(j1);

        // // Puntos
        // if (leoncito.getX() >= aro.getX()) {
        // // if(j1_jugando)
        // j1.sumarPuntos(100);
        // marcador.getPuntajeTotal(j1);
        // // else
        // // j2.sumarPuntosPasados(100);
        // }

        // Procesar teclas de direccion
        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
            if (gameover || ganaste) {
                leoncito.quieto();
                Charlie.quieto();
            } else {
                leoncito.right(HEROE_DESPLAZAMIENTO * delta);
                Charlie.right(HEROE_DESPLAZAMIENTO * delta);
            }
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
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

        if (!gameover) {
            // bolsa.Movimientobonus(delta);

            // arosgrandes
            for (int i = 0; i < CantidadArosGrandes; i++) {
                arosgrandes.get(i).MovimientoAro(delta);
                // respawn al final del mapa
                if (arosgrandes.get(i).getX() == 10)
                    arosgrandes.get(i).spawnAroGrande(10000);
                // choque con personajes
                if (leoncito.getHitbox().intersects(arosgrandes.get(i).getHitbox())
                        || Charlie.getHitbox().intersects(arosgrandes.get(i).getHitbox())) {
                    gameover = true;
                }
                // aros chicos
                if (i < CantidadArosChicos) {
                    aroschicos.get(i).MovimientoAro(delta);
                    // respawn al final del mapa
                    if (arosgrandes.get(i).getX() == 10)
                        aroschicos.get(i).spawnAroGrande(8500);
                    // choque con personajes
                    if (leoncito.getHitbox().intersects(aroschicos.get(i).getHitbox())
                            || Charlie.getHitbox().intersects(aroschicos.get(i).getHitbox())) {
                        gameover = true;
                    }
                }

            }
            leoncito.update(delta);
            Charlie.update(delta);

            if (leoncito.getX() > calderass.getX() + 250 && !ganaste) {
                calderass.setPosicion(leoncito.getX() + espacioEntreCalderas, 553);
            }

            /*
             * if (leoncito.getX() > arito.getX() + 350 && !ganaste) {
             * 
             * cont++;
             * if (cont % 5 == 0) {
             * arito.spawn(leoncito.getX() + DistanciaNuevoSpawnXarito);
             * bolsa.spawn(leoncito.getX() + DistanciaNuevoSpawnXbonus);
             * contbon = 0;
             * }
             * }
             */

            if (leoncito.getHitbox().intersects(calderass.getHitbox()))
                gameover = true;
            if (leoncito.getHitbox().intersects(tarima)) {
                ganaste = true;
                Charlie.ganar(10050, 440);
                leoncito.ganar(10020, 490);
                for (int i = 0; i < CantidadArosGrandes; i++) {
                    arosgrandes.get(i).MovimientoAro(0);
                    if (i < CantidadArosChicos) {
                        aroschicos.get(i).MovimientoAro(0);
                    }
                }

                // bolsa.Movimientobonus(0);
            }
            // if (Charlie.getHitbox().intersects(bolsa)) {
            // contbon++;
            // bonus = true;
            // if (!bonusTimer.isRunning()) {
            // bonusTimer.start();
            // }
            // }
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

        for (int i = 0; i < CantidadArosGrandes; i++) {
            arosgrandes.get(i).displayDelante(g);
            if (i < CantidadArosChicos) {
                aroschicos.get(i).displayDelante(g);
            }
        }

        // if (!Charlie.getHitbox().intersects(bolsa) && contbon == 0)
        // bolsa.display(g);

        leoncito.display(g);

        Charlie.display(g);

        for (int i = 0; i < CantidadArosGrandes; i++) {
            arosgrandes.get(i).displayDetras(g);
            if (i < CantidadArosChicos) {
                aroschicos.get(i).displayDetras(g);
            }
        }

        g.translate(-cam.getX(), -cam.getY());

        g.setColor(Color.red);

        g.drawString("Tecla ESC = Fin del Juego ", 490, 20);

        marcador.display(g);

        if (bonus) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("500", (1024 / 2) - 200, (720 / 2) - 200);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        j1.sumarPuntos(100);
        marcador.draw(g);

        g.setFont(new Font("Arial", Font.BOLD, 70));

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
}

/*
 * private void reiniciarObjeto() {
 * // Establece la posición de reinicio del objeto delante del personaje
 * double posX = leoncito.getX() + offsetSpawnX;
 * double posY = y; // Define la posición adecuada en Y según sea necesario
 * objetoEnJuego.setPosicion(posX, posY);
 * }
 */
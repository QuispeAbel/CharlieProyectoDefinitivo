package poo;

import javax.swing.Timer;

import com.entropyinteractive.Keyboard;

import java.awt.*;
import java.awt.event.*; //eventos
import java.util.*;

public class CharlieNivel3 extends CharlieNivel {

    Charlie Charlie;
    tarima tarima;
    boolean gameover = false;
    boolean ganaste = false;
    boolean bonus = false;
    Timer bonusTimer;
    // int contbon = 0;
    // private long lastSpawnTime; // Guarda el tiempo del último spawn
    // private long spawnInterval = 5000; // Intervalo de tiempo entre spawns en
    // milisegundos

    private int CantidadPelotas = 10;
    private int DistanciaEntrePelotas = 350;
    ArrayList<Pelota_Charlie> pelotas = new ArrayList<Pelota_Charlie>();
    Pelota_Charlie pelota_inicial;
    ArrayList<Integer> cont = new ArrayList<Integer>();

    final double HEROE_DESPLAZAMIENTO = 350.0;

    public CharlieNivel3() {

        setEstado(4);

        Mundo m = Mundo.getInstance();

        pelotas.add(new Pelota_Charlie(("imagenes/pelota.png"), 350, 575));
        pelotas.get(0).spawn(-500);

        for (int i = 0; i < CantidadPelotas; i++) {
            pelotas.add(new Pelota_Charlie(("imagenes/pelota.png"), (DistanciaEntrePelotas * (i + 1)), 575));
            // pelotas.get(i).aroGrande();
            // pelotas.get(i).spawn(DistanciaEntrePelotas * (i + 1));
        }

        Charlie = new Charlie("imagenes/Charlie/CharlieCaminando3.gif", 350, 300);
        Charlie.setPiso(575);

        marcador = new Marcador_Puntaje("imagenes/marcadorCopia.jpg");
        marcador.setPosicion(4, 30);
        marcador.getHi();

        cam = new Camara(0, 0);

        cam.setRegionVisible(1024, 720);

        fondo = new Fondo("imagenes/FondoCharliLevel1.png");
        m.setLimitesMundo(fondo.getWidthIm(), fondo.getHeightIm());

        Charlie.quieto();
        // pelota.quieto();

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

    public void Update(double delta, Keyboard keyboard, Jugador jugador) {

        cam.seguirPersonaje(Charlie); /// la camara sigue al Personaje
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
                Charlie.quieto();
                for (int i = 0; i < CantidadPelotas; i++) {
                    if (Charlie.getHitbox().intersects(pelotas.get(i).getHitbox()))
                        pelotas.get(i).quieto();
                }
            } else {
                Charlie.right(HEROE_DESPLAZAMIENTO * delta);
                for (int i = 0; i < CantidadPelotas; i++) {
                    if (Charlie.getHitbox().intersects(pelotas.get(i).getHitbox()))
                        pelotas.get(i).right(HEROE_DESPLAZAMIENTO * delta);
                    /*
                     * if(pelotas.get(i).getHitbox().intersects(pelotas.get(i+1).getHitbox())){
                     * Charlie.setY(350);
                     * pelotas.get(i).DisparadaIzq(delta);
                     * pelotas.get(i+1).Disparadader(delta);
                     * Charlie.setPiso(575);
                     * Charlie.perder(575);
                     * gameover=true;
                     * }
                     */

                }
            }
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (gameover || ganaste) {

                Charlie.quieto();
                for (int i = 0; i < CantidadPelotas; i++) {
                    if (Charlie.getHitbox().intersects(pelotas.get(i).getHitbox()))
                        pelotas.get(i).quieto();
                }
            } else {
                Charlie.left(HEROE_DESPLAZAMIENTO * delta);
                for (int i = 0; i < CantidadPelotas; i++) {
                    if (Charlie.getHitbox().intersects(pelotas.get(i).getHitbox()))
                        pelotas.get(i).left(HEROE_DESPLAZAMIENTO * delta);
                    /*
                     * if(pelotas.get(i).getHitbox().intersects(pelotas.get(i+1).getHitbox())){
                     * Charlie.setY(350);
                     * pelotas.get(i).DisparadaIzq(delta);
                     * pelotas.get(i+1).Disparadader(delta);
                     * Charlie.setPiso(575);
                     * Charlie.perder(575);
                     * gameover=true;
                     * }
                     */

                }
            }

        }

        if (keyboard.isKeyPressed(KeyEvent.KEY_RELEASED)) {
            Charlie.quieto();
            for (int i = 0; i < CantidadPelotas; i++) {
                if (Charlie.getHitbox().intersects(pelotas.get(i).getHitbox()))
                    pelotas.get(i).quieto();
            }
        }

        if ((keyboard.isKeyPressed(KeyEvent.VK_SPACE))) {
            if (gameover || ganaste) {
                Charlie.quieto();
                for (int i = 0; i < CantidadPelotas; i++) {
                    if (Charlie.getHitbox().intersects(pelotas.get(i).getHitbox()))
                        pelotas.get(i).quieto();
                }
            } else {
                Charlie.jump();
            }
        }

        if (!gameover) {
            // arosgrandes
            for (int i = 0; i < CantidadPelotas; i++) {
                if (!Charlie.getHitbox().intersects(pelotas.get(i).getHitbox())) {
                    pelotas.get(i).MovimientoPelota(delta);
                }

                if (Charlie.getHitbox().intersects(pelotas.get(i).getHitbox())) {
                    Charlie.setX(pelotas.get(i).getX());
                    Charlie.setPiso(525);
                }

                if (!Charlie.getHitbox().intersects(pelotas.get(i).getHitbox())
                        && Charlie.getX() > pelotas.get(i).getX() && Charlie.getX() > pelotas.get(i + 1).getX()) {
                    Charlie.setPiso(575);
                }

                // respawn al final del mapa
                if (pelotas.get(i).getX() <= 20)
                    pelotas.get(i).spawn(10000);
                // choque con personajes
                // if (Charlie.getHitbox().intersects(pelotas.get(i).getHitbox())) {
                // gameover = true;
                // }
            }

            Charlie.update(delta);

            if (Charlie.getHitbox().intersects(tarima)) {
                ganaste = true;
                Charlie.ganar(10050, 480);
                for (int i = 0; i < CantidadPelotas; i++) {
                    pelotas.get(i).MovimientoPelota(0);
                }
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

        for (int i = 0; i < CantidadPelotas; i++) {
            pelotas.get(i).display(g);
        }

        Charlie.display(g);

        g.translate(-cam.getX(), -cam.getY());

        g.setColor(Color.red);

        g.drawString("Tecla ESC = Fin del Juego ", 490, 20);

        g.setFont(new Font("Press Start 2P", Font.BOLD, 20));
        marcador.display(g);
        marcador.draw(g);

        if (bonus) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("500", (1024 / 2) - 200, 315);
            g.setFont(new Font("Arial", Font.BOLD, 70));
            g.drawString("bonus!", 100, 250);
            // g.setColor(Color.white);

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

}

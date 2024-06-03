package poo;

import com.entropyinteractive.*;

import java.awt.*;
import java.awt.event.*; //eventos
import java.util.*;
import javax.swing.Timer;

public class CharlieNivel2 extends CharlieNivel {

    private Charlie Charlie;
    private Tarima tarima;
    private boolean gameover = false;
    private boolean ganaste = false;
    private boolean bonus = false;
    private Timer bonusTimer;
    // int contbon = 0;
    // private long lastSpawnTime; // Guarda el tiempo del último spawn
    // private long spawnInterval = 5000; // Intervalo de tiempo entre spawns en
    // milisegundos

    private int CantidadMonos = 10;
    private int DistanciaEntreMonos = 1000;
    private ArrayList<Monito> monos = new ArrayList<Monito>();

    private int CantidadMonosAz = 4;
    private int DistanciaEntreMonosAz = 2000;
    private ArrayList<Monito> monosaz = new ArrayList<Monito>();
    private ArrayList<Integer> cont = new ArrayList<Integer>();

    final double HEROE_DESPLAZAMIENTO = 350.0;

    public CharlieNivel2() {
        setEstado(2);

        Mundo m = Mundo.getInstance();

        // primer aro chico necesario porque la diferencia entre aros es distinta al
        // primer spawn
        monosaz.add(new Monito("imagenes/mono_azul.png"));
        monosaz.get(0).spawn(2500);
        cont.add(0);

        // arito = new Aro("imagenes/aroMitad2Peque.png",
        // "imagenes/aroMitad1Peque.png");
        for (int i = 0; i < CantidadMonos; i++) {
            monos.add(new Monito("imagenes/mono.png"));
            monos.get(i).spawn(DistanciaEntreMonos * (i + 1));

            // aros chicos
            if (i < CantidadMonosAz && 0 < i) {
                monosaz.add(new Monito("imagenes/mono_azul.png"));
                monosaz.get(i).spawn((DistanciaEntreMonosAz * (i + 1)) + 500);
                cont.add(0);
            }
        }

        Charlie = new Charlie("imagenes/Charlie/CharlieCaminando3.gif", 350, 640);
        Charlie.setPiso(322);

        marcador = new Marcador_Puntaje("imagenes/marcadorCopia.jpg");
        marcador.setPosicion(4, 30);
        marcador.getHi();

        cam = new Camara(0, 0);

        cam.setRegionVisible(1024, 720);

        fondo = new Fondo("imagenes/FondoCharliLevel2.png");
        m.setLimitesMundo(fondo.getWidthIm(), fondo.getHeightIm());

        Charlie.quieto();

        tarima = new Tarima("imagenes/tarima_columna.gif", 10000, 320);

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
            } else {
                Charlie.right(HEROE_DESPLAZAMIENTO * delta);
            }
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (gameover || ganaste) {
                Charlie.quieto();
            } else {
                Charlie.left(HEROE_DESPLAZAMIENTO * delta);
            }

        }

        if (keyboard.isKeyPressed(KeyEvent.KEY_RELEASED)) {
            Charlie.quieto();
        }

        if ((keyboard.isKeyPressed(KeyEvent.VK_SPACE))) {
            if (gameover || ganaste) {
                Charlie.quieto();
            } else {
                Charlie.jump();
            }
        }

        if (!gameover) {
            // monos
            for (int i = 0; i < CantidadMonos; i++) {
                monos.get(i).MovimientoMono(delta);

                if (Charlie.getX() >= monos.get(i).getX() - 3
                        && Charlie.getX() <= monos.get(i).getX() + 3) {
                    // if(j1_jugando)
                    jugador.sumarPuntos(100);
                    marcador.getPuntajeTotal(jugador);
                    // else
                    // j2.sumarPuntosPasados(100);
                }

                // Suma Puntos Aro Grande
                /*
                 * if (leoncito.getX() >= monos.get(i).getX() - 3
                 * && leoncito.getX() <= monos.get(i).getX() + 3) {
                 * // if(j1_jugando)
                 * j1.sumarPuntos(100);
                 * marcador.getPuntajeTotal(j1);
                 * // else
                 * // j2.sumarPuntosPasados(100);
                 * }
                 */

                // respawn al final del mapa
                if (monos.get(i).getX() <= 350)
                    monos.get(i).spawn(10000);
                // choque con personajes
                if (Charlie.getHitbox().intersects(monos.get(i).getHitbox())) {
                    gameover = true;
                }
                // aros chicos
                if (i < CantidadMonosAz) {

                    if (Charlie.getX() >= monosaz.get(i).getX() - 10
                            && Charlie.getX() <= monosaz.get(i).getX() + 10) {
                        // if(j1_jugando)
                        jugador.sumarPuntos(500);
                        marcador.getPuntajeTotal(jugador);
                        // else
                        // j2.sumarPuntosPasados(100);
                    }

                    monosaz.get(i).MovimientoMono(delta * 2.5);
                    // respawn al final del mapa
                    if (monosaz.get(i).getX() <= 350) {
                        monosaz.get(i).spawn(8500);
                    }

                    if (monos.get(i).getHitbox().intersects(monosaz.get(i).getHitbox())
                            && monos.get(i).getY() == monosaz.get(i).getY())
                        monosaz.get(i).SaltoMono(delta);

                    if (Charlie.getX() >= monosaz.get(i).getX() - 5 && Charlie.getX() <= monosaz.get(i).getX() + 5)
                        monosaz.get(i).SaltoMono(delta);

                    if (!monos.get(i).getHitbox().intersects(monosaz.get(i).getHitbox()))
                        monosaz.get(i).update(delta);

                    // choque con personajes
                    if (Charlie.getHitbox().intersects(monosaz.get(i).getHitbox())) {
                        gameover = true;
                    }

                    /*
                     * if (leoncito.getHitbox().intersects(bolsa.get(i).getHitbox())
                     * || Charlie.getHitbox().intersects(bolsa.get(i).getHitbox())) {
                     * bonus = true;
                     * cont.set(i,1);
                     * if (!bonusTimer.isRunning()) {
                     * bonusTimer.start();
                     * }
                     * }
                     */
                    /*
                     * if(Charlie.getHitbox().intersects(bolsa.get(i).getHitbox()) &&
                     * (leoncito.getX() >= monosaz.get(i).getX() - 3 && leoncito.getX() <=
                     * monosaz.get(i).getX() + 3)){
                     * j1.sumarPuntos(500);
                     * marcador.getPuntajeTotal(j1);
                     * }
                     */
                }

            }

            Charlie.update(delta);

            if (Charlie.getHitbox().intersects(tarima)) {
                setEstado(3);
                ganaste = true;
                Charlie.ganar(10050, 270);
                for (int i = 0; i < CantidadMonos; i++) {
                    monos.get(i).MovimientoMono(0);
                    if (i < CantidadMonosAz) {
                        monosaz.get(i).MovimientoMono(0);
                    }
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

        for (int i = 0; i < CantidadMonos; i++) {
            monos.get(i).display(g);
            if (i < CantidadMonosAz) {
                monosaz.get(i).display(g);
                /*
                 * if (!Charlie.getHitbox().intersects(bolsa.get(i).getHitbox()) && (cont.get(i)
                 * == 0)){
                 * bolsa.get(i).display(g);
                 * }
                 */
            }
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

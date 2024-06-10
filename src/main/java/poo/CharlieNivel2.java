package poo;

import com.entropyinteractive.*;

import java.awt.*;
import java.awt.event.*; //eventos
import java.util.*;

public class CharlieNivel2 extends CharlieNivel {

    private Charlie charlie;
    private Tarima tarima;
    private boolean gameover = false;
    private boolean ganaste = false;
    private int piso = 575;

    private int CantidadMonos = 10;
    private int DistanciaEntreMonos = 1000;
    private ArrayList<Monito> monos = new ArrayList<Monito>();

    private int CantidadMonosAz = 4;
    private int DistanciaEntreMonosAz = 2000;
    private ArrayList<MonoAzul> monosaz = new ArrayList<MonoAzul>();
    private ArrayList<Integer> cont = new ArrayList<Integer>();

    final double HEROE_DESPLAZAMIENTO = 350.0;

    public CharlieNivel2() {

        setEstado(2);

        Mundo m = Mundo.getInstance();

        // primer aro chico necesario porque la diferencia entre aros es distinta al
        // primer spawn
        monosaz.add(new MonoAzul("imagenes/mono_azul.png"));
        monosaz.get(0).spawn(2500);
        cont.add(0);

        // arito = new Aro("imagenes/aroMitad2Peque.png",
        // "imagenes/aroMitad1Peque.png");
        for (int i = 0; i < CantidadMonos; i++) {
            monos.add(new Monito("imagenes/mono.png"));
            monos.get(i).spawn(DistanciaEntreMonos * (i + 1));

            // aros chicos
            if (i < CantidadMonosAz && 0 < i) {
                monosaz.add(new MonoAzul("imagenes/mono_azul.png"));
                monosaz.get(i).spawn((DistanciaEntreMonosAz * (i + 1)) + 500);
                cont.add(0);
            }
        }

        charlie = new Charlie("imagenes/Charlie/CharlieCaminando3.gif", 350, 640);
        charlie.setPiso(322);

        marcador = new Marcador_Puntaje("imagenes/marcadorCopia.jpg");
        marcador.setPosicion(4, 30);
        marcador.getHi();
        marcador.setStage(2);

        fondo = new Fondo("imagenes/FondoCharliLevel2.png");
        m.setLimitesMundo(fondo.getWidthIm(), fondo.getHeightIm());

        charlie.quieto();

        tarima = new Tarima("imagenes/tarima_columna.gif", 10000, 320);

        // Pasa el tiempo
        timer.start();

    }

    public void Update(double delta, Keyboard keyboard, Jugador jugador) {

        cam.seguirPersonaje(charlie); /// la camara sigue al Personaje

        if (gameover) {
            gameover = false;
            jugador.setVidas(jugador.getVidas() - 1);
            marcador.setVidas(jugador);
            gameover = false;
        }

        if (!gameover && !ganaste) {

            if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
                charlie.right(HEROE_DESPLAZAMIENTO * delta);
            }

            if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
                charlie.left(HEROE_DESPLAZAMIENTO * delta);
            }

            if (keyboard.isKeyPressed(KeyEvent.KEY_RELEASED)) {
                charlie.quieto();
            }

            if ((keyboard.isKeyPressed(KeyEvent.VK_SPACE))) {
                charlie.jump();
            }
            // monos
            for (int i = 0; i < CantidadMonos; i++) {
                monos.get(i).MovimientoMono(delta);

                if (charlie.getX() >= monos.get(i).getX() - 3
                        && charlie.getX() <= monos.get(i).getX() + 3) {
                    // if(j1_jugando)
                    jugador.sumarPuntos(100);
                    marcador.getPuntajeTotal(jugador);
                    // else
                    // j2.sumarPuntosPasados(100);
                }

                // respawn al final del mapa
                if (monos.get(i).getX() <= 400)
                    monos.get(i).spawn(10000);
                // choque con personajes
                if (charlie.getHitbox().intersects(monos.get(i).getHitbox())) {
                    charlie.setPiso(piso);
                }

                if (i < CantidadMonosAz) {

                    if (charlie.getX() >= monosaz.get(i).getX() - 10
                            && charlie.getX() <= monosaz.get(i).getX() + 10) {
                        // if(j1_jugando)
                        jugador.sumarPuntos(500);
                        marcador.getPuntajeTotal(jugador);
                        // else
                        // j2.sumarPuntosPasados(100);
                    }

                    monosaz.get(i).MovimientoMono(delta * 2.5);
                    // respawn al final del mapa
                    if (monosaz.get(i).getX() <= 400) {
                        monosaz.get(i).spawn(8500);
                    }
                    for (int j = 0; j < CantidadMonosAz; j++) {
                        if (monos.get(i).getHitbox().intersects(monosaz.get(j).getHitbox())
                                && monos.get(i).getY() == monosaz.get(j).getY())
                            monosaz.get(j).jump();
                    }
                    if (charlie.getX() >= monosaz.get(i).getX() - 5 && charlie.getX() <= monosaz.get(i).getX() + 5)
                        monosaz.get(i).jump();

                    if (charlie.getHitbox().intersects(monosaz.get(i).getHitbox())) {
                        charlie.setPiso(piso);
                    }

                    monosaz.get(i).update(delta);

                }

                if (charlie.getY() == piso) {
                    charlie.setPiso(322);
                    charlie.setX(350);
                    gameover = true;
                }

            }

            charlie.update(delta);

            if (charlie.getHitbox().intersects(tarima)) {
                setEstado(3);
                ganaste = true;
                charlie.ganar(10050, 270);

                // Sumar tiempo
                jugador.sumarPuntos(marcador.getTiempo());
                marcador.getPuntajeTotal(jugador);

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
            }
        }

        charlie.display(g);

        g.translate(-cam.getX(), -cam.getY());

        g.setColor(Color.red);

        g.drawString("Tecla ESC = Fin del Juego ", 490, 20);

        g.setFont(new Font("Press Start 2P", Font.BOLD, 20));
        marcador.display(g);
        marcador.draw(g);

    }
}

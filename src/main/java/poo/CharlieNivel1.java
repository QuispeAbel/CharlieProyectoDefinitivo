package poo;

import com.entropyinteractive.*;

import java.awt.*;
import java.awt.event.*; //eventos
import java.util.*;
import javax.swing.Timer;

public class CharlieNivel1 extends CharlieNivel {

    Leon leoncito;
    Charlie charlie;
    Tarima tarima;
    Caldera calderass;
    int espacioEntreCalderas = 800;
    boolean gameover = false;
    boolean ganaste = false;
    boolean bonus = false;
    Timer bonusTimer;

    private int CantidadArosGrandes = 10;
    private int DistanciaEntreAros = 1000;
    ArrayList<Aro> arosgrandes = new ArrayList<Aro>();

    private int CantidadArosChicos = 4;
    private int DistanciaEntreArosChicos = 2000;
    ArrayList<Aro> aroschicos = new ArrayList<Aro>();
    ArrayList<Bonus> bolsa = new ArrayList<Bonus>();
    ArrayList<Integer> cont = new ArrayList<Integer>();

    final double HEROE_DESPLAZAMIENTO = 350.0;

    public CharlieNivel1() {

        setEstado(0);

        Mundo m = Mundo.getInstance();

        // primer aro chico necesario porque la diferencia entre aros es distinta al
        // primer spawn

        aroschicos.add(new Aro("imagenes/aroMitad2Peque.png", "imagenes/aroMitad1Peque.png"));
        aroschicos.get(0).spawn(2500);
        bolsa.add(new Bonus("imagenes/Bolsa.gif"));
        bolsa.get(0).spawn(2510);
        cont.add(0);

        for (int i = 0; i < CantidadArosGrandes; i++) {
            arosgrandes.add(new Aro("imagenes/aroGrande1.png", "imagenes/aroGrande2.png"));
            arosgrandes.get(i).aroGrande();
            arosgrandes.get(i).spawnAroGrande(DistanciaEntreAros * (i + 1));

            // aros chicos
            if (i < CantidadArosChicos && 0 < i) {
                aroschicos.add(new Aro("imagenes/aroMitad2Peque.png", "imagenes/aroMitad1Peque.png"));
                aroschicos.get(i).spawn((DistanciaEntreArosChicos * (i + 1)) + 500);
                bolsa.add(new Bonus("imagenes/Bolsa.gif"));
                bolsa.get(i).spawn((DistanciaEntreArosChicos * (i + 1)) + 500);
                cont.add(0);
            }
        }

        leoncito = new Leon("imagenes/leoncito.png", 320, 575);
        charlie = new Charlie("imagenes/Charlie/CharlieCaminando3.gif", 350, 525);
        charlie.setPiso(525);
        calderass = new Caldera("imagenes/caldera1.png");

        fondo = new Fondo("imagenes/FondoCharliLevel1.png");
        m.setLimitesMundo(fondo.getWidthIm(), fondo.getHeightIm());

        charlie.quieto();
        leoncito.quieto();

        tarima = new Tarima("imagenes/tarima.png", 10000, 530);

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

        cam.seguirPersonaje(leoncito);

        if (gameover) {
            leoncito.setX(320);
            charlie.setX(350);
            jugador.setVidas(jugador.getVidas() - 1);
            gameover = false;
        }

        if (!gameover && !ganaste) {

            leoncito.update(delta);
            charlie.update(delta);

            if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
                leoncito.right(HEROE_DESPLAZAMIENTO * delta);
                charlie.right(HEROE_DESPLAZAMIENTO * delta);
            }

            if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
                leoncito.left(HEROE_DESPLAZAMIENTO * delta);
                charlie.left(HEROE_DESPLAZAMIENTO * delta);
            }
            if (keyboard.isKeyPressed(KeyEvent.KEY_RELEASED)) {
                leoncito.quieto();
                charlie.quieto();
            }
            if ((keyboard.isKeyPressed(KeyEvent.VK_SPACE))) {
                leoncito.jump();
                charlie.jump();
            }
            // arosgrandes
            for (int i = 0; i < CantidadArosGrandes; i++) {
                arosgrandes.get(i).MovimientoAro(delta);

                // Suma Puntos Aro Grande
                if (leoncito.getX() >= arosgrandes.get(i).getX() - 3
                        && leoncito.getX() <= arosgrandes.get(i).getX() + 3) {
                    // if(j1_jugando)
                    jugador.sumarPuntos(100);
                    marcador.getPuntajeTotal(jugador);
                    // else
                    // j2.sumarPuntosPasados(100);
                }

                // respawn al final del mapa
                if (arosgrandes.get(i).getX() <= 350)
                    arosgrandes.get(i).spawnAroGrande(10000);
                // choque con personajes
                if (leoncito.getHitbox().intersects(arosgrandes.get(i).getHitbox())
                        || charlie.getHitbox().intersects(arosgrandes.get(i).getHitbox())) {
                    gameover = true;

                }
                // aros chicos
                if (i < CantidadArosChicos) {
                    aroschicos.get(i).MovimientoAro(delta);
                    bolsa.get(i).Movimientobonus(delta);
                    // respawn al final del mapa
                    if (aroschicos.get(i).getX() <= 350) {
                        aroschicos.get(i).spawn(10000);
                        bolsa.get(i).spawn(10010);
                    }
                    // choque con personajes
                    if (leoncito.getHitbox().intersects(aroschicos.get(i).getHitbox())
                            || charlie.getHitbox().intersects(aroschicos.get(i).getHitbox())) {
                        gameover = true;
                    }

                    if (leoncito.getHitbox().intersects(bolsa.get(i).getHitbox())
                            || charlie.getHitbox().intersects(bolsa.get(i).getHitbox())) {
                        bonus = true;
                        cont.set(i, 1);
                        if (!bonusTimer.isRunning()) {
                            bonusTimer.start();
                        }
                    }
                    if (charlie.getHitbox().intersects(bolsa.get(i).getHitbox())
                            && (leoncito.getX() >= aroschicos.get(i).getX() - 3
                                    && leoncito.getX() <= aroschicos.get(i).getX() + 3)) {
                        jugador.sumarPuntos(500);
                        marcador.getPuntajeTotal(jugador);
                    }
                }

            }

            // colisiones

            if (leoncito.getX() > calderass.getX() + 250 && !ganaste) {
                calderass.setPosicion(leoncito.getX() + espacioEntreCalderas, 553);
            }

            if (leoncito.getHitbox().intersects(calderass.getHitbox()))
                gameover = true;
            if (leoncito.getHitbox().intersects(tarima)) {
                ganaste = true;
                setEstado(1);
                charlie.ganar(10050, 440);
                leoncito.ganar(10020, 490);
                for (int i = 0; i < CantidadArosGrandes; i++) {
                    arosgrandes.get(i).MovimientoAro(0);
                    if (i < CantidadArosChicos) {
                        aroschicos.get(i).MovimientoAro(0);
                        bolsa.get(i).Movimientobonus(0);
                    }
                }
            }

        }
    }

    public void Draw(Graphics2D g) {
        g.setColor(Color.WHITE);

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
                if (!charlie.getHitbox().intersects(bolsa.get(i).getHitbox()) && (cont.get(i) == 0)) {
                    bolsa.get(i).display(g);
                }
            }
        }

        leoncito.display(g);

        charlie.display(g);

        for (int i = 0; i < CantidadArosGrandes; i++) {
            arosgrandes.get(i).displayDetras(g);
            if (i < CantidadArosChicos) {
                aroschicos.get(i).displayDetras(g);
            }
        }

        g.translate(-cam.getX(), -cam.getY());

        marcador.draw(g);

        if (bonus) {

            g.drawString("500", (1024 / 2) - 200, 315);

            g.drawString("bonus!", 100, 250);

            if (gameover) {
                g.drawString("GAME OVER!", 100, 250);
            }

            if (ganaste) {
                g.drawString("GANASTE!", 100, 250);
            }
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

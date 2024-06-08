package poo;

import javax.swing.Timer;

import com.entropyinteractive.Keyboard;

import java.awt.*;
import java.awt.event.*; //eventos
import java.util.*;
import javax.swing.Timer;

public class CharlieNivel3 extends CharlieNivel {

    private Charlie Charlie;
    private Tarima tarima;
    private boolean gameover = false;
    private boolean ganaste = false;
    private boolean bonus = false;
    private boolean colicion_pelota= false;
    private Timer bonusTimer;
    private int sobrePelota = 1;
    private Timer timer;
    private boolean noSumar = true;

    // private long lastSpawnTime; // Guarda el tiempo del último spawn
    // private long spawnInterval = 5000; // Intervalo de tiempo entre spawns en
    // milisegundos

    private int CantidadPelotas = 30;
    private int DistanciaEntrePelotas = 950;
    ArrayList<Pelota_Charlie> pelotas = new ArrayList<Pelota_Charlie>();
    Pelota_Charlie pelota_inicial;
    ArrayList<Integer> cont = new ArrayList<Integer>();
    private int piso = 575;
    private int altura_pelota = 515;

    final double HEROE_DESPLAZAMIENTO = 350.0;

    public CharlieNivel3() {

        setEstado(4);

        Mundo m = Mundo.getInstance();

        for (int i = 0; i < CantidadPelotas; i++) {
            // el spawn de pelotas se realiza de este modo
            // para que no esten siempre a la misma distancia
            if (i % 5 == 0 && i > 0) {
                pelotas.add(
                        new Pelota_Charlie(("imagenes/pelota.png"), ((DistanciaEntrePelotas * (i + 1)) - 1400), 575));
                // pelotas.add(new Pelota_Charlie(("imagenes/pelota.png"),
                // ((DistanciaEntrePelotas* (i + 1))-1200 ), 575));
            } else {
                pelotas.add(
                        new Pelota_Charlie(("imagenes/pelota.png"), ((DistanciaEntrePelotas * (i + 1)) - 630), 575));
            }
        }

        Charlie = new Charlie("imagenes/Charlie/CharlieCaminando3.gif", 350, 515);
        Charlie.setPiso(piso);

        marcador = new Marcador_Puntaje("imagenes/marcadorCopia.jpg");
        marcador.setPosicion(4, 30);
        marcador.getHi();

        marcador.setStage(3);

        cam = new Camara(0, 0);

        cam.setRegionVisible(1024, 720);

        fondo = new Fondo("imagenes/FondoCharliLevel1.png");
        m.setLimitesMundo(fondo.getWidthIm(), fondo.getHeightIm());

        Charlie.quieto();

        tarima = new Tarima("imagenes/tarima.png", 10000, 530);

        // cuenta el tiempo cuando aparece el carterl "Bonus"
        bonusTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bonus = false;
                bonusTimer.stop();
            }
        });
        bonusTimer.setRepeats(false);

        //Pasa el tiempo
        timer = new Timer(250, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!gameover && !ganaste)
                marcador.pasaTiempo();
            }
        });
        timer.start();

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
                    // condicion charlie intersecta la pelota i y la
                    // pos X de charlie no es mayor a la posicion X de pelota (i)
                    if (Charlie.getHitbox().intersects(pelotas.get(i).getHitbox())
                            && !(Charlie.getX() > pelotas.get(i).getX()))
                        pelotas.get(i).right(HEROE_DESPLAZAMIENTO * delta);
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
                    // condicion charlie intersecta la pelota i y la
                    // pos X de charlie no es mayor a la posicion X de pelota (i)
                    if (Charlie.getHitbox().intersects(pelotas.get(i).getHitbox()))
                        pelotas.get(i).left(HEROE_DESPLAZAMIENTO * delta);
                }
            }

        }

        /*
         * if (keyboard.isKeyPressed(KeyEvent.KEY_RELEASED)) {
         * Charlie.quieto();
         * for (int i = 0; i < CantidadPelotas; i++) {
         * if(Charlie.getHitbox().intersects(pelotas.get(i).getHitbox()))
         * pelotas.get(i).quieto();
         * }
         * }
         */

        if ((keyboard.isKeyPressed(KeyEvent.VK_SPACE))) {
            if (gameover || ganaste) {
                Charlie.quieto();
                for (int i = 0; i < CantidadPelotas; i++) {
                    if (Charlie.getHitbox().intersects(pelotas.get(i).getHitbox()))
                        pelotas.get(i).quieto();
                }
            } else {
                // solo salta charlie la pelota no
                Charlie.jump();

                for (int i = 0; i < CantidadPelotas; i++) {
                    //la pelota de la que salta charlie
                    //sera desplazada rapidamenta a la izq
                    if (Charlie.getX() > pelotas.get(i).getX() )
                    pelotas.get(i).DisparadaIzq(delta);
                }

               
            }
        }
        

        if (!gameover) {
            for (int i = 0; i < CantidadPelotas; i++) {
                // si charlie no intersecta la pelota
                // la pelota se movera por su cuenta

                if (Charlie.getHitbox().intersects(pelotas.get(i).getHitbox()) && !ganaste && sobrePelota % 2 == 0) {
                    jugador.sumarPuntos(100);
                    marcador.getPuntajeTotal(jugador);
                    sobrePelota = 1;
                }

                if (Charlie.getY() < 500) {
                    sobrePelota = 2;
                }

                // if (!Charlie.getHitbox().intersects(pelotas.get(i).getHitbox()) && !ganaste){
                // sobrePelota = 2;
                // }
                //if(colicion_pelota){
                    //pelotas.get(i).DisparadaIzq(delta);
                    //pelotas.get(i + 1).Disparadader(delta);
                    //Charlie.setY(390);
                    //Charlie.setPiso(piso);
                //}     
                if (!Charlie.getHitbox().intersects(pelotas.get(i).getHitbox())) {
                    
                    pelotas.get(i).MovimientoPelota(delta);
            
                 
                    if (Charlie.getX() > pelotas.get(i).getX()+50 && !colicion_pelota)
                    pelotas.get(i).DisparadaIzq(delta);



                  

                    // si charlie esta adelante de la pelota i y detras de la pelota i+1
                    // y aun no gano el juego, el piso de charlie sera piso y perdera
                    // sino intersecta la pelota i+1
                    if (Charlie.getX() > pelotas.get(i).getX() && Charlie.getX() < pelotas.get(i + 1).getX()
                            && !ganaste) {
                        Charlie.setPiso(piso);
                        if(colicion_pelota){
                        pelotas.get(i).DisparadaIzq(delta);
                        pelotas.get(i + 1).Disparadader(delta*2);
                        }
                    }

                    // sobrePelota = 2;
                }

                // charlie intersecta la pelota i
                if (Charlie.getHitbox().intersects(pelotas.get(i).getHitbox()) && !ganaste) {

                   
                    // ubicar a personaje en el centro de la pelota
                    // setear el piso de charlie a la altura de la pelota
                    Charlie.setX(pelotas.get(i).getX());
                    Charlie.setPiso(altura_pelota);

                     // si se intersecta la pelota i y i+1 mientras charlie esta en la pelota i
                      if (pelotas.get(i).getHitbox().intersects(pelotas.get(i + 1).getHitbox()) ) {
                        // charlie dara un salto y caera al piso, pierde
                       Charlie.setY(450);
                       Charlie.setPiso(piso);
                        pelotas.get(i).DisparadaIzq(delta);
                        pelotas.get(i + 1).Disparadader(delta);
                        colicion_pelota= true;
                        
                    }

                  
                           
                }


                if (Charlie.getY() == piso)
                    gameover = true;

                // respawn al final del mapa
                if (pelotas.get(i).getX() <= -600)
                    pelotas.get(i).spawn(500);
            }

            Charlie.update(delta);

            if (Charlie.getHitbox().intersects(tarima)) {
                ganaste = true;
                Charlie.ganar(10040, 480);

                //Sumar tiempo
                if(noSumar){
                    jugador.sumarPuntos(marcador.getTiempo());
                    marcador.getPuntajeTotal(jugador);
                    noSumar = false;
                }

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

        if (!ganaste || !gameover) {
            for (int i = 0; i < CantidadPelotas; i++) {
                pelotas.get(i).display(g);
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
                g.setFont(new Font("Arial", Font.BOLD, 50));
                g.drawString("GANASTE!", 250, 500);
            }
        }
    }
}

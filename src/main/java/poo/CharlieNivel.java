package poo;

import com.entropyinteractive.Keyboard;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public abstract class CharlieNivel {

    protected Camara cam;
    protected Fondo fondo;
    protected Marcador_Puntaje marcador;
    protected static int Estado;
    protected Timer timer;

    CharlieNivel() {
        marcador = new Marcador_Puntaje("imagenes/marcadorCopia.jpg");
        marcador.setPosicion(4, 30);
        marcador.getHi();

        cam = new Camara(0, 0);

        cam.setRegionVisible(1024, 768);

        timer = new Timer(250, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                marcador.pasaTiempo();
            }
        });
    }

    public static int getEstado() {
        return Estado;
    }

    public void setEstado(int stado) {
        Estado = stado;
    }

    public abstract void Update(double delta, Keyboard keyboard, Jugador jugador);

    public abstract void Draw(Graphics2D g);
}

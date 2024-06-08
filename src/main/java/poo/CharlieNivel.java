package poo;

import com.entropyinteractive.Keyboard;
import java.awt.*;

public abstract class CharlieNivel {

    protected Camara cam;
    protected Fondo fondo;
    protected Marcador_Puntaje marcador;
    protected static int Estado = -1;

    CharlieNivel() {
        marcador = new Marcador_Puntaje("imagenes/marcadorCopia.jpg");
        marcador.setPosicion(4, 30);
        marcador.getHi();

        cam = new Camara(0, 0);

        cam.setRegionVisible(1024, 720);
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

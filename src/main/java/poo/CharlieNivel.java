package poo;

import com.entropyinteractive.Keyboard;
import java.awt.*;

public abstract class CharlieNivel {

    protected Camara cam;
    protected Fondo fondo;
    protected Marcador_Puntaje marcador;
    protected int Estado = 0;

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    public abstract void Update(double delta, Keyboard keyboard, Jugador jugador);

    public abstract void Draw(Graphics2D g);
}

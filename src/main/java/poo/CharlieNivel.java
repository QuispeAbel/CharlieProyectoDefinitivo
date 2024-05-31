package poo;

import com.entropyinteractive.Keyboard;
import java.awt.*;

public abstract class CharlieNivel {

    public int Estado = 0;

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

    // public abstract void Start();

    public abstract void Update(double delta, Keyboard keyboard);

    public abstract void Draw(Graphics2D g);
}

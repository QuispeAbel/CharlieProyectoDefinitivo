package poo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.entropyinteractive.Keyboard;

public class CharlieNivel0 extends CharlieNivel {

    CharlieNivel0() {
        fondo = new Fondo("imagenes/FondoMenuCharlie.png");
    }

    @Override
    public void Update(double delta, Keyboard keyboard, Jugador jugador) {
        if (keyboard.isKeyPressed(KeyEvent.VK_ENTER)) {
            setEstado(-1);
        }
    }

    @Override
    public void Draw(Graphics2D g) {
        fondo.display(g);
    }

}

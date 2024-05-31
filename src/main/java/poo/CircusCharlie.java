package poo;

import com.entropyinteractive.JGame;
import com.entropyinteractive.Keyboard;

//import com.entropyinteractive.JGame;

import java.awt.*;
import java.awt.event.*;

public class CircusCharlie extends JGame {
    CharlieNivel nivelActual;
    Jugador jugador1;

    public CircusCharlie() {
        super("CircusCharlie", 1024, 768);
        jugador1 = new Jugador();
        nivelActual = new CharlieNivel2();
    }

    @Override
    public void gameDraw(Graphics2D arg0) {
        nivelActual.Draw(arg0);
    }

    @Override
    public void gameShutdown() {
    }

    @Override
    public void gameStartup() {
    }

    @Override
    public void gameUpdate(double arg0) {
        Keyboard keyboard = getKeyboard();

        switch (nivelActual.getEstado()) {
            case 1:
                nivelActual = new CharlieNivel2();
                break;
            case 3:
                nivelActual = new CharlieNivel3();
                break;

            default:
                break;
        }

        nivelActual.Update(arg0, keyboard, jugador1);
        if ((keyboard.isKeyPressed(KeyEvent.VK_ESCAPE))) {
            stop();
        }
    }

}

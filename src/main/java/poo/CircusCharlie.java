package poo;

import com.entropyinteractive.JGame;
import com.entropyinteractive.Keyboard;

//import com.entropyinteractive.JGame;

import java.awt.*;
import java.awt.event.*;

public class CircusCharlie extends JGame {
    CharlieNivel nivelActual;
    Jugador jugador1;

    // CharlieNivel1 nivel1 = new CharlieNivel1();
    // CharlieNivel2 nivel2 = new CharlieNivel2();
    // CharlieNivel3 nivel3 = new CharlieNivel3();

    public CircusCharlie() {
        // call game constructor
        super("CircusCharlie", 1024, 768);
        jugador1 = new Jugador();
        nivelActual = new CharlieNivel2();
    }

    // public static void main(String... z) {
    // CircusCharlie jogo = new CircusCharlie();
    // jogo.run(1.0 / 60.0);
    // System.exit(0);
    // }

    @Override
    public void gameDraw(Graphics2D arg0) {
        nivelActual.Draw(arg0);
    }

    @Override
    public void gameShutdown() {
        // TODO Auto-generated method stub
    }

    @Override
    public void gameStartup() {
        // nivelActual.Start();
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

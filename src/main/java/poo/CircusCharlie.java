package poo;

import com.entropyinteractive.JGame;
import com.entropyinteractive.Keyboard;

//import com.entropyinteractive.JGame;

import java.awt.*;
import java.awt.event.*;

public class CircusCharlie extends JGame {
    Boolean pausa = false;
    CharlieNivel nivelActual;
    Jugador jugador1;

    public CircusCharlie() {
        super("CircusCharlie", 1024, 768);
        jugador1 = new Jugador();
        nivelActual = new CharlieNivel1();
    }

    @Override
    public void gameDraw(Graphics2D arg0) {

        arg0.setFont(new Font("Press Start 2P", Font.BOLD, 20));
        nivelActual.Draw(arg0);
        if (pausa)
            arg0.drawString("Pulse P!", getWidth() / 2, getHeight() / 2);
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
        if (!pausa)
            nivelActual.Update(arg0, keyboard, jugador1);

        if ((keyboard.isKeyPressed(KeyEvent.VK_ESCAPE))) {
            stop();
        }

        if ((keyboard.isKeyPressed(KeyEvent.VK_P))) {
            if (pausa)
                pausa = false;
            else
                pausa = true;
        }
    }

}

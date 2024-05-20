package poo;

import com.entropyinteractive.JGame;
import com.entropyinteractive.Keyboard;

//import com.entropyinteractive.JGame;

import java.awt.*;
import java.awt.event.*;

public class CircusCharlie extends JGame {

    CharlieNivel Nivel1 = new CharlieNivel();

    public CircusCharlie() {
        // call game constructor
        super("CircusCharlie", 1024, 768);

    }

    public static void main(String... z) {
        CircusCharlie jogo = new CircusCharlie();
        jogo.run(1.0 / 60.0);
        System.exit(0);
    }

    @Override
    public void gameDraw(Graphics2D arg0) {
        Nivel1.Draw(arg0);
    }

    @Override
    public void gameShutdown() {
        // TODO Auto-generated method stub
    }

    @Override
    public void gameStartup() {
        Nivel1.Start();
    }

    @Override
    public void gameUpdate(double arg0) {
        Keyboard keyboard = getKeyboard();

        Nivel1.Update(arg0, keyboard);
        if ((keyboard.isKeyPressed(KeyEvent.VK_ESCAPE))) {
            stop();
        }
    }

}

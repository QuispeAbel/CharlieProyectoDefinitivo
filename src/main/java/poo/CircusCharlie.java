package poo;

import javax.swing.*;

//import com.entropyinteractive.JGame;

import java.awt.*;
import java.awt.event.*;

public class CircusCharlie extends JFrame implements ActionListener {

    CharlieNivel Nivel1;

    public CircusCharlie() {
        setBackground(Color.ORANGE);
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            };
        };
        addWindowListener(l);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

    }

    public static void main(String... z) {
        new CircusCharlie();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}

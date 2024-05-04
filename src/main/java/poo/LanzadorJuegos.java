package poo;

/**
Compilar
javac -cp ".;bucleJuego.jar" LanzadorJuegos.java

Ejecutar
java -cp ".;bucleJuego.jar" LanzadorJuegos
  */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
//import javax.swing.event.*;

import com.entropyinteractive.*; //las librerias JJGame,JGameLoop,KeyBoard,Mouse,etc...

public class LanzadorJuegos extends JPanel implements ActionListener {

    JGame juego;
    Thread t;

    public LanzadorJuegos() {
        int filas = 0;
        int columnas = 3;
        int separacion = 10;

        this.setLayout(new GridLayout(filas, columnas, separacion, separacion));

        ImageIcon icono = new ImageIcon(getClass().getResource("imagenes/NES-gameplay.gif"));

        String[] arrEtiquetas = { "DemoJuego02", "DemoJuego03" };
        JButton boton;
        JButton boton1 = new JButton(icono);
        for (String etiqueta : arrEtiquetas) {

            boton = new JButton(etiqueta);

            boton.addActionListener(this);
            this.add(boton);
        }
        this.add(boton1);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("DemoJuego02")) {
            juego = new DemoJuego02();

            t = new Thread() {
                public void run() {
                    juego.run(1.0 / 60.0);
                }
            };

            t.start();
        }

        if (e.getActionCommand().equals("DemoJuego03")) {
            juego = new DemoJuego03();

            t = new Thread() {
                public void run() {
                    juego.run(1.0 / 60.0);
                }
            };

            t.start();
        }
    }

    public static void main(String... z) {
        JFrame f = new JFrame("Lanzador");

        f.add(new LanzadorJuegos());
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            };
        };

        f.addWindowListener(l);
        f.pack();
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }

}

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
    // ImageIcon icono1 = new
    // ImageIcon(getClass().getResource("imagenes/NES-gameplay.gif"));
    JButton boton2 = new JButton(new ImageIcon(getClass().getResource("imagenes/gif_circus_charlie.gif")));
    JButton boton3 = new JButton(new ImageIcon(getClass().getResource("imagenes/1_moc7ZNP8dWxjOmwj1GJ1YA.gif")));

    public LanzadorJuegos() {
        int filas = 0;
        int columnas = 2;
        int separacion = 10;

        this.setLayout(new GridLayout(filas, columnas, separacion, separacion));
        // boton2.setBorderPainted(false); // Elimina el borde del botón
        // boton2.setContentAreaFilled(false); // Hace que el área de contenido no se
        // pinte
        boton2.setBackground(Color.BLACK);
        boton2.addActionListener(this);

        // boton3.setBorderPainted(false); // Elimina el borde del botón
        // boton3.setContentAreaFilled(false); // Hace que el área de contenido no se
        // pinte
        boton3.setBackground(Color.BLACK);
        boton3.addActionListener(this);
        this.add(boton2);
        this.add(boton3);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == boton2) {
            juego = new DemoJuego02();

            t = new Thread() {
                public void run() {
                    juego.run(1.0 / 60.0);
                }
            };

            t.start();
        }

        if (e.getSource() == boton3) {
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

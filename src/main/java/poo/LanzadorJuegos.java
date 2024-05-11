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
    JButton boton2;
    JButton boton3;
    JPanel PanelDelCentro;
    JPanel PanelTitulo;
    Frame Config;

    public LanzadorJuegos() {
        int filas = 0;
        int columnas = 2;
        int separacion = 10;
        JFrame f = new JFrame("Lanzador");

        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        boton2 = new JButton(
                new ImageIcon(getClass().getResource(
                        "imagenes/1_moc7ZNP8dWxjOmwj1GJ1YA.gif")));
        boton3 = new JButton(
                new ImageIcon(getClass().getResource("imagenes/gif_circus_charlie.gif")));

        f.setLayout(new BorderLayout());

        PanelTitulo = new JPanel();// Panel para el titulo de la Ventana
        Color ColorFondo = new Color(7, 14, 17);
        PanelTitulo.setBackground(ColorFondo);
        PanelTitulo.add(new JLabel(new ImageIcon(getClass().getResource("imagenes/OIG3.jpg"))));

        PanelDelCentro = new JPanel();// PAnel con los botones
        PanelDelCentro.setBackground(ColorFondo);
        PanelDelCentro.setLayout(new GridLayout(filas, columnas, separacion, separacion));

        boton2.setBackground(Color.BLACK);// hago el fondo del boton negro
        boton2.addActionListener(this);

        boton3.setBackground(Color.BLACK);
        boton3.addActionListener(this);

        PanelDelCentro.add(boton2);
        PanelDelCentro.add(boton3);

        f.add(PanelDelCentro, BorderLayout.CENTER);
        f.add(PanelTitulo, BorderLayout.NORTH);

        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            };
        };

        // f.add(PanelDelCentro);

        f.addWindowListener(l);
        f.pack();
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == boton2) {
            juego = new Pong();

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
        new LanzadorJuegos();

        // f.add(new LanzadorJuegos()); creo el frame en el constructor para agregar el
        // menu
        /*
         * WindowListener l = new WindowAdapter() {
         * public void windowClosing(WindowEvent e) {
         * System.exit(0);
         * };
         * };
         */

        // f.addWindowListener(l);
        // f.pack();
        // f.setVisible(true);
        // f.setLocationRelativeTo(null);
    }

}

package poo;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.entropyinteractive.*; //las librerias JJGame,JGameLoop,KeyBoard,Mouse,etc...

public class LanzadorJuegos extends JFrame implements ActionListener {

    JGame juego;
    Thread t;
    JButton boton2;
    JButton boton3;
    JButton botonVolver;
    JPanel PanelDelCentro;
    JPanel PanelTitulo;
    JPanel PanelMenuCircus;
    Frame Config;
    ConfiguracionPong Confvid;
    Color ColorFondo = new Color(7, 14, 17);

    public LanzadorJuegos() {
        int filas = 0;
        int columnas = 2;
        int separacion = 10;

        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        boton2 = new JButton(
                new ImageIcon(getClass().getResource(
                        "imagenes/1_moc7ZNP8dWxjOmwj1GJ1YA.gif")));
        boton3 = new JButton(
                new ImageIcon(getClass().getResource("imagenes/gif_circus_charlie.gif")));

        setLayout(new BorderLayout());

        PanelTitulo = new JPanel();// Panel para el titulo de la Ventana
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

        add(PanelDelCentro, BorderLayout.CENTER);
        add(PanelTitulo, BorderLayout.NORTH);

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

    public void actionPerformed(ActionEvent e) {

        // String cmd = e.getActionCommand();

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
            /*
             * juego = new DemoCamaraHeroe();
             * 
             * t = new Thread() {
             * public void run() {
             * juego.run(1.0 / 60.0);
             * }
             * };
             * 
             * t.start();
             */

            remove(PanelDelCentro);
            Confvid = new ConfiguracionPong();
            botonVolver = new JButton("volver");
            botonVolver.setBackground(ColorFondo);
            botonVolver.setForeground(Color.WHITE);
            botonVolver.addActionListener(this);
            add(Confvid, BorderLayout.CENTER);
            add(botonVolver, BorderLayout.SOUTH);

            validate();
            repaint();
        }

        if (e.getSource() == botonVolver) {
            remove(Confvid);
            remove(botonVolver);
            add(PanelDelCentro, BorderLayout.CENTER);

            validate();
            repaint();
        }
    }

    public static void main(String... z) {
        new LanzadorJuegos();
    }

}
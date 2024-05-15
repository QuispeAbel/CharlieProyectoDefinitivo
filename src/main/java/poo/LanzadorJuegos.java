package poo;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.entropyinteractive.*; //las librerias JJGame,JGameLoop,KeyBoard,Mouse,etc...

public class LanzadorJuegos extends JFrame implements ActionListener {

    JGame juego;
    Thread t;
    JButton boton2, jugarButton, resetButton, boton3, botonVolver;
    JPanel PanelDelCentro, PanelTitulo, PanelMenuCircus, CabeceraCharile, PanelBotones;
    Frame Config;
    ConfiguracionPong Confvid;
    Color ColorFondo = new Color(7, 14, 17);
    int Juegoseleccionado;

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

        CabeceraCharile = new JPanel();
        CabeceraCharile.setBackground(ColorFondo);
        CabeceraCharile.add(new JLabel(new ImageIcon(getClass().getResource("imagenes/FondoCharlie1.jpg"))));

        PanelDelCentro = new JPanel();// PAnel con los botones
        PanelDelCentro.setBackground(ColorFondo);
        PanelDelCentro.setLayout(new GridLayout(filas, columnas, separacion, separacion));

        boton2.setBackground(Color.BLACK);// hago el fondo del boton negro
        boton2.addActionListener(this);

        boton3.setBackground(Color.BLACK);
        boton3.addActionListener(this);

        botonVolver = new JButton("volver");
        botonVolver.setBackground(ColorFondo);
        botonVolver.setForeground(Color.WHITE);
        botonVolver.addActionListener(this);

        jugarButton = new JButton("Jugar");
        jugarButton.setBackground(ColorFondo);
        jugarButton.setForeground(Color.WHITE);
        jugarButton.addActionListener(this);
        add(jugarButton);

        resetButton = new JButton("Reset");
        resetButton.setBackground(ColorFondo);
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(this);
        add(resetButton);

        PanelBotones = new JPanel();
        PanelBotones.setLayout(new GridLayout(1, 3));
        PanelBotones.add(jugarButton);
        PanelBotones.add(resetButton);
        PanelBotones.add(botonVolver);

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

        if (e.getSource() == boton2) {
            Juegoseleccionado = 1;
            remove(PanelDelCentro);
            Confvid = new ConfiguracionPong();
            add(Confvid, BorderLayout.CENTER);
            add(PanelBotones, BorderLayout.SOUTH);
            // Confvid.juegoSeleccionado(Juegoseleccionado);

            validate();
            repaint();

        }

        if (e.getSource() == boton3) {
            Juegoseleccionado = 2;
            remove(PanelDelCentro);

            Confvid = new ConfiguracionPong();
            // add(CabeceraCharile, BorderLayout.NORTH);
            add(Confvid, BorderLayout.CENTER);
            add(PanelBotones, BorderLayout.SOUTH);
            // Confvid.juegoSeleccionado(Juegoseleccionado);

            validate();
            repaint();

        }

        if (e.getSource() == botonVolver) {
            remove(Confvid);
            remove(PanelBotones);
            // remove(CabeceraCharile);
            add(PanelTitulo, BorderLayout.NORTH);
            add(PanelDelCentro, BorderLayout.CENTER);

            validate();
            repaint();
        }

        if (e.getSource() == jugarButton) {
            lanzar(Juegoseleccionado);
        }

        if (e.getSource() == resetButton) {
            Confvid.resetConfiguracion();
        }

    }

    public void lanzar(int JuegoSel) {
        if (JuegoSel == 1) {
            Pong juego = new Pong("imagenes/pelota.jpg", Confvid.SkinJ1(), Confvid.SkinJ2());

            t = new Thread() {
                public void run() {
                    juego.run(1.0 / 60.0);
                }
            };

            t.start();
        }

        if (JuegoSel == 2) {
            CharlieNivel juego = new CharlieNivel();

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
    }

}
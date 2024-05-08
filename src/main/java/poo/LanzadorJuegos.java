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
        String[] arrComponentes = { "circus charlie", "pong" };
        JFrame f = new JFrame("Lanzador");

        /* Creacion de la Barra de Menu y los items de menu */
        MenuBar mBar = new MenuBar();
        f.setMenuBar(mBar);

        Menu menuArchivo = new Menu("Archivo");
        Menu menuComponentes = new Menu("juegos");

        mBar.add(menuArchivo);
        menuArchivo.add(new MenuItem("Salir"));
        menuArchivo.addActionListener(this);
        menuArchivo.add(new MenuItem("configuracion"));

        // Menu Componentes
        mBar.add(menuComponentes);
        for (String compo : arrComponentes) {
            MenuItem menuItem = new MenuItem(compo);
            menuComponentes.add(menuItem);
        }
        menuComponentes.addActionListener(this);

        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        boton2 = new JButton(
                new ImageIcon(getClass().getResource(
                        "imagenes/gif_circus_charlie.gif")));
        boton3 = new JButton(
                new ImageIcon(getClass().getResource("imagenes/1_moc7ZNP8dWxjOmwj1GJ1YA.gif")));

        setLayout(new BorderLayout());

        PanelTitulo = new JPanel();// Panel para el titulo de la Ventana
        PanelTitulo.add(new JLabel("SELECCIONE SU JUEGO"));

        PanelDelCentro = new JPanel();// PAnel con los botones
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

        f.add(PanelDelCentro);

        f.addWindowListener(l);
        f.pack();
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        if (cmd == "Salir") {
            System.exit(0); // Sale del programita
        }

        switch (cmd) {
            case "circus charlie":
                juego = new Pong();
                t = new Thread() {
                    public void run() {
                        juego.run(1.0 / 60.0);
                    }
                };
                t.start();
                break;
            case "pong":
                juego = new DemoJuego03();

                t = new Thread() {
                    public void run() {
                        juego.run(1.0 / 60.0);
                    }
                };
                t.start();
                break;
            case "configuracion":
                System.out.println("configuracion");
                Config = new Frame("configuracion");
                Config.setSize(640, 480);
                Config.setVisible(true);

                JPanel teclas = new JPanel();
                teclas.setLayout(new GridLayout(0, 2, 10, 10));
                teclas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                Label avanzar = new Label("avanzar:");
                Label retroceder = new Label("retroceder:");
                Label arriba = new Label("arriba:");
                Label abajo = new Label("abajo:");
                Label saltar = new Label("saltar:");

                teclas.add(avanzar);
                teclas.add(new TextField("d"));
                teclas.add(retroceder);
                teclas.add(new TextField("a"));
                teclas.add(arriba);
                teclas.add(new TextField("w"));
                teclas.add(abajo);
                teclas.add(new TextField("s"));
                teclas.add(saltar);
                teclas.add(new TextField("space"));

                Config.add(teclas);
                Config.pack();
                Config.setLocationRelativeTo(null);

                Config.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent windowEvent) {
                        Config.setVisible(false);
                    }
                });

                break;
        }

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

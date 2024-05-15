package poo;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ConfiguracionPong extends ConfiguracionVideoJuegos {
    protected JCheckBox sonidoCheckBox;
    protected JComboBox<String> pistaMusicalComboBox, personajeComboBox, ModoDeJuegoComboBox, personaje2ComboBox,
            TeclasJ1ComboBoxBajar, TeclasJ1ComboBoxSubir, TeclasJ2ComboBoxSubir, TeclasJ2ComboBoxBajar;
    protected JTextField teclaEfectosTextField, teclaMusicaTextField, teclaPausaTextField,
            teclaIzquierdaTextField, teclaDerechaTextField, teclaArribaTextField,
            teclaAbajoTextField, teclaSaltoTextField, teclaInicioTextField;
    protected Color ColorFondo = new Color(7, 14, 17);
    protected JLabel modojuego, sonidogeneral, SonidoFX, musicaFondo, PausarReanudar, MoverIzquierda, MoverDerecha,
            moverAbajo, moverAbajo2, moverArriba2,
            moverArriba, IniciarJuego, SelecPistaMusical, selecPersonaje, selecPersonaje2;

    public ConfiguracionPong() {

        setBackground(ColorFondo);
        setLayout(new GridLayout(0, 2, 0, 0));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        modojuego = new JLabel("Modo de Juego:");
        modojuego.setForeground(Color.WHITE);
        add(modojuego);

        String[] modos = { "Ventana", "Pantalla Completa" };
        ModoDeJuegoComboBox = new JComboBox<>(modos);
        ModoDeJuegoComboBox.setBackground(Color.WHITE);
        add(ModoDeJuegoComboBox);

        sonidogeneral = new JLabel("Sonido General:");
        sonidogeneral.setForeground(Color.WHITE);
        add(sonidogeneral);

        sonidoCheckBox = new JCheckBox("Activado", true);
        sonidoCheckBox.setBackground(ColorFondo);
        sonidoCheckBox.setForeground(Color.WHITE);
        add(sonidoCheckBox);

        /*
         * MoverIzquierda = new JLabel("Tecla para mover hacia la izquierda (←):");
         * MoverIzquierda.setForeground(Color.WHITE);
         * add(MoverIzquierda);
         * 
         * teclaIzquierdaTextField = new JTextField("←");
         * teclaIzquierdaTextField.setBackground(Color.WHITE);
         * add(teclaIzquierdaTextField);
         * 
         * MoverDerecha = new JLabel("Tecla para mover hacia la derecha (→):");
         * MoverDerecha.setForeground(Color.WHITE);
         * add(MoverDerecha);
         * teclaDerechaTextField = new JTextField("→");
         * teclaDerechaTextField.setBackground(Color.WHITE);
         * add(teclaDerechaTextField);
         */

        moverArriba = new JLabel("Tecla para mover hacia arriba J1:");
        moverArriba.setForeground(Color.WHITE);
        add(moverArriba);

        String[] TeclasSubir = { "↑", "w" };
        TeclasJ1ComboBoxSubir = new JComboBox<>(TeclasSubir);
        TeclasJ1ComboBoxSubir.setBackground(Color.WHITE);
        add(TeclasJ1ComboBoxSubir);

        moverAbajo = new JLabel("Tecla para mover hacia abajo J1:");
        add(moverAbajo);
        moverAbajo.setForeground(Color.WHITE);

        String[] TeclasBajar = { "↓", "s" };
        TeclasJ1ComboBoxBajar = new JComboBox<>(TeclasBajar);
        TeclasJ1ComboBoxBajar.setBackground(Color.WHITE);
        add(TeclasJ1ComboBoxBajar);

        moverArriba2 = new JLabel("Tecla para mover hacia arriba J2:");
        moverArriba2.setForeground(Color.WHITE);
        add(moverArriba2);

        String[] TeclasSubir2 = { "w", "↑" };
        TeclasJ2ComboBoxSubir = new JComboBox<>(TeclasSubir2);
        TeclasJ2ComboBoxSubir.setBackground(Color.WHITE);
        add(TeclasJ2ComboBoxSubir);

        moverAbajo2 = new JLabel("Tecla para mover hacia abajo J2:");
        add(moverAbajo2);
        moverAbajo2.setForeground(Color.WHITE);

        String[] TeclasBajar2 = { "s", "↓" };
        TeclasJ2ComboBoxBajar = new JComboBox<>(TeclasBajar2);
        TeclasJ2ComboBoxBajar.setBackground(Color.WHITE);
        add(TeclasJ2ComboBoxBajar);
        /*
         * TeclaSaltar = new JLabel("Tecla para saltar (X):");
         * TeclaSaltar.setForeground(Color.WHITE);
         * add(TeclaSaltar);
         * teclaSaltoTextField = new JTextField("X");
         * teclaSaltoTextField.setBackground(Color.WHITE);
         * add(teclaSaltoTextField);
         */

        SelecPistaMusical = new JLabel("Pista Musical:");
        SelecPistaMusical.setForeground(Color.WHITE);
        add(SelecPistaMusical);

        String[] pistasMusicales = { "Original", "Pista 1", "Pista 2", "Pista 3" };
        pistaMusicalComboBox = new JComboBox<>(pistasMusicales);
        pistaMusicalComboBox.setBackground(Color.WHITE);
        add(pistaMusicalComboBox);

        String[] personajes = { "Original", "Argentina", "Brazil" };

        selecPersonaje = new JLabel("Personaje J1:");
        selecPersonaje.setForeground(Color.WHITE);
        add(selecPersonaje);
        personajeComboBox = new JComboBox<>(personajes);
        personajeComboBox.setBackground(Color.WHITE);
        add(personajeComboBox);

        selecPersonaje2 = new JLabel("Personaje J2:");
        selecPersonaje2.setForeground(Color.WHITE);
        add(selecPersonaje2);
        personaje2ComboBox = new JComboBox<>(personajes);
        personaje2ComboBox.setBackground(Color.WHITE);
        add(personaje2ComboBox);
    }

    public void resetConfiguracion() {
        // Implementa aquí la lógica para resetear la configuración
        ModoDeJuegoComboBox.setSelectedIndex(0);
        sonidoCheckBox.setSelected(true);

        TeclasJ1ComboBoxSubir.setSelectedIndex(0);
        TeclasJ1ComboBoxBajar.setSelectedIndex(0);

        TeclasJ2ComboBoxSubir.setSelectedIndex(0);
        TeclasJ2ComboBoxBajar.setSelectedIndex(0);

        pistaMusicalComboBox.setSelectedIndex(0);

        personajeComboBox.setSelectedIndex(0);
        personaje2ComboBox.setSelectedIndex(0);

        JOptionPane.showMessageDialog(this, "Configuración reseteada");
    }

    public String SkinJ1() {
        String direccion = "imagenes/paleta.jpg";

        if (personajeComboBox.getSelectedItem() == "Brazil") {
            direccion = "imagenes/Brasil.jpg";
        }

        if (personajeComboBox.getSelectedItem() == "Argentina") {
            direccion = "imagenes/Argentina.jpg";
        }

        return direccion;
    }

    public String SkinJ2() {
        String direccion = "imagenes/paleta.jpg";

        if (personaje2ComboBox.getSelectedItem() == "Brazil") {
            direccion = "imagenes/Brasil.jpg";
        }

        if (personaje2ComboBox.getSelectedItem() == "Argentina") {
            direccion = "imagenes/Argentina.jpg";
        }

        return direccion;
    }

    public static void main(String[] args) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}

package poo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//import javafx.applicaton.Application;

public class ConfiguracionVideojuego extends JPanel implements ActionListener {
    protected JRadioButton ventanaRadioButton, pantallaCompletaRadioButton;
    protected JCheckBox sonidoCheckBox;
    protected JComboBox<String> pistaMusicalComboBox, personajeComboBox;
    protected JTextField teclaEfectosTextField, teclaMusicaTextField, teclaPausaTextField,
            teclaIzquierdaTextField, teclaDerechaTextField, teclaArribaTextField,
            teclaAbajoTextField, teclaSaltoTextField, teclaInicioTextField;
    protected JButton guardarButton, resetButton;
    protected Color ColorFondo = new Color(7, 14, 17);
    protected JLabel modojuego, sonidogeneral, SonidoFX, musicaFondo, PausarReanudar, MoverIzquierda, MoverDerecha,
            moverAbajo,
            moverArriba, TeclaSaltar, IniciarJuego, SelecPistaMusical, selecPersonaje;

    public ConfiguracionVideojuego() {

        setBackground(ColorFondo);
        setLayout(new GridLayout(0, 2, 0, 0));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        modojuego = new JLabel("Modo de Juego:");
        modojuego.setForeground(Color.WHITE);
        add(modojuego);

        JPanel modoJuegoPanel = new JPanel();
        modoJuegoPanel.setLayout(new GridLayout(1, 2));
        modoJuegoPanel.setBackground(ColorFondo);

        ventanaRadioButton = new JRadioButton("Ventana", true);
        ventanaRadioButton.setBackground(ColorFondo);
        ventanaRadioButton.setForeground(Color.WHITE);

        pantallaCompletaRadioButton = new JRadioButton("Pantalla Completa");
        pantallaCompletaRadioButton.setBackground(ColorFondo);
        pantallaCompletaRadioButton.setForeground(Color.WHITE);

        ButtonGroup modoJuegoGroup = new ButtonGroup();
        modoJuegoGroup.add(ventanaRadioButton);
        modoJuegoGroup.add(pantallaCompletaRadioButton);
        modoJuegoPanel.add(ventanaRadioButton);
        modoJuegoPanel.add(pantallaCompletaRadioButton);
        add(modoJuegoPanel);

        sonidogeneral = new JLabel("Sonido General:");
        sonidogeneral.setForeground(Color.WHITE);
        add(sonidogeneral);

        sonidoCheckBox = new JCheckBox("Activado", true);
        sonidoCheckBox.setBackground(ColorFondo);
        sonidoCheckBox.setForeground(Color.WHITE);
        add(sonidoCheckBox);

        SonidoFX = new JLabel("Sonido FX");
        SonidoFX.setForeground(Color.WHITE);
        add(SonidoFX);

        teclaEfectosTextField = new JTextField("q");
        teclaEfectosTextField.setBackground(Color.gray);
        add(teclaEfectosTextField);

        musicaFondo = new JLabel("Tecla para música de fondo (w):");
        musicaFondo.setForeground(Color.WHITE);
        add(musicaFondo);

        teclaMusicaTextField = new JTextField("w");
        teclaMusicaTextField.setBackground(Color.GRAY);
        add(teclaMusicaTextField);

        PausarReanudar = new JLabel("Tecla para pausar/reanudar el juego (Barra espaciadora):");
        PausarReanudar.setForeground(Color.WHITE);
        add(PausarReanudar);

        teclaPausaTextField = new JTextField("Espacio");
        teclaPausaTextField.setBackground(Color.GRAY);
        add(teclaPausaTextField);

        MoverIzquierda = new JLabel("Tecla para mover hacia la izquierda (←):");
        MoverIzquierda.setForeground(Color.WHITE);
        add(MoverIzquierda);

        teclaIzquierdaTextField = new JTextField("←");
        teclaIzquierdaTextField.setBackground(Color.GRAY);
        add(teclaIzquierdaTextField);

        MoverDerecha = new JLabel("Tecla para mover hacia la derecha (→):");
        MoverDerecha.setForeground(Color.WHITE);
        add(MoverDerecha);
        teclaDerechaTextField = new JTextField("→");
        teclaDerechaTextField.setBackground(Color.GRAY);
        add(teclaDerechaTextField);

        moverArriba = new JLabel("Tecla para mover hacia arriba (↑):");
        moverArriba.setForeground(Color.WHITE);
        add(moverArriba);
        teclaArribaTextField = new JTextField("↑");
        teclaArribaTextField.setBackground(Color.GRAY);
        add(teclaArribaTextField);

        moverAbajo = new JLabel("Tecla para mover hacia abajo (↓):");
        add(moverAbajo);
        moverAbajo.setForeground(Color.WHITE);
        teclaAbajoTextField = new JTextField("↓");
        teclaAbajoTextField.setBackground(Color.GRAY);
        add(teclaAbajoTextField);

        TeclaSaltar = new JLabel("Tecla para saltar (X):");
        TeclaSaltar.setForeground(Color.WHITE);
        add(TeclaSaltar);
        teclaSaltoTextField = new JTextField("X");
        teclaSaltoTextField.setBackground(Color.GRAY);
        add(teclaSaltoTextField);

        IniciarJuego = new JLabel("Tecla para iniciar el juego (Enter):");
        IniciarJuego.setForeground(Color.WHITE);
        add(IniciarJuego);
        teclaInicioTextField = new JTextField("Enter");
        teclaInicioTextField.setBackground(Color.GRAY);
        add(teclaInicioTextField);

        SelecPistaMusical = new JLabel("Pista Musical:");
        SelecPistaMusical.setForeground(Color.WHITE);
        add(SelecPistaMusical);
        String[] pistasMusicales = { "Tema Original", "Pista 1", "Pista 2", "Pista 3" };
        pistaMusicalComboBox = new JComboBox<>(pistasMusicales);
        pistaMusicalComboBox.setBackground(Color.GRAY);
        add(pistaMusicalComboBox);

        selecPersonaje = new JLabel("Personaje:");
        selecPersonaje.setForeground(Color.WHITE);
        add(selecPersonaje);
        String[] personajes = { "Original", "Personaje 1", "Personaje 2", "Personaje 3" };
        personajeComboBox = new JComboBox<>(personajes);
        personajeComboBox.setBackground(Color.GRAY);
        add(personajeComboBox);

        guardarButton = new JButton("Jugar");
        guardarButton.setBackground(ColorFondo);
        guardarButton.setForeground(Color.WHITE);
        guardarButton.addActionListener(this);
        add(guardarButton);

        resetButton = new JButton("Reset");
        resetButton.setBackground(ColorFondo);
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(e -> resetConfiguracion());
        add(resetButton);
    }

    private void resetConfiguracion() {
        // Implementa aquí la lógica para resetear la configuración
        ventanaRadioButton.setSelected(true);
        sonidoCheckBox.setSelected(true);
        teclaEfectosTextField.setText("q");
        teclaMusicaTextField.setText("w");
        teclaPausaTextField.setText("Espacio");
        teclaIzquierdaTextField.setText("←");
        teclaDerechaTextField.setText("→");
        teclaArribaTextField.setText("↑");
        teclaAbajoTextField.setText("↓");
        teclaSaltoTextField.setText("X");
        teclaInicioTextField.setText("Enter");
        pistaMusicalComboBox.setSelectedIndex(0);
        personajeComboBox.setSelectedIndex(0);
        JOptionPane.showMessageDialog(this, "Configuración reseteada");
    }

    public static void main(String[] args) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guardarButton){

        }
    }
}

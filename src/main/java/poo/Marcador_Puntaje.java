package poo;

import java.awt.Graphics2D;

import java.awt.font.*;

import java.awt.Color; // Import java.awt.Color for use with Graphics2D

public class Marcador_Puntaje extends ObjetoGrafico{
    int nro_jugador_actual=0;

    public Marcador_Puntaje(String filename) {
		super(filename);
	}

    public void setNroJugador(Jugador j){
        nro_jugador_actual = j.getNúmero();
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.drawString( Integer.toString(nro_jugador_actual) , 10,30);
    }
}

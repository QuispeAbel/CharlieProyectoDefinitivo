package poo;

public class Jugador {
    int Puntos = 0;

    Jugador() {
    }

    public int getPuntos() {
        return Puntos;
    }

    public void setPuntos(int puntos) {
        Puntos = puntos;
    }

    public void sumarPunto() {
        this.Puntos++;
    }
}

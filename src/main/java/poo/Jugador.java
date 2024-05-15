package poo;

public class Jugador {
    int Puntos = 0;
    int número = 0;

    Jugador() {
        número++;
    }

    public int getNúmero(){
        return número;
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

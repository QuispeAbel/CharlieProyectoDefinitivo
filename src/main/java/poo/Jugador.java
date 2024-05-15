package poo;

abstract public class Jugador {
    int PuntosTotales = 0;
    int número = 0;

    Jugador(){
        número++;
    }

    public int getNúmero(){
        return número;
    }

    public int getPuntos() {
        return PuntosTotales;
    }

    public void setPuntos(int puntos) {
        PuntosTotales = puntos;
    }

    abstract void sumarPuntos();
}

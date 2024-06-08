package poo;

public class Jugador {
    int PuntosTotales = 0;
    int vidas = 3;
    int número = 0;

    Jugador() {
        número++;
    }

    public int getNúmero() {
        return número;
    }

    public int getPuntos() {
        return PuntosTotales;
    }

    public void setPuntos(int puntos) {
        PuntosTotales = puntos;
    }

    public void sumarPuntos() {
        PuntosTotales++;
    }

    public void sumarPuntos(int puntos) {
        PuntosTotales = PuntosTotales + puntos;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
}

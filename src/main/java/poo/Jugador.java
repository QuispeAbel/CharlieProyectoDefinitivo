package poo;

public class Jugador {
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

    public void sumarPuntos(){
        PuntosTotales++;
    }

    public void sumarPuntos(int puntos){
        PuntosTotales = PuntosTotales + puntos;
    }
}

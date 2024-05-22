package poo;

import java.awt.Graphics2D;

public class Marcador_Puntaje extends ObjetoGrafico {
    int nro_jugador_actual = 0;
    int PuntajeTotal = 0;
    int record = 38000;

    public Marcador_Puntaje(String filename) {
        super(filename);
    }

    public void setNroJugador(Jugador j) {
        nro_jugador_actual = j.getNúmero();
    }

    public void getPuntajeTotal(Jugador j) {
        PuntajeTotal = j.getPuntos();
    }

    public void getHi (){
        record = 38000; //Acceder al Archivo del record (es provisorio)
    }

    public void draw(Graphics2D g){
        g.drawString(Integer.toString(nro_jugador_actual)+"P- ", 50, 78);
        g.drawString(Integer.toString(PuntajeTotal), 112, 78);
        g.drawString("HI-"+Integer.toString(record), 500, 78);
    }
}

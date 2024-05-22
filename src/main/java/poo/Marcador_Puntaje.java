package poo;

import java.awt.Graphics2D;

public class Marcador_Puntaje extends ObjetoGrafico {
    int nro_jugador_actual = 1;
    int PuntajeTotal = 0;
    int record = 38000;
    int stage = 01;

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

    public void getStage(){
        stage = 0; //cambiar luego
    }

    public void draw(Graphics2D g){
        g.drawString(Integer.toString(nro_jugador_actual)+"P- ", 50, 78);
        g.drawString(Integer.toString(PuntajeTotal), 112, 78);
        g.drawString("HI-"+Integer.toString(record), 450, 78);
        g.drawString("STAGE-0"+Integer.toString(stage), 820, 78);
    }
}

package poo;

import java.awt.Color;
import java.awt.Graphics2D;

public class Marcador_Puntaje extends ObjetoGrafico {
    int nro_jugador_actual = 1;
    int PuntajeTotal = 0;
    int record = 17800;
    int stage = 1;
    int tiempo = 5000;
    int vidas = 3;

    public Marcador_Puntaje(String filename) {
        super(filename);
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public void setNroJugador(Jugador j) {
        nro_jugador_actual = j.getNúmero();
    }

    public void getPuntajeTotal(Jugador j) {
        PuntajeTotal = j.getPuntos();
    }

    public void getHi() {
        record = 17800; // Acceder al Archivo del record (es provisorio)
    }

    public void getStage() {
        stage = 0; // cambiar luego
    }

    public void pasaTiempo(){
        tiempo = tiempo - 10;
    }

    public int getTiempo(){
        return tiempo;
    }

    public void setVidas(Jugador j){
        vidas = j.getVidas();
    }

    public void draw(Graphics2D g) {
        super.draw(g);
        g.setColor(Color.white);
        g.drawString(Integer.toString(nro_jugador_actual) + "P- ", 50, 78);
        g.drawString(Integer.toString(PuntajeTotal), 112, 78);
        g.drawString("HI-" + Integer.toString(record), 450, 78);
        g.drawString("STAGE-0" + Integer.toString(stage), 820, 78);
        g.drawString("-" + Integer.toString(tiempo), 555, 98);
        g.drawString("Vidas:"+Integer.toString(vidas) , 820, 98);
        g.setColor(Color.decode("#C3013B"));
        g.drawString("BONUS" , 450, 98);
    }
}

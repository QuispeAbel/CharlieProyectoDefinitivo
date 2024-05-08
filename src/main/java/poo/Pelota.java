package poo;

public class Pelota extends ObjetoGrafico {
    private int velocidadPelota = 10;
    
    
    public void moverPelota(){
        super.setX(getX()+velocidadPelota);
        super.setY(getY()+velocidadPelota);
    }
}

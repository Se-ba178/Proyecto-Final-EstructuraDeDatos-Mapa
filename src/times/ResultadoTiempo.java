package times;

import java.util.*;

import mapa.PuntoMapa;

public class ResultadoTiempo {
    private List<PuntoMapa> ruta;
    private double tiempo;
    private int aristas;

    public ResultadoTiempo(List<PuntoMapa> ruta, double tiempo) {//inicializa el resultado con la ruta el tiempo y el numero de aristas
        this.ruta = ruta;
        this.tiempo = tiempo;
        this.aristas = (ruta.size() > 1) ? ruta.size() - 1 : 0;
    }
    // devuelve la ruta encontrada
    public List<PuntoMapa> getRuta() {
        return ruta;
    }
    // devuelve l tiempo de ejecucion
    public double getTiempo() {
        return tiempo;
    }
    //devuelve el numero de aristasencontradas
    public int getAristas(){
        return aristas;
    }
}

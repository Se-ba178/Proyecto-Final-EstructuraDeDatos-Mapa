package times;

import java.util.*;

import mapa.PuntoMapa;

public class ResultadoTiempo {
    private List<PuntoMapa> ruta;
    private double tiempo;
    private int aristas;

    public ResultadoTiempo(List<PuntoMapa> ruta, double tiempo) {
        this.ruta = ruta;
        this.tiempo = tiempo;
        this.aristas = (ruta.size() > 1) ? ruta.size() - 1 : 0;
    }

    public List<PuntoMapa> getRuta() {
        return ruta;
    }

    public double getTiempo() {
        return tiempo;
    }
    public int getAristas(){
        return aristas;
    }
}

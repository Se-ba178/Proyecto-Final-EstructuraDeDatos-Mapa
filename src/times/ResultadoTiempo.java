package times;

import java.util.*;

import mapa.PuntoMapa;

public class ResultadoTiempo {
    private List<PuntoMapa> ruta;
    private double tiempo;

    public ResultadoTiempo(List<PuntoMapa> ruta, double tiempo) {
        this.ruta = ruta;
        this.tiempo = tiempo;
    }

    public List<PuntoMapa> getRuta() {
        return ruta;
    }

    public double getTiempo() {
        return tiempo;
    }
}

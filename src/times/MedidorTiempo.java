package times;

import mapa.PuntoMapa;
import structures.node.graphs.Graph;
import structures.node.graphs.PathFinder;
import structures.node.graphs.PathResult;

public class MedidorTiempo {
    
    public static ResultadoTiempo ejecutar(PathFinder<PuntoMapa> algoritmo,Graph<PuntoMapa> grafo, PuntoMapa inicio, PuntoMapa fin) {
        //ejecuta el algoritmo y registra el tiempo empleado
        long inicioTiempo = System.nanoTime();
        PathResult<PuntoMapa> resultado = algoritmo.find(grafo, inicio, fin);
        long finTiempo = System.nanoTime();
        double tiempoMs = (finTiempo - inicioTiempo) / 1_000_000.0;
        return new ResultadoTiempo(resultado.getPath(), tiempoMs);
    }
    
}

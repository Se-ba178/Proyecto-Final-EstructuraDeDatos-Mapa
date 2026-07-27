package persistence;

import java.io.IOException;

import mapa.MapPanel;
import mapa.PuntoMapa;
import structures.node.graphs.Graph;

public interface GraphRepository {
    void guardar(String archivo,
                 Graph<PuntoMapa> grafo,
                 MapPanel mapa) throws IOException;

    Graph<PuntoMapa> cargar(String archivo,
                            MapPanel mapa) throws IOException;

}
    


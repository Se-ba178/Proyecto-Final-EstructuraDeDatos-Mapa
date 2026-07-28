package persistence;

import java.io.IOException;

import mapa.MapPanel;
import mapa.PuntoMapa;
import structures.node.graphs.Graph;

public interface GraphRepository { 
    //Guarda el grafo en un archivo
    void guardar(String archivo,Graph<PuntoMapa> grafo, MapPanel mapa) throws IOException;
    //carga un grafo desde un archivo
    Graph<PuntoMapa> cargar(String archivo, MapPanel mapa) throws IOException;

}
package controllers;

import mapa.PuntoMapa;
import structures.node.graphs.Graph;

public class MapController {
    //atributo de tipo grafo de la clase punto mapa 
    private Graph<PuntoMapa> graph;

    public MapController(Graph<PuntoMapa> graph) {
        this.graph = graph;
    }
    //añadir punto
    public void addPoint(PuntoMapa node){
        graph.add(node);
    }
    //conectar el punto
    public void connectPoint(PuntoMapa p1, PuntoMapa p2){
        graph.addEdge(p1, p2);
    }

    public Graph<PuntoMapa> getGraph() {
        return graph;
    }
    
}

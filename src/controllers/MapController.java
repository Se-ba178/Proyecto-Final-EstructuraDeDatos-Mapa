package controllers;

import mapa.PuntoMapa;
import structures.node.graphs.Graph;
import structures.node.node.Node;

public class MapController {
    private Graph<PuntoMapa> graph;

    public MapController(Graph<PuntoMapa> graph) {
        this.graph = graph;
    }
    
    public void addPoint(PuntoMapa node){
        graph.add(node);
        
        
    }
    public void connectPoint(PuntoMapa p1, PuntoMapa p2){
        graph.addEdge(p1, p2);

    }

    public Graph<PuntoMapa> getGraph() {
        return graph;
    }
    
}

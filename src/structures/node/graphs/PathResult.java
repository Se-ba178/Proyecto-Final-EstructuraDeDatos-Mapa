package structures.node.graphs;

import java.util.Set;

/**
 * PathResult
 */
public class PathResult<T> {
    private final Set<T> visitados;
    private final Set<T> camino;


    public PathResult(Set<T> visitados, Set<T> camino) {
        this.visitados = visitados;
        this.camino = camino;
    }


    public Set<T> getVisitados() {
        return visitados;
    }


    public Set<T> getPath() {
        return camino;
    }


    @Override
    public String toString() {
        return "PathResult [\n visitados=" + visitados + 
        (!camino.isEmpty() 
        ?  "Path= " + camino
        :" \n No se encontro camino entre los nodos ");
    }

    

    



}

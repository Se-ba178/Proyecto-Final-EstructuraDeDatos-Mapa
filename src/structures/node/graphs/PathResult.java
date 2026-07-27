package structures.node.graphs;
import java.util.*;

/**
 * PathResult
 */
public class PathResult<T> {
    private final Set<T> visitados;
    private final List<T> camino;


    public PathResult(Set<T> visitados, List<T> camino) {
        this.visitados = visitados;
        this.camino = camino;
    }


    public Set<T> getVisitados() {
        return visitados;
    }


    public List<T> getPath() {
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

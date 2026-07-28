package structures.node.graphs.algoritmos;

import java.util.*;

import structures.node.graphs.Graph;
import structures.node.graphs.PathFinder;
import structures.node.graphs.PathResult;
import structures.node.node.Node;

public class DFSPathFinder<T> implements PathFinder<T>{

    @Override
    public PathResult<T> find(Graph<T> graph, T start, T end) {//Encuentra un camino usando la busqueda DFS visto en clase
        Stack<T> pila = new Stack<>();
        Set<T> visitados = new HashSet<>();
        Map<T, T> padre = new HashMap<>();
        pila.push(start);
        while (!pila.isEmpty()) {
            T actual = pila.pop();
            if (!visitados.contains(actual)) {
                visitados.add(actual);
                if (actual.equals(end)) {
                    break;
                }
                for (Node<T> vecino : graph.getVecinos(actual)) {
                    T datoVecino = vecino.getDatos();
                    if (!visitados.contains(datoVecino)) {
                        padre.put(datoVecino, actual);
                        pila.push(datoVecino);
                    }
                }
            }
        }
        List<T> camino = reconstruirCamino(padre, start, end);
        return new PathResult<>(visitados, camino);
    }

    private List<T> reconstruirCamino(Map<T, T> padre, T inicio, T fin) {// sirve para reconstruir el camino desde el nodo incial hasta el nodo final
        List<T> camino = new ArrayList<>();
        if (!inicio.equals(fin) && !padre.containsKey(fin)) {
            return camino;
        }
        T actual = fin;
        while (actual != null) {
            camino.add(0, actual);
            actual = padre.get(actual);
        }
        return camino;
    }
    
}

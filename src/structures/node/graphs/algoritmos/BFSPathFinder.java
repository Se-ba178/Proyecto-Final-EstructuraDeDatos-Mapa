package structures.node.graphs.algoritmos;

import java.util.*;

import structures.node.graphs.Graph;
import structures.node.graphs.PathFinder;
import structures.node.graphs.PathResult;
import structures.node.node.Node;

public class BFSPathFinder<T> implements PathFinder<T> {

    @Override
    public PathResult<T> find(Graph<T> graph, T start, T end) {

        Queue<T> cola = new LinkedList<>();
        Set<T> visitados = new HashSet<>();
        Map<T, T> padre = new HashMap<>();

        cola.offer(start);
        visitados.add(start);

        while (!cola.isEmpty()) {

            T actual = cola.poll();

            if (actual.equals(end)) {
                break;
            }

            for (Node<T> vecino : graph.getVecinos(actual)) {

                T datoVecino = vecino.getDatos();

                if (!visitados.contains(datoVecino)) {

                    visitados.add(datoVecino);
                    padre.put(datoVecino, actual);
                    cola.offer(datoVecino);

                }
            }
        }

        List<T> camino = reconstruirCamino(padre, start, end);

        return new PathResult<>(visitados, camino);
    }

    private List<T> reconstruirCamino(Map<T, T> padre, T inicio, T fin) {

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

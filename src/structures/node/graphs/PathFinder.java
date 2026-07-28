package structures.node.graphs;

public interface PathFinder<T> {
    //Encuentra el camino entre los nodos
    PathResult<T> find(Graph<T> graph, T start, T end);
} 
package structures.node.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import structures.node.node.Node;


public class Graph<T> {
    //atributo 
    private Map<Node<T>, Set<Node<T>>> graph;

    public Graph() {// crea una estrucuravacia 
        this.graph = new HashMap<Node<T>, Set<Node<T>>>();
    }

    public void add(T data) {//Agrega a un nodo al grafo ei aun existe
        Node<T> node = new Node<T>(data);
        graph.putIfAbsent(node, new HashSet<Node<T>>());

    }

    public void addEdge(T v1, T v2) {// Aqui es bi dereccional entre los nodos del grafo
        add(v1);
        add(v2);
        Node<T> nv1 = new Node<>(v1);
        Node<T> nv2 = new Node<>(v2);
        graph.get(nv1).add(nv2);
        graph.get(nv2).add(nv1);
    }

    public void addEdgeUni(T v1, T v2) { // Solo uno le conoce al otro y esta enlazado con solo uno
        Node<T> nv1 = new Node<T>(v1);
        Node<T> nv2 = new Node<T>(v2);
        add(v1);
        add(v2);
        graph.get(nv1).add(nv2);

    }

    public void printGrafo() {//recorre el grafo e imprime cada nodo unto con las conexiones
        for (Map.Entry<Node<T>, Set<Node<T>>> entry : graph.entrySet()) {
            System.out.print(entry.getKey() + "->");
            for (Node<T> connecciones : entry.getValue()) {
                System.out.print(connecciones);
            }
            System.out.println();
        }
    }

    public Set<Node<T>> getVecinos(T current) {//obtiene los vecinos y si no existen devuelve vacio
        return graph.getOrDefault(new Node<T>(current), new HashSet<Node<T>>());
    }

    public void remove(T datos) {//elimina un nodo del grafo y borra todas sus conexiones
        Node<T> node = new Node<>(datos);
        graph.remove(node);
        for (Set<Node<T>> vecinos : graph.values()) {
            vecinos.remove(node);
        }
    }

    public boolean contains(T data) {//vaa verificar si existe dentro del grafo
        return graph.containsKey(new Node<>(data));
    }

    public Set<Node<T>> getNodes() {//Devuelve el conjunto de nodos del grafo
        return graph.keySet();
    }

    public void removeEdge(T a, T b) {//Elimin la conexion entre dos nodos del grafo
        Node<T> n1 = new Node<>(a);
        Node<T> n2 = new Node<>(b);
        if (graph.containsKey(n1))
            graph.get(n1).remove(n2);
        if (graph.containsKey(n2))
            graph.get(n2).remove(n1);
    }
}

package structures.node;

public class Node <T> { 
    private T datos;

    public Node() {
    }
    public Node(T datos){
        this.datos = datos;
    }
    public T getDatos() {
        return datos;
    }
    public void setDatos(T datos) {
        this.datos = datos;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((datos == null) ? 0 : datos.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node<?> other = (Node<?>) obj;
        if (datos == null) {
            if (other.datos != null)
                return false;
        } else if (!datos.equals(other.datos))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Node [" + datos + "]";
    }
    

    

    
}

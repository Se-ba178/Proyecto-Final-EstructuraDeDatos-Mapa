package mapa;

import java.util.ArrayList;

public class PuntoMapa {


    private int x;
    private int y;
    private String nombre;

    // Nodos conectados a este nodo
    private ArrayList<PuntoMapa> vecinos;

    public PuntoMapa() {
        vecinos = new ArrayList<>();
    }

    public PuntoMapa(int x, int y, String nombre) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        vecinos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {

        this.x = x;
    }

    public int getY() {

        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Agregar conexión con otro nodo
    public void agregarVecino(PuntoMapa punto) {
        if(!vecinos.contains(punto)){
            vecinos.add(punto);
        }
    }

    // Obtener nodos conectados
    public ArrayList<PuntoMapa> getVecinos(){
        return vecinos;
    }

    // Crear conexión de ida y vuelta
    public void conectar(PuntoMapa punto){
        agregarVecino(punto);
        punto.agregarVecino(this);
    }

    @Override
    public String toString(){
        return nombre;
    }
}
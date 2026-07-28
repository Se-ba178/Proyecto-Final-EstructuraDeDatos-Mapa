package mapa;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import structures.node.graphs.Graph;

public class MapPanel extends JPanel {

    private Image mapa;// la imagen del apa de fondo
    private ArrayList<PuntoMapa> puntos;// lista donde se vana agregar los nodos
    private PuntoMapa inicio;
    private PuntoMapa fin;
    private ArrayList<PuntoMapa> ruta;// ya sea BFS-DFS
    private String modo = "INICIO"; // se puede cambiar para q ni bien se ejecute se puedan agregar nodos
    private Graph<PuntoMapa> grafo;// grafo donde estan conectados los nodos

    public MapPanel(Graph<PuntoMapa> grafo) {
        this.grafo = grafo;
        ImageIcon icono = new ImageIcon("imagenes/Imagen Fondo.png");// cargar la imagen
        mapa = icono.getImage();
        puntos = new ArrayList<>();// lista vacia
        ruta = new ArrayList<>();// lista vacia
        setBackground(Color.WHITE);// si no carga la imagen fondo blanco
        // evento al detctar clik
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MapPanel.this.mouseClicked(e);
            }
        });
    }

    // aregar un punto a l lista de puntos
    public void agregarPunto(PuntoMapa punto) {
        puntos.add(punto);
        repaint();
    }

    // se borra la ruta anterior , copia la ruta actual y vuelve a dibujar el anel
    public void mostrarRuta(ArrayList<PuntoMapa> camino) {
        ruta.clear();
        ruta.addAll(camino);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        // Suaviza las líneas
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar mapa
        if (mapa != null) {
            g2.drawImage(mapa, 0, 0, getWidth(), getHeight(), this);
        }
        // Dibujar ruta
        if (ruta != null && ruta.size() > 1) {
            g2.setColor(Color.MAGENTA);
            g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            for (int i = 0; i < ruta.size() - 1; i++) {
                PuntoMapa a = ruta.get(i);
                PuntoMapa b = ruta.get(i + 1);
                g2.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
            }
        }
        // Dibujar nodos
        for (PuntoMapa p : puntos) {
            if (p.equals(inicio)) {
                g2.setColor(Color.GREEN);
            } else if (p.equals(fin)) {
                g2.setColor(Color.BLUE);
            } else {
                g2.setColor(Color.RED);
            }
            g2.fillOval(p.getX() - 6, p.getY() - 6, 12, 12);
            g2.setColor(Color.BLACK);
            g2.drawString(p.getNombre(), p.getX() + 8, p.getY() - 8);
        }
    }

    public void mouseClicked(MouseEvent e) {// crea un evento q va a controlar las acciones q tenganal hacer clik sobre el mapa procesa los cliks del ususario sobre eel modo q escogio
        int x = e.getX();
        int y = e.getY();
        PuntoMapa seleccionado = null;
        for (PuntoMapa p : puntos) {
            double distancia = Math.sqrt(Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2));
            if (distancia < 15) {
                seleccionado = p;
                break;
            }
        }
        if (modo.equals("ELIMINAR")) {
            if (seleccionado != null) {
                puntos.remove(seleccionado);
                this.grafo.remove(seleccionado);
                if (seleccionado.equals(inicio)) {
                    inicio = null;
                }
                if (seleccionado.equals(fin)) {
                    fin = null;
                }
                ruta.clear();
                repaint();
            }
            return;
        }
        if (modo.equals("INICIO")) {
            if (seleccionado != null) {
                inicio = seleccionado;
                repaint();
            }
            return;
        }
        if (modo.equals("FINAL")) {
            if (seleccionado != null) {
                fin = seleccionado;
                repaint();
            }
            return;
        }
        if (modo.equals("AGREGAR")) {
            if (seleccionado == null) {
                String nombre = JOptionPane.showInputDialog("Nombre del nodo:");
                if (nombre != null && !nombre.isBlank()) {
                    PuntoMapa punto = new PuntoMapa(x, y, nombre);
                    agregarPunto(punto);
                    grafo.add(punto);
                    // Buscar el nodo existente más cercano
                    PuntoMapa masCercano = null;
                    double menorDistancia = Double.MAX_VALUE;
                    for (PuntoMapa p : puntos) {
                        if (p.equals(punto)) {
                            continue; // No compararse consigo mismo
                        }
                        double distancia = Math.sqrt(Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2));
                        if (distancia < menorDistancia) {
                            menorDistancia = distancia;
                            masCercano = p;
                        }
                    }
                    // Conectar con el nodo más cercano
                    if (masCercano != null) {
                        grafo.addEdge(masCercano, punto);
                        System.out.println("Conectado: "+ masCercano.getNombre() + " <-> " + punto.getNombre());
                        grafo.printGrafo();
                    }
                    repaint();
                }
            }
            return;
        }
    }

    public PuntoMapa getInicio() {
        return inicio;
    }

    public PuntoMapa getFin() {
        return fin;
    }

    public ArrayList<PuntoMapa> getPuntos() {
        return puntos;
    }

    // elimina el nodo inico y el nodo fin y vacia la lista de la ruta le da la
    // orden de dibujar desde cero
    public void limpiarInicioFin() {
        inicio = null;
        fin = null;
        ruta.clear();
        repaint();
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public void setGrafo(Graph<PuntoMapa> grafo) {
        this.grafo = grafo;
        repaint();
    }

}
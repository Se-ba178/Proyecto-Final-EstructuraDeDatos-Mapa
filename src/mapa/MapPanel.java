package mapa;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import structures.node.graphs.Graph;

public class MapPanel extends JPanel {

    private Image mapa;
    private ArrayList<PuntoMapa> puntos;
    private PuntoMapa inicio;
    private PuntoMapa fin;
    private ArrayList<PuntoMapa> ruta;
    private String modo = "INICIO";
    private Graph<PuntoMapa> grafo;

    public MapPanel(Graph<PuntoMapa> grafo) {
        this.grafo = grafo;
        ImageIcon icono = new ImageIcon("imagenes/Imagen Fondo.png");
        mapa = icono.getImage();
        puntos = new ArrayList<>();
        ruta = new ArrayList<>();
        setBackground(Color.WHITE);
        // Detectar clic en mapa
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MapPanel.this.mouseClicked(e);
            }
        });
    }

    public void agregarPunto(PuntoMapa punto) {
        puntos.add(punto);
        repaint();
    }

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

    public void mouseClicked(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();

        // Buscar si hizo clic sobre un nodo
        PuntoMapa seleccionado = null;

        for (PuntoMapa p : puntos) {
            double distancia = Math.sqrt(Math.pow(x - p.getX(), 2)
                    + Math.pow(y - p.getY(), 2));

            if (distancia < 15) {
                seleccionado = p;
                break;
            }
        }

        // -------- ELIMINAR --------
        if (modo.equals("ELIMINAR")) {

            if (seleccionado != null) {

                puntos.remove(seleccionado);

                this.grafo.remove(seleccionado);
                repaint();
            }

            return;
        }

        // -------- INICIO --------
        if (modo.equals("INICIO")) {

            if (seleccionado != null) {
                inicio = seleccionado;
                repaint();
            }

            return;
        }

        // -------- FINAL --------
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

                    this.grafo.add(punto);

                    // Conectar con el nodo anterior
                    if (puntos.size() > 1) {
                        PuntoMapa anterior = puntos.get(puntos.size() - 2);
                        this.grafo.addEdge(anterior, punto);
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

    public void limpiarInicioFin() {
        inicio = null;
        fin = null;
        ruta.clear();
        repaint();
    }
    
    public void setModo(String modo) {
        this.modo = modo;
    }

}
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
    
    

    

    public MapPanel(Graph<PuntoMapa> grafo) {
        
        ImageIcon icono = new ImageIcon("imagenes/Imagen Fondo.png");
        mapa = icono.getImage();
        puntos = new ArrayList<>();
        ruta = new ArrayList<>();
        setBackground(Color.WHITE);
        // Detectar clic en mapa
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                // Revisar si seleccionó un nodo existente
                for(PuntoMapa p : puntos){
                    double distancia = Math.sqrt(Math.pow(x - p.getX(), 2) +Math.pow(y - p.getY(), 2));
                    if(distancia < 15){
                        if(inicio == null){
                            inicio = p;
                            System.out.println("Inicio: " + p.getNombre());
                        }else if(fin == null){
                            fin = p;
                            System.out.println("Final: " + p.getNombre());
                        }else{
                            JOptionPane.showMessageDialog(null,"Ya existe inicio y final");
                        }
                        repaint();
                        return;
                    }
                }
                // Si no seleccionó nodo crea uno nuevo
                String nombre = JOptionPane.showInputDialog("Nombre del nodo:");
                if(nombre != null && !nombre.isEmpty()){
                    PuntoMapa punto = new PuntoMapa(x,y,nombre);
                    agregarPunto(punto);
                    grafo.add(punto);
                    if(!puntos.isEmpty() && puntos.size()>1){
                        PuntoMapa anterior=puntos.get(puntos.size()-2);
                        grafo.addEdge(anterior, punto);
                    }
                    
                }
            }
        });
    }

    public void agregarPunto(PuntoMapa punto){
        puntos.add(punto);
        repaint();
    }

    public void mostrarRuta(ArrayList<PuntoMapa> camino){
        ruta.clear();
        ruta.addAll(camino);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(mapa != null){
            g.drawImage(mapa,0,0,getWidth(),getHeight(),this);
        }

        // Dibujar nodos
        for(PuntoMapa p : puntos){
            if(p == inicio){
                g.setColor(Color.GREEN);
            }else if(p == fin){
                g.setColor(Color.BLUE);
            }else{
                g.setColor(Color.RED);
            }
            g.fillOval(p.getX()-6,p.getY()-6,12,12);
            g.setColor(Color.BLACK);
            g.drawString(p.getNombre(),p.getX()+10,p.getY());
        }
        // Dibujar ruta BFS o DFS
        g.setColor(Color.MAGENTA);
        for(int i = 0; i < ruta.size()-1; i++){
            PuntoMapa a = ruta.get(i);
            PuntoMapa b = ruta.get(i+1);
            g.drawLine(a.getX(),a.getY(),b.getX(),b.getY());
        }
    }

    public PuntoMapa getInicio(){
        return inicio;
    }

    public PuntoMapa getFin(){
        return fin;
    }

    public ArrayList<PuntoMapa> getPuntos(){
        return puntos;
    }

    public void limpiarInicioFin(){
        inicio= null;
        fin= null;
        ruta.clear();
        repaint();
    }
    
}
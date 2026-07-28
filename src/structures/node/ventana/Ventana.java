package structures.node.ventana;

import java.awt.*;
import java.util.ArrayList;
import persistence.FileGraphRepository;
import javax.swing.*;

import mapa.MapPanel;
import mapa.PuntoMapa;
import structures.node.graphs.Graph;
import structures.node.graphs.PathResult;
import structures.node.graphs.algoritmos.BFSPathFinder;
import structures.node.graphs.algoritmos.DFSPathFinder;

public class Ventana {

    public static void main(String[] args) {

        JFrame frmMiVentana = new JFrame("Proyecto Final");
        frmMiVentana.setSize(1550, 820);
        frmMiVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMiVentana.setLayout(new BorderLayout());

        // MENU SUPERIOR

        JMenuBar menu = new JMenuBar();

        JMenu archivo = new JMenu("Opciones");

        JMenu salir = new JMenu("Pulse aqui para salir ");

        JMenuItem salirD = new JMenuItem("Salir");
        salir.add(salirD);

        menu.add(archivo);
        menu.add(salir);

        // BARRA LATERAL

        JToolBar barra = new JToolBar(JToolBar.VERTICAL);
        barra.setOrientation(JToolBar.VERTICAL);
        barra.setFloatable(false);
        barra.setPreferredSize(new Dimension(170, 0));


        JButton play = new JButton("Ejecutar");
        JButton limpiar = new JButton("Limpiar");
        JButton inicio = new JButton("Marcar inicio");
        JButton finale = new JButton("Marcar final");
        JButton agregar = new JButton("Agregar nodo");
        JButton eliminar = new JButton("Eliminar Nodo");
        JButton guardar = new JButton("Guardar");

        barra.add(play);
        barra.add(limpiar);
        barra.add(inicio);
        barra.add(finale);
        barra.add(agregar);
        barra.add(eliminar);
        barra.add(guardar);
        barra.addSeparator();
        


        // MENU BFS DFS

        JPopupMenu menuBusqueda = new JPopupMenu();

        JMenuItem opcionBFS = new JMenuItem("Metodo -> BFS");
        JMenuItem opcionDFS = new JMenuItem("Metodo -> DFS");

        menuBusqueda.add(opcionBFS);
        menuBusqueda.add(opcionDFS);

        // MAPA
        final Graph<PuntoMapa>[] grafo = new Graph[] { new Graph<>() };

        MapPanel mapa = new MapPanel(grafo[0]);

        // RESULTADOS

        JTextArea txtResultados = new JTextArea();
        txtResultados.setEditable(false);

        JFrame ventanaResultados = new JFrame("Resultados");
        ventanaResultados.setSize(250, 400);
        ventanaResultados.add(new JScrollPane(txtResultados));
        ventanaResultados.setLocation(200, 100);
        // AGREGAR
        frmMiVentana.setJMenuBar(menu);
        frmMiVentana.add(barra, BorderLayout.WEST);
        frmMiVentana.add(mapa, BorderLayout.CENTER);


        FileGraphRepository repositorio = new FileGraphRepository();

        System.out.println("Ruta actual: " + new java.io.File(".").getAbsolutePath());
        try {
            grafo[0] = repositorio.cargar("mapa.txt", mapa);
            System.out.println("Mapa cargado correctamente.");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("No se pudo cargar mapa.txt");
        }

        // EVENTOS
        salirD.addActionListener(e -> System.exit(0));
        play.addActionListener(e -> {
            menuBusqueda.show(play, 0, play.getHeight());
        });

        // BFS
        opcionBFS.addActionListener(e -> {
            if (mapa.getInicio() != null && mapa.getFin() != null) {
                BFSPathFinder<PuntoMapa> bfs = new BFSPathFinder<>();
                PathResult<PuntoMapa> resultado = bfs.find(grafo[0], mapa.getInicio(), mapa.getFin());
                ArrayList<PuntoMapa> camino = new ArrayList<>(resultado.getPath());
                mapa.mostrarRuta(camino);
                txtResultados.setText("Ruta encontrada con BFS\\n\n");
                for (PuntoMapa p : camino) {
                    txtResultados.append(p.getNombre() + "\n");
                }
                ventanaResultados.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione nodo inicio y nodo final");
            }
        });

        // DFS
        opcionDFS.addActionListener(e -> {
            if (mapa.getInicio() != null && mapa.getFin() != null) {
                DFSPathFinder<PuntoMapa> dfs = new DFSPathFinder<>();
                PathResult<PuntoMapa> resultado = dfs.find(grafo[0], mapa.getInicio(), mapa.getFin());
                ArrayList<PuntoMapa> camino = new ArrayList<>(resultado.getPath());
                mapa.mostrarRuta(camino);
                txtResultados.setText("Ruta encontrada con DFS\\n\n");
                for (PuntoMapa p : camino) {
                    txtResultados.append(p.getNombre() + "\n");
                }
                ventanaResultados.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione nodo inicio y nodo final");
            }
        });

        guardar.addActionListener(e -> {
            try {
                repositorio.guardar("mapa.txt", grafo[0], mapa);
                JOptionPane.showMessageDialog(frmMiVentana, "Mapa guardado correctamente.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frmMiVentana, "Error al guardar.");
            }
        });
        eliminar.addActionListener(e -> {
            mapa.setModo("ELIMINAR");
            JOptionPane.showMessageDialog(frmMiVentana, "Seleccione el nodo que desea eliminar.");
        });
        inicio.addActionListener(e -> {
            mapa.setModo("INICIO");
            JOptionPane.showMessageDialog(frmMiVentana, "Seleccione el nodo de inicio.");
        });

        limpiar.addActionListener(e -> {
            mapa.limpiarInicioFin();
            txtResultados.setText("");
            mapa.setModo("AGREGAR");
        });

        finale.addActionListener(e -> {
            mapa.setModo("FINAL");
            JOptionPane.showMessageDialog(frmMiVentana, "Seleccione el nodo final.");
        });
        agregar.addActionListener(e -> {
            mapa.setModo("AGREGAR");
            JOptionPane.showMessageDialog(frmMiVentana, "Haga clic en el mapa para crear un nodo.");
        });

        frmMiVentana.setLocationRelativeTo(null);
        frmMiVentana.setVisible(true);

    }
}
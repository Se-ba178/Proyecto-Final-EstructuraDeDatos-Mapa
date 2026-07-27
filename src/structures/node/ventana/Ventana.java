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
        frmMiVentana.setSize(1500, 800);
        frmMiVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMiVentana.setLayout(new BorderLayout());

        // MENU SUPERIOR

        JMenuBar menu = new JMenuBar();

        JMenu archivo = new JMenu("Acciones");
        JMenu salir = new JMenu("X");

        JMenuItem abrir = new JMenuItem("Abrir");
        JMenuItem guardar = new JMenuItem("Guardar");
        JMenuItem eliminar = new JMenuItem("Eliminar");
        JMenuItem salirD = new JMenuItem("Salir");

        archivo.add(abrir);
        archivo.add(guardar);
        archivo.add(eliminar);

        salir.add(salirD);

        menu.add(archivo);
        menu.add(salir);

        // BARRA LATERAL

        JToolBar barra = new JToolBar();
        barra.setOrientation(JToolBar.VERTICAL);
        barra.setFloatable(true);
        barra.setPreferredSize(new Dimension(150, 400));

        JPanel menuLateral = new JPanel(new BorderLayout());

        JPanel menuInferior = new JPanel();
        menuInferior.setLayout(new GridLayout(4, 1, 5, 5));

        JButton play = new JButton("Ejecutar");
        JButton limpiar = new JButton("Limpiar");
        JButton inicio = new JButton("Marcar inicio");
        JButton finale = new JButton("Marcar final");

        menuInferior.add(play);
        menuInferior.add(limpiar);
        menuInferior.add(inicio);
        menuInferior.add(finale);

        menuLateral.add(menu, BorderLayout.NORTH);
        menuLateral.add(menuInferior, BorderLayout.SOUTH);

        barra.add(menuLateral);

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

        JScrollPane scroll = new JScrollPane(txtResultados);

        // AGREGAR

        frmMiVentana.add(barra, BorderLayout.WEST);
        frmMiVentana.add(mapa, BorderLayout.CENTER);
        frmMiVentana.add(scroll, BorderLayout.SOUTH);

       
        

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
                txtResultados.setText("Ruta encontrada con BFS\n");
                for (PuntoMapa p : camino) {
                    txtResultados.append(p.getNombre() + "\n");
                }
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
                txtResultados.setText("Ruta encontrada con DFS\n");
                for (PuntoMapa p : camino) {
                    txtResultados.append(p.getNombre() + "\n");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione nodo inicio y nodo final");
            }
        });

        abrir.addActionListener(e -> {

            try {

                grafo[0] = repositorio.cargar("mapa.txt", mapa);

                JOptionPane.showMessageDialog(
                        frmMiVentana,
                        "Mapa cargado correctamente.");

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        frmMiVentana,
                        "Error al abrir el archivo.");

            }

        });
        guardar.addActionListener(e -> {

            try {

                repositorio.guardar("mapa.txt", grafo[0], mapa);

                JOptionPane.showMessageDialog(
                        frmMiVentana,
                        "Mapa guardado correctamente.");

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        frmMiVentana,
                        "Error al guardar.");

            }

        });
        limpiar.addActionListener(e -> {
            mapa.limpiarInicioFin();
            txtResultados.setText("");



        });
        frmMiVentana.setLocationRelativeTo(null);
        frmMiVentana.setVisible(true);

    }
}
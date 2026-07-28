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
import times.MedidorTiempo;
import times.ResultadoTiempo;

public class Ventana {
    // private static Graph<PuntoMapa> grafo = new Graph<>();

    public static void main(String[] args) {

        // creacion de la ventana principal en la q añadiremos todos los componentes
        JFrame frmMiVentana = new JFrame("Proyecto Final");
        frmMiVentana.setSize(1550, 820);
        frmMiVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMiVentana.setLayout(new BorderLayout());

        // menu q contiene las opciones y cerrarle
        JMenuBar menu = new JMenuBar();
        JMenu archivo = new JMenu("Opciones");
        JMenu salir = new JMenu("Pulse aqui para salir ");
        JMenuItem salirD = new JMenuItem("Salir");
        salir.add(salirD);
        menu.add(archivo);
        menu.add(salir);
        // resultados de tiempo
        JTextField txtTiempo = new JTextField(15);
        txtTiempo.setEditable(false);
        txtTiempo.setText("Tiempo :");
        ;

        // BARRA LATERAL
        JToolBar barra = new JToolBar(JToolBar.VERTICAL);
        barra.setOrientation(JToolBar.VERTICAL);
        barra.setFloatable(false);
        barra.setPreferredSize(new Dimension(170, 0));

        // cracion de botones para todas las opciones de nuestro mapa
        JButton play = new JButton("Ejecutar");
        JButton limpiar = new JButton("Limpiar");
        JButton inicio = new JButton("Marcar inicio");
        JButton finale = new JButton("Marcar final");
        JButton agregar = new JButton("Agregar nodo");
        JButton eliminar = new JButton("Eliminar Nodo");
        JButton guardar = new JButton("Guardar");

        // añadimos los botones a la barra menu
        barra.add(play);
        barra.add(limpiar);
        barra.add(inicio);
        barra.add(finale);
        barra.add(agregar);
        barra.add(eliminar);
        barra.add(guardar);
        barra.addSeparator();
        barra.add(new JLabel("Tiempo"));
        barra.add(txtTiempo);

        // menu con las os opciones de BFS y DFS
        JPopupMenu menuBusqueda = new JPopupMenu();
        JMenuItem opcionBFS = new JMenuItem("Metodo -> BFS");
        JMenuItem opcionDFS = new JMenuItem("Metodo -> DFS");

        menuBusqueda.add(opcionBFS);
        menuBusqueda.add(opcionDFS);

        final Graph<PuntoMapa>[] grafo = new Graph[] { new Graph<>() };
        MapPanel mapa = new MapPanel(grafo[0]);

        // se crea un espacio onde poner todos los resultados
        JTextArea txtResultados = new JTextArea();
        txtResultados.setEditable(false);

        // se crea una ventana q aparecera cn todos los resultados obtenidos
        JFrame ventanaResultados = new JFrame(" Nodos Visitados");
        ventanaResultados.setSize(600, 100);
        ventanaResultados.add(new JScrollPane(txtResultados));
        ventanaResultados.setLocation(200, 100);

        // agregamso todo lo q creamos a la ventana pricnipala pa q se muestre
        frmMiVentana.setJMenuBar(menu);
        frmMiVentana.add(barra, BorderLayout.WEST);
        frmMiVentana.add(mapa, BorderLayout.CENTER);

        FileGraphRepository repositorio = new FileGraphRepository();

        System.out.println("Ruta actual: " + new java.io.File(".").getAbsolutePath());
        try {
            grafo[0] = repositorio.cargar("mapa.txt", mapa);
            mapa.setGrafo(grafo[0]);
            System.out.println("Mapa cargado correctamente.");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("No se pudo cargar mapa.txt");
        }

        // BFS
        opcionBFS.addActionListener(e -> {
            if (mapa.getInicio() != null && mapa.getFin() != null) {

                System.out.println("Inicio: " + mapa.getInicio());
                System.out.println("Fin: " + mapa.getFin());
                System.out.println("Vecinos del inicio: " + grafo[0].getVecinos(mapa.getInicio()));

                // Ejecutar BFS y medir el tiempo
                ResultadoTiempo resultado = MedidorTiempo.ejecutar(
                        new BFSPathFinder<>(),
                        grafo[0],
                        mapa.getInicio(),
                        mapa.getFin());

                ArrayList<PuntoMapa> camino = new ArrayList<>(resultado.getRuta());

                System.out.println("Camino BFS: " + camino);

                mapa.mostrarRuta(camino);

                txtResultados.setText("Ruta encontrada con BFS\n");

                if (camino.isEmpty()) {

                    txtResultados.append("No se encontró una ruta.");

                } else {

                    for (PuntoMapa p : camino) {
                        txtResultados.append(p.getNombre() + " -> ");
                    }

                    txtResultados.append("\n");
                    txtResultados.append("Cantidad de aristas: " + resultado.getAristas());
                }

                // Mostrar el tiempo de ejecución
                txtTiempo.setText(String.format("%.6f ms", resultado.getTiempo()));

                ventanaResultados.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Seleccione nodo inicio y nodo final");
            }
        });

        // DFS
        opcionDFS.addActionListener(e -> {
            if (mapa.getInicio() != null && mapa.getFin() != null) {

                System.out.println("Inicio: " + mapa.getInicio());
                System.out.println("Fin: " + mapa.getFin());
                System.out.println("Vecinos del inicio: " + grafo[0].getVecinos(mapa.getInicio()));

                // Ejecutar DFS y medir el tiempo
                ResultadoTiempo resultado = MedidorTiempo.ejecutar(
                        new DFSPathFinder<>(),
                        grafo[0],
                        mapa.getInicio(),
                        mapa.getFin());

                ArrayList<PuntoMapa> camino = new ArrayList<>(resultado.getRuta());

                System.out.println("Camino DFS: " + camino);

                mapa.mostrarRuta(camino);

                txtResultados.setText("Ruta encontrada con DFS\n");

                if (camino.isEmpty()) {

                    txtResultados.append("No se encontró una ruta.");

                } else {

                    for (PuntoMapa p : camino) {
                        txtResultados.append(p.getNombre() + " -> ");
                    }

                    txtResultados.append("\n");
                    txtResultados.append("Cantidad de aristas: " + resultado.getAristas());
                }

                // Mostrar el tiempo de ejecución
                txtTiempo.setText(String.format("%.6f ms", resultado.getTiempo()));

                ventanaResultados.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Seleccione nodo inicio y nodo final");
            }
        });

        play.addActionListener(e -> {
            menuBusqueda.show(play, 0, play.getHeight());
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
package persistence;

import java.io.*;
import java.util.*;

import mapa.MapPanel;
import mapa.PuntoMapa;
import structures.node.Node;
import structures.node.graphs.Graph;

public class FileGraphRepository implements GraphRepository {

    @Override
    public void guardar(String archivo,
                        Graph<PuntoMapa> grafo,
                        MapPanel mapa) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

        // ======== NODOS ========

        for (PuntoMapa p : mapa.getPuntos()) {

            bw.write("NODE;"
                    + p.getNombre() + ";"
                    + p.getX() + ";"
                    + p.getY());

            bw.newLine();

        }

        bw.newLine();


        Set<String> escritas = new HashSet<>();

        for (Node<PuntoMapa> nodo : grafo.getNodes()) {

            PuntoMapa origen = nodo.getDatos();

            for (Node<PuntoMapa> vecino : grafo.getVecinos(origen)) {

                PuntoMapa destino = vecino.getDatos();

                String a = origen.getNombre();
                String b = destino.getNombre();

                String llave;

                if (a.compareTo(b) < 0)
                    llave = a + "-" + b;
                else
                    llave = b + "-" + a;

                if (!escritas.contains(llave)) {

                    bw.write("EDGE;"
                            + a + ";"
                            + b + ";true");

                    bw.newLine();

                    escritas.add(llave);

                }

            }

        }

        bw.newLine();

        // ======== META ========

        if (mapa.getInicio() != null)
            bw.write("META;start;" + mapa.getInicio().getNombre());

        bw.newLine();

        if (mapa.getFin() != null)
            bw.write("META;end;" + mapa.getFin().getNombre());

        bw.close();

    }

    @Override
    public Graph<PuntoMapa> cargar(String archivo,
                                   MapPanel mapa) throws IOException {

        Graph<PuntoMapa> grafo = new Graph<>();

        mapa.limpiarInicioFin();

        BufferedReader br = new BufferedReader(new FileReader(archivo));

        String linea;

        Map<String, PuntoMapa> nodos = new HashMap<>();

        while ((linea = br.readLine()) != null) {

            if (linea.isBlank())
                continue;

            String datos[] = linea.split(";");

            switch (datos[0]) {

                case "NODE":

                    PuntoMapa p = new PuntoMapa(

                            Integer.parseInt(datos[2]),
                            Integer.parseInt(datos[3]),
                            datos[1]);

                    mapa.agregarPunto(p);

                    grafo.add(p);

                    nodos.put(datos[1], p);

                    break;

                case "EDGE":

                    PuntoMapa a = nodos.get(datos[1]);
                    PuntoMapa b = nodos.get(datos[2]);

                    if (a != null && b != null)
                        grafo.addEdge(a, b);

                    break;

                case "META":

                    // Aquí puedes luego marcar inicio y fin
                    // dependiendo de cómo implementes MapPanel.

                    break;

            }

        }

        br.close();

        return grafo;

    }

}


    


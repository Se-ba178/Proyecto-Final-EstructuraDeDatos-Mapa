package ventana;
import java.awt.*;


import javax.swing.*;

public class Ventana{

    public static void main(String[] args) {

        // Ventana
        JFrame frmMiVentana = new JFrame("Proyecto Final");
        frmMiVentana.setSize(1500, 800);
        frmMiVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMiVentana.setLayout(new BorderLayout());

        // Barra de herramientas
        JMenuBar menu= new JMenuBar();
        menu.setSize(50, 10);
        JMenu archivo= new JMenu("Acciones");
        JMenu salir= new JMenu("\t X");
        JToolBar barra= new JToolBar();
        barra.setFloatable(true);
        barra.setOrientation(JToolBar.VERTICAL);
        barra.setPreferredSize(new Dimension(150, 400));
        JPanel menuLateral=new JPanel(new BorderLayout());
        menuLateral.setPreferredSize(new Dimension(50, 100));
        JPanel menuInferior= new JPanel(new GridLayout());
        menuInferior.setLayout(new GridLayout(4,1,5,5));
       

        //Botones
        JButton play= new JButton("Ejecutar");
        JButton limpiar= new JButton("Limpiar");
        JButton inicio= new JButton("Marcar como inicio");
        JButton finale= new JButton("Marcar final");




        //JToolBar barra = new JToolBar("Opciones Para el Mapa ");
        //barra.setFloatable(true);
        //barra.setSize(150,160);
        //Parte del menu para guardar elimiar o abrir el archivo
        JMenuItem Abrir = new JMenuItem("Abrir");
        JMenuItem guardar = new JMenuItem("Guardar");
        JMenuItem eliminar = new JMenuItem("Eliminar");
        JMenuItem salirD= new JMenuItem("Salir");

       

        archivo.add(Abrir);
        archivo.add(guardar);
        archivo.add(eliminar);
        salir.add(salirD);
        menuInferior.add(play);
        menuInferior.add(limpiar);
        menuInferior.add(inicio);
        menuInferior.add(finale);

        menu.add(archivo);
        menu.add(salir);
        menuLateral.add(menu,BorderLayout.NORTH);
        menuLateral.add(menuInferior,BorderLayout.SOUTH);
        barra.add(menuLateral,BorderLayout.CENTER);
        
        

        // Imagen
        ImageIcon imagen = new ImageIcon("imagenes/Imagen Fondo.png");
        JLabel lblImagen = new JLabel(imagen);
        lblImagen.setHorizontalAlignment(JLabel.CENTER);

        // Área de resultados
        JTextArea txtResultados = new JTextArea(5, 20);
        txtResultados.setEditable(false);

        JScrollPane scroll = new JScrollPane(txtResultados);

        // Agregar componentes
        frmMiVentana.add(barra,BorderLayout.WEST);
        frmMiVentana.add(lblImagen, BorderLayout.CENTER);
        frmMiVentana.add(scroll, BorderLayout.SOUTH);

        frmMiVentana.setLocationRelativeTo(null);

        salirD.addActionListener(e -> System.exit(0));
        frmMiVentana.setVisible(true);
    }
}
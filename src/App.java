import java.awt.BorderLayout;
import javax.swing.*;

public class App {

    public static void main(String[] args) {

        // Ventana
        JFrame frmMiVentana = new JFrame("Proyecto Final");
        frmMiVentana.setSize(1500, 800);
        frmMiVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMiVentana.setLayout(new BorderLayout());

        // Barra de herramientas
        JToolBar barra = new JToolBar("Opciones Para el Mapa ");
        barra.setFloatable(true);

        JButton btnAbrir = new JButton("Abrir");
        JButton btnGuardar = new JButton("Guardar");
        JButton btnEliminar = new JButton("Eliminar");

        barra.add(btnAbrir);
        barra.add(btnGuardar);
        barra.add(btnEliminar);

        // Imagen
        ImageIcon imagen = new ImageIcon("imagenes/Imagen Fondo.png");
        JLabel lblImagen = new JLabel(imagen);
        lblImagen.setHorizontalAlignment(JLabel.CENTER);

        // Área de resultados
        JTextArea txtResultados = new JTextArea(5, 20);
        txtResultados.setEditable(false);

        JScrollPane scroll = new JScrollPane(txtResultados);

        // Agregar componentes
        frmMiVentana.add(barra, BorderLayout.NORTH);
        frmMiVentana.add(lblImagen, BorderLayout.CENTER);
        frmMiVentana.add(scroll, BorderLayout.SOUTH);

        frmMiVentana.setLocationRelativeTo(null);
        frmMiVentana.setVisible(true);
    }
}
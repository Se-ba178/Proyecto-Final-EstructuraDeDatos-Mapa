import java.awt.Color;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        //Configuracion ventana 
        JFrame frmMiVentana = new JFrame("Proyecto Final");
        frmMiVentana.setSize(1500, 800);
        frmMiVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // pa q se pueda mover 
        JDesktopPane desktop = new JDesktopPane();
        frmMiVentana.setContentPane(desktop);

        // Imagen Fondo
        ImageIcon imagen = new ImageIcon("imagenes/Imagen Fondo.png");
        JLabel lblImagen = new JLabel(imagen);
        lblImagen.setBounds(0, 0, 1500, 800);
        desktop.add(lblImagen, Integer.valueOf(0)); //capa base 

        JInternalFrame menuFlotante = new JInternalFrame("Barra De Menu", false, false, false, false);
        menuFlotante.setBounds(50, 50, 260, 70); // Posición inicial y tamaño
        
        
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.DARK_GRAY); // Un color más moderno
        panelBotones.add(new JButton("Abrir"));
        panelBotones.add(new JButton("Guardar"));
        panelBotones.add(new JButton("Eliminar"));
        
        menuFlotante.add(panelBotones);
        menuFlotante.setVisible(true);

        // Añadimos el menú en una capa superior (capa 1)
        desktop.add(menuFlotante, Integer.valueOf(1));

        frmMiVentana.setVisible(true);
    }
}
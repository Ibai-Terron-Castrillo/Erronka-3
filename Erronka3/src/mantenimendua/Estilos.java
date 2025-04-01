package mantenimendua;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class Estilos {

    public static void aplicarEstiloGlobal() {
        try {
            // Establecer Look and Feel de Nimbus para un diseño más moderno
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            // Configurar fuente global
            FontUIResource fuente = new FontUIResource("Arial", Font.PLAIN, 14);
            UIManager.put("Label.font", fuente);
            UIManager.put("Button.font", fuente);
            UIManager.put("TextField.font", fuente);
            UIManager.put("PasswordField.font", fuente);

            // Personalizar los colores de la interfaz
            UIManager.put("control", new Color(30, 30, 30));  // Fondo general
            UIManager.put("info", new Color(50, 50, 50));  // Color de tooltip
            UIManager.put("nimbusBase", new Color(45, 45, 45));  
            UIManager.put("nimbusBlueGrey", new Color(80, 80, 80));  
            UIManager.put("nimbusLightBackground", new Color(40, 40, 40));  // Fondo de paneles
            UIManager.put("nimbusFocus", new Color(255, 87, 34));  // Naranja fuerte (resaltado)
            UIManager.put("text", new Color(200, 200, 200));  // Texto claro

            // Configurar colores globales
            UIManager.put("Panel.background", new Color(40, 40, 40)); // Fondo oscuro
            UIManager.put("Label.foreground", Color.WHITE);           // Texto blanco

            // Personalizar botones
            Color rojoVino = new Color(178, 34, 34); // Rojo vino
            Color rojoVinoOscuro = new Color(100, 0, 50); // Rojo vino más oscuro
            Color textoBlanco = Color.WHITE;

            UIManager.put("Button.background", rojoVino);             // Fondo de los botones
            UIManager.put("Button.foreground", textoBlanco);          // Texto de los botones
            UIManager.put("Button.pressedBackground", rojoVinoOscuro); // Fondo al hacer clic
            UIManager.put("Button.hoverBackground", rojoVino.brighter()); // Fondo al pasar el mouse
            UIManager.put("Button.border", BorderFactory.createLineBorder(rojoVino.brighter(), 2));
            UIManager.put("Button.font", new Font("Arial", Font.BOLD, 14));

            // Personalizar botones de JOptionPane
            UIManager.put("OptionPane.background", new Color(40, 40, 40)); // Fondo del cuadro de diálogo
            UIManager.put("OptionPane.messageForeground", textoBlanco);   // Texto del mensaje

            // Personalizar etiquetas
            UIManager.put("Label.foreground", textoBlanco);
            UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 16));

        } catch (Exception e) {
            // Manejo de excepciones con más información
            System.err.println("Error al aplicar los estilos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


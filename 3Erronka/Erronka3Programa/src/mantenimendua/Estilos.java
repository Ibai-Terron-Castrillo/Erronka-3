package mantenimendua;

import javax.swing.*;
import java.awt.*;

public class Estilos {

    public static void aplicarEstiloGlobal() {
        try {
            // Establecer Look and Feel de Nimbus para un diseño más moderno
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            // Personalizar los colores de la interfaz
            UIManager.put("control", new Color(30, 30, 30));  // Fondo general
            UIManager.put("info", new Color(50, 50, 50));  // Color de tooltip
            UIManager.put("nimbusBase", new Color(45, 45, 45));  
            UIManager.put("nimbusBlueGrey", new Color(80, 80, 80));  
            UIManager.put("nimbusLightBackground", new Color(40, 40, 40));  // Fondo de paneles
            UIManager.put("nimbusFocus", new Color(255, 87, 34));  // Naranja fuerte (resaltado)
            UIManager.put("text", new Color(200, 200, 200));  // Texto claro

            // Personalizar botones
            UIManager.put("Button.background", new Color(178, 34, 34));
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("Button.border", BorderFactory.createLineBorder(new Color(255, 87, 34), 2));
            UIManager.put("Button.font", new Font("Arial", Font.BOLD, 14));

            // Personalizar etiquetas
            UIManager.put("Label.foreground", Color.WHITE);
            UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 16));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


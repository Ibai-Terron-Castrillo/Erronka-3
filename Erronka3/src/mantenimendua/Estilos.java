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
            UIManager.put("Button.background", new Color(60, 60, 60)); // Botón oscuro
            UIManager.put("Button.foreground", Color.WHITE);          // Texto del botón blanco

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


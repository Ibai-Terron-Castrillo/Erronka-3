package formularioak;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Sarrera extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Sarrera frame = new Sarrera();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Sarrera() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(new Color(60, 63, 65));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{1, 1, 1, 1, 1};
        gbl_contentPane.rowHeights = new int[]{1, 1, 1, 1, 1};
        gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};
        gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};
        contentPane.setLayout(gbl_contentPane);

        // Botones
        addButton("Bezeroak", 1, 1, new Bezeroak());
        addButton("Eskaerak", 2, 1, new Erreserbak());
        addButton("Ordutegia", 3, 1, new Ordutegia());
        addButton("EsOrdutegiak", 1, 2, new EsOrdutegiak());
        addButton("Eserlekuak", 2, 2, new Eserlekuak());
        addButton("Errerserba Historikoa", 3, 2, new ErrHist());
        addButton("Sarrerak", 1, 3, new Sarrerakk());
        addButton("Aretoak", 2, 3, new aretoa());
    }

    private void addButton(String text, int gridx, int gridy, final JFrame nextFrame) {
        JButton button = createButton(text);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                nextFrame.setVisible(true);
                dispose();
            }
        });
        GridBagConstraints gbc_button = new GridBagConstraints();
        gbc_button.fill = GridBagConstraints.BOTH;
        gbc_button.insets = new Insets(10, 10, 10, 10);
        gbc_button.gridx = gridx;
        gbc_button.gridy = gridy;
        contentPane.add(button, gbc_button);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button.setBackground(new Color(128, 0, 0)); // Rojo vino (dark red)
        button.setForeground(Color.WHITE); // Texto blanco
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 63, 65), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        
        // Asegurar que el fondo del botón no se vea afectado por el sistema operativo
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(true);
        
        return button;
    }
}

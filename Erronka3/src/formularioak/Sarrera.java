package formularioak;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mantenimendua.Estilos;

public class Sarrera extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel imageLabel;
    private static final String[] BUTTON_LABELS = {"Bezeroak", "Erreserbak", "Ordutegia", "EsOrdutegiak", "Eserlekuak", "ErrHist", "Sarrerak", "Aretoa"};
    private static final String[] CLASS_NAMES = {"Bezeroak", "Erreserbak", "Ordutegia", "EsOrdutegiak", "Eserlekuak", "ErrHist", "Sarrerakk", "aretoa"};

    public static void main(String[] args) {
        Estilos.aplicarEstiloGlobal();
        EventQueue.invokeLater(() -> {
            try {
                Sarrera frame = new Sarrera();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Sarrera() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1920, 1080);
        contentPane = new JPanel() {
            @Override
            public void doLayout() {
                int radius = 300;
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                int componentCount = getComponentCount();
                double angleStep = 2 * Math.PI / (componentCount - 2);

                if (imageLabel != null) {
                    Dimension imageSize = imageLabel.getPreferredSize();
                    imageLabel.setBounds(centerX - imageSize.width / 2, centerY - imageSize.height / 2, imageSize.width, imageSize.height);
                }

                int buttonIndex = 0;
                for (int i = 0; i < componentCount; i++) {
                    Component comp = getComponent(i);
                    if (comp == imageLabel || comp == saioaItxiButton) continue;
                    int x = (int) (centerX + radius * Math.cos(buttonIndex * angleStep) - comp.getPreferredSize().width / 2);
                    int y = (int) (centerY + radius * Math.sin(buttonIndex * angleStep) - comp.getPreferredSize().height / 2);
                    comp.setBounds(x, y, comp.getPreferredSize().width, comp.getPreferredSize().height);
                    buttonIndex++;
                }

                saioaItxiButton.setBounds(20, 20, 150, 50);
            }
        };
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(new Color(60, 63, 65));
        setContentPane(contentPane);

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/rsc/logo.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        imageLabel = new JLabel(scaledIcon);
        contentPane.add(imageLabel);

        // Ajustamos el bucle para que no acceda fuera de los límites del arreglo más pequeño
        int maxLength = Math.min(BUTTON_LABELS.length, CLASS_NAMES.length);
        for (int i = 0; i < maxLength; i++) {
            String buttonLabel = BUTTON_LABELS[i];
            CustomButton button = createCircularButton(buttonLabel);
            contentPane.add(button);

            final String className = CLASS_NAMES[i];
            button.addActionListener(e -> openWindow(className));
        }

        saioaItxiButton = createRectangularButton("Saioa itxi");
        saioaItxiButton.addActionListener(e -> {
            Login loginWindow = new Login();
            loginWindow.setVisible(true);
            dispose();
        });
        contentPane.add(saioaItxiButton);
    }

    private CustomButton createCircularButton(String text) {
        CustomButton button = new CustomButton(text);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(150, 150));
        button.setBackground(new Color(178, 34, 34));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setHover(true);
                button.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setHover(false);
                button.repaint();
            }
        });

        return button;
    }

    private CustomButton createRectangularButton(String text) {
        CustomButton button = new CustomButton(text);
        button.setContentAreaFilled(true);
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setPreferredSize(new Dimension(150, 50));
        button.setBackground(new Color(178, 34, 34));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setHover(true);
                button.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setHover(false);
                button.repaint();
            }
        });

        return button;
    }

    private void openWindow(String className) {
        try {
            JFrame newFrame = (JFrame) Class.forName("formularioak." + className).getDeclaredConstructor().newInstance();
            newFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            newFrame.setVisible(true);
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class CustomButton extends JButton {
        private static final long serialVersionUID = 1L;
        private boolean hover = false;
        private static final int PADDING = 10;

        public CustomButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }

        public void setHover(boolean hover) {
            this.hover = hover;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int diameter = Math.min(getWidth(), getHeight()) - 2 * PADDING;
            int x = (getWidth() - diameter) / 2;
            int y = (getHeight() - diameter) / 2;

            if (hover) {
                g2.setColor(getBackground().brighter());
            } else {
                g2.setColor(getBackground());
            }
            g2.fillOval(x, y, diameter, diameter);

            super.paintComponent(g);
        }
    }

    private CustomButton saioaItxiButton;
}

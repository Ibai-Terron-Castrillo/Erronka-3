package formularioak;

import org.kordamp.ikonli.swing.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import mantenimendua.Estilos;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class SarreraAdmin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel imageLabel;
    private static final String[] BUTTON_LABELS = {"Bezeroak", "Langileak", "Erreserbak", "Ordutegia", "EsOrdutegiak", "Eserlekuak", "ErrHist", "Sarrerak", "Aretoa", "Pelikulak"};
    private static final String[] CLASS_NAMES = {"BezeroakAdmin", "Langileak", "ErreserbakAdmin", "OrdutegiaAdmin", "EsOrdutegiakAdmin", "EserlekuaAdmin", "ErrHistAdmin", "SarrerakAdmin", "aretoakAdmin", "Pelikulak"};
    private CustomButton saioaItxiButton;

    public static void main(String[] args) {
        Estilos.aplicarEstiloGlobal();
        EventQueue.invokeLater(() -> {
            try {
                SarreraAdmin frame = new SarreraAdmin();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SarreraAdmin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1920, 1080);
        contentPane = new JPanel() {
            @Override
            public void doLayout() {
                int radius = 300; // Reduced radius to make the space smaller
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

                saioaItxiButton.setBounds(20, 20, 200, 60);
            }
        };
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(new Color(60, 63, 65));
        setContentPane(contentPane);

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/rsc/logo.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        imageLabel = new JLabel(scaledIcon);
        contentPane.add(imageLabel);

        FontAwesomeSolid[] icons = {
            FontAwesomeSolid.USERS, // Bezeroak
            FontAwesomeSolid.USER_TIE, // Langileak
            FontAwesomeSolid.CALENDAR_CHECK, // Erreserbak
            FontAwesomeSolid.CLOCK, // Ordutegia
            FontAwesomeSolid.CALENDAR_ALT, // EsOrdutegiak
            FontAwesomeSolid.CHAIR, // Eserlekuak
            FontAwesomeSolid.HISTORY, // ErrHist
            FontAwesomeSolid.TICKET_ALT, // Sarrerak
            FontAwesomeSolid.BUILDING, // Aretoa
            FontAwesomeSolid.FILM // Pelikulak
        };

        for (int i = 0; i < BUTTON_LABELS.length; i++) {
            String buttonLabel = BUTTON_LABELS[i];
            FontAwesomeSolid icon = icons[i];
            CustomButton button = createCircularButton(buttonLabel, icon);
            contentPane.add(button);

            final String className = CLASS_NAMES[i];
            button.addActionListener(e -> openWindow(className));
        }

        saioaItxiButton = createRectangularButton("Saioa itxi", FontAwesomeSolid.SIGN_OUT_ALT);
        saioaItxiButton.addActionListener(e -> {
            Login loginWindow = new Login();
            loginWindow.setVisible(true);
            dispose();
        });
        contentPane.add(saioaItxiButton);
    }

    private CustomButton createCircularButton(String text, FontAwesomeSolid icon) {
        CustomButton button = new CustomButton("");
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(200, 200));
        button.setBackground(new Color(178, 34, 34));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));

        FontIcon iconLabel = FontIcon.of(icon, 32, Color.WHITE);
        button.setIcon(iconLabel);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setText(text);
                button.setHover(true);
                button.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setText("");
                button.setHover(false);
                button.repaint();
            }
        });

        return button;
    }

    private CustomButton createRectangularButton(String text, FontAwesomeSolid icon) {
        CustomButton button = new CustomButton("");
        button.setContentAreaFilled(true);
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setPreferredSize(new Dimension(200, 60));
        button.setBackground(new Color(178, 34, 34));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));

        FontIcon iconLabel = FontIcon.of(icon, 32, Color.WHITE);
        button.setIcon(iconLabel);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setText(text);
                button.setHover(true);
                button.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setText("");
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
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

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
}

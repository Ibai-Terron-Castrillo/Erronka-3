package formularioak;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import klaseak.langilea;
import mantenimendua.Estilos;
import mantenimendua.LangileakKudeatu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtErabiltzailea;
    private JPasswordField txtPasahitza;
    private JButton btnAtera;
    private JButton btnSartu;

    public static void main(String[] args) {
        Estilos.aplicarEstiloGlobal(); // Aplicar estilos globales
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);  // Centrar ventana
        setTitle("Login");

        // Configurar el panel principal
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(new Color(40, 40, 40)); // Fondo oscuro
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Etiqueta Título
        JLabel lblTitle = new JLabel("Login");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(50, 20, 290, 30);
        contentPane.add(lblTitle);

        // Etiquetas de usuario y contraseña
        JLabel lblErabiltzailea = new JLabel("Erabiltzailea:");
        lblErabiltzailea.setBounds(50, 70, 100, 30);
        contentPane.add(lblErabiltzailea);

        JLabel lblPasahitza = new JLabel("Pasahitza:");
        lblPasahitza.setBounds(50, 120, 100, 30);
        contentPane.add(lblPasahitza);

        // Campos de entrada
        txtErabiltzailea = new JTextField();
        txtErabiltzailea.setBounds(160, 70, 180, 30);
        contentPane.add(txtErabiltzailea);
        txtErabiltzailea.setColumns(10);

        txtPasahitza = new JPasswordField();
        txtPasahitza.setBounds(160, 120, 180, 30);
        contentPane.add(txtPasahitza);

        // Botón Atera
        btnAtera = new JButton("Atera");
        btnAtera.setBounds(50, 180, 130, 40);
        btnAtera.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atera();
            }
        });
        contentPane.add(btnAtera);

        // Botón Sartu
        btnSartu = new JButton("Sartu");
        btnSartu.setBounds(210, 180, 130, 40);
        btnSartu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sartu();
            }
        });
        contentPane.add(btnSartu);
    }

    protected void sartu() {
        String erabiltzailea = txtErabiltzailea.getText();
        String pasahitza = String.valueOf(txtPasahitza.getPassword());

        langilea langile = new langilea();
        langile.setErabiltzailea(erabiltzailea);
        langile.setPasahitza(pasahitza);

        LangileakKudeatu langileakKudeatu = new LangileakKudeatu();
        langilea lan = langileakKudeatu.lortuLangilea(langile);

        if (lan != null) {
            JOptionPane.showMessageDialog(contentPane, "Ongi Etorri!");
            this.dispose(); 

            if (lan.isAdmin()) {
                SarreraAdmin sarrera = new SarreraAdmin();
                sarrera.setExtendedState(JFrame.MAXIMIZED_BOTH);
                sarrera.setVisible(true);
            } else {
                Sarrera sarrera = new Sarrera();
                sarrera.setExtendedState(JFrame.MAXIMIZED_BOTH);
                sarrera.setVisible(true);
            }

        } else {
            JOptionPane.showMessageDialog(contentPane, "Ez da erabiltzailea aurkitu", "Errorea",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void atera() {
        System.exit(0);
    }
}

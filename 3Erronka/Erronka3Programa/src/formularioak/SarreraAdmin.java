package formularioak;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SarreraAdmin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            // Cambiar el Look and Feel a Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SarreraAdmin frame = new SarreraAdmin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public SarreraAdmin() {
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

        JButton btnBezeroak = createCircularButton("Bezeroak");
        btnBezeroak.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BezeroakAdmin bezeroak = new BezeroakAdmin();
                bezeroak.setExtendedState(JFrame.MAXIMIZED_BOTH);
                bezeroak.setVisible(true);
                dispose();
            }
        });
        GridBagConstraints gbc_btnBezeroak = new GridBagConstraints();
        gbc_btnBezeroak.fill = GridBagConstraints.BOTH;
        gbc_btnBezeroak.insets = new Insets(10, 10, 10, 10);
        gbc_btnBezeroak.gridx = 0;
        gbc_btnBezeroak.gridy = 0;
        contentPane.add(btnBezeroak, gbc_btnBezeroak);
        
        JButton btnLangileak = createCircularButton("Langileak");
        btnLangileak.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Langileak Langileak = new Langileak();
                Langileak.setExtendedState(JFrame.MAXIMIZED_BOTH);
                Langileak.setVisible(true);
                dispose();
            }
        });
        GridBagConstraints gbc_btnLangileak = new GridBagConstraints();
        gbc_btnLangileak.fill = GridBagConstraints.BOTH;
        gbc_btnLangileak.insets = new Insets(10, 10, 10, 10);
        gbc_btnLangileak.gridx = 1;
        gbc_btnLangileak.gridy = 0;
        contentPane.add(btnLangileak, gbc_btnLangileak);

        JButton btnEskaerak = createCircularButton("Erreserbak");
        btnEskaerak.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ErreserbakAdmin eskaerak = new ErreserbakAdmin();
                eskaerak.setExtendedState(JFrame.MAXIMIZED_BOTH);
                eskaerak.setVisible(true);
                dispose();
            }
        });
        GridBagConstraints gbc_btnEskaerak = new GridBagConstraints();
        gbc_btnEskaerak.fill = GridBagConstraints.BOTH;
        gbc_btnEskaerak.insets = new Insets(10, 10, 10, 10);
        gbc_btnEskaerak.gridx = 2;
        gbc_btnEskaerak.gridy = 0;
        contentPane.add(btnEskaerak, gbc_btnEskaerak);

        JButton btnOrdutegia = createCircularButton("Ordutegia");
        btnOrdutegia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrdutegiaAdmin ordutegia = new OrdutegiaAdmin();
                ordutegia.setExtendedState(JFrame.MAXIMIZED_BOTH);
                ordutegia.setVisible(true);
                dispose();
            }
        });
        GridBagConstraints gbc_btnOrdutegia = new GridBagConstraints();
        gbc_btnOrdutegia.fill = GridBagConstraints.BOTH;
        gbc_btnOrdutegia.insets = new Insets(10, 10, 10, 10);
        gbc_btnOrdutegia.gridx = 3;
        gbc_btnOrdutegia.gridy = 0;
        contentPane.add(btnOrdutegia, gbc_btnOrdutegia);

        JButton btnEsOrdutegiak = createCircularButton("EsOrdutegiak");
        btnEsOrdutegiak.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EsOrdutegiakAdmin esOrdutegiak = new EsOrdutegiakAdmin();
                esOrdutegiak.setExtendedState(JFrame.MAXIMIZED_BOTH);
                esOrdutegiak.setVisible(true);
                dispose();
            }
        });
        GridBagConstraints gbc_btnEsOrdutegiak = new GridBagConstraints();
        gbc_btnEsOrdutegiak.fill = GridBagConstraints.BOTH;
        gbc_btnEsOrdutegiak.insets = new Insets(10, 10, 10, 10);
        gbc_btnEsOrdutegiak.gridx = 0;
        gbc_btnEsOrdutegiak.gridy = 1;
        contentPane.add(btnEsOrdutegiak, gbc_btnEsOrdutegiak);

        JButton btnEserlekuak = createCircularButton("Eserlekuak");
        btnEserlekuak.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EserlekuaAdmin eserlekuak = new EserlekuaAdmin();
                eserlekuak.setExtendedState(JFrame.MAXIMIZED_BOTH);
                eserlekuak.setVisible(true);
                dispose();
            }
        });
        GridBagConstraints gbc_btnEserlekuak = new GridBagConstraints();
        gbc_btnEserlekuak.fill = GridBagConstraints.BOTH;
        gbc_btnEserlekuak.insets = new Insets(10, 10, 10, 10);
        gbc_btnEserlekuak.gridx = 1;
        gbc_btnEserlekuak.gridy = 1;
        contentPane.add(btnEserlekuak, gbc_btnEserlekuak);

        JButton btnErrHist = createCircularButton("ErrHist");
        btnErrHist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ErrHistAdmin errHist = new ErrHistAdmin();
                errHist.setExtendedState(JFrame.MAXIMIZED_BOTH);
                errHist.setVisible(true);
                dispose();
            }
        });
        GridBagConstraints gbc_btnErrHist = new GridBagConstraints();
        gbc_btnErrHist.fill = GridBagConstraints.BOTH;
        gbc_btnErrHist.insets = new Insets(10, 10, 10, 10);
        gbc_btnErrHist.gridx = 2;
        gbc_btnErrHist.gridy = 1;
        contentPane.add(btnErrHist, gbc_btnErrHist);

        JButton btnSarrerakk = createCircularButton("Sarrerakk");
        btnSarrerakk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SarrerakAdmin sarrerakk = new SarrerakAdmin();
                sarrerakk.setExtendedState(JFrame.MAXIMIZED_BOTH);
                sarrerakk.setVisible(true);
                dispose();
            }
        });
        GridBagConstraints gbc_btnSarrerakk = new GridBagConstraints();
        gbc_btnSarrerakk.fill = GridBagConstraints.BOTH;
        gbc_btnSarrerakk.insets = new Insets(10, 10, 10, 10);
        gbc_btnSarrerakk.gridx = 0;
        gbc_btnSarrerakk.gridy = 2;
        contentPane.add(btnSarrerakk, gbc_btnSarrerakk);

        JButton btnAretoa = createCircularButton("Aretoa");
        btnAretoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aretoakAdmin aretoa = new aretoakAdmin();
                aretoa.setExtendedState(JFrame.MAXIMIZED_BOTH);
                aretoa.setVisible(true);
                dispose();
            }
        });
        GridBagConstraints gbc_btnAretoa = new GridBagConstraints();
        gbc_btnAretoa.fill = GridBagConstraints.BOTH;
        gbc_btnAretoa.insets = new Insets(10, 10, 10, 10);
        gbc_btnAretoa.gridx = 1;
        gbc_btnAretoa.gridy = 2;
        contentPane.add(btnAretoa, gbc_btnAretoa);

        JButton btnPelikulak = createCircularButton("Pelikulak");
        btnPelikulak.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pelikulak pelikulak = new Pelikulak();
                pelikulak.setExtendedState(JFrame.MAXIMIZED_BOTH);
                pelikulak.setVisible(true);
                dispose();
            }
        });
        GridBagConstraints gbc_btnPelikulak = new GridBagConstraints();
        gbc_btnPelikulak.fill = GridBagConstraints.BOTH;
        gbc_btnPelikulak.insets = new Insets(10, 10, 10, 10);
        gbc_btnPelikulak.gridx = 2;
        gbc_btnPelikulak.gridy = 2;
        contentPane.add(btnPelikulak, gbc_btnPelikulak);
    }
    
    private JButton createCircularButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button.setBackground(new Color(128, 0, 0)); // Rojo vino (dark red)
        button.setForeground(Color.WHITE); // Texto blanco
        button.setFocusPainted(false);
        
        // Establecer el tamaño del botón para que sea cuadrado
        button.setPreferredSize(new java.awt.Dimension(100, 100)); // Ajusta el tamaño a tus necesidades

        // Establecer forma circular utilizando un border
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

        // Asegurar que el fondo del botón no se vea afectado por el sistema operativo
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(true);

        return button;
    }
}

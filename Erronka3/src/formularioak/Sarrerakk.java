package formularioak;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import klaseak.Sarrerak;
import mantenimendua.SarrerakKudeatu;
import mantenimendua.sarrerakTaula;

public class Sarrerakk extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private SarrerakKudeatu dao;

    public Sarrerakk() {
        setTitle("Sarrerak");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1006, 780);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Aukerak");
        menuBar.add(menu);

        // Opción para volver a la pantalla principal
        JMenuItem bueltatu = new JMenuItem("Sarrerara Bueltatu");
        bueltatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sarrera sarrera = new Sarrera();
                sarrera.setExtendedState(JFrame.MAXIMIZED_BOTH);
                sarrera.setVisible(true);
                dispose();
            }
        });
        menu.add(bueltatu);

        // Opción para aplicar filtros de búsqueda
        JMenuItem filtratu = new JMenuItem("Bilaketa Filtroak Aplikatu");
        filtratu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] opciones = { "ID Erreserba", "Prezio Tartea", "ID Eserleku" };
                String irizpidea = (String) JOptionPane.showInputDialog(
                        Sarrerakk.this,
                        "Aukeratu irizpidea:",
                        "Filtroa Aplikatu",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]); // Valor por defecto es "ID Erreserba"

                if (irizpidea != null && !irizpidea.trim().isEmpty()) {
                    switch (irizpidea) {
                        case "ID Erreserba":
                            filtrarPorErreserbaId();
                            break;
                        case "Prezio Tartea":
                            filtrarPorPrezioTartea();
                            break;
                        case "ID Eserleku":
                            filtrarPorEserlekuId();
                            break;
                        default:
                            break;
                    }
                } else {
                    taulaBirkargatu();
                }
            }
        });
        menu.add(filtratu);

        // Opción para recargar la tabla
        JMenuItem birkargatu = new JMenuItem("Taula Birkargatu");
        birkargatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taulaBirkargatu();
            }
        });
        menu.add(birkargatu);

        // Opción para cerrar sesión
        JMenuItem saioaItxi = new JMenuItem("Saioa Itxi");
        saioaItxi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
                dispose();
            }
        });
        menu.add(saioaItxi);

        setJMenuBar(menuBar);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        dao = new SarrerakKudeatu();
        taulaBirkargatu();
    }

    private void filtrarPorErreserbaId() {
        String idErreserba = JOptionPane.showInputDialog(this, "Sartu Erreserbaren ID-a:", "ID Erreserbaren arabera filtratu", JOptionPane.QUESTION_MESSAGE);
        if (idErreserba != null && !idErreserba.trim().isEmpty()) {
            try {
                int idErreserbaInt = Integer.parseInt(idErreserba);
                List<Sarrerak> filtratutakoLista = dao.filtratuSarrerakErreserbaId(idErreserbaInt);
                table.setModel(new sarrerakTaula(filtratutakoLista));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID Erreserba zenbakizkoa izan behar da.", "Errorea", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void filtrarPorPrezioTartea() {
        String prezioMin = JOptionPane.showInputDialog(this, "Sartu prezio minimoa:", "Prezio Tartearen arabera filtratu", JOptionPane.QUESTION_MESSAGE);
        String prezioMax = JOptionPane.showInputDialog(this, "Sartu prezio maximoa:", "Prezio Tartearen arabera filtratu", JOptionPane.QUESTION_MESSAGE);

        if (prezioMin != null && prezioMax != null && !prezioMin.trim().isEmpty() && !prezioMax.trim().isEmpty()) {
            try {
                double prezioMinDouble = Double.parseDouble(prezioMin);
                double prezioMaxDouble = Double.parseDouble(prezioMax);
                List<Sarrerak> filtratutakoLista = dao.filtratuSarrerakPrezioTartea(prezioMinDouble, prezioMaxDouble);
                table.setModel(new sarrerakTaula(filtratutakoLista));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Prezio minimoa eta maximoa zenbakizkoak izan behar dira.", "Errorea", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void filtrarPorEserlekuId() {
        String idEserleku = JOptionPane.showInputDialog(this, "Sartu Eserlekuaren ID-a:", "ID Eserlekuaren arabera filtratu", JOptionPane.QUESTION_MESSAGE);
        if (idEserleku != null && !idEserleku.trim().isEmpty()) {
            try {
                int idEserlekuInt = Integer.parseInt(idEserleku);
                List<Sarrerak> filtratutakoLista = dao.filtratuSarrerakEserlekuId(idEserlekuInt);
                table.setModel(new sarrerakTaula(filtratutakoLista));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID Eserlekua zenbakizkoa izan behar da.", "Errorea", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    protected void taulaBirkargatu() {
        List<Sarrerak> lista = dao.lortuSarrerak();
        sarrerakTaula model = new sarrerakTaula(lista);
        if (table == null) {
            table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            contentPane.add(scrollPane, BorderLayout.CENTER);
        } else {
            table.setModel(model);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Sarrerakk frame = new Sarrerakk();
            frame.setVisible(true);
        });
    }
}
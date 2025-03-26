package formularioak;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import klaseak.Aretoa;
import mantenimendua.aretoaKudeatu;
import mantenimendua.aretoaTaula;

public class aretoa extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private aretoaKudeatu dao;

    public aretoa() {
        setTitle("Aretoa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1006, 780);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Aukerak");
        menuBar.add(menu);

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

        JMenuItem filtratu = new JMenuItem("Bilaketa Filtroak Aplikatu");
        filtratu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String irizpidea = JOptionPane.showInputDialog(aretoa.this, 
                        "Sartu bilaketa irizpidea (Izena, etab...):");
                if (irizpidea != null && !irizpidea.trim().isEmpty()) {
                    List<Aretoa> filtratutakoLista = dao.filtratuAretoak(irizpidea);
                    aretoaTaula newModel = new aretoaTaula(filtratutakoLista);
                    table.setModel(newModel);
                } else {
                    List<Aretoa> listaOriginal = dao.lortuAretoak();
                    table.setModel(new aretoaTaula(listaOriginal));
                }
            }
        });
        menu.add(filtratu);
        
        JMenuItem birkargatu = new JMenuItem("Taula Birkargatu");
        birkargatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taulaBirkargatu();
            }

            private void taulaBirkargatu() {
                List<Aretoa> lista = dao.lortuAretoak();
                aretoaTaula model = new aretoaTaula(lista);
                if (table == null) {
                    table = new JTable(model);
                    JScrollPane scrollPane = new JScrollPane(table);
                    contentPane.add(scrollPane, BorderLayout.CENTER);
                } else {
                    table.setModel(model);
                }
            }
        });
        menu.add(birkargatu);
        
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

        dao = new aretoaKudeatu();
        List<Aretoa> lista = dao.lortuAretoak();
        aretoaTaula model = new aretoaTaula(lista);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            aretoa frame = new aretoa();
            frame.setVisible(true);
        });
    }
}

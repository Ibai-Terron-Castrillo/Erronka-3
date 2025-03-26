package formularioak;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import klaseak.Eserlekua;
import mantenimendua.EserlekuaKudeatu;
import mantenimendua.EserlekuaTaula;

public class Eserlekuak extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private EserlekuaKudeatu dao;

    public Eserlekuak() {
        setTitle("Eserlekuak");
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
        filtratu.addActionListener(e -> {
            String irizpidea = JOptionPane.showInputDialog(Eserlekuak.this, 
                    "Sartu bilaketa irizpidea (Izena, data, etab...):");
            if (irizpidea != null && !irizpidea.trim().isEmpty()) {
                List<Eserlekua> filtratutakoLista = dao.filtratuEserlekuak(irizpidea);
                EserlekuaTaula newModel = new EserlekuaTaula(filtratutakoLista);
                table.setModel(newModel);
            } else {
                List<Eserlekua> listaOriginal = dao.lortuEserlekuak();
                table.setModel(new EserlekuaTaula(listaOriginal));
            }
        });
        menu.add(filtratu);

        JMenuItem birkargatu = new JMenuItem("Taula Birkargatu");
        birkargatu.addActionListener(e -> taulaBirkargatu());
        menu.add(birkargatu);

        JMenuItem saioaItxi = new JMenuItem("Saioa Itxi");
        saioaItxi.addActionListener(e -> {
            Login login = new Login();
            login.setVisible(true);
            dispose();
        });
        menu.add(saioaItxi);

        setJMenuBar(menuBar);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        dao = new EserlekuaKudeatu();
        List<Eserlekua> lista = dao.lortuEserlekuak();
        EserlekuaTaula model = new EserlekuaTaula(lista);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    protected void taulaBirkargatu() {
        List<Eserlekua> lista = dao.lortuEserlekuak();
        EserlekuaTaula model = new EserlekuaTaula(lista);
        table.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Eserlekuak frame = new Eserlekuak();
            frame.setVisible(true);
        });
    }
}

package formularioak;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import mantenimendua.OrdutegiaKudeatu;
import mantenimendua.OrdutegiaTaula;
import klaseak.Ordutegia;

public class OrdutegiaAdmin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private OrdutegiaKudeatu dao;

    public OrdutegiaAdmin() {
        setTitle("Ordutegia - Administratzailea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1006, 780);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Aukerak");
        menuBar.add(menu);

        JMenuItem sortu = new JMenuItem("Ordutegia berria gehitu");
        sortu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrdutegiaSortu formulario = new OrdutegiaSortu(OrdutegiaAdmin.this, dao);
                formulario.setVisible(true);
            }
        });
        menu.add(sortu);

        JMenuItem eguneratu = new JMenuItem("Ordutegiaren datuak eguneratu");
        eguneratu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    Ordutegia ordutegia = ((OrdutegiaTaula) table.getModel()).getOrdutegiaAt(filaSeleccionada);
                    OrdutegiaEguneratu formulario = new OrdutegiaEguneratu(OrdutegiaAdmin.this, dao, ordutegia);
                    formulario.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(OrdutegiaAdmin.this, "Aukeratu ordutegi bat lehenik");
                }
            }
        });
        menu.add(eguneratu);

        JMenuItem ezabatu = new JMenuItem("Ordutegi bat ezabatu");
        ezabatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    int idOrdutegia = (int) table.getValueAt(row, 0);
                    int confirm = JOptionPane.showConfirmDialog(OrdutegiaAdmin.this, 
                            "Ziur zaude ordutegi hau ezabatu nahi duzula?", "Ezabatu", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        dao.ezabatuOrdutegia(idOrdutegia);
                        taulaBirkargatu();
                        JOptionPane.showMessageDialog(OrdutegiaAdmin.this, "Ordutegia ezabatua!");
                    }
                } else {
                    JOptionPane.showMessageDialog(OrdutegiaAdmin.this, "Aukeratu ordutegi bat lehenik!");
                }
            }
        });
        menu.add(ezabatu);

        JMenuItem bueltatu = new JMenuItem("Sarrerara Bueltatu");
        bueltatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SarreraAdmin sarrera = new SarreraAdmin();
                sarrera.setExtendedState(JFrame.MAXIMIZED_BOTH);
                sarrera.setVisible(true);
                dispose();
            }
        });
        menu.add(bueltatu);
        
        JMenuItem filtratu = new JMenuItem("Bilaketa Filtroak Aplikatu");
        filtratu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String irizpidea = JOptionPane.showInputDialog(OrdutegiaAdmin.this, 
                        "Sartu bilaketa irizpidea (Izena, Mota, etab...):");
                if (irizpidea != null && !irizpidea.trim().isEmpty()) {
                    List<Ordutegia> filtratutakoLista = dao.filtratuOrdutegia(irizpidea);
                    OrdutegiaTaula newModel = new OrdutegiaTaula(filtratutakoLista);
                    table.setModel(newModel);
                } else {
                    List<Ordutegia> listaOriginal = dao.lortuOrdutegia();
                    table.setModel(new OrdutegiaTaula(listaOriginal));
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
                List<Ordutegia> lista = dao.lortuOrdutegia();
                OrdutegiaTaula model = new OrdutegiaTaula(lista);
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

        dao = new OrdutegiaKudeatu();
        taulaBirkargatu();
    }

    protected void taulaBirkargatu() {
        List<Ordutegia> lista = dao.lortuOrdutegia();
        OrdutegiaTaula model = new OrdutegiaTaula(lista);
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
            OrdutegiaAdmin frame = new OrdutegiaAdmin();
            frame.setVisible(true);
        });
    }
}

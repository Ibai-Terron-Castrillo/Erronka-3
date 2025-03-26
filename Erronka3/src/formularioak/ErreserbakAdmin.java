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

import klaseak.Erreserba;
import mantenimendua.ErreserbakKudeatu;
import mantenimendua.ErreserbakTaula;

public class ErreserbakAdmin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private ErreserbakKudeatu dao;

    public ErreserbakAdmin() {
        setTitle("Eskaerak");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1006, 780);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Aukerak");
        menuBar.add(menu);
        
        JMenuItem sortu = new JMenuItem("Erreserba Sortu");
        sortu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ErreserbakSortu formulario = new ErreserbakSortu(ErreserbakAdmin.this, dao);
                formulario.setVisible(true);
            }
        });
        menu.add(sortu);
        
        JMenuItem eguneratu = new JMenuItem("Erreserba Eguneratu");
        eguneratu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    Erreserba seleccionado = ((ErreserbakTaula) table.getModel()).getErreserbaAt(filaSeleccionada);
                    ErreserbakEguneratu formulario = new ErreserbakEguneratu(ErreserbakAdmin.this, dao, seleccionado);
                    formulario.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(ErreserbakAdmin.this, "Ezarri Erreserba bat lehenik");
                }
            }
        });
        menu.add(eguneratu);

        JMenuItem ezabatu = new JMenuItem("Erreserba Ezabatu");
        ezabatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    int idEskaera = (int) table.getValueAt(row, 0);
                    int confirm = JOptionPane.showConfirmDialog(ErreserbakAdmin.this, 
                            "Ziur zaude Erreserba hau ezabatu nahi duzula?", "Erreserba Ezabatu", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        dao.ezabatuEskaera(idEskaera);
                        taulaBirkargatu();
                        JOptionPane.showMessageDialog(ErreserbakAdmin.this, "Erreserba ezabatua!");
                    }
                } else {
                    JOptionPane.showMessageDialog(ErreserbakAdmin.this, "Lehenik erreserba bat aukeratu!");
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
                String irizpidea = JOptionPane.showInputDialog(ErreserbakAdmin.this, 
                        "Sartu bilaketa irizpidea (Helbidea, Egoera, etab...):");
                if (irizpidea != null && !irizpidea.trim().isEmpty()) {
                    List<Erreserba> filtratutakoLista = dao.filtratuEskaerak(irizpidea);
                    ErreserbakTaula newModel = new ErreserbakTaula(filtratutakoLista);
                    table.setModel(newModel);
                } else {
                    List<Erreserba> listaOriginal = dao.lortuEskaerak();
                    table.setModel(new ErreserbakTaula(listaOriginal));
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
                List<Erreserba> lista = dao.lortuEskaerak();
                ErreserbakTaula model = new ErreserbakTaula(lista);
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

        dao = new ErreserbakKudeatu();
        List<Erreserba> lista = dao.lortuEskaerak();
        ErreserbakTaula model = new ErreserbakTaula(lista);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    protected void taulaBirkargatu() {
        List<Erreserba> lista = dao.lortuEskaerak();
        ErreserbakTaula model = new ErreserbakTaula(lista);
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
            ErreserbakAdmin frame = new ErreserbakAdmin();
            frame.setVisible(true);
        });
    }
}

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

import klaseak.Bezeroa;
import mantenimendua.BezeroakKudeatu;
import mantenimendua.BezeroakTaula;

public class BezeroakAdmin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private BezeroakKudeatu dao;

    public BezeroakAdmin() {
        setTitle("Bezeroak");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1006, 780);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Aukerak");
        menuBar.add(menu);

        JMenuItem sortu = new JMenuItem("Bezeroa Sortu");
        sortu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BezeroaSortu formulario = new BezeroaSortu(BezeroakAdmin.this, dao);
                formulario.setVisible(true);
            }
        });
        menu.add(sortu);
        
        JMenuItem eguneratu = new JMenuItem("Bezeroa Eguneratu");
        eguneratu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    Bezeroa seleccionado = ((BezeroakTaula) table.getModel()).getBezeroaAt(filaSeleccionada);
                    BezeroaEguneratu formulario = new BezeroaEguneratu(BezeroakAdmin.this, dao, seleccionado);
                    formulario.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(BezeroakAdmin.this, "Ezarri bezero bat lehenik");
                }
            }
        });
        menu.add(eguneratu);

        JMenuItem ezabatu = new JMenuItem("Bezeroa Ezabatu");
        ezabatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    int idBezeroa = (int) table.getValueAt(row, 0);
                    int confirm = JOptionPane.showConfirmDialog(BezeroakAdmin.this, 
                            "Ziur zaude bezero hau ezabatu nahi duzula?", "Bezeroa Ezabatu", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        dao.ezabatuBezeroa(idBezeroa);
                        taulaBirkargatu();
                        JOptionPane.showMessageDialog(BezeroakAdmin.this, "Bezeroa ezabatua!");
                    }
                } else {
                    JOptionPane.showMessageDialog(BezeroakAdmin.this, "Lehenik bezero bat aukeratu!");
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
                String irizpidea = JOptionPane.showInputDialog(BezeroakAdmin.this, 
                        "Sartu bilaketa irizpidea (Izena, NAN-a, etab...):");
                if (irizpidea != null && !irizpidea.trim().isEmpty()) {
                    List<Bezeroa> filtratutakoLista = dao.filtratuBezeroak(irizpidea);
                    BezeroakTaula newModel = new BezeroakTaula(filtratutakoLista);
                    table.setModel(newModel);
                } else {
                    List<Bezeroa> listaOriginal = dao.lortuBezeroak();
                    table.setModel(new BezeroakTaula(listaOriginal));
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
		        List<Bezeroa> lista = dao.lortuBezeroak();
		        BezeroakTaula model = new BezeroakTaula(lista);
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

        dao = new BezeroakKudeatu();
        List<Bezeroa> lista = dao.lortuBezeroak();
        BezeroakTaula model = new BezeroakTaula(lista);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    protected void taulaBirkargatu() {
        List<Bezeroa> lista = dao.lortuBezeroak();
        BezeroakTaula model = new BezeroakTaula(lista);
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
            BezeroakAdmin frame = new BezeroakAdmin();
            frame.setVisible(true);
        });
    }
}

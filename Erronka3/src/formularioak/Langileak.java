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

import klaseak.langilea;
import mantenimendua.LangileakKudeatu;
import mantenimendua.LangileakTaula;

public class Langileak extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private LangileakKudeatu dao;

    public Langileak() {
        setTitle("Langileak");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1006, 780);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Aukerak");
        menuBar.add(menu);

        JMenuItem sortu = new JMenuItem("Langilea Sortu");
        sortu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LangileaSortu formulario = new LangileaSortu(Langileak.this, dao);
                formulario.setVisible(true);
            }
        });
        menu.add(sortu);
        
        JMenuItem eguneratu = new JMenuItem("Langilea Eguneratu");
        eguneratu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    langilea seleccionado = ((LangileakTaula) table.getModel()).getLangileaAt(filaSeleccionada);
                    LangileaEguneratu formulario = new LangileaEguneratu(Langileak.this, dao, seleccionado);
                    formulario.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(Langileak.this, "Ezarri Langile bat lehenik");
                }
            }
        });
        menu.add(eguneratu);

        JMenuItem ezabatu = new JMenuItem("Langilea Ezabatu");
        ezabatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    int idLangilea = (int) table.getValueAt(row, 0);
                    int confirm = JOptionPane.showConfirmDialog(Langileak.this, 
                            "Ziur zaude Langile hau ezabatu nahi duzula?", "Langilea Ezabatu", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        dao.ezabatuLangilea(idLangilea);
                        taulaBirkargatu();
                        JOptionPane.showMessageDialog(Langileak.this, "Langilea ezabatua!");
                    }
                } else {
                    JOptionPane.showMessageDialog(Langileak.this, "Lehenik Langile bat aukeratu!");
                }
            }
        });
        menu.add(ezabatu);


        JMenuItem bueltatu = new JMenuItem("Sarrerara Bueltatu");
        bueltatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SarrerakAdmin sarrera = new SarrerakAdmin();
                sarrera.setExtendedState(JFrame.MAXIMIZED_BOTH);
                sarrera.setVisible(true);
                dispose();
            }
        });
        menu.add(bueltatu);

        JMenuItem filtratu = new JMenuItem("Bilaketa Filtroak Aplikatu");
        filtratu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String irizpidea = JOptionPane.showInputDialog(Langileak.this, 
                        "Sartu bilaketa irizpidea (Izena, NAN-a, etab...):");
                if (irizpidea != null && !irizpidea.trim().isEmpty()) {
                    List<langilea> filtratutakoLista = dao.filtratuLangileak(irizpidea);
                    LangileakTaula newModel = new LangileakTaula(filtratutakoLista);
                    table.setModel(newModel);
                } else {
                    List<langilea> listaOriginal = dao.lortuLangileak();
                    table.setModel(new LangileakTaula(listaOriginal));
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
                List<langilea> lista = dao.lortuLangileak();
                LangileakTaula model = new LangileakTaula(lista);
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

        dao = new LangileakKudeatu();
        List<langilea> lista = dao.lortuLangileak();
        LangileakTaula model = new LangileakTaula(lista);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    protected void taulaBirkargatu() {
        List<langilea> lista = dao.lortuLangileak();
        LangileakTaula model = new LangileakTaula(lista);
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
            Langileak frame = new Langileak();
            frame.setVisible(true);
        });
    }
}

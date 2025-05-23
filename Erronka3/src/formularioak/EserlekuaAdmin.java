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

import klaseak.Eserlekua;
import mantenimendua.EserlekuaKudeatu;
import mantenimendua.EserlekuaTaula;

public class EserlekuaAdmin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private EserlekuaKudeatu dao;

    public EserlekuaAdmin() {
        setTitle("Eserlekua - Administratzailea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1006, 780);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Aukerak");
        menuBar.add(menu);

        JMenuItem sortu = new JMenuItem("Eserleku berria gehitu");
        sortu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EserlekuakSortu formulario = new EserlekuakSortu(EserlekuaAdmin.this, dao);
                formulario.setVisible(true);
            }
        });
        menu.add(sortu);

        JMenuItem eguneratu = new JMenuItem("Eserlekuaren datuak eguneratu");
        eguneratu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    Eserlekua eserlekua = ((EserlekuaTaula) table.getModel()).getEserlekuaAt(filaSeleccionada);
                    EserlekuakEguneratu formulario = new EserlekuakEguneratu(EserlekuaAdmin.this, dao, eserlekua);
                    formulario.setVisible(true);                } else {
                    JOptionPane.showMessageDialog(EserlekuaAdmin.this, "Aukeratu eserleku bat lehenik");
                }
            }
        });
        menu.add(eguneratu);

        JMenuItem ezabatu = new JMenuItem("Eserleku bat ezabatu");
        ezabatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    int idEserlekua = (int) table.getValueAt(row, 0);
                    int confirm = JOptionPane.showConfirmDialog(EserlekuaAdmin.this, 
                            "Ziur zaude eserleku hau ezabatu nahi duzula?", "Ezabatu", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        dao.ezabatuEserlekua(idEserlekua);
                        taulaBirkargatu();
                        JOptionPane.showMessageDialog(EserlekuaAdmin.this, "Eserlekua ezabatua!");
                    }
                } else {
                    JOptionPane.showMessageDialog(EserlekuaAdmin.this, "Aukeratu eserleku bat lehenik!");
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
                String[] opciones = { "ID", "ID Areto", "Zenbakia", "Beteta" };
                String irizpidea = (String) JOptionPane.showInputDialog(
                        EserlekuaAdmin.this,
                        "Aukeratu irizpidea:",
                        "Filtroa Aplikatu",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]);

                if (irizpidea != null && !irizpidea.trim().isEmpty()) {
                    if (irizpidea.equals("ID")) {
                        String id = JOptionPane.showInputDialog(EserlekuaAdmin.this,
                                "Sartu eserlekuaren ID-a:",
                                "ID-ren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (id != null && !id.trim().isEmpty()) {
                            List<Eserlekua> filtratutakoLista = dao.filtratuEserlekuak(id);
                            EserlekuaTaula newModel = new EserlekuaTaula(filtratutakoLista);
                            table.setModel(newModel);
                        }
                    } else if (irizpidea.equals("ID Areto")) {
                        String idAreto = JOptionPane.showInputDialog(EserlekuaAdmin.this,
                                "Sartu aretoaren ID-a:",
                                "ID Aretoaren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (idAreto != null && !idAreto.trim().isEmpty()) {
                            try {
                                int idAretoInt = Integer.parseInt(idAreto);
                                List<Eserlekua> filtratutakoLista = dao.filtratuEserlekuakAretoId(idAretoInt);
                                EserlekuaTaula newModel = new EserlekuaTaula(filtratutakoLista);
                                table.setModel(newModel);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(EserlekuaAdmin.this,
                                        "ID Areto zenbakizkoa izan behar da.",
                                        "Errorea",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else if (irizpidea.equals("Zenbakia")) {
                        String zenbakia = JOptionPane.showInputDialog(EserlekuaAdmin.this,
                                "Sartu eserlekuaren zenbakia:",
                                "Zenbakiaren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (zenbakia != null && !zenbakia.trim().isEmpty()) {
                            List<Eserlekua> filtratutakoLista = dao.filtratuEserlekuak(zenbakia);
                            EserlekuaTaula newModel = new EserlekuaTaula(filtratutakoLista);
                            table.setModel(newModel);
                        }
                    } 
                } else {
                    List<Eserlekua> listaOriginal = dao.lortuEserlekuak();
                    table.setModel(new EserlekuaTaula(listaOriginal));
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
                List<Eserlekua> lista = dao.lortuEserlekuak();
                EserlekuaTaula model = new EserlekuaTaula(lista);
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
                // Implementar la lógica para cerrar sesión
            }
        });
        menu.add(saioaItxi);
        setJMenuBar(menuBar);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        dao = new EserlekuaKudeatu();
        taulaBirkargatu();
    }

    protected void taulaBirkargatu() {
        List<Eserlekua> lista = dao.lortuEserlekuak();
        EserlekuaTaula model = new EserlekuaTaula(lista);
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
            EserlekuaAdmin frame = new EserlekuaAdmin();
            frame.setVisible(true);
        });
    }
}
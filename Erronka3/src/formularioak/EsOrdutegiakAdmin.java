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

import klaseak.EsOrdutegia;
import mantenimendua.EsOrdKudeatu;
import mantenimendua.EsOrdTaula;

public class EsOrdutegiakAdmin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private EsOrdKudeatu dao;

    public EsOrdutegiakAdmin() {
        setTitle("Eserlekua Ordutegian - Administratzailea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1006, 780);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Aukerak");
        menuBar.add(menu);

        JMenuItem sortu = new JMenuItem("Eserlekua Ordutegian berria gehitu");
        sortu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EsOrduSortu formulario = new EsOrduSortu(EsOrdutegiakAdmin.this, dao);
                formulario.setVisible(true);
            }
        });
        menu.add(sortu);

        JMenuItem eguneratu = new JMenuItem("Eserlekua Ordutegian datuak eguneratu");
        eguneratu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    EsOrdutegia esOrdutegia = ((EsOrdTaula) table.getModel()).getEsOrdutegiaAt(filaSeleccionada);
                    EsOrduEguneratu formulario = new EsOrduEguneratu(EsOrdutegiakAdmin.this, dao, esOrdutegia);
                    formulario.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(EsOrdutegiakAdmin.this, "Aukeratu eserlekua ordutegian bat lehenik");
                }
            }
        });
        menu.add(eguneratu);

        JMenuItem ezabatu = new JMenuItem("Eserlekua Ordutegian bat ezabatu");
        ezabatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    int idEsOrdutegia = (int) table.getValueAt(row, 0);
                    int confirm = JOptionPane.showConfirmDialog(EsOrdutegiakAdmin.this, 
                            "Ziur zaude eserlekua ordutegian hau ezabatu nahi duzula?", "Ezabatu", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        dao.ezabatuEsOrdutegia(idEsOrdutegia);
                        taulaBirkargatu();
                        JOptionPane.showMessageDialog(EsOrdutegiakAdmin.this, "Eserlekua Ordutegian ezabatua!");
                    }
                } else {
                    JOptionPane.showMessageDialog(EsOrdutegiakAdmin.this, "Aukeratu eserlekua ordutegian bat lehenik!");
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

     // Opción para aplicar filtros de búsqueda
        JMenuItem filtratu = new JMenuItem("Bilaketa Filtroak Aplikatu");
        filtratu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear un cuadro de diálogo para elegir el criterio de filtrado
                String[] opciones = { "ID", "ID Eserlekua", "ID Ordutegi", "Beteta" }; // Opciones de filtrado
                String irizpidea = (String) JOptionPane.showInputDialog(
                        EsOrdutegiakAdmin.this,
                        "Aukeratu irizpidea:",
                        "Filtroa Aplikatu",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]); // Valor por defecto es "ID"

                // Si el usuario selecciona un criterio de filtrado
                if (irizpidea != null && !irizpidea.trim().isEmpty()) {
                    if (irizpidea.equals("ID")) {
                        // Filtrar por ID
                        String id = JOptionPane.showInputDialog(EsOrdutegiakAdmin.this,
                                "Sartu ID-a:",
                                "ID-ren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (id != null && !id.trim().isEmpty()) {
                            List<EsOrdutegia> filtratutakoLista = dao.filtratuEsOrdutegiak(id);
                            EsOrdTaula newModel = new EsOrdTaula(filtratutakoLista);
                            table.setModel(newModel);
                        }
                    } else if (irizpidea.equals("ID Eserlekua")) {
                        // Filtrar por ID Eserlekua
                        String idEserlekua = JOptionPane.showInputDialog(EsOrdutegiakAdmin.this,
                                "Sartu eserlekuaren ID-a:",
                                "ID Eserlekuaren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (idEserlekua != null && !idEserlekua.trim().isEmpty()) {
                            try {
                                int idEserlekuaInt = Integer.parseInt(idEserlekua);
                                List<EsOrdutegia> filtratutakoLista = dao.filtratuEsOrdutegiakEserlekuaId(idEserlekuaInt);
                                EsOrdTaula newModel = new EsOrdTaula(filtratutakoLista);
                                table.setModel(newModel);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(EsOrdutegiakAdmin.this,
                                        "ID Eserlekua zenbakizkoa izan behar da.",
                                        "Errorea",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else if (irizpidea.equals("ID Ordutegi")) {
                        // Filtrar por ID Ordutegi
                        String idOrdutegi = JOptionPane.showInputDialog(EsOrdutegiakAdmin.this,
                                "Sartu ordutegiaren ID-a:",
                                "ID Ordutegiaren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (idOrdutegi != null && !idOrdutegi.trim().isEmpty()) {
                            try {
                                int idOrdutegiInt = Integer.parseInt(idOrdutegi);
                                List<EsOrdutegia> filtratutakoLista = dao.filtratuEsOrdutegiakOrdutegiId(idOrdutegiInt);
                                EsOrdTaula newModel = new EsOrdTaula(filtratutakoLista);
                                table.setModel(newModel);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(EsOrdutegiakAdmin.this,
                                        "ID Ordutegi zenbakizkoa izan behar da.",
                                        "Errorea",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else if (irizpidea.equals("Beteta")) {
                        // Filtrar por estado (Beteta)
                        String beteta = JOptionPane.showInputDialog(EsOrdutegiakAdmin.this,
                                "Sartu beteta egoera (true/false):",
                                "Betetaren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (beteta != null && !beteta.trim().isEmpty()) {
                            try {
                                boolean betetaBool = Boolean.parseBoolean(beteta);
                                List<EsOrdutegia> filtratutakoLista = dao.filtratuEsOrdutegiakBeteta(betetaBool);
                                EsOrdTaula newModel = new EsOrdTaula(filtratutakoLista);
                                table.setModel(newModel);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(EsOrdutegiakAdmin.this,
                                        "Beteta egoera true edo false izan behar da.",
                                        "Errorea",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } else {
                    // Si no se elige ningún filtro, mostrar todos los registros
                    List<EsOrdutegia> listaOriginal = dao.lortuEsOrdutegiak();
                    table.setModel(new EsOrdTaula(listaOriginal));
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
                List<EsOrdutegia> lista = dao.lortuEsOrdutegiak();
                EsOrdTaula model = new EsOrdTaula(lista);
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

        dao = new EsOrdKudeatu();
        taulaBirkargatu();
    }

    protected void taulaBirkargatu() {
        List<EsOrdutegia> lista = dao.lortuEsOrdutegiak();
        EsOrdTaula model = new EsOrdTaula(lista);
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
            EsOrdutegiakAdmin frame = new EsOrdutegiakAdmin();
            frame.setVisible(true);
        });
    }
}
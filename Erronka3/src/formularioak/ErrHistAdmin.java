package formularioak;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
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

import klaseak.ErrHistorikoa;
import mantenimendua.ErrHistKudeatu;
import mantenimendua.ErrHistTaula;

public class ErrHistAdmin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private ErrHistKudeatu dao;

    public ErrHistAdmin() {
        setTitle("Erreserbak Historikoa - Administratzailea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1006, 780);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Aukerak");
        menuBar.add(menu);

       

        JMenuItem eguneratu = new JMenuItem("Erreserbaren datuak eguneratu");
        eguneratu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    ErrHistorikoa errHistorikoa = ((ErrHistTaula) table.getModel()).getErrHistorikoaAt(filaSeleccionada);
                    ErrHistEguneratu formulario = new ErrHistEguneratu(ErrHistAdmin.this, dao, errHistorikoa);
                    formulario.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(ErrHistAdmin.this, "Aukeratu erreserba bat lehenik");
                }
            }
        });
        menu.add(eguneratu);

        JMenuItem ezabatu = new JMenuItem("Erreserba bat ezabatu");
        ezabatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    int idErreserba = (int) table.getValueAt(row, 0);
                    int confirm = JOptionPane.showConfirmDialog(ErrHistAdmin.this, 
                            "Ziur zaude erreserba hau ezabatu nahi duzula?", "Ezabatu", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        dao.ezabatuEskaera(idErreserba);
                        taulaBirkargatu();
                        JOptionPane.showMessageDialog(ErrHistAdmin.this, "Erreserba ezabatua!");
                    }
                } else {
                    JOptionPane.showMessageDialog(ErrHistAdmin.this, "Aukeratu erreserba bat lehenik!");
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
                String[] opciones = { "ID", "ID Bezero", "Amaiera Data" }; // Opciones de filtrado
                String irizpidea = (String) JOptionPane.showInputDialog(
                        ErrHistAdmin.this,
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
                        String id = JOptionPane.showInputDialog(ErrHistAdmin.this,
                                "Sartu erreserbaren ID-a:",
                                "ID-ren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (id != null && !id.trim().isEmpty()) {
                            List<ErrHistorikoa> filtratutakoLista = dao.filtratuEskaerak(id);
                            ErrHistTaula newModel = new ErrHistTaula(filtratutakoLista);
                            table.setModel(newModel);
                        }
                    } else if (irizpidea.equals("ID Bezero")) {
                        // Filtrar por ID Bezero
                        String idBezeroa = JOptionPane.showInputDialog(ErrHistAdmin.this,
                                "Sartu bezeroaren ID-a:",
                                "ID Bezeroaren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (idBezeroa != null && !idBezeroa.trim().isEmpty()) {
                            try {
                                int idBezero = Integer.parseInt(idBezeroa);
                                List<ErrHistorikoa> filtratutakoLista = dao.filtratuEskaerakBezeroId(idBezero);
                                ErrHistTaula newModel = new ErrHistTaula(filtratutakoLista);
                                table.setModel(newModel);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(ErrHistAdmin.this,
                                        "ID Bezero zenbakizkoa izan behar da.",
                                        "Errorea",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else if (irizpidea.equals("Amaiera Data")) {
                        // Filtrar por Amaiera Data
                        String amaieraData = JOptionPane.showInputDialog(ErrHistAdmin.this,
                                "Sartu amaiera data (YYYY-MM-DD):",
                                "Amaiera Data-ren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (amaieraData != null && !amaieraData.trim().isEmpty()) {
                            try {
                                Date data = Date.valueOf(amaieraData);
                                List<ErrHistorikoa> filtratutakoLista = dao.filtratuEskaerakAmaieraData(data);
                                ErrHistTaula newModel = new ErrHistTaula(filtratutakoLista);
                                table.setModel(newModel);
                            } catch (IllegalArgumentException ex) {
                                JOptionPane.showMessageDialog(ErrHistAdmin.this,
                                        "Data formatu egokia izan behar da (YYYY-MM-DD).",
                                        "Errorea",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } else {
                    // Si no se elige ningún filtro, mostrar todos los registros
                    List<ErrHistorikoa> listaOriginal = dao.lortuEskaerak();
                    table.setModel(new ErrHistTaula(listaOriginal));
                }
            }
        });
        menu.add(filtratu);


        JMenuItem birkargatu = new JMenuItem("Taula Birkargatu");
        birkargatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taulaBirkargatu();
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

        dao = new ErrHistKudeatu();
        taulaBirkargatu();
    }

    protected void taulaBirkargatu() {
        List<ErrHistorikoa> lista = dao.lortuEskaerak();
        ErrHistTaula model = new ErrHistTaula(lista);
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
            ErrHistAdmin frame = new ErrHistAdmin();
            frame.setVisible(true);
        });
    }
}
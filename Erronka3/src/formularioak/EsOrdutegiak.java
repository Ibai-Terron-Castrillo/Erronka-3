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

public class EsOrdutegiak extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private EsOrdKudeatu dao;

    public EsOrdutegiak() {
        setTitle("Eserlekua Ordutegian");
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
                // Crear un cuadro de diálogo para elegir el criterio de filtrado
                String[] opciones = { "ID", "ID Eserlekua", "ID Ordutegi", "Beteta" }; // Opciones de filtrado
                String irizpidea = (String) JOptionPane.showInputDialog(
                        EsOrdutegiak.this,
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
                        String id = JOptionPane.showInputDialog(EsOrdutegiak.this,
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
                        String idEserlekua = JOptionPane.showInputDialog(EsOrdutegiak.this,
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
                                JOptionPane.showMessageDialog(EsOrdutegiak.this,
                                        "ID Eserlekua zenbakizkoa izan behar da.",
                                        "Errorea",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else if (irizpidea.equals("ID Ordutegi")) {
                        // Filtrar por ID Ordutegi
                        String idOrdutegi = JOptionPane.showInputDialog(EsOrdutegiak.this,
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
                                JOptionPane.showMessageDialog(EsOrdutegiak.this,
                                        "ID Ordutegi zenbakizkoa izan behar da.",
                                        "Errorea",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else if (irizpidea.equals("Beteta")) {
                        // Filtrar por estado (Beteta)
                        String beteta = JOptionPane.showInputDialog(EsOrdutegiak.this,
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
                                JOptionPane.showMessageDialog(EsOrdutegiak.this,
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
            EsOrdutegiak frame = new EsOrdutegiak();
            frame.setVisible(true);
        });
    }
}
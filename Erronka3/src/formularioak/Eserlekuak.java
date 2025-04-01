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
                String[] opciones = { "ID", "ID Areto", "Zenbakia", "Beteta" }; // Opciones de filtrado
                String irizpidea = (String) JOptionPane.showInputDialog(
                        Eserlekuak.this,
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
                        String id = JOptionPane.showInputDialog(Eserlekuak.this,
                                "Sartu eserlekuaren ID-a:",
                                "ID-ren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (id != null && !id.trim().isEmpty()) {
                            List<Eserlekua> filtratutakoLista = dao.filtratuEserlekuak(id);
                            EserlekuaTaula newModel = new EserlekuaTaula(filtratutakoLista);
                            table.setModel(newModel);
                        }
                    } else if (irizpidea.equals("ID Areto")) {
                        // Filtrar por ID Areto
                        String idAreto = JOptionPane.showInputDialog(Eserlekuak.this,
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
                                JOptionPane.showMessageDialog(Eserlekuak.this,
                                        "ID Areto zenbakizkoa izan behar da.",
                                        "Errorea",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else if (irizpidea.equals("Zenbakia")) {
                        // Filtrar por Zenbakia
                        String zenbakia = JOptionPane.showInputDialog(Eserlekuak.this,
                                "Sartu eserlekuaren zenbakia:",
                                "Zenbakiaren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (zenbakia != null && !zenbakia.trim().isEmpty()) {
                            List<Eserlekua> filtratutakoLista = dao.filtratuEserlekuak(zenbakia);
                            EserlekuaTaula newModel = new EserlekuaTaula(filtratutakoLista);
                            table.setModel(newModel);
                        }
                    } else if (irizpidea.equals("Beteta")) {
                        // Filtrar por estado (Beteta)
                        String beteta = JOptionPane.showInputDialog(Eserlekuak.this,
                                "Sartu beteta egoera (true/false):",
                                "Betetaren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (beteta != null && !beteta.trim().isEmpty()) {
                            try {
                                boolean betetaBool = Boolean.parseBoolean(beteta);
                                List<Eserlekua> filtratutakoLista = dao.filtratuEserlekuakBeteta(betetaBool);
                                EserlekuaTaula newModel = new EserlekuaTaula(filtratutakoLista);
                                table.setModel(newModel);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(Eserlekuak.this,
                                        "Beteta egoera true edo false izan behar da.",
                                        "Errorea",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } else {
                    // Si no se elige ningún filtro, mostrar todos los registros
                    List<Eserlekua> listaOriginal = dao.lortuEserlekuak();
                    table.setModel(new EserlekuaTaula(listaOriginal));
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
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

public class Erreserbak extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private ErreserbakKudeatu dao;

    public Erreserbak() {
        setTitle("Erreserbak");
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
                String[] opciones = { "ID", "ID Bezero", "ID Ordutegi", "Kopurua", "Egoera" }; // Opciones de filtrado
                String irizpidea = (String) JOptionPane.showInputDialog(
                        Erreserbak.this,
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
                        String id = JOptionPane.showInputDialog(Erreserbak.this,
                                "Sartu erreserbaren ID-a:",
                                "ID-ren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (id != null && !id.trim().isEmpty()) {
                            List<Erreserba> filtratutakoLista = dao.filtratuErreserbak(id);
                            ErreserbakTaula newModel = new ErreserbakTaula(filtratutakoLista);
                            table.setModel(newModel);
                        }
                    } else if (irizpidea.equals("ID Bezero")) {
                        // Filtrar por ID Bezero
                        String idBezeroa = JOptionPane.showInputDialog(Erreserbak.this,
                                "Sartu bezeroaren ID-a:",
                                "ID Bezeroaren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (idBezeroa != null && !idBezeroa.trim().isEmpty()) {
                            try {
                                int idBezero = Integer.parseInt(idBezeroa);
                                List<Erreserba> filtratutakoLista = dao.filtratuErreserbakBezeroId(idBezero);
                                ErreserbakTaula newModel = new ErreserbakTaula(filtratutakoLista);
                                table.setModel(newModel);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(Erreserbak.this,
                                        "ID Bezero zenbakizkoa izan behar da.",
                                        "Errorea",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else if (irizpidea.equals("Egoera")) {
                        // Filtrar por Egoera
                        String egoera = JOptionPane.showInputDialog(Erreserbak.this,
                                "Sartu erreserbaren egoera:",
                                "Egoeraren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (egoera != null && !egoera.trim().isEmpty()) {
                            List<Erreserba> filtratutakoLista = dao.filtratuErreserbakEgoera(egoera);
                            ErreserbakTaula newModel = new ErreserbakTaula(filtratutakoLista);
                            table.setModel(newModel);
                        }
                    }
                } else {
                    // Si no se elige ningún filtro, mostrar todas las reservas
                    List<Erreserba> listaOriginal = dao.lortuEskaerak();
                    table.setModel(new ErreserbakTaula(listaOriginal));
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

        dao = new ErreserbakKudeatu();
        List<Erreserba> lista = dao.lortuEskaerak();
        ErreserbakTaula model = new ErreserbakTaula(lista);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Erreserbak frame = new Erreserbak();
            frame.setVisible(true);
        });
    }
}
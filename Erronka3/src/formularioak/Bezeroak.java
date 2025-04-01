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

public class Bezeroak extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private BezeroakKudeatu dao;

    public Bezeroak() {
        setTitle("Bezeroak");
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
                String[] opciones = { "Izena", "NAN", "Email" }; // Opciones de filtrado
                String irizpidea = (String) JOptionPane.showInputDialog(
                        Bezeroak.this,
                        "Aukeratu irizpidea:",
                        "Filtroa Aplikatu",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]); // Valor por defecto es "Izena"

                // Si el usuario selecciona un criterio de filtrado
                if (irizpidea != null && !irizpidea.trim().isEmpty()) {
                    if (irizpidea.equals("Izena")) {
                        // Filtrar por nombre
                        String izena = JOptionPane.showInputDialog(Bezeroak.this,
                                "Sartu bezeroaren izena:",
                                "Izenaren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (izena != null && !izena.trim().isEmpty()) {
                            List<Bezeroa> filtratutakoLista = dao.filtratuBezeroakIzena(izena);
                            BezeroakTaula newModel = new BezeroakTaula(filtratutakoLista);
                            table.setModel(newModel);
                        }
                    } else if (irizpidea.equals("NAN")) {
                        // Filtrar por NAN
                        String nan = JOptionPane.showInputDialog(Bezeroak.this,
                                "Sartu bezeroaren NAN-a:",
                                "NAN-aren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (nan != null && !nan.trim().isEmpty()) {
                            List<Bezeroa> filtratutakoLista = dao.filtratuBezeroakNan(nan);
                            BezeroakTaula newModel = new BezeroakTaula(filtratutakoLista);
                            table.setModel(newModel);
                        }
                    } else if (irizpidea.equals("Email")) {
                        // Filtrar por email
                        String email = JOptionPane.showInputDialog(Bezeroak.this,
                                "Sartu bezeroaren email-a:",
                                "Email-aren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (email != null && !email.trim().isEmpty()) {
                            List<Bezeroa> filtratutakoLista = dao.filtratuBezeroakEmail(email);
                            BezeroakTaula newModel = new BezeroakTaula(filtratutakoLista);
                            table.setModel(newModel);
                        }
                    }
                } else {
                    // Si no se elige ningún filtro, mostrar todos los bezeroak
                    List<Bezeroa> listaOriginal = dao.lortuBezeroak();
                    table.setModel(new BezeroakTaula(listaOriginal));
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

        dao = new BezeroakKudeatu();
        List<Bezeroa> lista = dao.lortuBezeroak();
        BezeroakTaula model = new BezeroakTaula(lista);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Bezeroak frame = new Bezeroak();
            frame.setVisible(true);
        });
    }
}
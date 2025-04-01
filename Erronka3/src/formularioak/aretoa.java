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

import klaseak.Aretoa;
import mantenimendua.aretoaKudeatu;
import mantenimendua.aretoaTaula;

public class aretoa extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private aretoaKudeatu dao;

    public aretoa() {
        setTitle("Aretoa");
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
                String[] opciones = { "Izena", "Edukiera" }; // Opciones de filtrado
                String irizpidea = (String) JOptionPane.showInputDialog(
                        aretoa.this,
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
                        String izena = JOptionPane.showInputDialog(aretoa.this,
                                "Sartu aretoaren izena:",
                                "Izenaren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (izena != null && !izena.trim().isEmpty()) {
                            List<Aretoa> filtratutakoLista = dao.filtratuAretoakIzena(izena);
                            aretoaTaula newModel = new aretoaTaula(filtratutakoLista);
                            table.setModel(newModel);
                        }
                    } else if (irizpidea.equals("Edukiera")) {
                        // Filtrar por capacidad
                        String edukiera = JOptionPane.showInputDialog(aretoa.this,
                                "Sartu edukiera (zenbakizkoa):",
                                "Edukieraren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (edukiera != null && !edukiera.trim().isEmpty()) {
                            try {
                                int kap = Integer.parseInt(edukiera);
                                List<Aretoa> filtratutakoLista = dao.filtratuAretoakKapazitatea(kap);
                                aretoaTaula newModel = new aretoaTaula(filtratutakoLista);
                                table.setModel(newModel);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(aretoa.this,
                                        "Edukiera zenbakizkoa izan behar da.",
                                        "Errorea",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } else {
                    // Si no se elige ningún filtro, mostrar todos los aretoak
                    List<Aretoa> listaOriginal = dao.lortuAretoak();
                    table.setModel(new aretoaTaula(listaOriginal));
                }
            }
        });
        menu.add(filtratu);
        JMenuItem bueltatu1 = new JMenuItem("Sarrerara Bueltatu");
        bueltatu1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sarrera sarrera = new Sarrera();
                sarrera.setExtendedState(JFrame.MAXIMIZED_BOTH);
                sarrera.setVisible(true);
                dispose();
            }
        });
        menu.add(bueltatu1);
        // Opción para recargar la tabla
        JMenuItem birkargatu = new JMenuItem("Taula Birkargatu");
        birkargatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taulaBirkargatu();
            }

            private void taulaBirkargatu() {
                List<Aretoa> lista = dao.lortuAretoak();
                aretoaTaula model = new aretoaTaula(lista);
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

        dao = new aretoaKudeatu();
        List<Aretoa> lista = dao.lortuAretoak();
        aretoaTaula model = new aretoaTaula(lista);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            aretoa frame = new aretoa();
            frame.setVisible(true);
        });
    }
}
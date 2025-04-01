package formularioak;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import klaseak.Aretoa;
import mantenimendua.aretoaKudeatu;
import mantenimendua.aretoaTaula;

public class aretoakAdmin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private aretoaKudeatu dao;

    public aretoakAdmin() {
        setTitle("Aretoak - Administratzailea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1006, 780);
        setLocationRelativeTo(null);

        // Menú superior
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Aukerak");
        menuBar.add(menu);

        // Opción para agregar un nuevo areto
        JMenuItem sortu = new JMenuItem("Aretoa berria gehitu");
        sortu.setIcon(new ImageIcon("path/to/icon.png")); // Reemplazar con el icono deseado
        sortu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aretoakSortu formulario = new aretoakSortu(aretoakAdmin.this, dao);
                formulario.setVisible(true);
            }
        });
        menu.add(sortu);

        // Opción para actualizar un areto
        JMenuItem eguneratu = new JMenuItem("Aretoaren datuak eguneratu");
        eguneratu.setIcon(new ImageIcon("path/to/icon.png"));
        eguneratu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    Aretoa aretoa = ((aretoaTaula) table.getModel()).getAretoaAt(filaSeleccionada);
                    aretoakEguneratu formulario = new aretoakEguneratu(aretoakAdmin.this, dao, aretoa);
                    formulario.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(aretoakAdmin.this, "Aukeratu areto bat lehenik");
                }
            }
        });
        menu.add(eguneratu);

        // Opción para eliminar un areto
        JMenuItem ezabatu = new JMenuItem("Areto bat ezabatu");
        ezabatu.setIcon(new ImageIcon("path/to/icon.png"));
        ezabatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    int idAretoa = (int) table.getValueAt(row, 0);
                    int confirm = JOptionPane.showConfirmDialog(aretoakAdmin.this, 
                            "Ziur zaude areto hau ezabatu nahi duzula?", "Ezabatu", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        dao.ezabatuAretoa(idAretoa);
                        taulaBirkargatu();
                        JOptionPane.showMessageDialog(aretoakAdmin.this, "Aretoa ezabatua!");
                    }
                } else {
                    JOptionPane.showMessageDialog(aretoakAdmin.this, "Aukeratu areto bat lehenik!");
                }
            }
        });
        menu.add(ezabatu);

        // Opción para aplicar filtros de búsqueda
        JMenuItem filtratu = new JMenuItem("Bilaketa Filtroak Aplikatu");
        filtratu.setIcon(new ImageIcon("path/to/icon.png"));
        filtratu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear un cuadro de diálogo para elegir el criterio de filtrado
                String[] opciones = { "Izena", "Edukiera" }; // Opciones de filtrado
                String irizpidea = (String) JOptionPane.showInputDialog(
                        aretoakAdmin.this,
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
                        String izena = JOptionPane.showInputDialog(aretoakAdmin.this,
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
                        String edukiera = JOptionPane.showInputDialog(aretoakAdmin.this,
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
                                JOptionPane.showMessageDialog(aretoakAdmin.this,
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
        

        // Opción para recargar la tabla
        JMenuItem birkargatu = new JMenuItem("Taula Birkargatu");
        birkargatu.setIcon(new ImageIcon("path/to/icon.png"));
        birkargatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taulaBirkargatu();
            }
        });
        menu.add(birkargatu);

        // Opción para cerrar sesión
        JMenuItem saioaItxi = new JMenuItem("Saioa Itxi");
        saioaItxi.setIcon(new ImageIcon("path/to/icon.png"));
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

        // Personalización de la tabla
        dao = new aretoaKudeatu();
        taulaBirkargatu();
    }

    protected void taulaBirkargatu() {
        List<Aretoa> lista = dao.lortuAretoak();
        aretoaTaula model = new aretoaTaula(lista);
        if (table == null) {
            table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            contentPane.add(scrollPane, BorderLayout.CENTER);
        } else {
            table.setModel(model);
        }

        // Personalizar el encabezado de la tabla
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(200, 200, 255));  // Color de fondo del encabezado
        header.setFont(new Font("Arial", Font.BOLD, 14));  // Fuente del encabezado

        // Personalizar las celdas
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);  // Centrado de texto
        table.setDefaultRenderer(Object.class, renderer);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            aretoakAdmin frame = new aretoakAdmin();
            frame.setVisible(true);
        });
    }
    
}
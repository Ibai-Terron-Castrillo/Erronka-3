package formularioak;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.util.List;

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
        
        // Establecer tema
       

        // Menú superior con un estilo más atractivo
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Aukerak");
        menuBar.add(menu);

        // Botones con iconos y estilos personalizados
        JMenuItem sortu = new JMenuItem("Aretoa berria gehitu");
        sortu.setIcon(new ImageIcon("path/to/icon.png")); // Reemplazar con el icono deseado
        sortu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aretoakSortu formulario = new aretoakSortu(aretoakAdmin.this, dao);
                formulario.setVisible(true);
            }
        });
        menu.add(sortu);

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

        JMenuItem bueltatu = new JMenuItem("Sarrerara Bueltatu");
        bueltatu.setIcon(new ImageIcon("path/to/icon.png"));
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
        filtratu.setIcon(new ImageIcon("path/to/icon.png"));
        filtratu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String irizpidea = JOptionPane.showInputDialog(aretoakAdmin.this, 
                        "Sartu bilaketa irizpidea (Izena, etab...):");
                if (irizpidea != null && !irizpidea.trim().isEmpty()) {
                    List<Aretoa> filtratutakoLista = dao.filtratuAretoak(irizpidea);
                    aretoaTaula newModel = new aretoaTaula(filtratutakoLista);
                    table.setModel(newModel);
                } else {
                    List<Aretoa> listaOriginal = dao.lortuAretoak();
                    table.setModel(new aretoaTaula(listaOriginal));
                }
            }
        });
        menu.add(filtratu);

        JMenuItem birkargatu = new JMenuItem("Taula Birkargatu");
        birkargatu.setIcon(new ImageIcon("path/to/icon.png"));
        birkargatu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taulaBirkargatu();
            }
        });
        menu.add(birkargatu);

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


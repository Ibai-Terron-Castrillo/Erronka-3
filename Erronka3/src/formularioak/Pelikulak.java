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

import klaseak.Pelikula;

import mantenimendua.PelikulakKudeatu;

import mantenimendua.PelikulakTaula;

public class Pelikulak extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTable table;

    private PelikulakKudeatu dao;

    public Pelikulak() {

        setTitle("Pelikulak");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1006, 780);

        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Aukerak");

        menuBar.add(menu);

        JMenuItem sortu = new JMenuItem("Pelikula Sortu");

        sortu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                PelikulakSortu formulario = new PelikulakSortu(Pelikulak.this, dao);

                formulario.setVisible(true);

            }

        });

        menu.add(sortu);

        JMenuItem eguneratu = new JMenuItem("Pelikula Eguneratu");

        eguneratu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int filaSeleccionada = table.getSelectedRow();

                if (filaSeleccionada != -1) {

                    Pelikula seleccionado = ((PelikulakTaula) table.getModel()).getPelikulaAt(filaSeleccionada);

                    PelikulakEguneratu formulario = new PelikulakEguneratu(Pelikulak.this, dao, seleccionado);

                    formulario.setVisible(true);

                } else {

                    JOptionPane.showMessageDialog(Pelikulak.this, "Ezarri Pelikula bat lehenik");

                }

            }

        });

        menu.add(eguneratu);

        JMenuItem ezabatu = new JMenuItem("Pelikula Ezabatu");

        ezabatu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();

                if (row != -1) {

                    int idPelikula = (int) table.getValueAt(row, 0);

                    int confirm = JOptionPane.showConfirmDialog(Pelikulak.this,

                            "Ziur zaude Pelikula hau ezabatu nahi duzula?", "Pelikula Ezabatu",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {

                        dao.ezabatuPelikula(idPelikula);

                        taulaBirkargatu();

                        JOptionPane.showMessageDialog(Pelikulak.this, "Pelikula ezabatua!");

                    }

                } else {

                    JOptionPane.showMessageDialog(Pelikulak.this, "Lehenik Pelikula bat aukeratu!");

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
                // Crear un cuadro de diálogo para elegir el criterio de filtrado
                String[] opciones = { "Generoa", "Sailkapena", "Izenburua" }; // Añadir "Izenburua"
                String criterio = (String) JOptionPane.showInputDialog(
                        Pelikulak.this,
                        "Aukeratu irizpidea:",
                        "Filtroa Aplikatu",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]); // Valor por defecto es "Generoa"

                // Si el usuario selecciona un criterio de filtrado
                if (criterio != null && !criterio.trim().isEmpty()) {
                    if (criterio.equals("Generoa")) {
                        // Filtrar por género
                        List<String> generos = dao.lortuGeneroDistinct(); // Usar el método ya existente
                        String genero = (String) JOptionPane.showInputDialog(Pelikulak.this,
                                "Generoa aukeratu:",
                                "Generoaren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                generos.toArray(),
                                generos.get(0)); // valor por defecto

                        if (genero != null && !genero.trim().isEmpty()) {
                            List<Pelikula> filtradasPorGenero = dao.filtratuPelikulakGenero(genero); // Filtrar por
                                                                                                     // género
                            PelikulakTaula newModel = new PelikulakTaula(filtradasPorGenero);
                            table.setModel(newModel);
                        }
                    } else if (criterio.equals("Sailkapena")) {
                        // Filtrar por calificación (sailkapena)
                        List<String> sailkapenak = dao.lortuDistinctSailkapena(); // Usar el método ya existente para
                                                                                  // obtener las calificaciones
                        String sailkapena = (String) JOptionPane.showInputDialog(Pelikulak.this,
                                "Sailkapena aukeratu:",
                                "Sailkapenaren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                sailkapenak.toArray(),
                                sailkapenak.get(0)); // valor por defecto

                        if (sailkapena != null && !sailkapena.trim().isEmpty()) {
                            List<Pelikula> filtradasPorSailkapena = dao.filtratuPelikulakSailkapena(sailkapena); // Filtrar
                                                                                                                 // por
                                                                                                                 // sailkapena
                            PelikulakTaula newModel = new PelikulakTaula(filtradasPorSailkapena);
                            table.setModel(newModel);
                        }
                    } else if (criterio.equals("Izenburua")) {
                        // Filtrar por título (izena)
                        String izenburua = JOptionPane.showInputDialog(Pelikulak.this,
                                "Pelikularen izenburua sartu:",
                                "Izenburuaren arabera filtratu",
                                JOptionPane.QUESTION_MESSAGE);

                        if (izenburua != null && !izenburua.trim().isEmpty()) {
                            List<Pelikula> filtradasPorIzenburua = dao.filtratuPelikulak(izenburua); // Filtrar por
                                                                                                     // título
                            PelikulakTaula newModel = new PelikulakTaula(filtradasPorIzenburua);
                            table.setModel(newModel);
                        }
                    }
                } else {
                    // Si no se elige ningún filtro, mostrar todas las películas
                    List<Pelikula> listaOriginal = dao.lortuPelikulak();
                    table.setModel(new PelikulakTaula(listaOriginal));
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

                List<Pelikula> lista = dao.lortuPelikulak();

                PelikulakTaula model = new PelikulakTaula(lista);

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

        dao = new PelikulakKudeatu();

        List<Pelikula> lista = dao.lortuPelikulak();

        PelikulakTaula model = new PelikulakTaula(lista);

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        contentPane.add(scrollPane, BorderLayout.CENTER);

    }

    protected void taulaBirkargatu() {

        List<Pelikula> lista = dao.lortuPelikulak();

        PelikulakTaula model = new PelikulakTaula(lista);

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

            Pelikulak frame = new Pelikulak();

            frame.setVisible(true);

        });

    }

}

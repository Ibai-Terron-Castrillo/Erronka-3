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

import klaseak.Ordutegiak;
import mantenimendua.OrdutegiaKudeatu;
import mantenimendua.OrdutegiaTaula;

public class Ordutegia extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private OrdutegiaKudeatu dao;

    public Ordutegia() {
        setTitle("Ordutegia");
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
                String[] opciones = { "Ordua", "Eguna", "Egun Tartea" }; // Opciones de filtrado
                String irizpidea = (String) JOptionPane.showInputDialog(
                        Ordutegia.this,
                        "Aukeratu irizpidea:",
                        "Filtroa Aplikatu",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]); // Valor por defecto es "Ordua"

                // Si el usuario selecciona un criterio de filtrado
                if (irizpidea != null && !irizpidea.trim().isEmpty()) {
                    switch (irizpidea) {
                        case "Ordua":
                            filtrarPorOrdua();
                            break;
                        case "Eguna":
                            filtrarPorEguna();
                            break;
                        case "Egun Tartea":
                            filtrarPorRangoEguna();
                            break;
                        default:
                            break;
                    }
                } else {
                    // Si no se elige ningún filtro, mostrar todos los registros
                    taulaBirkargatu();
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

        setJMenuBar(menuBar);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        dao = new OrdutegiaKudeatu();
        taulaBirkargatu();
    }

    private void filtrarPorOrdua() {
        String ordua = JOptionPane.showInputDialog(this, "Sartu ordua (HH:MM):", "Orduaren arabera filtratu", JOptionPane.QUESTION_MESSAGE);
        if (ordua != null && !ordua.trim().isEmpty()) {
            List<Ordutegiak> filtratutakoLista = dao.filtratuOrdutegiaOrdua(ordua);
            table.setModel(new OrdutegiaTaula(filtratutakoLista));
        }
    }

    private void filtrarPorEguna() {
        String eguna = JOptionPane.showInputDialog(this, "Sartu eguna (YYYY-MM-DD):", "Egunaren arabera filtratu", JOptionPane.QUESTION_MESSAGE);
        if (eguna != null && !eguna.trim().isEmpty()) {
            List<Ordutegiak> filtratutakoLista = dao.filtratuOrdutegiaEguna(eguna);
            table.setModel(new OrdutegiaTaula(filtratutakoLista));
        }
    }

    private void filtrarPorRangoEguna() {
        String egunaHasiera = JOptionPane.showInputDialog(this, "Sartu hasierako eguna (YYYY-MM-DD):", "Rangoaren hasiera", JOptionPane.QUESTION_MESSAGE);
        String egunaAmaiera = JOptionPane.showInputDialog(this, "Sartu amaierako eguna (YYYY-MM-DD):", "Rangoaren amaiera", JOptionPane.QUESTION_MESSAGE);

        if (egunaHasiera != null && egunaAmaiera != null && !egunaHasiera.trim().isEmpty() && !egunaAmaiera.trim().isEmpty()) {
            List<Ordutegiak> filtratutakoLista = dao.filtratuOrdutegiaRangoEguna(egunaHasiera, egunaAmaiera);
            table.setModel(new OrdutegiaTaula(filtratutakoLista));
        }
    }

    protected void taulaBirkargatu() {
        List<Ordutegiak> lista = dao.lortuOrdutegia();
        OrdutegiaTaula model = new OrdutegiaTaula(lista);
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
            Ordutegia frame = new Ordutegia();
            frame.setVisible(true);
        });
    }
}
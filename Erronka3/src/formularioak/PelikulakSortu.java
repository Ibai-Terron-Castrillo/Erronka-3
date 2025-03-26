package formularioak;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import klaseak.Pelikula;
import mantenimendua.PelikulakKudeatu;

public class PelikulakSortu extends JDialog {

    private JTextField txtIzena, txtIraunaldia, txtGeneroa, txtSinopsia, txtAktoreak, txtZuzendaria, txtKartela, txtTrailerra;
    private JComboBox<String> comboSailkapena;  // Cambiado de JTextField a JComboBox
    private PelikulakKudeatu dao;

    public PelikulakSortu(JFrame parent, PelikulakKudeatu dao) {
        super(parent, "Pelikula Sortu", true);
        this.dao = dao;

        getContentPane().setLayout(new GridLayout(10, 2));
        setSize(400, 400);
        setLocationRelativeTo(parent);

        getContentPane().add(new JLabel("     Izena:"));
        txtIzena = new JTextField();
        getContentPane().add(txtIzena);

        getContentPane().add(new JLabel("     Iraunaldia:"));
        txtIraunaldia = new JTextField();
        getContentPane().add(txtIraunaldia);

        getContentPane().add(new JLabel("     Generoa:"));
        txtGeneroa = new JTextField();
        getContentPane().add(txtGeneroa);

        // Agregar JComboBox para "Sailkapena" con las tres opciones
        getContentPane().add(new JLabel("     Sailkapena:"));
        String[] opcionesSailkapena = {"Helduentzat", "Familiarra", "Haurrentzat"};
        comboSailkapena = new JComboBox<>(opcionesSailkapena);
        getContentPane().add(comboSailkapena);

        getContentPane().add(new JLabel("     Sinopsia:"));
        txtSinopsia = new JTextField();
        getContentPane().add(txtSinopsia);

        getContentPane().add(new JLabel("     Aktoreak:"));
        txtAktoreak = new JTextField();
        getContentPane().add(txtAktoreak);

        getContentPane().add(new JLabel("     Zuzendaria:"));
        txtZuzendaria = new JTextField();
        getContentPane().add(txtZuzendaria);

        getContentPane().add(new JLabel("     Kartela:"));
        txtKartela = new JTextField();
        getContentPane().add(txtKartela);

        getContentPane().add(new JLabel("     Trailerra:"));
        txtTrailerra = new JTextField();
        getContentPane().add(txtTrailerra);

        // Botón para guardar
        JButton btnSave = new JButton("Gorde");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener el valor seleccionado en comboSailkapena
                String sailkapenaSeleccionado = (String) comboSailkapena.getSelectedItem();

                // Crear una nueva película con los datos introducidos
                Pelikula pelikula = new Pelikula(0, txtIzena.getText(), Integer.parseInt(txtIraunaldia.getText()), 
                        txtGeneroa.getText(), sailkapenaSeleccionado, txtSinopsia.getText(), txtAktoreak.getText(), 
                        txtZuzendaria.getText(), txtKartela.getText(), txtTrailerra.getText());

                // Guardar la película en la base de datos o almacenamiento
                dao.sortuPelikula(pelikula);

                // Mensaje de éxito
                JOptionPane.showMessageDialog(PelikulakSortu.this, "Pelikula sortu da!");
                dispose();
            }
        });
        getContentPane().add(btnSave);

        setVisible(true);
    }
}


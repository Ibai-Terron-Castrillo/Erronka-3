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

public class PelikulakEguneratu extends JDialog {

    private JTextField txtIzena, txtIraunaldia, txtGeneroa, txtSinopsia, txtAktoreak, txtZuzendaria, txtKartela, txtTrailerra;
    private JComboBox<String> comboSailkapena;  // Cambiado de JTextField a JComboBox
    private PelikulakKudeatu dao;
    private Pelikula pelikula;

    public PelikulakEguneratu(JFrame parent, PelikulakKudeatu dao, Pelikula pelikula) {
        super(parent, "Pelikula Eguneratu", true);
        this.dao = dao;
        this.pelikula = pelikula;

        setLayout(new GridLayout(10, 2));
        setSize(400, 400);
        setLocationRelativeTo(parent);

        // Izena
        add(new JLabel("     Izena:"));
        txtIzena = new JTextField(pelikula.getIzena());
        add(txtIzena);

        // Iraunaldia
        add(new JLabel("     Iraunaldia:"));
        txtIraunaldia = new JTextField(String.valueOf(pelikula.getIraunaldia()));
        add(txtIraunaldia);

        // Generoa
        add(new JLabel("     Generoa:"));
        txtGeneroa = new JTextField(pelikula.getGeneroa());
        add(txtGeneroa);

        // Sailkapena (JComboBox con tres opciones)
        add(new JLabel("     Sailkapena:"));
        String[] opcionesSailkapena = {"Helduentzat", "Familiarra", "Haurrentzat"};
        comboSailkapena = new JComboBox<>(opcionesSailkapena);
        comboSailkapena.setSelectedItem(pelikula.getSailkapena());  // Establecer el valor actual de Sailkapena
        add(comboSailkapena);

        // Sinopsia
        add(new JLabel("     Sinopsia:"));
        txtSinopsia = new JTextField(pelikula.getSinopsia());
        add(txtSinopsia);

        // Aktoreak
        add(new JLabel("     Aktoreak:"));
        txtAktoreak = new JTextField(pelikula.getAktoreak());
        add(txtAktoreak);

        // Zuzendaria
        add(new JLabel("     Zuzendaria:"));
        txtZuzendaria = new JTextField(pelikula.getZuzendaria());
        add(txtZuzendaria);

        // Kartela
        add(new JLabel("     Kartela:"));
        txtKartela = new JTextField(pelikula.getKartela());
        add(txtKartela);

        // Trailerra
        add(new JLabel("     Trailerra:"));
        txtTrailerra = new JTextField(pelikula.getTrailerra());
        add(txtTrailerra);

        // Botón para guardar los cambios
        JButton btnSave = new JButton("Eguneratu");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener el valor seleccionado en comboSailkapena
                String sailkapenaSeleccionado = (String) comboSailkapena.getSelectedItem();

                // Actualizar la película con los nuevos valores
                pelikula.setIzena(txtIzena.getText());
                pelikula.setIraunaldia(Integer.parseInt(txtIraunaldia.getText()));
                pelikula.setGeneroa(txtGeneroa.getText());
                pelikula.setSailkapena(sailkapenaSeleccionado);  // Establecer el nuevo Sailkapena
                pelikula.setSinopsia(txtSinopsia.getText());
                pelikula.setAktoreak(txtAktoreak.getText());
                pelikula.setZuzendaria(txtZuzendaria.getText());
                pelikula.setKartela(txtKartela.getText());
                pelikula.setTrailerra(txtTrailerra.getText());

                // Actualizar la película en la base de datos o almacenamiento
                dao.eguneratuPelikula(pelikula);

                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(PelikulakEguneratu.this, "Pelikula eguneratu da!");
                dispose();
            }
        });
        add(btnSave);

        setVisible(true);
    }
}


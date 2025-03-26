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

import klaseak.Erreserba;
import mantenimendua.ErreserbakKudeatu;

public class ErreserbakEguneratu extends JDialog {

    private JTextField txtIdBezeroa, txtIdOrdutegi, txtKopurua;
    private JComboBox<String> comboEgoera;  // Cambiado de JTextField a JComboBox
    private ErreserbakKudeatu dao;
    private Erreserba erreserba;

    public ErreserbakEguneratu(JFrame parent, ErreserbakKudeatu dao, Erreserba erreserba) {
        super(parent, "Erreserba Eguneratu", true);
        this.dao = dao;
        this.erreserba = erreserba;

        setLayout(new GridLayout(5, 2));
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // ID Bezeroa
        add(new JLabel("     Id Bezeroa:"));
        txtIdBezeroa = new JTextField(String.valueOf(erreserba.getIdBezeroa()));
        add(txtIdBezeroa);

        // ID Ordutegi
        add(new JLabel("     Id Ordutegi:"));
        txtIdOrdutegi = new JTextField(String.valueOf(erreserba.getIdOrdutegi()));
        add(txtIdOrdutegi);

        // Kopurua
        add(new JLabel("     Kopurua:"));
        txtKopurua = new JTextField(String.valueOf(erreserba.getKopurua()));
        add(txtKopurua);

        // Egoera - JComboBox para las opciones "Prozesatzen" y "Ordaindua"
        add(new JLabel("     Egoera:"));
        String[] opcionesEgoera = {"Prozesatzen", "Ordaindua"};
        comboEgoera = new JComboBox<>(opcionesEgoera);
        
        // Establecer la opción seleccionada de acuerdo al estado actual de la "Egoera"
        comboEgoera.setSelectedItem(erreserba.getEgoera());  // Si la "Egoera" es "Prozesatzen" o "Ordaindua"
        add(comboEgoera);

        // Botón para actualizar
        JButton btnSave = new JButton("Eguneratu");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                erreserba.setIdBezeroa(Integer.parseInt(txtIdBezeroa.getText()));
                erreserba.setIdOrdutegi(Integer.parseInt(txtIdOrdutegi.getText()));
                erreserba.setKopurua(Integer.parseInt(txtKopurua.getText()));
                // Obtener la opción seleccionada del JComboBox y actualizar la "Egoera"
                erreserba.setEgoera((String) comboEgoera.getSelectedItem());

                dao.eguneratuEskaera(erreserba);
                JOptionPane.showMessageDialog(ErreserbakEguneratu.this, "Erreserba eguneratu da!");
                dispose();
            }
        });
        add(btnSave);

        setVisible(true);
    }
}


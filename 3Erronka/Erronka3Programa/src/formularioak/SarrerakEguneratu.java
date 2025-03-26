package formularioak;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import klaseak.Sarrerak;
import mantenimendua.SarrerakKudeatu;

public class SarrerakEguneratu extends JDialog {

    private JTextField txtIdErreserba, txtPrezioa, txtIdEserleku;
    private SarrerakKudeatu dao;
    private Sarrerak sarrera;

    public SarrerakEguneratu(JFrame parent, SarrerakKudeatu dao, Sarrerak sarrera) {
        super(parent, "Sarrera Eguneratu", true);
        this.dao = dao;
        this.sarrera = sarrera;

        setLayout(new GridLayout(4, 2));
        setSize(400, 300);
        setLocationRelativeTo(parent);

        getContentPane().add(new JLabel("ID Erreserba:"));
        txtIdErreserba = new JTextField(String.valueOf(sarrera.getIdErreserba()));
        getContentPane().add(txtIdErreserba);

        getContentPane().add(new JLabel("Prezioa:"));
        txtPrezioa = new JTextField(String.valueOf(sarrera.getPrezioa()));
        getContentPane().add(txtPrezioa);

        getContentPane().add(new JLabel("ID Eserleku:"));
        txtIdEserleku = new JTextField(String.valueOf(sarrera.getIdEserleku()));
        getContentPane().add(txtIdEserleku);

        JButton btnSave = new JButton("Eguneratu");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eguneratuSarrera();
            }
        });
        getContentPane().add(btnSave);

        setVisible(true);
    }

    private void eguneratuSarrera() {
        try {
            int idErreserba = Integer.parseInt(txtIdErreserba.getText());
            double prezioa = Double.parseDouble(txtPrezioa.getText());
            int idEserleku = Integer.parseInt(txtIdEserleku.getText());

            sarrera.setIdErreserba(idErreserba);
            sarrera.setPrezioa(prezioa);
            sarrera.setIdEserleku(idEserleku);

            dao.eguneratuSarrera(sarrera);
            JOptionPane.showMessageDialog(this, "Sarrera eguneratu da!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Sartu balio zuzenak.", "Errorea", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errorea: " + ex.getMessage(), "Errorea", JOptionPane.ERROR_MESSAGE);
        }
    }
}


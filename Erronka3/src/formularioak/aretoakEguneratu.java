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

import klaseak.Aretoa;
import mantenimendua.aretoaKudeatu;

public class aretoakEguneratu extends JDialog {

    private JTextField txtIzena, txtEdukiera;
    private aretoaKudeatu dao;
    private Aretoa aretoa;

    public aretoakEguneratu(JFrame parent, aretoaKudeatu dao, Aretoa aretoa) {
        super(parent, "Aretoa Eguneratu", true);
        this.dao = dao;
        this.aretoa = aretoa;

        setLayout(new GridLayout(3, 2));
        setSize(400, 200);
        setLocationRelativeTo(parent);

        add(new JLabel("     Izena:"));
        txtIzena = new JTextField(aretoa.getIzena());
        add(txtIzena);

        add(new JLabel("     Edukiera:"));
        txtEdukiera = new JTextField(String.valueOf(aretoa.getEdukiera()));
        add(txtEdukiera);

        JButton btnSave = new JButton("Eguneratu");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eguneratuAretoa();
            }
        });
        add(btnSave);

        setVisible(true);
    }

    private void eguneratuAretoa() {
        try {
            String izena = txtIzena.getText();
            int edukiera = Integer.parseInt(txtEdukiera.getText());

            aretoa.setIzena(izena);
            aretoa.setEdukiera(edukiera);

            dao.eguneratuAretoa(aretoa);
            JOptionPane.showMessageDialog(this, "Aretoa eguneratu da!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Sartu balio zuzenak.", "Errorea", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errorea: " + ex.getMessage(), "Errorea", JOptionPane.ERROR_MESSAGE);
        }
    }
}

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

public class aretoakSortu extends JDialog {

    private JTextField txtIzena, txtEdukiera;
    private aretoaKudeatu dao;

    public aretoakSortu(JFrame parent, aretoaKudeatu dao) {
        super(parent, "Aretoa Sortu", true);
        this.dao = dao;

        getContentPane().setLayout(new GridLayout(3, 2));
        setSize(400, 200);
        setLocationRelativeTo(parent);

        getContentPane().add(new JLabel("Izena:"));
        txtIzena = new JTextField();
        getContentPane().add(txtIzena);

        getContentPane().add(new JLabel("Edukiera:"));
        txtEdukiera = new JTextField();
        getContentPane().add(txtEdukiera);

        JButton btnSave = new JButton("Gorde");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortuAretoa();
            }
        });
        getContentPane().add(btnSave);

        setVisible(true);
    }

    private void sortuAretoa() {
        try {
            String izena = txtIzena.getText();
            int edukiera = Integer.parseInt(txtEdukiera.getText());

            Aretoa aretoa = new Aretoa(0, izena, edukiera);
            dao.sortuAretoa(aretoa);
            JOptionPane.showMessageDialog(this, "Aretoa sortu da!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Sartu balio zuzenak.", "Errorea", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errorea: " + ex.getMessage(), "Errorea", JOptionPane.ERROR_MESSAGE);
        }
    }
}

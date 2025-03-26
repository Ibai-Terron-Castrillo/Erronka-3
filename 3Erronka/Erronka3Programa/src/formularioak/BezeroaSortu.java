package formularioak;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import klaseak.Bezeroa;
import mantenimendua.BezeroakKudeatu;

public class BezeroaSortu extends JDialog {

    private JTextField txtIzena, txtAbizena1, txtAbizena2, txtEmail, txtNan;
    private JPasswordField txtPasahitza;
    private BezeroakKudeatu dao;

    public BezeroaSortu(JFrame parent, BezeroakKudeatu dao) {
        super(parent, "Bezeroa Sortu", true);
        this.dao = dao;

        getContentPane().setLayout(new GridLayout(7, 2));
        setSize(400, 350);
        setLocationRelativeTo(parent);

        getContentPane().add(new JLabel("     Izena:"));
        txtIzena = new JTextField();
        getContentPane().add(txtIzena);

        getContentPane().add(new JLabel("     Abizena1:"));
        txtAbizena1 = new JTextField();
        getContentPane().add(txtAbizena1);

        getContentPane().add(new JLabel("     Abizena2:"));
        txtAbizena2 = new JTextField();
        getContentPane().add(txtAbizena2);

        getContentPane().add(new JLabel("     Email:"));
        txtEmail = new JTextField();
        getContentPane().add(txtEmail);

        getContentPane().add(new JLabel("     NAN:"));
        txtNan = new JTextField();
        getContentPane().add(txtNan);

        getContentPane().add(new JLabel("     Pasahitza:"));
        txtPasahitza = new JPasswordField();
        getContentPane().add(txtPasahitza);

        JButton btnSave = new JButton("Gorde");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pasahitza = new String(txtPasahitza.getPassword());
                Bezeroa bezeroa = new Bezeroa(0, txtIzena.getText(), txtAbizena1.getText(), txtAbizena2.getText(),
                        txtNan.getText(), txtEmail.getText(), pasahitza);
                dao.sortuBezeroa(bezeroa);
                JOptionPane.showMessageDialog(BezeroaSortu.this, "Bezeroa sortu da!");
                dispose();
            }
        });
        getContentPane().add(btnSave);

        setVisible(true);
    }
}


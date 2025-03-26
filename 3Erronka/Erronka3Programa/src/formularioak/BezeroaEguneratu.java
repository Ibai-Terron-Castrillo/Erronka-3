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

public class BezeroaEguneratu extends JDialog {

    private JTextField txtIzena, txtAbizena1, txtAbizena2, txtEmail, txtNan;
    private JPasswordField txtPasahitza;
    private BezeroakKudeatu dao;
    private Bezeroa bezeroa;

    public BezeroaEguneratu(JFrame parent, BezeroakKudeatu dao, Bezeroa bezeroa) {
        super(parent, "Bezeroa Eguneratu", true);
        this.dao = dao;
        this.bezeroa = bezeroa;

        setLayout(new GridLayout(6, 2));
        setSize(400, 350);
        setLocationRelativeTo(parent);

        add(new JLabel("     Izena:"));
        txtIzena = new JTextField(bezeroa.getIzena());
        add(txtIzena);

        add(new JLabel("     Abizena1:"));
        txtAbizena1 = new JTextField(bezeroa.getAbizena1());
        add(txtAbizena1);

        add(new JLabel("     Abizena2:"));
        txtAbizena2 = new JTextField(bezeroa.getAbizena2());
        add(txtAbizena2);

        add(new JLabel("     Email:"));
        txtEmail = new JTextField(bezeroa.getEmail());
        add(txtEmail);

        add(new JLabel("     NAN:"));
        txtNan = new JTextField(bezeroa.getNan());
        add(txtNan);

        add(new JLabel("     Pasahitza:"));
        txtPasahitza = new JPasswordField(bezeroa.getPasahitza());
        add(txtPasahitza);

        JButton btnSave = new JButton("Eguneratu");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pasahitza = new String(txtPasahitza.getPassword());
                bezeroa.setIzena(txtIzena.getText());
                bezeroa.setAbizena1(txtAbizena1.getText());
                bezeroa.setAbizena2(txtAbizena2.getText());
                bezeroa.setEmail(txtEmail.getText());
                bezeroa.setNan(txtNan.getText());
                bezeroa.setPasahitza(pasahitza);

                dao.eguneratuBezeroa(bezeroa);
                JOptionPane.showMessageDialog(BezeroaEguneratu.this, "Bezeroa eguneratu da!");
                dispose();
            }
        });
        add(btnSave);

        setVisible(true);
    }
}


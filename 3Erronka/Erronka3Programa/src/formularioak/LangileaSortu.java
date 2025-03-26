package formularioak;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import klaseak.langilea;
import mantenimendua.LangileakKudeatu;

public class LangileaSortu extends JDialog {
    private JTextField txtIzena, txtAbizena1, txtAbizena2, txtErabiltzailea, txtEmail, txtTelefonoa, txtNan, txtHelbidea, txtAdmin;
    private JPasswordField txtPasahitza;
    private LangileakKudeatu dao;

    public LangileaSortu(JFrame parent, LangileakKudeatu dao) {
        super(parent, "Langilea Sortu", true);
        this.dao = dao;
        setSize(296, 624);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("     Izena:");
        label.setBounds(10, 26, 128, 39);
        getContentPane().add(label);
        txtIzena = new JTextField();
        txtIzena.setBounds(138, 26, 128, 39);
        getContentPane().add(txtIzena);

        JLabel label_1 = new JLabel("     Abizena1:");
        label_1.setBounds(10, 75, 128, 39);
        getContentPane().add(label_1);
        txtAbizena1 = new JTextField();
        txtAbizena1.setBounds(138, 75, 128, 39);
        getContentPane().add(txtAbizena1);

        JLabel label_2 = new JLabel("     Abizena2:");
        label_2.setBounds(10, 124, 128, 39);
        getContentPane().add(label_2);
        txtAbizena2 = new JTextField();
        txtAbizena2.setBounds(138, 124, 128, 39);
        getContentPane().add(txtAbizena2);

        JLabel label_3 = new JLabel("     Erabiltzailea:");
        label_3.setBounds(10, 173, 128, 39);
        getContentPane().add(label_3);
        txtErabiltzailea = new JTextField();
        txtErabiltzailea.setBounds(138, 173, 128, 39);
        getContentPane().add(txtErabiltzailea);

        JLabel label_4 = new JLabel("     Pasahitza:");
        label_4.setBounds(10, 222, 128, 39);
        getContentPane().add(label_4);
        txtPasahitza = new JPasswordField();
        txtPasahitza.setBounds(138, 222, 128, 39);
        getContentPane().add(txtPasahitza);

        JLabel label_5 = new JLabel("     Email:");
        label_5.setBounds(10, 271, 128, 39);
        getContentPane().add(label_5);
        txtEmail = new JTextField();
        txtEmail.setBounds(138, 271, 128, 39);
        getContentPane().add(txtEmail);

        JLabel label_6 = new JLabel("     Telefonoa:");
        label_6.setBounds(10, 320, 128, 39);
        getContentPane().add(label_6);
        txtTelefonoa = new JTextField();
        txtTelefonoa.setBounds(138, 320, 128, 39);
        getContentPane().add(txtTelefonoa);

        JLabel label_7 = new JLabel("     NAN:");
        label_7.setBounds(10, 369, 128, 39);
        getContentPane().add(label_7);
        txtNan = new JTextField();
        txtNan.setBounds(138, 369, 128, 39);
        getContentPane().add(txtNan);

        JLabel label_8 = new JLabel("     Helbidea:");
        label_8.setBounds(10, 418, 128, 39);
        getContentPane().add(label_8);
        txtHelbidea = new JTextField();
        txtHelbidea.setBounds(138, 418, 128, 39);
        getContentPane().add(txtHelbidea);

        JLabel label_9 = new JLabel("     Admin:");
        label_9.setBounds(10, 467, 128, 39);
        getContentPane().add(label_9);
        txtAdmin = new JTextField();
        txtAdmin.setBounds(138, 467, 128, 39);
        getContentPane().add(txtAdmin);

        JButton btnSave = new JButton("Gorde");
        btnSave.setBounds(75, 517, 128, 39);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pasahitza = new String(txtPasahitza.getPassword());
                langilea langilea = new langilea(
                    txtIzena.getText(),
                    txtAbizena1.getText(),
                    txtAbizena2.getText(),
                    txtErabiltzailea.getText(),
                    pasahitza,
                    txtEmail.getText(),
                    txtTelefonoa.getText(),
                    Boolean.parseBoolean(txtAdmin.getText()),
                    txtHelbidea.getText(),
                    txtNan.getText()
                );
                dao.sortuLangilea(langilea);
                JOptionPane.showMessageDialog(LangileaSortu.this, "Langilea sortu da!");
                dispose();
            }
        });
        getContentPane().add(btnSave);

        setVisible(true);
    }
}


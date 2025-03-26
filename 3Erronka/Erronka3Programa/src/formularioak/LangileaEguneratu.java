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

public class LangileaEguneratu extends JDialog {

    private JTextField txtIzena, txtAbizena1, txtAbizena2, txtEmail, txtNan, txtHelbidea, txtAdmin;
    private JPasswordField txtPasahitza;
    private LangileakKudeatu dao;
    private langilea langilea;

    public LangileaEguneratu(JFrame parent, LangileakKudeatu dao, langilea langilea) {
        super(parent, "Langilea Eguneratu", true);
        this.dao = dao;
        this.langilea = langilea;
        setSize(614, 882);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("     Izena:");
        label.setBounds(49, 20, 253, 73);
        getContentPane().add(label);
        txtIzena = new JTextField(langilea.getIzena());
        txtIzena.setBounds(302, 20, 253, 73);
        getContentPane().add(txtIzena);

        JLabel label_1 = new JLabel("     Abizena1:");
        label_1.setBounds(49, 92, 253, 73);
        getContentPane().add(label_1);
        txtAbizena1 = new JTextField(langilea.getAbizena1());
        txtAbizena1.setBounds(302, 92, 253, 73);
        getContentPane().add(txtAbizena1);

        JLabel label_2 = new JLabel("     Abizena2:");
        label_2.setBounds(49, 164, 253, 73);
        getContentPane().add(label_2);
        txtAbizena2 = new JTextField(langilea.getAbizena2());
        txtAbizena2.setBounds(302, 164, 253, 73);
        getContentPane().add(txtAbizena2);

        JLabel label_3 = new JLabel("     Email:");
        label_3.setBounds(49, 236, 253, 73);
        getContentPane().add(label_3);
        txtEmail = new JTextField(langilea.getEmail());
        txtEmail.setBounds(302, 236, 253, 73);
        getContentPane().add(txtEmail);

        JLabel label_4 = new JLabel("     NAN:");
        label_4.setBounds(49, 308, 253, 73);
        getContentPane().add(label_4);
        txtNan = new JTextField(langilea.getNan());
        txtNan.setBounds(302, 308, 253, 73);
        getContentPane().add(txtNan);
        
        JLabel label_5 = new JLabel("     Helbidea:");
        label_5.setBounds(49, 380, 253, 73);
        getContentPane().add(label_5);
        txtHelbidea = new JTextField(langilea.getHelbidea());
        txtHelbidea.setBounds(302, 380, 253, 73);
        getContentPane().add(txtHelbidea);
        
        JLabel label_6 = new JLabel("     Admin:");
        label_6.setBounds(49, 452, 253, 73);
        getContentPane().add(label_6);
        txtAdmin = new JTextField(String.valueOf(langilea.isAdmin()));
        txtAdmin.setBounds(302, 452, 253, 73);
        getContentPane().add(txtAdmin);

        JLabel label_7 = new JLabel("     Pasahitza:");
        label_7.setBounds(49, 524, 253, 73);
        getContentPane().add(label_7);
        txtPasahitza = new JPasswordField(langilea.getPasahitza());
        txtPasahitza.setBounds(302, 524, 253, 73);
        getContentPane().add(txtPasahitza);

        JButton btnSave = new JButton("Eguneratu");
        btnSave.setBounds(179, 762, 253, 73);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pasahitza = new String(txtPasahitza.getPassword());
                langilea.setIzena(txtIzena.getText());
                langilea.setAbizena1(txtAbizena1.getText());
                langilea.setAbizena2(txtAbizena2.getText());
                langilea.setEmail(txtEmail.getText());
                langilea.setNan(txtNan.getText());
                langilea.setHelbidea(txtHelbidea.getText());
                langilea.setAdmin(Boolean.parseBoolean(txtAdmin.getText()));
                langilea.setPasahitza(pasahitza);

                dao.eguneratuLangilea(langilea);
                JOptionPane.showMessageDialog(LangileaEguneratu.this, "Langilea eguneratu da!");
                dispose();
            }
        });
        getContentPane().add(btnSave);

        setVisible(true);
    }
}


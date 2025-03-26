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

import klaseak.Erreserba;
import mantenimendua.ErreserbakKudeatu;

public class ErreserbakSortu extends JDialog {

    private JTextField txtIdBezeroa, txtIdOrdutegi, txtKopurua, txtEgoera;
    private ErreserbakKudeatu dao;

    public ErreserbakSortu(JFrame parent, ErreserbakKudeatu dao) {
        super(parent, "Eskaera Sortu", true);
        this.dao = dao;

        getContentPane().setLayout(new GridLayout(5, 2));
        setSize(400, 300);
        setLocationRelativeTo(parent);

        getContentPane().add(new JLabel("     Id Bezeroa:"));
        txtIdBezeroa = new JTextField();
        getContentPane().add(txtIdBezeroa);

        getContentPane().add(new JLabel("     Id Ordutegi:"));
        txtIdOrdutegi = new JTextField();
        getContentPane().add(txtIdOrdutegi);

        getContentPane().add(new JLabel("     Kopurua:"));
        txtKopurua = new JTextField();
        getContentPane().add(txtKopurua);

       
        JButton btnSave = new JButton("Gorde");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Erreserba eskaera = new Erreserba(0, Integer.parseInt(txtIdBezeroa.getText()), Integer.parseInt(txtIdOrdutegi.getText()),
                        Integer.parseInt(txtKopurua.getText()));
                dao.sortuEskaera(eskaera);
                JOptionPane.showMessageDialog(ErreserbakSortu.this, "Eskaera sortu da!");
                dispose();
            }
        });
        getContentPane().add(btnSave);

        setVisible(true);
    }
}

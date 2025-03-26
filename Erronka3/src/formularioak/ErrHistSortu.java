package formularioak;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import klaseak.ErrHistorikoa;
import mantenimendua.ErrHistKudeatu;

public class ErrHistSortu extends JDialog {

    private JTextField txtIdBezeroa, txtIdOrdutegi, txtKopurua, txtAmaieraData;
    private ErrHistKudeatu dao;

    public ErrHistSortu(JFrame parent, ErrHistKudeatu dao) {
        super(parent, "Erreserba Sortu", true);
        this.dao = dao;

        setLayout(new GridLayout(5, 2));
        setSize(400, 300);
        setLocationRelativeTo(parent);

        add(new JLabel("ID Bezeroa:"));
        txtIdBezeroa = new JTextField();
        add(txtIdBezeroa);

        add(new JLabel("ID Ordutegi:"));
        txtIdOrdutegi = new JTextField();
        add(txtIdOrdutegi);

        add(new JLabel("Kopurua:"));
        txtKopurua = new JTextField();
        add(txtKopurua);

        add(new JLabel("Amaiera Data:"));
        txtAmaieraData = new JTextField();
        add(txtAmaieraData);

        JButton btnSave = new JButton("Gorde");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortuErreserba();
            }
        });
        add(btnSave);

        setVisible(true);
    }

    private void sortuErreserba() {
        try {
            int idBezeroa = Integer.parseInt(txtIdBezeroa.getText());
            int idOrdutegi = Integer.parseInt(txtIdOrdutegi.getText());
            int kopurua = Integer.parseInt(txtKopurua.getText());
            Date amaieraData = Date.valueOf(txtAmaieraData.getText());

            ErrHistorikoa errHistorikoa = new ErrHistorikoa(0, idBezeroa, idOrdutegi, kopurua, amaieraData);
            dao.sortuEskaera(errHistorikoa);
            JOptionPane.showMessageDialog(this, "Erreserba sortu da!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Sartu balio zuzenak.", "Errorea", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errorea: " + ex.getMessage(), "Errorea", JOptionPane.ERROR_MESSAGE);
        }
    }
}

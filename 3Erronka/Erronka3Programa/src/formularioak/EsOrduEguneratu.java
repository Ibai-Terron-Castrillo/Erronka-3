package formularioak;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import klaseak.EsOrdutegia;
import mantenimendua.EsOrdKudeatu;

public class EsOrduEguneratu extends JDialog {

    private JTextField txtIdEserlekua, txtIdOrdutegi;
    private JCheckBox chkBeteta;
    private EsOrdKudeatu dao;
    private EsOrdutegia esOrdutegia;

    public EsOrduEguneratu(JFrame parent, EsOrdKudeatu dao, EsOrdutegia esOrdutegia) {
        super(parent, "Eserlekua Ordutegian Eguneratu", true);
        this.dao = dao;
        this.esOrdutegia = esOrdutegia;

        setLayout(new GridLayout(4, 2));
        setSize(400, 200);
        setLocationRelativeTo(parent);

        add(new JLabel("ID Eserlekua:"));
        txtIdEserlekua = new JTextField(String.valueOf(esOrdutegia.getIdEserlekua()));
        add(txtIdEserlekua);

        add(new JLabel("ID Ordutegi:"));
        txtIdOrdutegi = new JTextField(String.valueOf(esOrdutegia.getIdOrdutegi()));
        add(txtIdOrdutegi);

        add(new JLabel("Beteta:"));
        chkBeteta = new JCheckBox();
        chkBeteta.setSelected(esOrdutegia.isBeteta());
        add(chkBeteta);

        JButton btnSave = new JButton("Eguneratu");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eguneratuEsOrdutegia();
            }
        });
        add(btnSave);

        setVisible(true);
    }

    private void eguneratuEsOrdutegia() {
        try {
            int idEserlekua = Integer.parseInt(txtIdEserlekua.getText());
            int idOrdutegi = Integer.parseInt(txtIdOrdutegi.getText());
            boolean beteta = chkBeteta.isSelected();

            esOrdutegia.setIdEserlekua(idEserlekua);
            esOrdutegia.setIdOrdutegi(idOrdutegi);
            esOrdutegia.setBeteta(beteta);

            dao.eguneratuEsOrdutegia(esOrdutegia);
            JOptionPane.showMessageDialog(this, "Eserlekua Ordutegian eguneratu da!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Sartu balio zuzenak.", "Errorea", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errorea: " + ex.getMessage(), "Errorea", JOptionPane.ERROR_MESSAGE);
        }
    }
}

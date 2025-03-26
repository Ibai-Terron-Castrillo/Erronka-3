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

public class EsOrduSortu extends JDialog {

    private JTextField txtIdEserlekua, txtIdOrdutegi;
    private JCheckBox chkBeteta;
    private EsOrdKudeatu dao;

    public EsOrduSortu(JFrame parent, EsOrdKudeatu dao) {
        super(parent, "Eserlekua Ordutegian Sortu", true);
        this.dao = dao;

        setLayout(new GridLayout(4, 2));
        setSize(400, 200);
        setLocationRelativeTo(parent);

        add(new JLabel("ID Eserlekua:"));
        txtIdEserlekua = new JTextField();
        add(txtIdEserlekua);

        add(new JLabel("ID Ordutegi:"));
        txtIdOrdutegi = new JTextField();
        add(txtIdOrdutegi);

        add(new JLabel("Beteta:"));
        chkBeteta = new JCheckBox();
        add(chkBeteta);

        JButton btnSave = new JButton("Gorde");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortuEsOrdutegia();
            }
        });
        add(btnSave);

        setVisible(true);
    }

    private void sortuEsOrdutegia() {
        try {
            int idEserlekua = Integer.parseInt(txtIdEserlekua.getText());
            int idOrdutegi = Integer.parseInt(txtIdOrdutegi.getText());
            boolean beteta = chkBeteta.isSelected();

            EsOrdutegia esOrdutegia = new EsOrdutegia(0, idEserlekua, idOrdutegi, beteta);
            dao.sortuEsOrdutegia(esOrdutegia);
            JOptionPane.showMessageDialog(this, "Eserlekua Ordutegian sortu da!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Sartu balio zuzenak.", "Errorea", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errorea: " + ex.getMessage(), "Errorea", JOptionPane.ERROR_MESSAGE);
        }
    }
}

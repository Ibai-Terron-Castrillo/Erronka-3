package formularioak;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import klaseak.Ordutegia;
import mantenimendua.OrdutegiaKudeatu;

public class OrdutegiaSortu extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField idPelikulaField, idAretoField, egunaField, orduaField;
    private JCheckBox amaituaCheckBox;
    private OrdutegiaKudeatu dao;

    public OrdutegiaSortu(JFrame parent, OrdutegiaKudeatu dao) {
        super("Ordutegia Sortu");
        this.dao = dao;
        setTitle("Ordutegia Sortu");
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("ID Pelikula:"));
        idPelikulaField = new JTextField();
        panel.add(idPelikulaField);

        panel.add(new JLabel("ID Areto:"));
        idAretoField = new JTextField();
        panel.add(idAretoField);

        panel.add(new JLabel("Eguna:"));
        egunaField = new JTextField();
        panel.add(egunaField);

        panel.add(new JLabel("Ordua:"));
        orduaField = new JTextField();
        panel.add(orduaField);

        panel.add(new JLabel("Amaitua:"));
        amaituaCheckBox = new JCheckBox();
        panel.add(amaituaCheckBox);

        JButton sortuButton = new JButton("Sortu");
        sortuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortuOrdutegia();
            }
        });
        panel.add(sortuButton);

        JButton itxiButton = new JButton("Itxi");
        itxiButton.addActionListener(e -> dispose());
        panel.add(itxiButton);

        add(panel, BorderLayout.CENTER);
    }

    private void sortuOrdutegia() {
        try {
            int idPelikula = Integer.parseInt(idPelikulaField.getText());
            int idAreto = Integer.parseInt(idAretoField.getText());
            String eguna = egunaField.getText();
            String ordua = orduaField.getText();
            boolean amaitua = amaituaCheckBox.isSelected();

            Ordutegia ordutegia = new Ordutegia(0, idPelikula, idAreto, eguna, ordua, amaitua);
            dao.sortuOrdutegia(ordutegia);
            JOptionPane.showMessageDialog(this, "Ordutegia ondo sortu da!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Sartu balio zuzenak ID Pelikula eta ID Aretoan.", "Errorea", JOptionPane.ERROR_MESSAGE);
        }
    }
}
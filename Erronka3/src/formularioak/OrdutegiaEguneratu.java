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

public class OrdutegiaEguneratu extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField idField, idPelikulaField, idAretoField, egunaField, orduaField;
    private JCheckBox amaituaCheckBox;
    private OrdutegiaKudeatu dao;
    private Ordutegia ordutegia;

    public OrdutegiaEguneratu(JFrame parent, OrdutegiaKudeatu dao, Ordutegia ordutegia) {
        super("Ordutegia Eguneratu");
        this.dao = dao;
        this.ordutegia = ordutegia;
        setTitle("Ordutegia Eguneratu");
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));

        panel.add(new JLabel("ID:"));
        idField = new JTextField(String.valueOf(ordutegia.getId()));
        idField.setEditable(false);
        panel.add(idField);

        panel.add(new JLabel("ID Pelikula:"));
        idPelikulaField = new JTextField(String.valueOf(ordutegia.getIdPelikula()));
        panel.add(idPelikulaField);

        panel.add(new JLabel("ID Areto:"));
        idAretoField = new JTextField(String.valueOf(ordutegia.getIdAreto()));
        panel.add(idAretoField);

        panel.add(new JLabel("Eguna:"));
        egunaField = new JTextField(ordutegia.getEguna());
        panel.add(egunaField);

        panel.add(new JLabel("Ordua:"));
        orduaField = new JTextField(ordutegia.getOrdua());
        panel.add(orduaField);

        panel.add(new JLabel("Amaitua:"));
        amaituaCheckBox = new JCheckBox();
        amaituaCheckBox.setSelected(ordutegia.isAmaitua());
        panel.add(amaituaCheckBox);

        JButton eguneratuButton = new JButton("Eguneratu");
        eguneratuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eguneratuOrdutegia();
            }
        });
        panel.add(eguneratuButton);

        JButton itxiButton = new JButton("Itxi");
        itxiButton.addActionListener(e -> dispose());
        panel.add(itxiButton);

        add(panel, BorderLayout.CENTER);
    }

    private void eguneratuOrdutegia() {
        try {
            int id = Integer.parseInt(idField.getText());
            int idPelikula = Integer.parseInt(idPelikulaField.getText());
            int idAreto = Integer.parseInt(idAretoField.getText());
            String eguna = egunaField.getText();
            String ordua = orduaField.getText();
            boolean amaitua = amaituaCheckBox.isSelected();

            Ordutegia ordutegia = new Ordutegia(id, idPelikula, idAreto, eguna, ordua, amaitua);
            dao.eguneratuOrdutegia(ordutegia);
            JOptionPane.showMessageDialog(this, "Ordutegia ondo eguneratu da!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Sartu balio zuzenak ID, ID Pelikula eta ID Aretoan.", "Errorea", JOptionPane.ERROR_MESSAGE);
        }
    }
}
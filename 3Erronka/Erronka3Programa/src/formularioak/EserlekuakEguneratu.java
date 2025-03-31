package formularioak;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import klaseak.Eserlekua;
import mantenimendua.EserlekuaKudeatu;

public class EserlekuakEguneratu extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextField txtId, txtIdAreto, txtZenbakia;
    private JCheckBox chkBeteta;
    private EserlekuaKudeatu dao;
    private Eserlekua eserlekua;

    public EserlekuakEguneratu(Eserlekuak frame, EserlekuaKudeatu dao, Eserlekua eserlekua) {
        super(frame, "Eserlekua Eguneratu", true);
        this.dao = dao;
        this.eserlekua = eserlekua;
        
        setSize(400, 300);
        setLocationRelativeTo(frame);
        
        JPanel panel = new JPanel(new GridLayout(5, 2));
        
        panel.add(new JLabel("ID Areto:"));
        txtIdAreto = new JTextField(String.valueOf(eserlekua.getIdAreto()));
        panel.add(txtIdAreto);
        
        panel.add(new JLabel("Zenbakia:"));
        txtZenbakia = new JTextField(String.valueOf(eserlekua.getZenbakia()));
        panel.add(txtZenbakia);
        
        panel.add(new JLabel("Beteta:"));
        chkBeteta = new JCheckBox();
        chkBeteta.setSelected(eserlekua.isBeteta());
        panel.add(chkBeteta);
        
        JButton btnEguneratu = new JButton("Eguneratu");
        btnEguneratu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eguneratuEserlekua();
            }
        });
        
        JPanel botoiPanel = new JPanel();
        botoiPanel.add(btnEguneratu);
        
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(botoiPanel, BorderLayout.SOUTH);
    }

    private void eguneratuEserlekua() {
        try {
            int idAreto = Integer.parseInt(txtIdAreto.getText());
            int zenbakia = Integer.parseInt(txtZenbakia.getText());
            boolean beteta = chkBeteta.isSelected();
            
            eserlekua.setIdAreto(idAreto);
            eserlekua.setZenbakia(zenbakia);
            eserlekua.setBeteta(beteta);
            
            dao.eguneratuEserlekua(eserlekua);
            JOptionPane.showMessageDialog(this, "Eserlekua eguneratu da!");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Errorea datuak eguneratzean", "Errorea", JOptionPane.ERROR_MESSAGE);
        }
    }
}

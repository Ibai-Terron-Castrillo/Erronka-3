package formularioak;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import klaseak.Eserlekua;
import mantenimendua.EserlekuaKudeatu;

public class EserlekuakSortu extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextField txtIdAreto, txtZenbakia;
    private JCheckBox chkBeteta;
    private EserlekuaKudeatu dao;
    private Eserlekuak parent;

    public EserlekuakSortu(JFrame parent, EserlekuaKudeatu dao) {
        super(parent, "Eserlekua Sortu", true);
        
        this.dao = dao;
        
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("ID Areto:"));
        txtIdAreto = new JTextField();
        panel.add(txtIdAreto);
        
        panel.add(new JLabel("Zenbakia:"));
        txtZenbakia = new JTextField();
        panel.add(txtZenbakia);
        
        panel.add(new JLabel("Beteta:"));
        chkBeteta = new JCheckBox();
        panel.add(chkBeteta);
        
        JButton btnSortu = new JButton("Sortu");
        btnSortu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortuEserlekua();
            }
        });
        
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnSortu);
        
        add(panel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(parent);
    }

    private void sortuEserlekua() {
        try {
            int idAreto = Integer.parseInt(txtIdAreto.getText());
            int zenbakia = Integer.parseInt(txtZenbakia.getText());
            boolean beteta = chkBeteta.isSelected();
            
            Eserlekua eserlekua = new Eserlekua(0, idAreto, zenbakia, beteta);
            dao.sortuEserlekua(eserlekua);
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace(); // Imprime la traza de la excepción
            JOptionPane.showMessageDialog(this, "Datuak okerrak dira!", "Errorea", JOptionPane.ERROR_MESSAGE);
        }
    }
}


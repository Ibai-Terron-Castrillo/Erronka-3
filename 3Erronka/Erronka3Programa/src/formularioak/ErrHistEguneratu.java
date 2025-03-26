package formularioak;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import klaseak.ErrHistorikoa;
import mantenimendua.ErrHistKudeatu;

public class ErrHistEguneratu extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextField txtId, txtIdBezeroa, txtIdOrdutegi, txtKopurua, txtAmaieraData;
    private ErrHistKudeatu dao;
    private ErrHistorikoa errHistorikoa;

    public ErrHistEguneratu(JFrame frame, ErrHistKudeatu dao, ErrHistorikoa errHistorikoa) {
        super(frame, "Erreserba Eguneratu", true);
        this.dao = dao;
        this.errHistorikoa = errHistorikoa;
        
        setSize(400, 300);
        setLocationRelativeTo(frame);
        
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("ID:"));
        txtId = new JTextField(String.valueOf(errHistorikoa.getId()));
        txtId.setEditable(false);
        panel.add(txtId);
        
        panel.add(new JLabel("ID Bezeroa:"));
        txtIdBezeroa = new JTextField(String.valueOf(errHistorikoa.getIdBezeroa()));
        panel.add(txtIdBezeroa);
        
        panel.add(new JLabel("ID Ordutegi:"));
        txtIdOrdutegi = new JTextField(String.valueOf(errHistorikoa.getIdOrdutegi()));
        panel.add(txtIdOrdutegi);
        
        panel.add(new JLabel("Kopurua:"));
        txtKopurua = new JTextField(String.valueOf(errHistorikoa.getKopurua()));
        panel.add(txtKopurua);
        
        panel.add(new JLabel("Amaiera Data:"));
        txtAmaieraData = new JTextField(String.valueOf(errHistorikoa.getAmaieraData()));
        panel.add(txtAmaieraData);
        
        JButton btnEguneratu = new JButton("Eguneratu");
        btnEguneratu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eguneratuErreserba();
            }
        });
        
        JPanel botoiPanel = new JPanel();
        botoiPanel.add(btnEguneratu);
        
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(botoiPanel, BorderLayout.SOUTH);
    }

    private void eguneratuErreserba() {
        try {
            int idBezeroa = Integer.parseInt(txtIdBezeroa.getText());
            int idOrdutegi = Integer.parseInt(txtIdOrdutegi.getText());
            int kopurua = Integer.parseInt(txtKopurua.getText());
            Date amaieraData = Date.valueOf(txtAmaieraData.getText());
            
            errHistorikoa.setIdBezeroa(idBezeroa);
            errHistorikoa.setIdOrdutegi(idOrdutegi);
            errHistorikoa.setKopurua(kopurua);
            errHistorikoa.setAmaieraData(amaieraData);
            
            dao.eguneratuEskaera(errHistorikoa);
            JOptionPane.showMessageDialog(this, "Erreserba eguneratu da!");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Errorea datuak eguneratzean", "Errorea", JOptionPane.ERROR_MESSAGE);
        }
    }
}


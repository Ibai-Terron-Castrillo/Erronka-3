package formularioak;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import klaseak.Erreserba;
import mantenimendua.ErreserbakKudeatu;
import util.DatabaseConnection;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DatabaseConnection;

public class ErreserbakEguneratu extends JFrame {
    private JTextField txtIdBezeroa, txtIdOrdutegi, txtKopurua;
    private JComboBox<String> comboEgoera;
    private JButton btnSave;
    private Erreserba erreserba;
    private ErreserbakKudeatu dao;

    public ErreserbakEguneratu(JFrame parent, ErreserbakKudeatu dao, Erreserba erreserba) {
        this.erreserba = erreserba;
        this.dao = dao;

        setTitle("Erreserba Eguneratu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblIdBezeroa = new JLabel("Bezeroa ID:");
        lblIdBezeroa.setBounds(20, 20, 100, 25);
        add(lblIdBezeroa);

        txtIdBezeroa = new JTextField(String.valueOf(erreserba.getIdBezeroa()));
        txtIdBezeroa.setBounds(130, 20, 200, 25);
        add(txtIdBezeroa);

        JLabel lblIdOrdutegi = new JLabel("Ordutegi ID:");
        lblIdOrdutegi.setBounds(20, 60, 100, 25);
        add(lblIdOrdutegi);

        txtIdOrdutegi = new JTextField(String.valueOf(erreserba.getIdOrdutegi()));
        txtIdOrdutegi.setBounds(130, 60, 200, 25);
        add(txtIdOrdutegi);

        JLabel lblKopurua = new JLabel("Kopurua:");
        lblKopurua.setBounds(20, 100, 100, 25);
        add(lblKopurua);

        txtKopurua = new JTextField(String.valueOf(erreserba.getKopurua()));
        txtKopurua.setBounds(130, 100, 200, 25);
        add(txtKopurua);

        JLabel lblEgoera = new JLabel("Egoera:");
        lblEgoera.setBounds(20, 140, 100, 25);
        add(lblEgoera);

        comboEgoera = new JComboBox<>(new String[]{"Prozesatzen", "Ordaindua"});
        comboEgoera.setSelectedItem(erreserba.getEgoera());
        comboEgoera.setBounds(130, 140, 200, 25);
        add(comboEgoera);

        btnSave = new JButton("Gorde");
        btnSave.setBounds(130, 200, 100, 30);
        add(btnSave);

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                erreserba.setIdBezeroa(Integer.parseInt(txtIdBezeroa.getText()));
                erreserba.setIdOrdutegi(Integer.parseInt(txtIdOrdutegi.getText()));
                erreserba.setKopurua(Integer.parseInt(txtKopurua.getText()));
                erreserba.setEgoera((String) comboEgoera.getSelectedItem());

                dao.eguneratuEskaera(erreserba);
                JOptionPane.showMessageDialog(ErreserbakEguneratu.this, "Erreserba eguneratu da!");

                if ("Ordaindua".equals(erreserba.getEgoera())) {
                    String pdfFile = generatePDF(erreserba);
                    if (pdfFile != null) {
                        uploadPDF(pdfFile);
                    }
                }
                dispose();
            }
        });
    }

    
        public static String generatePDF(Erreserba erreserba) {
            String pdfPath = "Factura_" + erreserba.getId() + ".pdf";
            String query = """
                    SELECT e.id, b.izena, b.abizena1, b.abizena2, o.eguna, o.ordua, a.izena AS aretoa, es.zenbakia, s.prezioa
                    FROM erreserba e
                    JOIN bezeroa b ON e.id_bezero = b.id
                    JOIN ordutegia o ON e.id_ordutegi = o.id
                    JOIN aretoa a ON o.id_areto = a.id
                    JOIN sarrera s ON e.id = s.id_erreserba
                    JOIN eserlekua es ON s.id_eserleku = es.id
                    WHERE e.id = ?
                    """;

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, erreserba.getId());
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    PdfWriter writer = new PdfWriter(pdfPath);
                    PdfDocument pdf = new PdfDocument(writer);
                    Document document = new Document(pdf);

                    // Encabezado
                    Paragraph title = new Paragraph("Faktura")
                            .setBold()
                            .setFontSize(20);
                    document.add(title);
                    document.add(new Paragraph("\n"));

                    // Datos del cliente
                    document.add(new Paragraph("Bezeroa: " + rs.getString("izena") + " " + rs.getString("abizena1") + " " + rs.getString("abizena2")));
                    document.add(new Paragraph("Eguna: " + rs.getString("eguna")));
                    document.add(new Paragraph("Ordua: " + rs.getString("ordua")));
                    document.add(new Paragraph("Aretoa: " + rs.getString("aretoa")));
                    document.add(new Paragraph("\n"));

                    // Tabla para los asientos y precios
                    Table table = new Table(new float[]{200, 200});
                    table.addHeaderCell(new Cell().add(new Paragraph("Eserlekua").setBold()));
                    table.addHeaderCell(new Cell().add(new Paragraph("Prezioa").setBold()));
                    
                    double total = 0;
                    do {
                        table.addCell(new Cell().add(new Paragraph(String.valueOf(rs.getInt("zenbakia")))));
                        double prezioa = rs.getDouble("prezioa");
                        table.addCell(new Cell().add(new Paragraph(prezioa + "€")));
                        total += prezioa;
                    } while (rs.next());
                    document.add(table);
                    
                    // Total
                    document.add(new Paragraph("\n"));
                    Paragraph totalParagraph = new Paragraph("Guztira: " + total + "€")
                            .setBold();
                    document.add(totalParagraph);

                    document.close();
                    return pdfPath;
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }


	// Function to upload PDF to Apache server
	public void uploadPDF(String filePath) {
		String serverURL = "http://172.16.237.169/upload.php";

		try {
			File file = new File(filePath);
			URL url = new URL(serverURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=*****");

			OutputStream outputStream = connection.getOutputStream();
			FileInputStream fileInputStream = new FileInputStream(file);

			outputStream.write(("--*****\r\n" + "Content-Disposition: form-data; name=\"file\"; filename=\""
					+ file.getName() + "\"\r\n" + "Content-Type: application/pdf\r\n\r\n").getBytes());

			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = fileInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			outputStream.write("\r\n--*****--\r\n".getBytes());
			outputStream.flush();
			fileInputStream.close();
			outputStream.close();

			int responseCode = connection.getResponseCode();
			System.out.println("Upload Response: " + responseCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
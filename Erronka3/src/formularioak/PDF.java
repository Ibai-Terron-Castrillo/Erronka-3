package formularioak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class PDF {
	public static void fakturaSortu(String eskaeraID, String bezeroa, String email, String telefonoa, String data,
			String helbidea, String produktuak, String guztira, String garraiolaria) {
		try (PDDocument document = new PDDocument()) {
			PDPage page = new PDPage();
			document.addPage(page);
			PDPageContentStream contentStream = new PDPageContentStream(document, page);

			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
			contentStream.beginText();
			contentStream.newLineAtOffset(100, 700);

			contentStream.showText("FAKTURA");
			contentStream.newLineAtOffset(0, -20);

			contentStream.setFont(PDType1Font.HELVETICA, 12);
			contentStream.showText("Eskaera ID: " + eskaeraID);
			contentStream.newLineAtOffset(0, -20);
			contentStream.showText("Bezeroa: " + bezeroa);
			contentStream.newLineAtOffset(0, -20);
			contentStream.showText("Email: " + email);
			contentStream.newLineAtOffset(0, -20);
			contentStream.showText("Telefonoa: " + telefonoa);
			contentStream.newLineAtOffset(0, -20);
			contentStream.showText("Data: " + data);
			contentStream.newLineAtOffset(0, -20);
			contentStream.showText("Helbidea: " + helbidea);
			contentStream.newLineAtOffset(0, -20);

			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
			lerroaIdatzi(contentStream, "Eskatutako Produktuak:");
			contentStream.setFont(PDType1Font.HELVETICA, 12);
			for (String linea : testuaZatitu(produktuak, 80)) {
				lerroaIdatzi(contentStream, linea);
			}

			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
			contentStream.showText("Guztira: " + guztira);
			contentStream.newLineAtOffset(0, -20);
			contentStream.showText("Garraiolaria: " + garraiolaria);
			contentStream.newLineAtOffset(0, -20);

			contentStream.endText();
			contentStream.close();

			String filePath = "Faktura_" + eskaeraID + ".pdf";
			document.save(filePath);
			document.close();

			JOptionPane.showMessageDialog(null, "Faktura sortu da: " + filePath);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Errorea faktura sortzean: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void lerroaIdatzi(PDPageContentStream contentStream, String text) throws IOException {
		contentStream.showText(text);
		contentStream.newLineAtOffset(0, -20);
	}

	private static List<String> testuaZatitu(String text, int maxLineLength) {
		List<String> lineas = new ArrayList<>();
		while (text.length() > maxLineLength) {
			int corte = text.lastIndexOf(" ", maxLineLength);
			if (corte == -1)
				corte = maxLineLength;
			lineas.add(text.substring(0, corte));
			text = text.substring(corte).trim();
		}
		lineas.add(text);
		return lineas;
	}
}

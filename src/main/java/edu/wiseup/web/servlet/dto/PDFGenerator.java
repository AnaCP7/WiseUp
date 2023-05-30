package edu.wiseup.web.servlet.dto;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.wiseup.persistence.dao.Score;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PDFGenerator {
    public static void generateRankingPDF(List<Score> rankingData, ByteArrayOutputStream outputStream) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
            Paragraph paragraph = new Paragraph("WiseUp Ranking", font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            PdfPCell tableHeaderCell = new PdfPCell();

            tableHeaderCell.setPadding(5);
            tableHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableHeaderCell.setBackgroundColor(BaseColor.LIGHT_GRAY);

            tableHeaderCell.setPhrase(new Phrase("Ranking", tableHeaderFont));
            table.addCell(tableHeaderCell);

            tableHeaderCell.setPhrase(new Phrase("Score", tableHeaderFont));
            table.addCell(tableHeaderCell);

            tableHeaderCell.setPhrase(new Phrase("User", tableHeaderFont));
            table.addCell(tableHeaderCell);

            tableHeaderCell.setPhrase(new Phrase("Date", tableHeaderFont));
            table.addCell(tableHeaderCell);

            Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA);
            PdfPCell tableCell = new PdfPCell();

            tableCell.setPadding(5);
            tableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

            for (int i = 0; i < rankingData.size(); i++) {
                Score score = rankingData.get(i);

                tableCell.setPhrase(new Phrase(String.valueOf(i + 1), tableCellFont));
                table.addCell(tableCell);

                tableCell.setPhrase(new Phrase(String.valueOf(score.getScore()), tableCellFont));
                table.addCell(tableCell);

                tableCell.setPhrase(new Phrase(score.getUser().getUsername(), tableCellFont));
                table.addCell(tableCell);

                tableCell.setPhrase(new Phrase(score.getDate().toString().substring(0, 10), tableCellFont));
                table.addCell(tableCell);
            }

            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}

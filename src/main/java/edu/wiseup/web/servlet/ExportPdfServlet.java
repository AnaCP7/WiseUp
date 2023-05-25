package edu.wiseup.web.servlet;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.wiseup.persistence.connector.MySQLConnector;
import edu.wiseup.persistence.dao.Score;
import edu.wiseup.persistence.manager.ScoreManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
@WebServlet(name = "ExportPdfServlet", urlPatterns = { "/export-pdf-servlet" })

public class ExportPdfServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"ranking.pdf\"");

        try (OutputStream outputStream = response.getOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);

            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
            Paragraph paragraph = new Paragraph("WiseUp Ranking", font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            document.add(Chunk.NEWLINE);

            MySQLConnector connector = new MySQLConnector();
            String url = connector.getURL();

            Connection connection = null;
            try {
                connection = connector.getMySQLConnection();
                ScoreManager scoreManager = new ScoreManager();
                List<Score> scores = scoreManager.findAllSorted(connection);

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

                for (int i = 0; i < scores.size(); i++) {
                    Score score = scores.get(i);

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
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}

package edu.wiseup.web.servlet;

import edu.wiseup.persistence.connector.MySQLConnector;
import edu.wiseup.persistence.manager.ScoreManager;
import edu.wiseup.persistence.dao.Score;
import edu.wiseup.persistence.dao.PDFGenerator;
import edu.wiseup.web.servlet.PDFGeneratorServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "PDFGeneratorServlet", urlPatterns = "/pdf-generator-servlet")
public class PDFGeneratorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtén los datos del ranking desde tu aplicación
        List<Score> rankingData = obtenerDatosDelRanking();

        // Genera el archivo PDF del ranking
        byte[] pdfBytes = generarPDFRanking(rankingData);

        // Descarga el archivo PDF
        descargarPDF(pdfBytes, response);
    }

    private List<Score> obtenerDatosDelRanking() {
        try {
            // Obtén una conexión a la base de datos
            MySQLConnector connector = new MySQLConnector();
            java.sql.Connection connection = MySQLConnector.getMySQLConnection();

            // Crea una instancia de ScoreManager
            ScoreManager scoreManager = new ScoreManager();

            // Obtiene los datos del ranking desde la base de datos utilizando ScoreManager
            List<Score> rankingData = scoreManager.findAllSorted(connection);

            // Cierra la conexión a la base de datos
            connection.close();

            // Retorna los datos del ranking
            return rankingData;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
        return null;
    }

    private byte[] generarPDFRanking(List<Score> rankingData) throws IOException {
        // Genera el archivo PDF del ranking utilizando la lista de objetos Score
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PDFGenerator.generateRankingPDF(rankingData, outputStream);
        return outputStream.toByteArray();
    }

    private void descargarPDF(byte[] pdfBytes, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=ranking.pdf");
        response.setContentLength(pdfBytes.length);

        response.getOutputStream().write(pdfBytes);
        response.getOutputStream().flush();
    }
}

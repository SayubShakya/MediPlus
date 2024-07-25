package pkg1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class ExportAllServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();

		HttpSession sessions=request.getSession(false);
	   	String currentUser=(String)sessions.getAttribute("currentUser");
	   	out.println("<p>Current User: "+currentUser+"<p>");
	   	
		try {
			String sql = "select * from appointments";
			Connection conn = new DatabaseConnection().connect();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			String PDFFileName = "C:\\Users\\acer\\Desktop\\Pdf.pdf";
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(PDFFileName));
			document.open();

			PdfPTable table = new PdfPTable(7);

			PdfPCell cell;

			cell = new PdfPCell(new Paragraph("ID"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Name"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Email"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Phone"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Department"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Doctor"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Date"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Message"));
			table.addCell(cell);

			while (rs.next()) {
				cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("id"))));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(rs.getString("name")));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(rs.getString("email")));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(rs.getString("phone")));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(rs.getString("department")));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(rs.getString("doctor")));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(rs.getString("date")));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(rs.getString("message")));
				table.addCell(cell);
			}

			document.add(table);
			document.close();
			out.println("Successfully exported data to PDF.");

		} catch (Exception e) {
			out.println("Error: " + e.getMessage());
		} finally {
			out.close();
		}
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

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
import com.itextpdf.text.pdf.PdfWriter;

public class ExportIndividualServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();

		HttpSession sessions=request.getSession(false);
	   	String currentUser=(String)sessions.getAttribute("currentUser");
	   	out.println("<p>Current User: "+currentUser+"<p>");
	   	
		try {
			Connection conn = new DatabaseConnection().connect();
			String id = request.getParameter("id");
			String sql = "SELECT * FROM appointments WHERE id = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, Integer.parseInt(id));
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				String PDFFileName = "C:\\Users\\acer\\Desktop\\Pdf" + id + ".pdf";
				Document document = new Document();
				PdfWriter.getInstance(document, new FileOutputStream(PDFFileName));
				document.open();
				Paragraph para1 = new Paragraph("ID: " + rs.getInt("id"));
				Paragraph para2 = new Paragraph("Name: " + rs.getString("name"));
				Paragraph para3 = new Paragraph("Email: " + rs.getString("email"));
				Paragraph para4 = new Paragraph("Phone: " + rs.getString("phone"));
				Paragraph para5 = new Paragraph("Department: " + rs.getString("department"));
				Paragraph para6 = new Paragraph("Doctor: " + rs.getString("doctor"));
				Paragraph para7 = new Paragraph("Date: " + rs.getString("date"));
				Paragraph para8 = new Paragraph("Message: " + rs.getString("message"));
				document.add(para1);
				document.add(para2);
				document.add(para3);
				document.add(para4);
				document.add(para5);
				document.add(para6);
				document.add(para7);
				document.add(para8);

				document.close();
				out.println("Successfully exported data to PDF for users ID: " + id);
			} else {
				out.println("No users found with ID: " + id);
			}
		} catch (Exception e) {
			out.println("Error: " + e.getMessage());
		} finally {
			out.close();
		}
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

package pkg1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteAppointmentservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String id = request.getParameter("id");
        
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection con = db.connect();
            String sql = "DELETE FROM appointments WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            
            // Set success message
            request.setAttribute("message", "Successfully Deleted.");
            
            // Forward to success.jsp
            request.getRequestDispatcher("success.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

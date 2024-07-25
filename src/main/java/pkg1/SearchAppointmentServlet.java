package pkg1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchQuery = request.getParameter("search");
        ResultSet rs = null;

        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection con = db.connect();
            String sql = "SELECT * FROM appointments WHERE name LIKE ? OR email LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchQuery + "%");
            ps.setString(2, "%" + searchQuery + "%");
            rs = ps.executeQuery();
            request.setAttribute("results", rs);
            request.getRequestDispatcher("Searchresult.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

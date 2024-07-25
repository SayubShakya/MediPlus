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
import java.sql.SQLException;

public class EditAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Appointment appointment = null;

		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection con = db.connect();
			String sql = "SELECT * FROM appointments WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				appointment = new Appointment();
				appointment.setId(rs.getInt("id"));
				appointment.setName(rs.getString("name"));
				appointment.setEmail(rs.getString("email"));
				appointment.setPhone(rs.getString("phone"));
				appointment.setDepartment(rs.getString("department"));
				appointment.setDoctor(rs.getString("doctor"));
				appointment.setDate(rs.getString("date"));
				appointment.setMessage(rs.getString("message"));
			}
			rs.close();
			ps.close();
			con.close();

			if (appointment != null) {
				request.setAttribute("appointment", appointment);
				request.getRequestDispatcher("editAppointment.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "No appointment found to edit.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("message", "Error retrieving appointment: " + e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

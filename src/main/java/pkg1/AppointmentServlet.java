package pkg1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String department = request.getParameter("department");
		String doctor = request.getParameter("doctor");
		String date = request.getParameter("date");
		String message = request.getParameter("message");

		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection con = db.connect();
			String sql = "INSERT INTO appointments (name, email, phone, department, doctor, date, message) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, phone);
			ps.setString(4, department);
			ps.setString(5, doctor);
			ps.setString(6, date);
			ps.setString(7, message);
			ps.executeUpdate();

			request.setAttribute("message", "Successfully Booked.");

			request.getRequestDispatcher("success.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

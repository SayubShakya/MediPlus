package pkg1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class LoginServlet2
 */
public class LoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String username = request.getParameter("txtUser");
	        String password = request.getParameter("txtPass");
	        String remember = request.getParameter("chkSave");

	        boolean isValidUser = false;

	        // Check credentials
	        if ("admin".equals(username) && "admin".equals(password)) {
	            isValidUser = true;
	        }

	        if (isValidUser) {
	            if ("yes".equals(remember)) {
	                Cookie userCookie = new Cookie("user", username);
	                Cookie passCookie = new Cookie("pass", password);
	                userCookie.setMaxAge(60 * 60 * 24); // 1 day
	                passCookie.setMaxAge(60 * 60 * 24); // 1 day
	                response.addCookie(userCookie);
	                response.addCookie(passCookie);
	            } else {
	                Cookie userCookie = new Cookie("user", "");
	                Cookie passCookie = new Cookie("pass", "");
	                userCookie.setMaxAge(0);
	                passCookie.setMaxAge(0);
	                response.addCookie(userCookie);
	                response.addCookie(passCookie);
	            }
	            response.sendRedirect("AdminDash.jsp");
	        } else {
	            response.sendRedirect("LoginUser.jsp?error=true");
	        }
	    }
	
	}



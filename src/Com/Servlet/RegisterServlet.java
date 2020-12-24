package Com.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Com.Model.User;
import Com.Service.UserService;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserService service = new UserService();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username.equals("") || password.equals("")) {
			response.getWriter().append("Invalid Input!");
			RequestDispatcher view = request.getRequestDispatcher("/register.jsp");      
	        view.include(request, response);
		}
		else {
			User user = new User(username, password);
			if (service.register(user)) {
				response.sendRedirect("registersuccess.jsp");	
			}
			else {
				response.getWriter().append("Username already exists!");
				RequestDispatcher view = request.getRequestDispatcher("/register.jsp");      
		        view.include(request, response);			
			}
		}
	}

}

package Com.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Com.Model.User;
import Com.Service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			UserService service = new UserService();
			HttpSession session = request.getSession();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (username.equals("") || password.equals("")) {
				response.getWriter().append("Invalid Input!");
				RequestDispatcher view = request.getRequestDispatcher("/login.jsp");      
		        view.include(request, response);
			}
			else {
				User user = new User(username, password);
				if (service.login(user)) {
					session.setAttribute("sesname", user.getName());
					response.sendRedirect("loginsuccess.jsp");	
				}
				else {
					response.getWriter().append("Information is incorrect!");
					RequestDispatcher view = request.getRequestDispatcher("/login.jsp");      
			        view.include(request, response);			
				}
			}

	}

}

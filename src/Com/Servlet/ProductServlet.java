package Com.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Com.Model.Product;
import Com.Service.ProductService;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ProductService service = new ProductService();
			String name = request.getParameter("name");
			int price = Integer.valueOf(request.getParameter("price"));
			Product product = new Product(name, price);
			service.add(product);
			response.sendRedirect("productsuccess.jsp");
		} catch (Exception e) {
			response.sendRedirect("productfail.jsp");

		}
	}

}

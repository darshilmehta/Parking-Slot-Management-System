package parking.register.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parking.register.bean.RegisterBean;
import parking.register.database.RegisterDao;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("fname");
		String lastname = request.getParameter("lname");
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		String email = request.getParameter("email");
		String customer_number = request.getParameter("cno");
		String vehicle_number = request.getParameter("vno");
		String vehicle_type = request.getParameter("vtype");
		
		RegisterBean registerbean = new RegisterBean();
		registerbean.setFirstname(firstname);
		registerbean.setLastname(lastname);
		registerbean.setUsername(username);
		registerbean.setPassword(password);
		registerbean.setEmail(email);
		registerbean.setCustomer_number(customer_number);
		registerbean.setVehicle_number(vehicle_number);
		registerbean.setVehicle_type(vehicle_type);
		
		RegisterDao registerdao = new RegisterDao();
		if(registerdao.registerClient(registerbean)) {
			HttpSession sess = request.getSession();
			sess.setAttribute("username", username);
			response.sendRedirect("clientloginsuccess.jsp");
		} else {
			response.sendRedirect("newlogin.jsp");
		}
	}
}

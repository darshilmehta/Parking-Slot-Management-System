package parking.update.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parking.update.bean.UpdateBean;
import parking.update.database.UpdateDao;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		String username = String.valueOf(sess.getAttribute("username"));
		
		String password = request.getParameter("pass");
		String vehicle_number = request.getParameter("vno");
		String vehicle_type = request.getParameter("vtype");
		
		UpdateBean updatebean = new UpdateBean();
		updatebean.setPassword(password);
		updatebean.setVehicle_number(vehicle_number);
		updatebean.setVehicle_type(vehicle_type);
		
		UpdateDao updatedao = new UpdateDao();
		if(updatedao.updateClient(updatebean, username)) {
			response.sendRedirect("clientloginsuccess.jsp");
		} else {
			response.sendRedirect("clientlogin.jsp");
		}
	}
}

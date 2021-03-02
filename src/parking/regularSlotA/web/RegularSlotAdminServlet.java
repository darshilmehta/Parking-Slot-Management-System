package parking.regularSlotA.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parking.regularSlotA.bean.RegularSlotAdminBean;
import parking.regularSlotA.database.RegularSlotAdminDao;

@WebServlet("/regular-slot-admin")
public class RegularSlotAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegularSlotAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		String username = String.valueOf(hs.getAttribute("c_username"));
		String location = request.getParameter("location");
		String startDate = request.getParameter("start-date");
		String duration  = request.getParameter("duration");
		
		RegularSlotAdminBean rsab = new RegularSlotAdminBean();
		rsab.setUsername(username);
		rsab.setLocation(location);
		rsab.setDuration(duration);
		rsab.setStartDate(startDate);
		
		RegularSlotAdminDao rsad = new RegularSlotAdminDao();
		try {
			if(rsad.bookSlotAdmin(rsab)) {
				response.sendRedirect("admin_slot_booking_success.jsp");
			}
			else {
				response.sendRedirect("error_no_slot_avail_admin.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package parking.regularPass.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import parking.regularPass.bean.RegularPassBean;
import parking.regularPass.database.RegularPassDao;

@WebServlet("/regular-pass")
public class RegularPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegularPassServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		String username = String.valueOf(sess.getAttribute("username"));
		String location = request.getParameter("location");
		String startDate = request.getParameter("start-date");
		String duration  = request.getParameter("duration");
		
		RegularPassBean rpb = new RegularPassBean();
		rpb.setUsername(username);
		rpb.setLocation(location);
		rpb.setStartDate(startDate);
		rpb.setDuration(duration);
		
		RegularPassDao rpd = new RegularPassDao();
		try {
			if(rpd.bookPass(rpb)) {
				response.sendRedirect("current_booking.jsp");
			}
			else {
				response.sendRedirect("error_no_slot_avail.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

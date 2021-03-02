package parking.regularSlotC.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import parking.regularSlotC.bean.RegualrSlotClientBean;
import parking.regularSlotC.database.RegularSlotClientDao;

@WebServlet("/regular-slot-client")
public class RegularSlotClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegularSlotClientServlet() {
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
		String location = request.getParameter("location");
		String startDate = request.getParameter("start-date");
		String duration  = request.getParameter("duration");
		
		RegualrSlotClientBean rscb = new RegualrSlotClientBean();
		rscb.setUsername(username);
		rscb.setLocation(location);
		rscb.setStartDate(startDate);
		rscb.setDuration(duration);
		
		RegularSlotClientDao rscd = new RegularSlotClientDao();
		try {
			if(rscd.bookSlotClient(rscb)) {
				response.sendRedirect("current_booking_short.jsp");
			}
			else {
				response.sendRedirect("error_no_slot_avail.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

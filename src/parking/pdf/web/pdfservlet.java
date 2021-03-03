package parking.pdf.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;
import parking.pdf.bean.pdfbean;
import parking.pdf.database.pdfdao;

@WebServlet("/pdf-generator")
public class pdfservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public pdfservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int booking_id = Integer.parseInt(request.getParameter("booking_id"));
		
		pdfbean db = new pdfbean();
		db.setBookingID(booking_id);
		
		pdfdao dd = new pdfdao();
		try {
			try {
				if(dd.MakeSlip(db)) {
					response.sendRedirect("#"); 
				} else {
					response.sendRedirect("#");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}
}

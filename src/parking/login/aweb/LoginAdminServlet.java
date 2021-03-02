package parking.login.aweb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import parking.login.abean.LoginBeanAdmin;
import parking.login.adatabase.LoginDaoAdmin;

@WebServlet("/alogin")
public class LoginAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String a = request.getParameter("admin");
		String p = request.getParameter("pass");
		
		LoginBeanAdmin loginbeanadmin = new LoginBeanAdmin();
		loginbeanadmin.setAdmin(a);
		loginbeanadmin.setPassword(p);
		
		LoginDaoAdmin logindaoadmin = new LoginDaoAdmin();
		if(logindaoadmin.validate(loginbeanadmin)) {
			response.sendRedirect("aloginsuccess.jsp");
		} else {
			response.sendRedirect("alogin.jsp");
		}
	}
}
package parking.login.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parking.login.bean.LoginBean;
import parking.login.database.LoginDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u = request.getParameter("username");
		String p = request.getParameter("password");
		
		LoginBean loginbean = new LoginBean();
		loginbean.setUsername(u);
		loginbean.setPassword(p);
		
		LoginDao logindao = new LoginDao();
		if(logindao.validate(loginbean)) {
			HttpSession sess = request.getSession();
			sess.setAttribute("username", u);
			response.sendRedirect("clientloginsuccess.jsp");
		} else {
			response.sendRedirect("clientlogin.jsp");
		}
	}
}

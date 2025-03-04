package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import dto.UserDto;

@WebServlet("/userlogin")
public class UserLogin extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		Dao dao = new Dao();
		UserDto user = dao.loginUser(email, password);
		if(user!=null) {
			// login success
			HttpSession session = req.getSession();
			if(user.getUserRole().equals("manager")) {
				// manager home page
				session.setAttribute("role", "manager");
				resp.sendRedirect("managerhome.jsp");
			}else {
				// customer home page
				session.setAttribute("role", "customer");
			}
		}else {
			// login failure
		}
	}
	
}

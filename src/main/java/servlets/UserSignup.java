package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.AccountDto;
import dto.AccountStatus;
import dto.AccountType;
import dto.UserDto;

@WebServlet("/usersignup")
public class UserSignup extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		Dao dao = new Dao();
		
		
		int userid = dao.getNumOfUsers();
		String name = req.getParameter("username");
		int age = Integer.parseInt(req.getParameter("userage"));
		String email = req.getParameter("useremail");
		long contact = Long.parseLong(req.getParameter("usercontact"));
		String password = req.getParameter("userpassword");
		String role = req.getParameter("role");
		
		String accounttype = req.getParameter("accounttype");
		
		AccountType actype = accounttype.equals("current")?AccountType.CURRENT:AccountType.SAVING;
		
		int accountid = dao.getAccountid();
		
		UserDto user = new UserDto(userid, name, age, email, contact, password, role, accountid) ;
		int res = dao.saveUser(user); // 0 means, already manager exist
		
		if(res!=0) {
			AccountDto accountDto = new AccountDto(accountid, 500,actype ,AccountStatus.BLOCKED);
			dao.createAccount(accountDto);
			resp.getWriter().println(res+"  operations done ");
		}else {
			// error saving customer or manager(manager already exists)
			resp.sendRedirect("login.jsp");
		}
		
	}
	
}

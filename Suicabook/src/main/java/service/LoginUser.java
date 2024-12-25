package service;

import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;

import bean.UserBean;
import dao.UserDao;

public class LoginUser {
	public UserBean execute(HttpServletRequest request) throws Exception{
		UserDao dao = null;
		try {
			String user_name = request.getParameter("user_name");
			String user_pass = request.getParameter("user_pass");
			dao = new UserDao();
			return dao.loginUser(user_name, user_pass);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		} finally {
			if(dao != null) {
				dao.close();
			}
		}
		return null;
	}			
}

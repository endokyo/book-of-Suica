package service;

import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;

import dao.UserDao;

public class SearchUser {
	public boolean execute(HttpServletRequest request) throws Exception{
		UserDao dao = null;
		try {
			String user_name = request.getParameter("user_name");
			dao = new UserDao();
			return dao.searchUser(user_name);
		
		} catch (SQLException e) {
			e.printStackTrace();
		
		} finally {
			if(dao != null) {
				dao.close();
			}
		}
		return false;
	}			
}

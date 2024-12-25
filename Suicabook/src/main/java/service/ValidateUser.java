package service;

import jakarta.servlet.http.HttpServletRequest;

public class ValidateUser {
	public boolean execute(HttpServletRequest request) throws Exception {
		String user_name = request.getParameter("user_name");
		return user_name == null || user_name.equals("");
	}
}

package service;

import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;

import bean.GenreBean;
import dao.GenreDao;

public class TodayGenre {
	public GenreBean execute(HttpServletRequest request) throws Exception{
		GenreDao dao = null;
		try {
			String user_id = request.getParameter("user_id");
			dao = new GenreDao();
			return dao.getTodayGenreId(1);
		
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

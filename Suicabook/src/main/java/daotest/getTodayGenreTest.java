package daotest;

import java.sql.SQLException;

import bean.GenreBean;
import dao.GenreDao;

public class getTodayGenreTest {
	public static void main(String[] args) {
		GenreDao dao = null;
		GenreBean bean = null;
		try {
			dao = new GenreDao();			
			bean = dao.getTodayGenreId(1);			
			if (bean != null) {
				System.out.println("genre_id:" + bean.getId());
				System.out.println("genre_name:" + bean.getGenre());
				System.out.println("---------------------------------------------------");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("クラスの読み込みに失敗しました");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("JDBCエラーです");
			e.getMessage();
			e.printStackTrace();
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}

package daotest;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.GenreBean;
import dao.GenreDao;

public class GenreListTest {
	public static void main(String[] args) {
		GenreDao dao = null;
		ArrayList<GenreBean> list = null;
		try {
			dao = new  GenreDao();
			
			list = dao.createGenreList();
			
			for (GenreBean bean : list) {
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

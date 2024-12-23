package daotest;

import java.sql.SQLException;

import bean.GenreBean;
import dao.GenreDao;

public class SearchGenreTest {
	public static void main(String[] args) {
		GenreDao dao = null;
		int id = Integer.parseInt(args[0]);
		GenreBean bean = null;
		try {
			dao = new GenreDao();

			bean = dao.searchGenre(id);
			if (bean != null) {
				System.out.println("genre_id：" + bean.getId());
				System.out.println("genre_name：" + bean.getGenre());
			} else {
				System.out.println("取得に失敗");
			}

		} catch (SQLException e) {
			System.out.println("JDBCエラーです");
			e.getMessage();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("クラスの読み込みに失敗しました");
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}

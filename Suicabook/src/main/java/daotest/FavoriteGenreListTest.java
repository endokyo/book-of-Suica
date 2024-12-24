package daotest;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.FavoriteDao;

public class FavoriteGenreListTest {
	public static void main(String[] args) {
		FavoriteDao dao = null;
		int id = Integer.parseInt(args[0]);
		ArrayList<String> list = null;
		try {
			dao = new FavoriteDao();

			list = dao.getFavoriteGenre(id);
			
			for (String genre : list) {
				System.out.println("genre_name:" + genre);
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

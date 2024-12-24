package daotest;

import java.sql.SQLException;

import bean.FavoriteBean;
import dao.FavoriteDao;

public class DeleteFavoriteTest {
	public static void main(String[] args) {
		FavoriteDao dao = null;
		FavoriteBean bean = new FavoriteBean();
		int id = 1;
		try {
			dao = new FavoriteDao();

			int numrow = dao.deleteFavorite(id);
			if (numrow > 0) {
				System.out.println("成功");
			} else {
				System.out.println("失敗");
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

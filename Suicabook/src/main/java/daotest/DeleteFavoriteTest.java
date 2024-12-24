package daotest;

import java.sql.SQLException;

import bean.FavoriteBean;
import dao.FavoriteDao;

public class DeleteFavoriteTest {
	public static void main(String[] args) {
		FavoriteDao dao = null;
		FavoriteBean bean = new FavoriteBean();
		try {
			dao = new FavoriteDao();

			bean.setFavorite_id(-1);

			int numrow = dao.deleteFavorite(Integer.parseInt(args[0]));
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

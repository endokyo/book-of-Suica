package daotest;

import java.sql.SQLException;

import bean.FavoriteBean;
import dao.FavoriteDao;

public class SearchFavoriteTest {
	public static void main(String[] args) {
		FavoriteDao dao = null;
		int favo_id = Integer.parseInt(args[0]);
		FavoriteBean bean = new FavoriteBean();
		try {
			dao = new FavoriteDao();
			
			bean = dao.searchFavorite(favo_id);
			
			if(bean != null) {
				System.out.println("favorite_id:" + bean.getFavorite_id());
				System.out.println("user_id:" + bean.getUser_id());
				System.out.println("book_id:" + bean.getBook_id());
			}
		} catch (ClassNotFoundException e) {
			System.out.println("クラスの読み込みに失敗しました");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("JDBCエラーです");
			e.getMessage();
			e.printStackTrace();
		}finally {
			if(dao != null) {
				dao.close();
			}
		}

	}
}

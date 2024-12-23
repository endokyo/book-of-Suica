package daotest;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.BookBean;
import dao.FavoriteDao;

public class FavoriteListTest {
	public static void main(String[] args) {
		FavoriteDao dao = null;
		int id = Integer.parseInt(args[0]);
		ArrayList<BookBean> list = null;
		try {
			dao = new FavoriteDao();

			list = dao.getFavoriteListSortByUser(id);
			
			for (BookBean bean : list) {
				System.out.println("book_id：" + bean.getId());
				System.out.println("book_cover：" + bean.getImg());
				System.out.println("book_title：" + bean.getTitle());
				System.out.println("book_creater：" + bean.getCreater());
				System.out.println("book_genre：" + bean.getGenre());
				System.out.println("book_comcount：" + bean.getComcount());
				System.out.println("book_favcount：" + bean.getFavcount());
				System.out.println("book_avecount：" + bean.getAvecount());
				System.out.println("book_ave：" + bean.getAverage());
				System.out.println("favorite：" + bean.isFavorite());
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

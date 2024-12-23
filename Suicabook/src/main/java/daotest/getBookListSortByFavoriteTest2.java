package daotest;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.BookBean;
import bean.UserBean;
import dao.BookDao;

public class getBookListSortByFavoriteTest2 {
	public static void main(String[] args) {
		BookDao dao = null;
		ArrayList<BookBean> list = null;
		String key = args[0];
		int genre = 1;

		try {
			dao = new BookDao();
			UserBean ub = new UserBean();
			ub.setId(1);
			ub.setPassword("pass01");
			ub.setName("user01");

			list = dao.getBookListSortByFavorite(ub, key, genre);

			for (BookBean bb : list) {
				System.out.println("book_id：" + bb.getId());
				System.out.println("book_cover：" + bb.getImg());
				System.out.println("book_title：" + bb.getTitle());
				System.out.println("book_creater：" + bb.getCreater());
				System.out.println("book_genre：" + bb.getGenre());
				System.out.println("book_twicount：" + bb.getTwicount());
				System.out.println("book_favcount：" + bb.getFavcount());
				System.out.println("book_avecount：" + bb.getAvecount());
				System.out.println("book_ave：" + bb.getAverage());
				System.out.println("favorite：" + bb.isFavorite());
				System.out.println("------------------------------------------");
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

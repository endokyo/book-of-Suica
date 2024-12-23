package daotest;

import java.sql.SQLException;

import bean.BookBean;
import dao.BookDao;

public class SearchBookTest {
	public static void main(String[] args) {
		BookDao dao = null;
		int id = Integer.parseInt(args[0]);
		BookBean bean = null;
		try {
			dao = new BookDao();

			bean = dao.searchBook(id, id);
			if (bean != null) {
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

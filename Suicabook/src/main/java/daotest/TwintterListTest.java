package daotest;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.TwintterBean;
import dao.TwintterDao;

public class TwintterListTest {
	public static void main(String[] args) {
		TwintterDao dao = null;
		int id = Integer.parseInt(args[0]);//book_id
		ArrayList<TwintterBean> list = null;
		try {
			dao = new TwintterDao();

			list = dao.getTwintterListSortByBook(id);
			
			for (TwintterBean bean : list) {
				System.out.println("user_id:" + bean.getUser_id());
				System.out.println("user_name:" + bean.getUser_name());
				System.out.println("book_id:" + bean.getBook_id());
				System.out.println("twintter_text:" + bean.getTwintter_text());
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

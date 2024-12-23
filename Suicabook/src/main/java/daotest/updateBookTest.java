package daotest;

import java.sql.SQLException;

import bean.BookBean;
import dao.BookDao;

public class updateBookTest {
	public static void main(String[] args) {
		BookDao dao = null;
		int i = Integer.parseInt(args[0]);
		BookBean bean = new BookBean();
		try {
			dao = new BookDao();

			bean.setId(i);
			bean.setAverage(3.5);

			int numrow = dao.updateBook(bean);
			if(numrow > 0) {
				System.out.println("成功");
			}else {
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

package daotest;

import java.sql.SQLException;

import bean.TwintterBean;
import dao.TwintterDao;

public class InsertTwintterTest {
	public static void main(String[] args) {
		TwintterDao dao = null;
		TwintterBean bean = new TwintterBean();
		try {
			dao = new TwintterDao();

			bean.setUser_id(-1);
			bean.setBook_id(1);
			bean.setTwintter_text("コメントを入力");

			int numrow = dao.insertTwintter(bean);
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

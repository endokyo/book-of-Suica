package daotest;

import java.sql.SQLException;

import bean.UserBean;
import dao.UserDao;

public class InsertUserTest {
	public static void main(String[] args) {
		UserDao dao = null;
		String name = args[0];
		UserBean bean = new UserBean();
		try {
			dao = new UserDao();

			bean.setPassword("PASS");
			bean.setName(name);

			int numrow = dao.InsertUser(bean);
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

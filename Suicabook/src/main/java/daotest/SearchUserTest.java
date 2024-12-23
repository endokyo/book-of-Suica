package daotest;

import java.sql.SQLException;

import bean.UserBean;
import dao.UserDao;

public class SearchUserTest {
	public static void main(String[] args) {
		UserDao dao = null;
		String name = args[0];
		UserBean bean = null;
		try {
			dao = new UserDao();

			bean = dao.searchUser(name);
			if (bean != null) {
				System.out.println("user_id：" + bean.getId());
				System.out.println("user_pass：" + bean.getPassword());
				System.out.println("user_name：" + bean.getName());
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

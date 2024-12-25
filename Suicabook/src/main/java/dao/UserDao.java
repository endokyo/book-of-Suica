package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.UserBean;

public class UserDao {
	private Connection connection;

	public UserDao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/java_web_system?useSSL=false";
		String user = "root";
		String password = "root";
		connection = DriverManager.getConnection(url, user, password);
	}

	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ユーザーが登録されているかをboolean型で返すメソッド
	public boolean searchUser(String user_name) throws SQLException, NumberFormatException {
		ResultSet rs = null;
		PreparedStatement pstatement = null;

		try {
			String sql = "select * from user where user_name = ?;";
			pstatement = connection.prepareStatement(sql);
			pstatement.setString(1, user_name);
			rs = pstatement.executeQuery();

			if (rs.next()) {
				return true;
			}
		} finally {
			pstatement.close();
		}
		return false;
	}

	// ログイン時にユーザー名とパスワードを用いてユーザーデータを取得するメソッド
	public UserBean loginUser(String user_name, String user_pass) throws SQLException, NumberFormatException {
		UserBean bean = null;
		ResultSet rs = null;
		PreparedStatement pstatement = null;

		try {
			String sql = "select * from user where user_name = ? and user_pass = ?;";
			pstatement = connection.prepareStatement(sql);
			pstatement.setString(1, user_name);
			pstatement.setString(2, user_pass);
			rs = pstatement.executeQuery();

			if (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getInt("user_id"));
				bean.setName(rs.getString("user_name"));
				bean.setPassword(rs.getString("user_pass"));
			}
		} finally {
			pstatement.close();
		}
		return bean;
	}

	// 指定したユーザー名のユーザーデータを取得するメソッド
	public UserBean getUser(String user_name) throws SQLException, NumberFormatException {
		UserBean bean = null;
		ResultSet rs = null;
		PreparedStatement pstatement = null;

		try {
			String sql = "select * from user where user_name = ?;";
			pstatement = connection.prepareStatement(sql);
			pstatement.setString(1, user_name);
			rs = pstatement.executeQuery();

			if (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getInt("user_id"));
				bean.setName(rs.getString("user_name"));
				bean.setPassword(rs.getString("user_pass"));
			}
		} finally {
			pstatement.close();
		}
		return bean;
	}

	//新規登録された行数を返すメソッド
	public int InsertUser(UserBean user) throws SQLException, NumberFormatException {
		int numrow = 0;
		PreparedStatement pstatement = null;
		try {
			String sql = "insert into user(user_pass,user_name) values (?,?);";
			pstatement = connection.prepareStatement(sql);
			pstatement.setString(1, user.getPassword());
			pstatement.setString(2, user.getName());
			numrow = pstatement.executeUpdate();
		} finally {
			pstatement.close();
		}
		return numrow;
	}
}
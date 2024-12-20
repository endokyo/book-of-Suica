package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.UserBean;

public class UserDao {
private Connection connection;
	
	public UserDao() throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/java_web_system?useSSL=false";
		String user = "root";
		String password = "root";
		connection = DriverManager.getConnection(url,user,password);
	}
	
	public void close() {
		try {
			if(connection != null) {
				connection.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//ユーザーデータを取得するメソッド
	public UserBean searchUser(String user_name) throws SQLException,NumberFormatException{
		UserBean bean = new UserBean();
		ResultSet rs = null;
		PreparedStatement pstatement = null;
		try {
			String sql = "select * from user where user_name = ?;";
			pstatement = connection.prepareStatement(sql);
			pstatement.setString(1,user_name);
			rs = pstatement.executeQuery();
			if(rs.next()) {
				bean.setId(rs.getInt("user_id"));
				bean.setName(rs.getString("user_name"));
				bean.setPassword(rs.getString("user_pass"));
			}
		}finally {
			pstatement.close();
		}
		return bean;
	}
	
	//新規登録された行数を返すメソッド
	public int InsertUser(UserBean user) throws SQLException,NumberFormatException{
		int numrow = 0;
		PreparedStatement pstatement = null;
		try {
			String sql = "insert into user(user_pass,user_name) values (?,?);";
			pstatement = connection.prepareStatement(sql);
			pstatement.setString(1,user.getPassword());
			pstatement.setString(2,user.getName());
			numrow = pstatement.executeUpdate();
		}finally {
			pstatement.close();
		}
		return numrow;
	}
}
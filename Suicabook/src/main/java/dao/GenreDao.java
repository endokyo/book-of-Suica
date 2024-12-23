package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.GenreBean;

public class GenreDao {
private Connection connection;
	
	public GenreDao() throws ClassNotFoundException,SQLException{
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
	
	//genre_idをもとにデータベースからデータを取得するメソッド
	public GenreBean searchGenre(int genre_id) throws SQLException,NumberFormatException{
		GenreBean bean = new GenreBean();
		ResultSet rs = null;
		PreparedStatement pstatement = null;
		try {
			String sql = "SELECT * FROM genre WHWRE genre_id = ?;";
			pstatement = connection.prepareStatement(sql);
			pstatement.setInt(1,genre_id);
			rs = pstatement.executeQuery();
			if(rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setGenre(rs.getString("genre"));
			}
		}finally {
			pstatement.close();
		}
		return bean;
	}
	
	//ジャンル一覧を取得するメソッド
	public ArrayList<GenreBean> createGenreList(String genre_id) throws SQLException{
		ArrayList<GenreBean> arrayGenre = new ArrayList<>();
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		
		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM genre;";
			pstatement = connection.prepareStatement(sql);
			
			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();
			
			while(rs.next()) {
				GenreBean gb = new GenreBean();
				//列名を指定してResultSetオブジェクトから値を取得
				gb.setGenre(rs.getString("genre"));
				arrayGenre.add(gb);
			}
			//ResultSetオブジェクトの解放
			rs.close();
		}finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return arrayGenre;
	}
}

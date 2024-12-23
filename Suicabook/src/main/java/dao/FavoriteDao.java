package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.BookBean;
import bean.FavoriteBean;

public class FavoriteDao {
	private Connection connection;

	//コンストラクター
	//todo_listデータベースとの接続を行う
	public FavoriteDao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/java_web_system?useSSL=false";
		String user = "root";
		String password = "root";
		connection = DriverManager.getConnection(url, user, password);
	}

	//java_web_systemデータベースと切断するメソッド
	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//favorite_idをもとにデータベースからデータを取得し空のFavoriteBeanに入れ、返すメソッド
	public FavoriteBean searchFavorite(int favorite_id) throws SQLException{
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		FavoriteBean bean = new FavoriteBean();
		
		try {

			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "select * from favorite where favorite_id = ?;";
			pstatement = connection.prepareStatement(sql);
			
			//INパラメータの設定
			pstatement.setInt(1,favorite_id);
			
			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();
			if(rs.next()) {
				bean.setFavorite_id(rs.getInt("favorite_id"));
				bean.setUser_id(rs.getInt("user_id"));
				bean.setBook_id(rs.getInt("book_id"));
			}
			
		}finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return bean;
	}
	
	//user_idをもとにお気に入り一覧を取得し返すメソッド
	public ArrayList<BookBean> getFavoriteListSortByUser(int user_id) throws SQLException{
		ArrayList<BookBean> list = new ArrayList<>();
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		
		try {

			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "select * from favorite join book on favorite.book_id = book.book_id join genre on book.genre_id = genre.genre_id where user_id = ?;";
			pstatement = connection.prepareStatement(sql);

			//INパラメータの設定
			pstatement.setInt(1, user_id);
			
			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();
			
			while (rs.next()) {
				BookBean bean = new BookBean();
				bean.setId(rs.getInt("book_id"));
				bean.setImg(rs.getString("book_cover"));
				bean.setTitle(rs.getString("book_title"));
				bean.setCreater(rs.getString("book_creater"));
				bean.setGenre(rs.getString("genre_name"));
				bean.setAverage(rs.getDouble("book_ave"));
			}
		}finally {
			pstatement.close();
		}
		return list;
	}
}

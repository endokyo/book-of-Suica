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
				BookBean bookbean = new BookBean();
				bookbean.setId(rs.getInt("book_id"));
				bookbean.setImg(rs.getString("book_cover"));
				bookbean.setTitle(rs.getString("book_title"));
				bookbean.setCreater(rs.getString("book_creater"));
				bookbean.setGenre(rs.getString("genre_name"));
				bookbean.setAverage(rs.getDouble("book_ave"));
				bookbean.setAvecount(rs.getInt("book_avecount"));
				bookbean.setComcount(rs.getInt("book_comcount"));
				bookbean.setFavcount(rs.getInt("book_favcount"));
				bookbean.setFavorite(true);
				list.add(bookbean);
			}
		}finally {
			pstatement.close();
		}
		return list;
	}
	
	public int insertFavorite(FavoriteBean favorite) throws SQLException{
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		int numRow = 0;
		
		try {
			//トランザクション開始
			connection.setAutoCommit(false);
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "INSERT INTO favorite(user_id,book_id) values (?,?);";
			pstatement = connection.prepareStatement(sql);

			//INパラメータの設定
			pstatement.setInt(1, favorite.getUser_id());
			pstatement.setInt(2, favorite.getBook_id());
			
			//登録を実行
			numRow = pstatement.executeUpdate();
		}finally {
			if(numRow > 0) {
				connection.commit();
			}else {
				connection.rollback();
			}
		}
		return numRow;
		
	}
	
	public int deleteFavorite(FavoriteBean favorite) throws SQLException{
		PreparedStatement pstatement = null;
		int numRow = 0;
		
		try {
			//トランザクション開始
			connection.setAutoCommit(false);
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "DELETE FROM favorite WHERE favorite_id = ?;";
			pstatement = connection.prepareStatement(sql);

			//INパラメータの設定
			pstatement.setInt(1, favorite.getFavorite_id());
			
			//登録を実行
			numRow = pstatement.executeUpdate();
		}finally {
			if(numRow > 0) {
				connection.commit();
			}else {
				connection.rollback();
			}
		}
		return numRow;
		
	}
}

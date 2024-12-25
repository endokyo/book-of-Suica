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
			String sql = "SELECT * FROM genre WHERE genre_id = ?;";
			pstatement = connection.prepareStatement(sql);
			
			pstatement.setInt(1,genre_id);
			
			rs = pstatement.executeQuery();
			
			if(rs.next()) {
				bean.setId(rs.getInt("genre_id"));
				bean.setGenre(rs.getString("genre_name"));
			}
		}finally {
			pstatement.close();
		}
		return bean;
	}
	
	//ジャンル一覧を取得するメソッド
	public ArrayList<GenreBean> createGenreList() throws SQLException{
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
				gb.setId(rs.getInt("genre_id"));
				gb.setGenre(rs.getString("genre_name"));
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
	
	// おすすめジャンルを選定し、選定したおすすめジャンル情報を返すメソッド
		public GenreBean getTodayGenreId(int user_id) throws SQLException {
			PreparedStatement pstatement = null;
			ResultSet rs = null;
			GenreBean bean = null;
			
			try {
				//SQLを保持するPreparedStatementオブジェクトの生成
				String sql = """
						SELECT g1.genre_id, sub.cnt, g1.genre_name
							FROM genre g1
							LEFT JOIN (
								SELECT g2.genre_id, count(g2.genre_id) cnt
									FROM genre g2
									LEFT JOIN book b ON g2.genre_id = b.genre_id
									LEFT JOIN favorite f ON b.book_id = f.book_id
									WHERE f.user_id = ?
									GROUP BY g2.genre_id
									ORDER BY genre_id
								) sub
							ON g1.genre_id = sub.genre_id
							WHERE (ifnull(sub.cnt, 0)) * 100 /
									(
									SELECT count(favorite_id)
										FROM favorite f
										WHERE f.user_id = ?
									)
								<= (100 / (SELECT count(*) FROM genre))
							ORDER BY RAND()
							LIMIT 1
							;
							""";

				pstatement = connection.prepareStatement(sql);
				pstatement.setInt(1, user_id);
				pstatement.setInt(2, user_id);

				//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
				rs = pstatement.executeQuery();

				while (rs.next()) {
					bean = new GenreBean();

					//列名を指定してResultSetオブジェクトから値を取得
					bean.setId(rs.getInt("genre_id"));
					bean.setGenre(rs.getString("genre_name"));
				}

				//ResultSetオブジェクトの解放
				rs.close();
				
			} finally {
				//PreparedStatementオブジェクトの解放
				pstatement.close();
			}
			return bean;
		}
}

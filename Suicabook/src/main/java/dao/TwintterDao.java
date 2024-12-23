package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.TwintterBean;

public class TwintterDao {
	private Connection connection;

	//コンストラクター
	//todo_listデータベースとの接続を行う
	public TwintterDao() throws ClassNotFoundException, SQLException {
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
	
	//book_idをもとにコメント一覧を取得し返すメソッド
	public ArrayList<TwintterBean> getTwintterListSortByBook(int book_id) throws SQLException{
		ArrayList<TwintterBean> list = new ArrayList<>();
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		
		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT  * FROM twintter JOIN user ON twintter.user_id = user.user_id where book_id = ?;";
			pstatement = connection.prepareStatement(sql);
			
			//INパラメータの設定
			pstatement.setInt(1, book_id);
			
			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();
			if(rs.next()) {
				TwintterBean twintter = new TwintterBean();
				twintter.setUser_id(rs.getInt("user_id"));
				twintter.setTwintter_text(rs.getString("twintter_text"));
				twintter.setBook_id(rs.getInt("book_id"));
				twintter.setUser_name(rs.getString("user_name"));
				list.add(twintter);
			}
		}finally {
			pstatement.close();
		}
		return list;
	}
	
	public int insertTwintter(TwintterBean twintter) throws SQLException{
		PreparedStatement pstatement = null;
		int numRow = 0;
		
		try {
			//トランザクション開始
			connection.setAutoCommit(false);
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "INSERT INTO twintter(user_id,book_id,twintter_text) values (?,?,?);";
			pstatement = connection.prepareStatement(sql);

			//INパラメータの設定
			pstatement.setInt(1, twintter.getUser_id());
			pstatement.setInt(2, twintter.getBook_id());
			pstatement.setString(3, twintter.getTwintter_text());
			
			//登録を実行
			numRow = pstatement.executeUpdate();
		}finally {
			if(numRow > 0) {
				connection.commit();
			}else {
				connection.rollback();
			}
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return numRow;
	}
}

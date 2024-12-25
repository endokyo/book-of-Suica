package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.BookBean;
import bean.UserBean;

public class BookDao {
	private Connection connection;

	//コンストラクター
	//todo_listデータベースとの接続を行う
	public BookDao() throws ClassNotFoundException, SQLException {
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

	//book_idをもとにデータベースからデータを取得し空のBookBeanに入れ、返すメソッド
	public BookBean searchBook(int book_id, int userid) throws SQLException {
		BookBean bb = new BookBean();
		PreparedStatement pstatement = null;
		ResultSet rs = null;

		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM book LEFT JOIN favorite ON book.book_id = favorite.book_id AND favorite.user_id = ? JOIN genre ON book.genre_id = genre.genre_id WHERE book.book_id = ?;";

			pstatement = connection.prepareStatement(sql);

			//INパラメータの設定
			pstatement.setInt(1, userid);
			pstatement.setInt(2, book_id);

			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();
			if (rs.next()) {
				//列名を指定してResultSetオブジェクトから値を取得
				bb.setId(rs.getInt("book_id"));
				bb.setTitle(rs.getString("book_title"));
				bb.setImg(rs.getString("book_cover"));
				bb.setCreater(rs.getString("book_creater"));
				bb.setGenre(rs.getString("genre_name"));
				bb.setGenre_id(rs.getInt("genre_id"));
				bb.setAvecount(rs.getInt("book_avecount"));
				bb.setAverage(rs.getDouble("book_ave"));
				bb.setTwicount(rs.getInt("book_twicount"));
				bb.setFavcount(rs.getInt("book_favcount"));
				bb.setFavorite_id(rs.getInt("favorite_id"));
				if (bb.getFavorite_id() > 0) {
					bb.setFavorite(true);
				} else {
					bb.setFavorite(false);
				}
			}
			//ResultSetオブジェクトの解放
			rs.close();
		} finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return bb;
	}

	//受け取った検索キーワードをもとに書籍登録順の昇順にソートした書籍一覧を取得し返すメソッド
	public ArrayList<BookBean> getBookListSortByRegist(UserBean user, String keyword) throws SQLException {
		ArrayList<BookBean> arrayBb = new ArrayList<>();
		PreparedStatement pstatement = null;
		ResultSet rs = null;

		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM book left JOIN favorite ON book.book_id = favorite.book_id AND favorite.user_id = ? JOIN genre ON book.genre_id = genre.genre_id WHERE (book.book_title LIKE ? or ' ' = ?);";
			pstatement = connection.prepareStatement(sql);

			//INパラメータの設定
			pstatement.setInt(1, user.getId());
			pstatement.setString(2, '%' + keyword + '%');
			pstatement.setString(3, keyword);

			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();

			while (rs.next()) {
				BookBean bb = new BookBean();
				//列名を指定してResultSetオブジェクトから値を取得
				bb.setId(rs.getInt("book_id"));
				bb.setTitle(rs.getString("book_title"));
				bb.setImg(rs.getString("book_cover"));
				bb.setCreater(rs.getString("book_creater"));
				bb.setGenre(rs.getString("genre_name"));
				bb.setGenre_id(rs.getInt("genre_id"));
				bb.setAvecount(rs.getInt("book_avecount"));
				bb.setAverage(rs.getDouble("book_ave"));
				bb.setTwicount(rs.getInt("book_twicount"));
				bb.setFavcount(rs.getInt("book_favcount"));
				bb.setFavorite_id(rs.getInt("favorite_id"));
				if (bb.getFavorite_id() > 0) {
					bb.setFavorite(true);
				} else {
					bb.setFavorite(false);
				}
				arrayBb.add(bb);
			}

			//ResultSetオブジェクトの解放
			rs.close();
		} finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return arrayBb;
	}

	//受け取った検索キーワード、ジャンルをもとに書籍登録順の昇順にソートした書籍一覧を取得し返すメソッド
	public ArrayList<BookBean> getBookListSortByRegist(UserBean user, String keyword, int genreid) throws SQLException {
		ArrayList<BookBean> arrayBb = new ArrayList<>();
		PreparedStatement pstatement = null;
		ResultSet rs = null;

		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM book left JOIN favorite on book.book_id = favorite.book_id AND favorite.user_id = ? JOIN genre ON book.genre_id = genre.genre_id WHERE (book.book_title LIKE ? or ' ' = ?) AND book.genre_id = ?;";
			pstatement = connection.prepareStatement(sql);

			//INパラメータの設定
			pstatement.setInt(1, user.getId());
			pstatement.setString(2, '%' + keyword + '%');
			pstatement.setString(3, keyword);
			pstatement.setInt(4, genreid);

			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();

			while (rs.next()) {
				BookBean bb = new BookBean();
				//列名を指定してResultSetオブジェクトから値を取得
				bb.setId(rs.getInt("book_id"));
				bb.setTitle(rs.getString("book_title"));
				bb.setImg(rs.getString("book_cover"));
				bb.setCreater(rs.getString("book_creater"));
				bb.setGenre(rs.getString("genre_name"));
				bb.setGenre_id(rs.getInt("genre_id"));
				bb.setAvecount(rs.getInt("book_avecount"));
				bb.setAverage(rs.getDouble("book_ave"));
				bb.setTwicount(rs.getInt("book_twicount"));
				bb.setFavcount(rs.getInt("book_favcount"));
				bb.setFavorite_id(rs.getInt("favorite_id"));
				if (bb.getFavorite_id() > 0) {
					bb.setFavorite(true);
				} else {
					bb.setFavorite(false);
				}
				arrayBb.add(bb);
			}

			//ResultSetオブジェクトの解放
			rs.close();
		} finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return arrayBb;
	}

	//受け取った検索キーワードをもとに評価平均値順の降順にソートした書籍一覧を取得し返すメソッド
	public ArrayList<BookBean> getBookListSortByAverage(UserBean user, String keyword) throws SQLException {
		ArrayList<BookBean> arrayBb = new ArrayList<>();
		PreparedStatement pstatement = null;
		ResultSet rs = null;

		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM book left JOIN favorite on book.book_id = favorite.book_id AND favorite.user_id = ? JOIN genre ON book.genre_id = genre.genre_id WHERE (book.book_title LIKE ? or ' ' = ?) ORDER BY book.book_ave DESC;";
			pstatement = connection.prepareStatement(sql);

			//INパラメータの設定
			pstatement.setInt(1, user.getId());
			pstatement.setString(2, '%' + keyword + '%');
			pstatement.setString(3, keyword);

			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();

			while (rs.next()) {
				BookBean bb = new BookBean();
				//列名を指定してResultSetオブジェクトから値を取得
				bb.setId(rs.getInt("book_id"));
				bb.setTitle(rs.getString("book_title"));
				bb.setImg(rs.getString("book_cover"));
				bb.setCreater(rs.getString("book_creater"));
				bb.setGenre(rs.getString("genre_name"));
				bb.setGenre_id(rs.getInt("genre_id"));
				bb.setAvecount(rs.getInt("book_avecount"));
				bb.setAverage(rs.getDouble("book_ave"));
				bb.setTwicount(rs.getInt("book_twicount"));
				bb.setFavcount(rs.getInt("book_favcount"));
				bb.setFavorite_id(rs.getInt("favorite_id"));
				if (bb.getFavorite_id() > 0) {
					bb.setFavorite(true);
				} else {
					bb.setFavorite(false);
				}
				arrayBb.add(bb);
			}

			//ResultSetオブジェクトの解放
			rs.close();
		} finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return arrayBb;
	}

	//受け取った検索キーワード、ジャンルをもとに評価平均値順の降順にソートした書籍一覧を取得し返すメソッド
	public ArrayList<BookBean> getBookListSortByAverage(UserBean user, String keyword, int genreid)
			throws SQLException {
		ArrayList<BookBean> arrayBb = new ArrayList<>();
		PreparedStatement pstatement = null;
		ResultSet rs = null;

		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM book left JOIN favorite on book.book_id = favorite.book_id AND favorite.user_id = ? JOIN genre ON book.genre_id = genre.genre_id WHERE (book.book_title LIKE ? or ' ' = ?) and book.genre_id = ? ORDER BY book.book_ave DESC;";
			pstatement = connection.prepareStatement(sql);

			//INパラメータの設定
			pstatement.setInt(1, user.getId());
			pstatement.setString(2, '%' + keyword + '%');
			pstatement.setString(3, keyword);
			pstatement.setInt(4, genreid);

			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();

			while (rs.next()) {
				BookBean bb = new BookBean();
				//列名を指定してResultSetオブジェクトから値を取得
				bb.setId(rs.getInt("book_id"));
				bb.setTitle(rs.getString("book_title"));
				bb.setImg(rs.getString("book_cover"));
				bb.setCreater(rs.getString("book_creater"));
				bb.setGenre(rs.getString("genre_name"));
				bb.setGenre_id(rs.getInt("genre_id"));
				bb.setAvecount(rs.getInt("book_avecount"));
				bb.setAverage(rs.getDouble("book_ave"));
				bb.setTwicount(rs.getInt("book_twicount"));
				bb.setFavcount(rs.getInt("book_favcount"));
				bb.setFavorite_id(rs.getInt("favorite_id"));
				if (bb.getFavorite_id() > 0) {
					bb.setFavorite(true);
				} else {
					bb.setFavorite(false);
				}
				arrayBb.add(bb);
			}

			//ResultSetオブジェクトの解放
			rs.close();
		} finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return arrayBb;
	}

	//受け取った検索キーワードをもとにお気に入り数順の降順にソートした書籍一覧を取得するメソッド
	public ArrayList<BookBean> getBookListSortByFavorite(UserBean user, String keyword) throws SQLException {
		ArrayList<BookBean> arrayBb = new ArrayList<>();
		PreparedStatement pstatement = null;
		ResultSet rs = null;

		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM book left JOIN favorite on book.book_id = favorite.book_id AND favorite.user_id = ? JOIN genre ON book.genre_id = genre.genre_id WHERE (book.book_title LIKE ? or ' ' = ?) ORDER BY book_favcount DESC;";
			pstatement = connection.prepareStatement(sql);

			//INパラメータの設定
			pstatement.setInt(1, user.getId());
			pstatement.setString(2, '%' + keyword + '%');
			pstatement.setString(3, keyword);

			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();

			while (rs.next()) {
				BookBean bb = new BookBean();
				//列名を指定してResultSetオブジェクトから値を取得
				bb.setId(rs.getInt("book_id"));
				bb.setTitle(rs.getString("book_title"));
				bb.setImg(rs.getString("book_cover"));
				bb.setCreater(rs.getString("book_creater"));
				bb.setGenre(rs.getString("genre_name"));
				bb.setGenre_id(rs.getInt("genre_id"));
				bb.setAvecount(rs.getInt("book_avecount"));
				bb.setAverage(rs.getDouble("book_ave"));
				bb.setTwicount(rs.getInt("book_twicount"));
				bb.setFavcount(rs.getInt("book_favcount"));
				bb.setFavorite_id(rs.getInt("favorite_id"));
				if (bb.getFavorite_id() > 0) {
					bb.setFavorite(true);
				} else {
					bb.setFavorite(false);
				}
				arrayBb.add(bb);
			}

			//ResultSetオブジェクトの解放
			rs.close();
		} finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return arrayBb;
	}

	//受け取った検索キーワード、ジャンルをもとにお気に入り数順の降順にソートした書籍一覧を取得するメソッド
	public ArrayList<BookBean> getBookListSortByFavorite(UserBean user, String keyword, int genreid)
			throws SQLException {
		ArrayList<BookBean> arrayBb = new ArrayList<>();
		PreparedStatement pstatement = null;
		ResultSet rs = null;

		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM book left JOIN favorite on book.book_id = favorite.book_id AND favorite.user_id = ? JOIN genre ON book.genre_id = genre.genre_id WHERE (book.book_title LIKE ? or ' ' = ?) AND book.genre_id = ?  ORDER BY book_favcount DESC;";
			pstatement = connection.prepareStatement(sql);

			//INパラメータの設定
			pstatement.setInt(1, user.getId());
			pstatement.setString(2, '%' + keyword + '%');
			pstatement.setString(3, keyword);
			pstatement.setInt(4, genreid);

			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();

			while (rs.next()) {
				BookBean bb = new BookBean();
				//列名を指定してResultSetオブジェクトから値を取得
				bb.setId(rs.getInt("book_id"));
				bb.setTitle(rs.getString("book_title"));
				bb.setImg(rs.getString("book_cover"));
				bb.setCreater(rs.getString("book_creater"));
				bb.setGenre(rs.getString("genre_name"));
				bb.setGenre_id(rs.getInt("genre_id"));
				bb.setAvecount(rs.getInt("book_avecount"));
				bb.setAverage(rs.getDouble("book_ave"));
				bb.setTwicount(rs.getInt("book_twicount"));
				bb.setFavcount(rs.getInt("book_favcount"));
				bb.setFavorite_id(rs.getInt("favorite_id"));
				if (bb.getFavorite_id() > 0) {
					bb.setFavorite(true);
				} else {
					bb.setFavorite(false);
				}
				arrayBb.add(bb);
			}

			//ResultSetオブジェクトの解放
			rs.close();
		} finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return arrayBb;
	}

	//受け取った検索キーワードをもとにコメント数順の降順にソートした書籍一覧を取得するメソッド
	public ArrayList<BookBean> getBookListSortByTwintter(UserBean user, String keyword) throws SQLException {
		ArrayList<BookBean> arrayBb = new ArrayList<>();
		PreparedStatement pstatement = null;
		ResultSet rs = null;

		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM book left JOIN favorite on book.book_id = favorite.book_id AND favorite.user_id = ? JOIN genre ON book.genre_id = genre.genre_id WHERE (book.book_title LIKE ? or ' ' = ?) ORDER BY book_twicount DESC;";
			pstatement = connection.prepareStatement(sql);

			//INパラメータの設定
			pstatement.setInt(1, user.getId());
			pstatement.setString(2, '%' + keyword + '%');
			pstatement.setString(3, keyword);

			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();

			while (rs.next()) {
				BookBean bb = new BookBean();
				//列名を指定してResultSetオブジェクトから値を取得
				bb.setId(rs.getInt("book_id"));
				bb.setTitle(rs.getString("book_title"));
				bb.setImg(rs.getString("book_cover"));
				bb.setCreater(rs.getString("book_creater"));
				bb.setGenre(rs.getString("genre_name"));
				bb.setGenre_id(rs.getInt("genre_id"));
				bb.setAvecount(rs.getInt("book_avecount"));
				bb.setAverage(rs.getDouble("book_ave"));
				bb.setTwicount(rs.getInt("book_twicount"));
				bb.setFavcount(rs.getInt("book_favcount"));
				bb.setFavorite_id(rs.getInt("favorite_id"));
				if (bb.getFavorite_id() > 0) {
					bb.setFavorite(true);
				} else {
					bb.setFavorite(false);
				}
				arrayBb.add(bb);
			}

			//ResultSetオブジェクトの解放
			rs.close();
		} finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return arrayBb;
	}

	//受け取った検索キーワード、ジャンルをもとにコメント数順の降順にソートした書籍一覧を取得するメソッド
	public ArrayList<BookBean> getBookListSortByTwintter(UserBean user, String keyword, int genreid)
			throws SQLException {
		ArrayList<BookBean> arrayBb = new ArrayList<>();
		PreparedStatement pstatement = null;
		ResultSet rs = null;

		try {
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM book left JOIN favorite on book.book_id = favorite.book_id AND favorite.user_id = ? JOIN genre ON book.genre_id = genre.genre_id WHERE (book.book_title LIKE ? or ' ' = ?) AND book.genre_id = ?  ORDER BY book_twicount DESC;";
			pstatement = connection.prepareStatement(sql);

			//INパラメータの設定
			pstatement.setInt(1, user.getId());
			pstatement.setString(2, '%' + keyword + '%');
			pstatement.setString(3, keyword);
			pstatement.setInt(4, genreid);

			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();

			while (rs.next()) {
				BookBean bb = new BookBean();
				//列名を指定してResultSetオブジェクトから値を取得
				bb.setId(rs.getInt("book_id"));
				bb.setTitle(rs.getString("book_title"));
				bb.setImg(rs.getString("book_cover"));
				bb.setCreater(rs.getString("book_creater"));
				bb.setGenre(rs.getString("genre_name"));
				bb.setGenre_id(rs.getInt("genre_id"));
				bb.setAvecount(rs.getInt("book_avecount"));
				bb.setAverage(rs.getDouble("book_ave"));
				bb.setTwicount(rs.getInt("book_twicount"));
				bb.setFavcount(rs.getInt("book_favcount"));
				bb.setFavorite_id(rs.getInt("favorite_id"));
				if (bb.getFavorite_id() > 0) {
					bb.setFavorite(true);
				} else {
					bb.setFavorite(false);
				}
				arrayBb.add(bb);
			}

			//ResultSetオブジェクトの解放
			rs.close();
		} finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return arrayBb;
	}

	//受け取ったBookBeanをもとにデータベースを更新するメソッド
	public int updateBook(BookBean book) throws SQLException {
		PreparedStatement pstatement = null;
		int numRow = 0;
		try {
			connection.setAutoCommit(false);
			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "UPDATE book SET book_twicount = ?,book_favcount= ?,book_avecount = ?,book_ave = ? WHERE book_id = ?;";
			pstatement = connection.prepareStatement(sql);

			pstatement.setInt(1, book.getTwicount()); //getTwicountに変更
			pstatement.setInt(2, book.getFavcount());
			pstatement.setInt(3, book.getAvecount());
			pstatement.setDouble(4, book.getAverage());
			pstatement.setInt(5, book.getId());

			//UPDATEを実行
			numRow = pstatement.executeUpdate();
		} finally {
			if (numRow > 0) {
				connection.commit();
			} else {
				connection.rollback();
			}
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return numRow;
	}

}

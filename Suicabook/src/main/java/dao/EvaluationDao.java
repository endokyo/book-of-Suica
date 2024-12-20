package dao;

public class EvaluationDao {

	private Connection connection;

	public EvaluationDao() throws ClassNotFoundException, SQLException {
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
    
	//evaluation_idをもとにデータベースからデータを取得し空のEvaluationBeanに入れ、返すメソッド
	public EvaluationBean searchEvaluation(int evaluation_id) throws SQLException, NumberFormatException {
		EvaluationBean bean = new EvaluationBean();
		ResultSet rs = null;
		PreparedStatement pstatement = null;
		try {
			String sql = "select * from evaluation where evaluation_id = ?;";
			pstatement = connection.prepareStatement(sql);
			pstatement.setString(1, evaluation_id);
			rs = pstatement.executeQuery();
			if (rs.next()) {
				bean.setId(rs.getInt("evaluation_id"));
				bean.setEvaluation_score(rs.getString("evaluation_score"));
				bean.setEvaluation_review(rs.getString("evaluation_review"));
				bean.setBook_id(rs.getString("book_id"));
				bean.setImg(rs.getString("book_cover"));
				bean.setTitle(rs.getString("book_title"));
			}
		} finally {
			pstatement.close();
		}
		return bean;
	}

	//新規登録された行数を返すメソッド
	public int insertEvaluation(EvaluationBean eval) throws SQLException,NumberFormatException{
		int numRow=0;
		PreparedStatement pstatement = null;
		try {
			// トランザクション開始
			connection.setAutoCommit(false);
			String sql = "insert into evaluation(user_id,book_id,evaluation_score,evaluation_review) values(?,?,?,?);";
			pstatement = connection.prepareStatement(sql);
			pstatement.setInt(1,eval.user_id);
			pstatement.setInt(2,eval.book_id);
			pstatement.setString(3,eval.evaluation_score);
			pstatement.setString(4,eval.evaluation_review);
			
			numRow = pstatement.executeUpdate();
			}
		}finally

	{
		if (numRow > 0) {
			// 登録成功時はコミット
			connection.commit();
		} else {
			// 登録失敗時はロールバック
			connection.rollback();
		}
		// PreparedStatement オブジェクトの解
		pstatement.close();
	}return numRow;
	}

	//更新を行い、行数を返すメソッド
	public int insertEvaluation(EvaluationBean eval) throws SQLException, NumberFormatException{
		int numRow=0;
		PreparedStatement pstatement = null;
		try {
			// トランザクション開始
			connection.setAutoCommit(false);
			String sql = "update evaluation set evaluation_score = ? ,evaluation_review = ? where evaluation_id = ?;";
			pstatement = connection.prepareStatement(sql);
			pstatement.setString(1,eval.evaluation_score);
			pstatement.setString(2,eval.evaluation_review);
			pstatement.setInt(3,eval.evaluation_id);
			
			numRow = pstatement.executeUpdate();
			}
		}finally

	{
		if (numRow > 0) {
			// 登録成功時はコミット
			connection.commit();
		} else {
			// 登録失敗時はロールバック
			connection.rollback();
		}
		// PreparedStatement オブジェクトの解
		pstatement.close();
	}return numRow;
	}

	//user_idをもとに評価/レビュー一覧を取得し返すメソッド
	public ArrayList<EvaluationBean> getEvaluationListSortByUser(int user_id)throws SQLException, NumberFormatException {
		EvaluationBean eval =new EvaluationBean();
		PreparedStatement pstatement = null;
		ResultSet rs = null;
			
		try {

		//SQLを保持するPreparedStatementオブジェクトの生成
	    String sql = "SELECT * FROM evaluation WHERE user_id = ?;";
	    pstatement=connection.prepareStatement(sql);

		//INパラメータの設定
		pstatement.setInt(1,user_id);

		//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
		rs=pstatement.executeQuery();
	 if(rs.next())
		{
			//列名を指定してResultSetオブジェクトから値を取得
			eval.setEvaluation_score(rs.getInt("evaluation_score"));
			eval.setEvaluation_review(rs.getString("evaluation_review"));
		}
		//ResultSetオブジェクトの解放
		rs.close();
	}finally{
	//PreparedStatementオブジェクトの解放
	pstatement.close();
	}return eval;
	}

	//book_idをもとに評価/レビュー一覧を取得し返すメソッド
	public ArrayList<EvaluationBean> getEvaluationListSortByBook(int book_id) throws SQLException, NumberFormatException {
		EvaluationBean eval = new EvaluationBean();
		PreparedStatement pstatement = null;
		ResultSet rs = null;

		try {

			//SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM evaluation WHERE book_id = ?;";
			pstatement = connection.prepareStatement(sql);

			//INパラメータの設定
			pstatement.setInt(1, book_id);

			//SQLを発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();
			if (rs.next()) {
				//列名を指定してResultSetオブジェクトから値を取得
				eval.setEvaluation_score(rs.getInt("evaluation_score"));
				eval.setEvaluation_review(rs.getString("evaluation_review"));
			}
			//ResultSetオブジェクトの解放
			rs.close();
		} finally {
			//PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return eval;
	}
}
	
	
	

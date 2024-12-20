package dao;

public class EvaluationDao {

private Connection connection;
	
	public EvaluationDao() throws ClassNotFoundException,SQLException{
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
	
	public EvaluationBean searchEvaluation(int evaluation_id) throws SQLException,NumberFormatException{
	    EvaluationBean bean = new EvaluationBean();
		ResultSet rs = null;
		PreparedStatement pstatement = null;
		try {
			String sql = "select * from evaluation where evaluation_id = ?;";
			pstatement = connection.prepareStatement(sql);
			pstatement.setString(1,evaluation_id);
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
	}

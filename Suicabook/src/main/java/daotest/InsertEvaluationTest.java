package daotest;

import java.sql.SQLException;

import bean.EvaluationBean;
import dao.EvaluationDao;

public class InsertEvaluationTest {
	public static void main(String[] args) {
		EvaluationDao dao = null;
		EvaluationBean bean = new EvaluationBean();
		try {
			dao = new EvaluationDao();

			bean.setUser_id(2);
			bean.setBook_id(2);
			bean.setEvaluation_score(4);
			bean.setEvaluation_review("レビュー内容");

			int numrow = dao.insertEvaluation(bean);
			if (numrow > 0) {
				System.out.println("成功");
			} else {
				System.out.println("失敗");
			}

		} catch (SQLException e) {
			System.out.println("JDBCエラーです");
			e.getMessage();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("クラスの読み込みに失敗しました");
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}

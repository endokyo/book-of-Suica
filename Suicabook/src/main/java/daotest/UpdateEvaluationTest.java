package daotest;

import java.sql.SQLException;

import bean.EvaluationBean;
import dao.EvaluationDao;

public class UpdateEvaluationTest {
	public static void main(String[] args) {
		EvaluationDao dao = null;
		EvaluationBean bean = new EvaluationBean();
		try {
			dao = new EvaluationDao();

			bean.setId(-1);
			bean.setEvaluation_score(3);
			bean.setEvaluation_review("面白いと感じた");

			int numrow = dao.updateEvaluation(bean);
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

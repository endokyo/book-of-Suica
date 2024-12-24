package daotest;

import java.sql.SQLException;

import bean.EvaluationBean;
import dao.EvaluationDao;

public class SearchEvalutionTest {
	public static void main(String[] args) {
		EvaluationDao dao = null;
		int user_id = Integer.parseInt(args[0]);
		int book_id = Integer.parseInt(args[1]);
		EvaluationBean bean = new EvaluationBean();
		try {
			dao = new EvaluationDao();
			
			bean = dao.searchEvaluation(user_id,book_id);
			
			if(bean != null) {
				System.out.println("evalution_id:" + bean.getId());
				System.out.println("user_id:" + bean.getUser_id());
				System.out.println("user_name:" + bean.getUser_name());
				System.out.println("book_id:" + bean.getBook_id());
				System.out.println("book_cover:" + bean.getImg());
				System.out.println("book_title:" + bean.getTitle());
				System.out.println("evaluation_score：" + bean.getEvaluation_score());
				System.out.println("evaluation_review:" + bean.getEvaluation_review());
			}
		} catch (ClassNotFoundException e) {
			System.out.println("クラスの読み込みに失敗しました");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("JDBCエラーです");
			e.getMessage();
			e.printStackTrace();
		}finally {
			if(dao != null) {
				dao.close();
			}
		}

	}
}

package daotest;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.EvaluationBean;
import dao.EvaluationDao;

public class EvaluationListTest {
	public static void main(String[] args) {
		EvaluationDao dao = null;
		int id = Integer.parseInt(args[0]);
		int flg = Integer.parseInt(args[1]);
		ArrayList<EvaluationBean> list = null;
		try {
			dao = new EvaluationDao();

			if (flg == 0) {//ユーザーが評価した書籍一覧
				list = dao.getEvaluationListSortByUser(id);
			} else if (flg == 1) {//書籍についた評価一覧
				list = dao.getEvaluationListSortByBook(id);
			}
			for (EvaluationBean bean : list) {
				System.out.println("evalution_id:" + bean.getId());
				System.out.println("user_id:" + bean.getUser_id());
				System.out.println("user_name:" + bean.getUser_name());
				System.out.println("book_id:" + bean.getBook_id());
				System.out.println("book_cover:" + bean.getImg());
				System.out.println("book_title:" + bean.getTitle());
				System.out.println("evaluation_score：" + bean.getEvaluation_score());
				System.out.println("evaluation_review:" + bean.getEvaluation_review());
				System.out.println("---------------------------------------------------");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("クラスの読み込みに失敗しました");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("JDBCエラーです");
			e.getMessage();
			e.printStackTrace();
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}

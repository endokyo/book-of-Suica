package service;

import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;

import bean.EvaluationBean;
import dao.EvaluationDao;

public class GetEvaluationData {
	public void execute(HttpServletRequest request, int book_id) throws Exception {
		//リストを作成しrequestに入れる
		ArrayList<EvaluationBean> list = new ArrayList<EvaluationBean>();
		EvaluationDao dao = null;
		book_id = 1;
		try {
			dao = new EvaluationDao();
			list = dao.getEvaluationListSortByBook(book_id);
			request.setAttribute("evaluation", list);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}

package service;

import jakarta.servlet.http.HttpServletRequest;

import bean.EvaluationBean;
import dao.EvaluationDao;

public class GetEvaluationData {
	public void execute(HttpServletRequest request, int user_id, int book_id) throws Exception {
		EvaluationDao dao = null;
		EvaluationBean bean;
		
		try {
			dao = new EvaluationDao();
			//特定の書籍の評価を取得する
			bean = dao.searchEvaluation(user_id, book_id);
			request.setAttribute("evaluationdetails", bean);

		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}

package service;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;

import bean.EvaluationBean;
import dao.EvaluationDao;

public class CreatePersonalEvaluation {
	public void execute(HttpServletRequest request, int user_id) throws Exception {
		EvaluationDao dao = null;
		ArrayList<EvaluationBean> list = new ArrayList<>();
		
		try {
			dao = new EvaluationDao();
			//ユーザーが評価を行った書籍の一覧を取得
			list = dao.getEvaluationListSortByUser(user_id);
			request.setAttribute("evalutionlist", list);
		}finally {
			if(dao != null) {
				dao.close();
			}
		}
	}
}

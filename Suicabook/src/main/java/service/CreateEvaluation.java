package service;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import bean.EvaluationBean;
import dao.EvaluationDao;

public class CreateEvaluation {
	public void execute(HttpServletRequest request, int user_id) throws Exception {
		EvaluationDao dao = null;
		try {
			dao = new EvaluationDao();
			int id = Integer.parseInt(request.getParameter("id"));
			List<EvaluationBean> evaluationlist = new ArrayList<>();
			
			evaluationlist = dao.getEvaluationListSortByUser(id);
			request.setAttribute("evaluationlist", evaluationlist);
			request.setAttribute("mode", 2);

		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}

package service;

import bean.EvaluationBean;
import dao.EvaluationDao;

public class AddEvaluation {
	public void execute(EvaluationBean bean) throws Exception {
		EvaluationDao dao = null;
		try {
				dao = new EvaluationDao();
				int numRow = dao.insertEvaluation(bean);
				if (numRow > 0) {
					System.out.println("登録成功");
				}
			else {
				System.out.println("登録失敗");
			}
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}

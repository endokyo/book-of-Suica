
package service;

import bean.EvaluationBean;
import dao.EvaluationDao;

public class UpdateEvaluation {
	public void execute(EvaluationBean bean) throws Exception {
		EvaluationDao dao = null;

		try {
			dao = new EvaluationDao();
			int numRow = dao.updateEvaluation(bean);

			if (numRow > 0) {
				System.out.println("更新成功");
			} else {
				System.out.println("更新失敗");
			}
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}

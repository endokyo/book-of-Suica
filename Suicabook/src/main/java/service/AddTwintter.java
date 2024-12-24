package service;

import bean.TwintterBean;
import dao.TwintterDao;

public class AddTwintter {
	public void execute(TwintterBean bean) throws Exception {
		TwintterDao dao = null;
		try {
				dao = new TwintterDao();
				int numRow = dao.insertTwintter(bean);
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

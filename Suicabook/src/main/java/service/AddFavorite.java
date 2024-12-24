package service;

import bean.FavoriteBean;
import dao.FavoriteDao;

public class AddFavorite {
	public void execute(FavoriteBean bean) throws Exception {
		FavoriteDao dao = null;
		try {
				dao = new FavoriteDao();
				int numRow = dao.insertFavorite(bean);
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

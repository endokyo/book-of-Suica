package service;

import jakarta.servlet.http.HttpServletRequest;

import dao.FavoriteDao;

public class DeleteFavorite {
	public void execute(HttpServletRequest request) throws Exception {
		FavoriteDao dao = null;
		// 情報をリクエストパラメーターから取得
		String favorite_id = request.getParameter("task_id");
		try {
			if (favorite_id != null) {
				dao = new FavoriteDao();
				int numRow = dao.deleteFavorite(Integer.parseInt(favorite_id));
				if (numRow > 0) {
					request.setAttribute("completeMessage", "情報を削除しました");
				} else {
					request.setAttribute("completeMessage", "情報を削除できませんでした");
				}
			} else {
				request.setAttribute("confirmMessage", "不正アクセスです");
			}
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}

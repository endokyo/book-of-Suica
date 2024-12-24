package service;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;

import bean.BookBean;
import dao.FavoriteDao;

public class CreatePersonalFavorite {
	public void execute(HttpServletRequest request, int user_id) throws Exception {
		FavoriteDao dao = null;
		ArrayList<BookBean> favlist = new ArrayList<>();
		try {
			dao = new FavoriteDao();
			//お気に入りリストを取得する
			favlist = dao.getFavoriteListSortByUser(user_id);
			request.setAttribute("favoritelist", favlist);
			//お好みのジャンルを選定する
			
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}

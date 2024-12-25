package service;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import bean.TwintterBean;
import dao.TwintterDao;

public class CreateTwintter {
	public void execute(HttpServletRequest request, int user_id) throws Exception {
		TwintterDao dao = null;
		try {
			dao = new TwintterDao();
			int id = Integer.parseInt(request.getParameter("id"));
			List<TwintterBean> twintterlist = new ArrayList<>();

			twintterlist = dao.getTwintterListSortByBook(id);
			request.setAttribute("twintterlist", twintterlist);
			request.setAttribute("mode", 1);

		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}

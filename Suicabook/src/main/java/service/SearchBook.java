package service;

import jakarta.servlet.http.HttpServletRequest;

import bean.BookBean;
import dao.BookDao;

public class SearchBook {
	public void execute(HttpServletRequest request, int user_id) throws Exception {
		BookDao dao = null;
		int id = Integer.parseInt(request.getParameter("id"));	// 単なる"id"はbook_idのこと
		
		try {
			dao = new BookDao();
			BookBean bookinfo = dao.searchBook(id, user_id);
			request.setAttribute("bookinfo", bookinfo);
			
		}finally {
			if(dao != null) {
				dao.close();
			}
		}		
	}
}

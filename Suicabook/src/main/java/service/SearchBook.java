package service;

import jakarta.servlet.http.HttpServletRequest;

import bean.BookBean;
import dao.BookDao;

public class SearchBook {
	public void execute(HttpServletRequest request, int user_id) throws Exception {
		BookDao dao = null;
		BookBean bean;
		String id = request.getParameter("bookid");
		int book_id = Integer.parseInt(id);
		try {
			dao = new BookDao();
			
			//特定の書籍を取得する
			bean = dao.searchBook(book_id, user_id);
			request.setAttribute("bookinfo", bean);
			
		}finally {
			if(dao != null) {
				dao.close();
			}
		}
		
	}
}

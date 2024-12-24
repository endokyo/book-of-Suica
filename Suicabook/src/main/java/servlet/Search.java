package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.UserBean;
import service.CreateList;

/**
 * Servlet implementation class UserLogin2
 */
@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// JSP への転送
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/search.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String jsp = "/search.jsp";
		CreateList cl = new CreateList();
		UserBean ub = new UserBean();
		
		try {
			String key = request.getParameter("id");
			cl.execute(request,ub,key);
		}catch(Exception ex) {
			//例外処理
			System.out.println("例外エラーSearch.java");
		}
		if(jsp.equals("/booklist.jsp")) {
			response.sendRedirect("http://localhost:8080/Suicabook/list");	//リダイレクトでdoGetを呼び出す
		}else {
			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}
	}
}

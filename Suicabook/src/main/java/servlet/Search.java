package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.StatusBean;
import service.CreateGenreList;

/**
 * Servlet implementation class UserLogin2
 */
@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//検索ジャンルの一覧を作成する
		CreateGenreList cgl = new CreateGenreList();
		try {
			cgl.execute(request);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
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
		HttpSession session = request.getSession(false);

		try {
			//requestから検索ワードとジャンル名を取得
			StatusBean sb = (StatusBean) session.getAttribute("status");
			sb.setKeyword(request.getParameter("id"));
			sb.setGenreid(Integer.parseInt(request.getParameter("genreid")));
			sb.setNowsort("登録");
			sb.setPage(1);
			session.setAttribute("status",sb);
			jsp = "/list.jsp";
		} catch (Exception ex) {
			//例外処理
			//System.out.println(ex);
			System.out.println("例外エラーSearch.java");
		}
		if (jsp.equals("/list.jsp")) {
			response.sendRedirect("http://localhost:8080/Suicabook/list"); //リダイレクトでdoGetを呼び出す
		} else {
			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}
	}
}

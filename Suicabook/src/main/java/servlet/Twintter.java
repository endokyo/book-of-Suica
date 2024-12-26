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

import bean.TwintterBean;
import bean.UserBean;
import service.AddTwintter;
import service.SearchBook;

/**
 * Servlet implementation class TaskEdit
 */
@WebServlet("/twintter")
public class Twintter extends HttpServlet {
	public static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String jsp = "/error.jsp";
		// TODO Auto-generated method stub
		if (session == null) {
			jsp = "/login.jsp";
		} else {
			UserBean user = (UserBean) session.getAttribute("user");
			request.setAttribute("who", user.getId());
			SearchBook search = new SearchBook();
			try {
				search.execute(request, user.getId());
				jsp = "/twintter.jsp";
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				request.setAttribute("errormessage", "エラーが発生しました");
			}

		}
		// JSP への転送
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// リクエストパラメーターの値;
		String jsp = null;
		String content = request.getParameter("twintter_text");
		String button = request.getParameter("button");
		String id = request.getParameter("id");
		String who = request.getParameter("who");

		try {
			if (button != null && !button.isEmpty()) {
				if (button.equals("投稿")) {
					if (content.length() <= 100) {
						TwintterBean bean = new TwintterBean();
						bean.setUser_id(Integer.parseInt(who));
						bean.setBook_id(Integer.parseInt(id));
						bean.setTwintter_text(content);
						AddTwintter at = new AddTwintter();
						at.execute(bean);
						jsp = "/detail";
					} else {
						request.setAttribute("errormessage", "コメントが100文字を超過しています。");
						jsp = "/error.jsp";
						request.setAttribute("returnjsp", "twintter");
					}
				} else if (button.equals("キャンセル")) {
					jsp = "/detail";
				} else {
					doGet(request, response);
				}
			} else {
				request.setAttribute("errormessage", "エラーが発生しました。ボタンの入力が確認できませんでした。");
				jsp = "/error.jsp";
				request.setAttribute("returnjsp", "twintter");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "エラーが発生しました。コメント入力画面に戻ります。");
			jsp = "/error.jsp";
			request.setAttribute("returnjsp", "twintter");
		}

		// JSP への転送
		ServletContext context = request.getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);

	}
}

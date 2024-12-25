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

import bean.BookBean;
import bean.TwintterBean;
import bean.UserBean;
import service.AddTwintter;

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
		HttpSession session = request.getSession(true);
		UserBean user = (UserBean) session.getAttribute("user");
		String jsp;
		// TODO Auto-generated method stub
		if (user == null) {
			jsp = "/login.jsp";
		} else {
			jsp = "/twintter.jsp";
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

		try {
			if (button != null && !button.isEmpty()) {
				if (button.equals("投稿")) {
					if (content.length() <= 100) {
						HttpSession se = request.getSession(false);
						UserBean user = (UserBean) se.getAttribute("user");
						BookBean book = (BookBean) se.getAttribute("book");
						TwintterBean bean = new TwintterBean();
						bean.setUser_id(user.getId());
						bean.setBook_id(book.getId());
						bean.setTwintter_text(request.getParameter("twintter_text"));
						AddTwintter at = new AddTwintter();
						at.execute(bean);
						jsp = "/detail.jsp";
					} else {
						request.setAttribute("errormessage", "コメントが100文字を超過しています。");
						jsp = "/error.jsp";
						request.setAttribute("returnjsp", "twintter");
					}
				} else {
					request.setAttribute("errormessage", "ボタンが押されませんでした。");
					jsp = "/error.jsp";
					request.setAttribute("returnjsp", "twintter");
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
		if (jsp.equals("/detail.jsp")) {
			response.sendRedirect("http://localhost:8080/Suicabook/detail"); //リダイレクトでdoGetを呼び出す
		} else {
			ServletContext context = request.getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher(jsp);
			dispatcher.forward(request, response);
		}
	}
}

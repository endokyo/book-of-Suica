package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.AddUser;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/regist")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 転送処理
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/registration.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String btn = request.getParameter("btn");
		String jsp = null;

		try {
			//押されたボタンが戻るの場合
			if (btn.equals("login")) {
				jsp = "/login.jsp";
			} //押されたボタンが新規登録の場合
			else if (btn.equals("regist")) {
				AddUser add = new AddUser();
				add.execute(request);
				jsp = "/registration.jsp";
			}else {
				request.setAttribute("message", "エラーが発生しました。ログイン画面に戻ってください");
				jsp = "/registration.jsp";
			}
		} catch (Exception e) {
			jsp = "/registration.jsp";
		}
		

		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

}

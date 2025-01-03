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

import bean.UserBean;
import dao.UserDao;
import service.CreatePersonalEvaluation;
import service.CreatePersonalFavorite;
import service.DeleteFavorite;

/**
 * Servlet implementation class Personal
 */
@WebServlet("/personal")
public class Personal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String jsp;
		//ログインができるまで
		//		user = new UserBean();
		//		user.setId(1);
		//		user.setName("user01");
		//		user.setPassword("pass01");
		//
		//		session.setAttribute("user", user);
		//ログインされてなければログインページに飛ぶ
		if (session == null) {
			jsp = "/login.jsp";
		} else {
			UserDao dao = null;
			try {
				//お気に入り一覧と評価した書籍一覧を作成
				UserBean user = null;
				String username = request.getParameter("user_name");
				if (username != null && !username.isEmpty()) {
					dao = new UserDao();
					user = dao.getUser(username);
				}else {
					user = (UserBean) session.getAttribute("user");
				}
				createPage(request, user);
				request.setAttribute("username", user.getName());
				request.setAttribute("userid", user.getId());
				jsp = "/personal.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("returnjsp", "personal");
				request.setAttribute("errormessage", "エラーが発生しました");
				jsp = "/error.jsp";
			} finally {
				if (dao != null) {
					dao.close();
				}
			}
		}

		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String btn = request.getParameter("btn");
		String jsp = "/error.jsp";
		HttpSession session = request.getSession(false);
		UserBean user = (UserBean) session.getAttribute("user");

		try {
			//お気に入り解除の場合
			if (btn.equals("cancel")) {
				//お気に入りの解除を行う
				DeleteFavorite delete = new DeleteFavorite();
				delete.execute(request);
				//お気に入り一覧と評価した書籍一覧を作成
				createPage(request, user);
				request.setAttribute("username", user.getName());
				request.setAttribute("userid", user.getId());
				jsp = "/personal.jsp";
			}
			//エラー画面から戻ってきた場合
			else if (btn.equals("error")) {
				createPage(request, user);
				jsp = "/personal.jsp";
			} //ボタンが選択されていなかった場合
			else {
				doGet(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("errormessage", "エラーが発生しました<br>戻るボタンを押下してください");
			request.setAttribute("back", "personal");
		}

		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

	//ユーザー個人画面の各種一覧の作成
	public void createPage(HttpServletRequest request, UserBean user) throws Exception {
		//お気に入り書籍リスト作成
		CreatePersonalFavorite favo = new CreatePersonalFavorite();
		favo.execute(request, user.getId());
		//評価した書籍リスト作成
		CreatePersonalEvaluation eva = new CreatePersonalEvaluation();
		eva.execute(request, user.getId());
	}
}

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
import service.CreateEvaluation;
import service.CreateTwintter;
import service.SearchBook;

@WebServlet("/detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションからユーザー情報を取得する
		HttpSession session = request.getSession(false);
		UserBean userbean = (UserBean) session.getAttribute("user");

		// ログイン中のユーザーIDを取得する		
		int user_id = userbean.getId();

		// 他画面からアクセスしたときのユーザーIDを取得する
		//int who = Integer.parseInt(request.getParameter("who"));

		// 書籍情報を取得する
		try {
			SearchBook searchbook = new SearchBook();
			searchbook.execute(request, user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// モードにより表示を分ける
		try {
			String strmode = request.getParameter("mode");
			System.out.println(strmode);
			if(strmode == null) {
				strmode = "1";
			}
			int mode = Integer.parseInt(strmode);
			System.out.println(mode);
			if(mode <= 0) {
				mode = 1;
			}
			if (mode == 1) {
				// コメント一覧を取得する
				CreateTwintter createtwintter = new CreateTwintter();
				createtwintter.execute(request, user_id);
			} else {
				// レビュー一覧を取得する
				CreateEvaluation createevaluation = new CreateEvaluation();
				createevaluation.execute(request, user_id);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// リクエスト処理
		//request.setAttribute("who", who); // ユーザーID

		// 転送処理
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

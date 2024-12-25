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

import bean.GenreBean;
import bean.StatusBean;
import bean.UserBean;
import service.LoginUser;
import service.SearchUser;
import service.TodayGenre;
import service.ValidateUser;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 転送処理
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエスト処理
		request.setCharacterEncoding("UTF-8");

		// 遷移先のJSP
		String jsp = "/login.jsp";

		try {
			// serviceを呼び出す
			ValidateUser validateuser = new ValidateUser();
			SearchUser searchuser = new SearchUser();
			LoginUser loginuser = new LoginUser();

			// ユーザー名が未入力かどうか判定し、未入力ならエラーメッセージを設定する
			if (validateuser.execute(request)) {
				request.setAttribute("message", "ユーザー名を入力してください");
			}

			// ユーザーが登録されているか判定し、登録されていなければエラーメッセージを設定する
			else if (!searchuser.execute(request)) {
				request.setAttribute("message", "入力されたユーザー名は登録されていません");
			}

			// ログイン処理を行う
			else {
				// ログイン情報を取得する
				UserBean userbean = loginuser.execute(request);

				// ユーザー名とパスワードが一致しているか判定し、一致していなければエラーメッセージを設定する
				if (userbean == null) {
					request.setAttribute("message", "ユーザー名とパスワードが一致しません");
				} else {
					// セッションにログイン情報を格納する
					HttpSession session = request.getSession(true);
					session.setAttribute("user", userbean);

					// 本日のおすすめを選定し、StatusBeanに格納する
					TodayGenre todaygenre = new TodayGenre();
					GenreBean genrebean = todaygenre.execute(request);

					StatusBean statusbean = new StatusBean();
					statusbean.setTodaygenreid(genrebean.getId());
					statusbean.setTodaygenre(genrebean.getGenre());
					statusbean.setPage(1); // 念のため1ページ目を表示するということでここで格納しておく
					statusbean.setKeyword(" ");
					statusbean.setGenreid(0);
					statusbean.setNowsort("登録");
					session.setAttribute("status", statusbean);

					// 書籍一覧画面にフォワードする
					jsp = "/list";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (jsp.equals("/list")) {
			response.sendRedirect("http://localhost:8080/Suicabook/list");
		} else {
			// 転送処理
			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}
	}
}

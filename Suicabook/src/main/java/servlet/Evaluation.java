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

import bean.EvaluationBean;
import bean.UserBean;
import service.AddEvaluation;
import service.CreatePersonalEvaluation;
import service.CreatePersonalFavorite;
import service.GetEvaluationData;
import service.SearchBook;
import service.UpdateEvaluation;

/**
 * Servlet implementation class TaskEdit
 */
@WebServlet("/evaluation")
public class Evaluation extends HttpServlet {
	public static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String jsp;
		// TODO Auto-generated method stub
		if (session == null) {
			jsp = "/login.jsp";
		} else {
			UserBean user = (UserBean) session.getAttribute("user");
			GetEvaluationData eva = new GetEvaluationData();
			String bookid = request.getParameter("id");
			int id = Integer.parseInt(bookid);
			try {
				eva.execute(request, user.getId(), id);
				SearchBook search = new SearchBook();
				search.execute(request, id);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			String who = request.getParameter("who");
			request.setAttribute("userid", user.getId());
			request.setAttribute("who", who);
			jsp = "/evaluation.jsp";
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

		String button = request.getParameter("button");
		String mode = request.getParameter("mode");
		String who = request.getParameter("who");
		String userid = request.getParameter("userid");

		try {
			if (button != null && !button.isEmpty()) {
				if (button.equals("投稿")) {
					HttpSession se = request.getSession(false);
					if (mode.equals("2")) { //ユーザー個人画面から既に入力した評価を編集する場合
						EvaluationBean bean = new EvaluationBean();
						String id = request.getParameter("evaluation_id");
						String score = request.getParameter("evaluation_score");
						String review = request.getParameter("evaluation_review");
						bean.setId(Integer.parseInt(id));
						bean.setEvaluation_score(Integer.parseInt(score));
						bean.setEvaluation_review(review);
						UpdateEvaluation ue = new UpdateEvaluation();
						ue.execute(bean);
						if (userid != null) {
							//お気に入り書籍リスト作成
							CreatePersonalFavorite favo = new CreatePersonalFavorite();
							favo.execute(request, Integer.parseInt(userid));
							//評価した書籍リスト作成
							CreatePersonalEvaluation eva = new CreatePersonalEvaluation();
							eva.execute(request, Integer.parseInt(userid));
							request.setAttribute("userid", Integer.parseInt(userid));
							jsp = "/personal.jsp";
						} else {
							jsp = "/personal.jsp"; //←書籍詳細画面から評価入力(編集)した際の処理が不十分
						}
					} else { //書籍詳細画面から初めて評価を入力する場合
						UserBean user = (UserBean) se.getAttribute("user");
						String id = request.getParameter("id");
						String score = request.getParameter("evaluation_score");
						String review = request.getParameter("evaluation_review");
						EvaluationBean bean = new EvaluationBean();
						bean.setUser_id(user.getId());
						bean.setBook_id(Integer.parseInt(id));
						bean.setEvaluation_score(Integer.parseInt(score));
						bean.setEvaluation_review(review);
						AddEvaluation ae = new AddEvaluation();
						ae.execute(bean);
						jsp = "/detail";
					}

				} else if (button.equals("キャンセル")) {
					if (who.equals("personal")) {
						//お気に入り書籍リスト作成
						CreatePersonalFavorite favo = new CreatePersonalFavorite();
						favo.execute(request, Integer.parseInt(userid));
						//評価した書籍リスト作成
						CreatePersonalEvaluation eva = new CreatePersonalEvaluation();
						eva.execute(request, Integer.parseInt(userid));
						request.setAttribute("userid", Integer.parseInt(userid));
						jsp = "/personal.jsp";
					} else {
						jsp = "/detail";
					}
				} else {
					doGet(request, response);
				}
			} else if (button.equals("error")) {
				jsp = "/evaluation.jsp";
			} else {
				request.setAttribute("errormessage", "エラーが発生しました。ボタンの入力が確認できませんでした。");
				jsp = "/error.jsp";
				request.setAttribute("returnjsp", "evaluation");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "エラーが発生しました。評価入力画面に戻ります。");
			jsp = "/error.jsp";
			request.setAttribute("returnjsp", "evaluation");
		}
		// JSP への転送
		if (jsp.equals("/booklist.jsp")) {
			response.sendRedirect("http://localhost:8080/Suicabook/list"); //リダイレクトでdoGetを呼び出す
		} else {
			ServletContext context = request.getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher(jsp);
			dispatcher.forward(request, response);
		}
	}
}

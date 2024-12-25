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
import bean.EvaluationBean;
import bean.UserBean;
import service.AddEvaluation;
import service.CreatePersonalEvaluation;
import service.CreatePersonalFavorite;
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
		HttpSession session = request.getSession(true);
		UserBean user = (UserBean) session.getAttribute("user");
		String jsp;
		// TODO Auto-generated method stub
		if (user == null) {
			jsp = "/login.jsp";
		} else {
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
						BookBean book = (BookBean) se.getAttribute("book");
						EvaluationBean eb = (EvaluationBean) request.getAttribute("evaluationdetails");
						EvaluationBean bean = new EvaluationBean();
						bean.setUser_id(user.getId());
						bean.setBook_id(book.getId());
						bean.setEvaluation_score(eb.getEvaluation_score());
						bean.setEvaluation_review(eb.getEvaluation_review());
						AddEvaluation ae = new AddEvaluation();
						ae.execute(bean);
						jsp = "/detail.jsp";
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
						jsp = "/detail.jsp";
					}
				} else {
					request.setAttribute("errormessage", "ボタンが押されませんでした。");
					jsp = "/error.jsp";
					request.setAttribute("returnjsp", "evaluation");
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

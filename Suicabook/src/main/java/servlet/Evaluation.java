package servlet;

import java.io.IOException;
import java.text.ParseException;

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

		String content = request.getParameter("evaluation_content");
		String button = request.getParameter("button");
		String btn = request.getParameter("btn");

		try {
			if (button != null && !button.isEmpty()) { 
				if (button.equals("投稿")) {
					if (content != null) {
						if (content.length() <= 500) {
							try {
								HttpSession se = request.getSession(false);
								if (btn.equals("編集")) {
									UserBean user = (UserBean) se.getAttribute("user");
									EvaluationBean eb = (EvaluationBean) request.getAttribute("evaluationdetails");
									EvaluationBean bean = new EvaluationBean();
									String id = request.getParameter("evaluation_id");
									bean.setId(Integer.parseInt(id));
									bean.setEvaluation_score(eb.getEvaluation_score());
									bean.setEvaluation_review(eb.getEvaluation_review());
									UpdateEvaluation update = new UpdateEvaluation();
									update.execute(bean);
								} else {
									UserBean user = (UserBean) se.getAttribute("user");
									EvaluationBean eb = (EvaluationBean) request.getAttribute("evaluationdetails");
									EvaluationBean bean = new EvaluationBean();
									bean.setEvaluation_score(eb.getEvaluation_score());
									bean.setEvaluation_review(eb.getEvaluation_review());
									UpdateEvaluation update = new UpdateEvaluation();
									update.execute(bean);
								}
								jsp = "/taskhome.jsp";
							} catch (ParseException e) {
								request.setAttribute("errormessage", "日付に正常な値が入力されていません。");
								request.setAttribute("returnjsp", "TaskEdit");
								jsp = "/error.jsp";
							}
						}
					} else{
						request.setAttribute("errormessage", "必須項目が入力されていません。");
						jsp = "/error.jsp";
						request.setAttribute("returnjsp", "TaskEdit");
					}

				} else {
					request.setAttribute("errormessage", "ボタンが押されませんでした。");
					jsp = "/error.jsp";
					request.setAttribute("returnjsp", "TaskEdit");
				}

			} else if (btn.equals("error")) {
				jsp = "/taskedit.jsp";

			} else {
				request.setAttribute("errormessage", "エラーが発生しました。ボタンの入力が確認できませんでした。");
				jsp = "/error.jsp";
				request.setAttribute("returnjsp", "TaskEdit");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "エラーが発生しました。編集画面に戻ります。");
			jsp = "/error.jsp";
			request.setAttribute("returnjsp", "TaskEdit");
		}
		// JSP への転送

		if (jsp.equals("/taskhome.jsp")) {
			response.sendRedirect("http://localhost:8080/TODO/home");
		} else {
			ServletContext context = request.getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher(jsp);
			dispatcher.forward(request, response);
		}
	}
}

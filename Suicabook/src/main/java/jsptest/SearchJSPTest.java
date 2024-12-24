package jsptest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.GenreBean;
import dao.GenreDao;

@WebServlet("/searchjsptest")
public class SearchJSPTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエスト処理
		request.setAttribute("message", "テストメッセージ");

		// プルダウンのジャンル名一覧を取得
		GenreDao genredao;
		try {
			genredao = new GenreDao();
			List<GenreBean> genrelist = genredao.createGenreList();
			request.setAttribute("genrelist", genrelist);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		
		
		// 転送処理
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/search.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

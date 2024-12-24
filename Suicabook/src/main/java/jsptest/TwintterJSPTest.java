package jsptest;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.BookBean;

@WebServlet("/twintterjsptest")
public class TwintterJSPTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエスト処理
		request.setAttribute("who", 1);		// アクセスしてきたユーザーのユーザーID

		// 書籍
		BookBean bookinfo = new BookBean();
		bookinfo.setId(1);
		bookinfo.setImg("img/1.jpg");
		bookinfo.setTitle("テストタイトル");
		bookinfo.setGenre("テストジャンル");
		bookinfo.setAvecount(20);
		bookinfo.setAverage(3.0);
		bookinfo.setTwicount(40);;
		bookinfo.setFavcount(50);

		request.setAttribute("bookinfo", bookinfo);
		request.setAttribute("message", "テストメッセージ");

		// 転送処理
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/twintter.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

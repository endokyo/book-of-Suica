package jsptest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.BookBean;
import bean.StatusBean;

@WebServlet("/recommendationjsptest")
public class RecommendationJSPTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエスト処理
		// おすすめ度ランキング
		List<BookBean> ranking = new ArrayList<>();

		BookBean bookbean1 = new BookBean();
		bookbean1.setImg("img/1.jpg");	// とりあえずすべて同じ画像
		bookbean1.setTitle("テストタイトル1");
		ranking.add(bookbean1);
		
		BookBean bookbean2 = new BookBean();
		bookbean2.setImg("img/1.jpg");
		bookbean2.setTitle("テストタイトル2");
		ranking.add(bookbean2);

		BookBean bookbean3 = new BookBean();
		bookbean3.setImg("img/1.jpg");
		bookbean3.setTitle("テストタイトル3");
		ranking.add(bookbean3);

		request.setAttribute("ranking", ranking);

		// 書籍一覧
		List<BookBean> booklist = new ArrayList<>();

		BookBean bookbean4 = new BookBean();
		bookbean4.setId(1);
		bookbean4.setImg("img/1.jpg");
		bookbean4.setTitle("テストタイトル4");
		bookbean4.setGenre("テストジャンル4");
		bookbean4.setAvecount(41);
		bookbean4.setAverage(4.0);
		bookbean4.setTwicount(42);;
		bookbean4.setFavcount(43);
		booklist.add(bookbean4);

		request.setAttribute("booklist", booklist);
		
		// メッセージ
		request.setAttribute("message", "お気に入りを探しに行こう");

		// セッション処理
		StatusBean statusbean = new StatusBean();
		statusbean.setGenre("テストジャンル");
		statusbean.setPage(1);	// とりあえず1ページ目表示想定
		
		HttpSession session = request.getSession(true);
		session.setAttribute("status", statusbean);
		
		// 転送処理
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/recommendation.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

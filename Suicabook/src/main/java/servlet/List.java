package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
import bean.UserBean;
import dao.BookDao;
import service.CreateList;



/**
 * Servlet implementation class List
 */
@WebServlet("/list")
public class List extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		UserBean user = (UserBean) session.getAttribute("user");
		String keyword = (String)request.getAttribute("keyword");
		String message = (String)request.getAttribute("message");
		CreateList createlist = new CreateList();
		String jsp;
		//ログインされてなければログインページに飛ぶ
		if(user == null) {
			jsp = "/login.jsp";
		}else {
			//書籍一覧を作成
			try {
				createlist.execute(request,user,keyword);
				request.setAttribute("message", "お気に入りの書籍を見つけよう！！");
				session.setAttribute("page", 1);
				jsp = "/list.jsp";
			}catch (Exception e){
				e.printStackTrace();
				request.setAttribute("errormessage", "");	//エラーメッセージ入れる
				request.setAttribute("returnjsp", "list"); 
				jsp = "/error.jsp";
			}
		}
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		UserBean user = (UserBean) session.getAttribute("user");
		BookDao bdao = null;
		BookBean bb = (BookBean)request.getAttribute("booklist");
		String keyword = (String)request.getAttribute("keyword");
		String genre = (String)request.getAttribute("genre");
		StatusBean sb = (StatusBean)session.getAttribute("page");
		int userid = user.getId();
		ArrayList<BookBean> booklist = new ArrayList<>();
		booklist.add(bb);
		int pagecount = sb.getPage();
		String jsp;
		
		try {
			bdao = new BookDao();
			if(genre != null) {
				bdao.getBookListSortByRegist(user,keyword,genre);			
			}else {//ジャンルがALLの時
				
				
				
			}
			ArrayList<BookBean> page = new ArrayList<>();
			for(int i = (20*(pagecount -1) +1); i <= 20*pagecount +1; i++) {
					if(booklist.contains(booklist.get(i))) {
						page.add(booklist.get(i));
					}else {
						break;
					}
			}
			
			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			bdao.close();
		}
	}
}

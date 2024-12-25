package servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.FavoriteBean;
import bean.StatusBean;
import bean.UserBean;
import dao.BookDao;
import service.AddFavorite;
import service.CreateAverage;
import service.CreateFavoriteCount;
import service.CreateList;
import service.CreateTwintterCount;
import service.DeleteFavorite;



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
		CreateList createlist = new CreateList();
		String jsp;
		user = new UserBean();
		user.setId(1);
		user.setName("user01");
		user.setPassword("pass01");
		session.setAttribute("user", user);
		StatusBean sb =new StatusBean();
		sb.setTodaygenre("アクション");
		sb.setTodaygenreid(1);
		sb.setGenreid(0);
		sb.setKeyword(" ");
		sb.setPage(1);
		sb.setNowsort("登録");
		
		session.setAttribute("status", sb);
		
		
		//ログインされてなければログインページに飛ぶ
		//if(user == null) {
			//jsp = "/login.jsp";
		//}else {
			//書籍一覧を作成
			try {
				createlist.execute(request);
				request.setAttribute("message", "お気に入りの書籍を見つけよう！！");
				jsp = "/list.jsp";
			}catch (Exception e){
				e.printStackTrace();
				request.setAttribute("errormessage", "");	//エラーメッセージ入れる
				request.setAttribute("returnjsp", "list"); 
				jsp = "/error.jsp";
			}
		//}
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		UserBean user = (UserBean) session.getAttribute("user");
		StatusBean sb = (StatusBean) session.getAttribute("status");
		BookDao bdao = null;
		String button = request.getParameter("button");
		String sortname = request.getParameter("sortname");
		//String keyword = (String)request.getAttribute("keyword");
		String jsp;
		int bookid = Integer.parseInt(request.getParameter("bookid"));
		
		try {
			bdao = new BookDao();
			if(sortname != null) {
				//ソート順毎の処理
				if(sortname.equals("regist")) {
					CreateList cl =new CreateList();
					cl.execute(request);
				}else if(sortname.equals("average")) {
					CreateAverage ca =new CreateAverage();
					ca.execute(request);
				}else if(sortname.equals("twinter")) {
					CreateTwintterCount ct =new CreateTwintterCount();
					ct.execute(request);
				}else if(sortname.equals("favorite")) {
					CreateFavoriteCount cf =new CreateFavoriteCount();
					cf.execute(request);
				}
				
			}else if(button != null) {
				//お気に入り登録ボタン
				if(button.equals("false")) {
					DeleteFavorite df = new DeleteFavorite();
					df.execute(request);
				}else if(button.equals("true")){
					FavoriteBean fb = new FavoriteBean();
					fb.setUser_id(user.getId());
					fb.setBook_id(bookid);
					AddFavorite af = new AddFavorite();
					af.execute(fb);
				//ページ戻る/進むボタン
				}else if(button.equals("top")){
					sb.setPage(1);
					session.setAttribute("status", sb);
				}else if(button.equals("back")){	
					int nowpage = sb.getPage();
					nowpage--;
					sb.setPage(nowpage);
					session.setAttribute("status", sb);
				}else if(button.equals("next")){
					int nowpage = sb.getPage();
					nowpage++;
					sb.setPage(nowpage);
					session.setAttribute("status", sb);
					bookListCreate(request,sb);
				}else if(button.equals("last")){
					int maxpage = sb.getMaxpage();
					sb.setPage(maxpage);
					session.setAttribute("status", sb);
					bookListCreate(request,sb);
				}
				jsp = "/list.jsp";
			}else {
				//エラーページ遷移
				request.setAttribute("returnjsp", "list");
				jsp = "/error.jsp";
			}
			jsp = "/list.jsp";
			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			bdao.close();
		}
	}
	
	//選択されたソート順でソートするメソッド
	public void bookListCreate(HttpServletRequest request,StatusBean sb)throws Exception {
		if(sb.getNowsort().equals("登録")) {
			CreateList cl = new CreateList();
			cl.execute(request);
		}else if(sb.getNowsort().equals("評価")) {
			CreateAverage ca = new CreateAverage();
			ca.execute(request);
		}else if(sb.getNowsort().equals("コメント数")) {
			CreateTwintterCount ct = new CreateTwintterCount();
			ct.execute(request);
		}else if(sb.getNowsort().equals("お気に入り数")) {
			CreateFavoriteCount cf = new CreateFavoriteCount();
			cf.execute(request);
		}
	}
}


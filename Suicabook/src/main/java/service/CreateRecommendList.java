package service;

import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import bean.BookBean;
import bean.StatusBean;
import bean.UserBean;
import dao.BookDao;

//書籍のおすすめランキングを作成する
public class CreateRecommendList {
	public void execute(HttpServletRequest request)throws Exception{
		//リストを作成しrequestに入れる
		HttpSession session = request.getSession(false);
		UserBean ub = (UserBean) session.getAttribute("user");
		StatusBean sb = (StatusBean) session.getAttribute("status");
		BookDao dao = null;
		ArrayList<BookBean> list = new ArrayList<>();
		int pagecount = sb.getPage();
		
		try {
			dao = new BookDao();
			//書籍一覧を並び替え
			if(sb.getTodaygenreid() > 0 ) {	//検索ワード、本日のおすすめジャンル
				list = dao.getBookListSortByAverage(ub,sb.getKeyword(),sb.getTodaygenreid());
			}
			
			//ランキング用に、ソートした書籍一覧から20冊取得する
			ArrayList<BookBean> page = new ArrayList<>();
			for(int i = 0; i < 3; i++) {
					if(list.size() > i) {
						page.add(list.get(i));
					}else {
						break;
					}
			}
			double x = ((double)list.size()/20);
			int y = (int) Math.ceil(x);
			sb.setMaxpage(y);
			
//			String str = "";
//			if(sb.getKeyword() == " ") {
//				str += "検索キーワード：" + sb.getKeyword();
//			}
//			if(sb.getGenreid() > 0) {
//				str += " 検索ジャンル：" + sb.getGenre();
//			}
//			if(sb.getKeyword() != " " || sb.getGenreid() > 0) {
//				str += " 検索件数：" + list.size() + "件";
//			}else {
//				str = "お気に入りの書籍を見つけよう！！";
//			}
//			request.setAttribute("message", str);
			
			//一覧表示用に、ソートした書籍一覧から20冊取得する
			ArrayList<BookBean> recommend = new ArrayList<>();
			for(int i = (20*(pagecount -1)); i <= 20*pagecount - 1; i++) {
					if(list.size() > i) {
						recommend.add(list.get(i));
					}else {
						break;
					}
			}
			session.setAttribute("status",sb);
			request.setAttribute("booklist", recommend);
			request.setAttribute("ranking", page);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(dao != null) {
				dao.close();
			}
		}
	}			
}
package service;

import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import bean.BookBean;
import bean.StatusBean;
import bean.UserBean;
import dao.BookDao;

//書籍の登録順の昇順リストを作成する
public class CreateAverage {
	public void execute(HttpServletRequest request,UserBean user,String keyword)throws Exception{
		//リストを作成しrequestに入れる
		HttpSession session = request.getSession(false);
		BookBean bb = (BookBean)request.getAttribute("booklist");
		int genreid = bb.getGenre_id();
		StatusBean sb = (StatusBean)session.getAttribute("page");
		int pagecount = sb.getPage();
		BookDao dao = null;
		ArrayList<BookBean> list = new ArrayList<>();
		try {
			dao = new BookDao();
			//書籍一覧を並び替え
			if(genreid != 0 ) {	//検索ワード、ジャンル
				list = dao.getBookListSortByAverage(user,keyword,genreid);
			}else {				//検索ワード、All
				list = dao.getBookListSortByAverage(user,keyword);
			}	
			//一覧表示用に、ソートした書籍一覧から20冊取得する
			ArrayList<BookBean> page = new ArrayList<>();
			for(int i = (20*(pagecount -1) +1); i <= 20*pagecount +1; i++) {
					if(list.contains(list.get(i))) {
						page.add(list.get(i));
					}else {
						break;
					}
			}
			request.setAttribute("booklist", page);
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
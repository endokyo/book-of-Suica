package service;

import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import bean.BookBean;
import bean.StatusBean;
import bean.UserBean;
import dao.BookDao;

//書籍の登録順の昇順リストを作成する
public class CreateList {
	public void execute(HttpServletRequest request,UserBean user,String keyword)throws Exception{
		//リストを作成しrequestに入れる
		HttpSession session = request.getSession(false);
		BookDao bdao = null;
		BookBean bb = (BookBean)request.getAttribute("booklist");
		StatusBean sb = (StatusBean)session.getAttribute("status");
		int genreid = sb.getGenre();
		ArrayList<BookBean> list = new ArrayList<>();
		BookDao dao = null;
		try {
			dao = new BookDao();
			if(genre != null) {
				list = dao.getBookListSortByRegist(user,keyword,genreid);
			}else {
				list = dao.getBookListSortByRegist(user,keyword);
			}	
			
			ArrayList<BookBean> page = new ArrayList<>();
			for(int i = (20*(pagecount -1) +1); i <= 20*pagecount +1; i++) {
					if(list.contains(list.get(i))) {
						page.add(list.get(i));
					}else {
						break;
					}
			}
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
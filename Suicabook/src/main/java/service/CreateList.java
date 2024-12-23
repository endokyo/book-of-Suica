package service;

import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;

import bean.BookBean;
import bean.UserBean;
import dao.BookDao;

//書籍の登録順の昇順リストを作成する
public class CreateList {
	public void execute(HttpServletRequest request,UserBean user,String keyword)throws Exception{
		//リストを作成しrequestに入れる
		ArrayList<BookBean> list = new ArrayList<>();
		BookDao dao = null;
		try {
			dao = new BookDao();
			list = dao.getBookListSortByRegist(user,keyword);
			request.setAttribute("booklist", list);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			if(dao != null) {
				dao.close();
			}
		}
	}			
}
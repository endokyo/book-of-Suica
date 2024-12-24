package service;

import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;

import bean.GenreBean;
import dao.GenreDao;

public class CreateGenreList {
	public ArrayList<GenreBean> execute(HttpServletRequest request) throws Exception {
		//リストを作成しrequestに入れる
		ArrayList<GenreBean> list = new ArrayList<GenreBean>();
		GenreDao dao = null;
		try {
			dao = new GenreDao();
			list = dao.createGenreList();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
		return list;
	}
}

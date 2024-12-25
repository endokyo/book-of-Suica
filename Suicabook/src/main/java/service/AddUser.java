package service;

import jakarta.servlet.http.HttpServletRequest;

import bean.UserBean;
import dao.UserDao;

public class AddUser {
	public void execute(HttpServletRequest request) throws Exception {
		UserDao dao = null;
		String name = request.getParameter("id");
		String pass = request.getParameter("pw");
		String repass = request.getParameter("pw2");

		try {
			dao = new UserDao();
			//すべての項目が入力されている場合
			if (name != null && !name.isEmpty() && pass != null && !pass.isEmpty() && repass != null && !repass.isEmpty()) {
				//データベース上に入力されたユーザー名がなかった場合
				if (dao.searchUser(name) == false) {
					//入力されたパスワードと確認用パスワードが同じ場合
					if (pass.equals(repass)) {
						UserBean newuser = new UserBean();
						newuser.setName(name);
						newuser.setPassword(pass);
						int numRow = dao.InsertUser(newuser);
						if(numRow > 0) {
							request.setAttribute("message", "新規登録完了しました。ログイン画面から再度ログインしてください。");
						}else {
							request.setAttribute("message", "データベースにてエラーが発生しました。管理者に問い合わせてください。");
						}
					} //入力されたパスワードと確認用パスワードが違う場合
					else {
						request.setAttribute("message", "パスワードの入力内容が一致しません<br>もう一度パスワードを入力してください");
					}
				} //データベース上に入力されたユーザー名があった場合
				else {
					request.setAttribute("message", "入力されたユーザー名は既に登録されています<br>別のユーザー名を入力してください");
				}
			}//未入力の項目がある場合
			else {
				request.setAttribute("message","未入力の項目があります");
			}
		} finally {
			dao.close();
		}
	}
}

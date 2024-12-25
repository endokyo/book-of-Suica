<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="dao.UserDao" %>
<%@ page import="bean.UserBean" %>

<!DOCTYPE html>
<html lang="ja">
<style>
{
text-align:center;
}
</style>
<head>
	<meta charset="UTF-8">
	<title>Suicaブックス　結合テスト</title>
	<%-- ログイン情報を取得してセッションに格納し、疑似的にログインされた状態を作る --%>
	<%
		//HttpSession session = request.getSession(false);
		UserDao userdao = new UserDao();
		UserBean userbean = userdao.loginUser("user01", "pass01");
		session.setAttribute("user", userbean);
	%>
</head>
<body>
	<header>
		<h1>
			<p>Suicaブックス　結合テスト</p>
		</h1>
	</header>
	<main>
		<%-- 結合テスト対象機能のサーブレットURLパターンをaction属性に指定する（先頭の/は不要） --%>
		<form action="detail" method="post">
			<div class="input">
				<p>
					key:user_name ログイン中のユーザー名（スクリプトレットで疑似的にログインさせたユーザーが表示される）<br>
					ログイン中のユーザー：<c:out value="${sessionScope.user.name}" />
				</p>
				<p>
					key：who 他画面からアクセスしたときのユーザーID（ユーザー「ID」であり、ユーザー名ではない）<br>
					<input type="text" name="who" placeholder="1">
				</p>
				<p>
					key:id 書籍ID（書籍IDはキー名もDBカラム名もbook_idではなく「id」）<br>
					<input type="text" name="id" placeholder="1">
				</p>
				<p>
					key:mode 動作モード（1でコメント一覧、2でレビュー一覧）<br>
					<input type="text" name="mode" placeholder="1">
				</p>
				<p>
					<input type="submit" value="テスト実行">
				</p>
			</div>
		</form>
	</main>
	<footer>
		<jsp:include page="footer.jsp" />		
	</footer>
</body>
</html>
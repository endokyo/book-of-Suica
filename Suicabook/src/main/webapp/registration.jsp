<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<style>
{
text-align:center;
}
</style>
<head>
<meta charset="UTF-8">
<title>Suicaブックス</title>
</head>
<body>
	<header>
		<%-- 新規登録画面では共通のheader.jspはincludeしない --%>
		<form action="regist" method="post">
			<input type="hidden" name="btn" value="login">
			<input type="submit" value="戻る">
		</form>
		<h1>
			<p>Suicaブックス</p>
		</h1>
	</header>
	<main>
		<p>登録するユーザー名とパスワードを入力してください</p>
		<c:if test="${not empty requestScope.message}">
			<p class="message">${requestScope.message}</p>
		</c:if>

		<form action="regist" method="post">
			<div class="input">
				<p>
					＊英数字1~15桁のユーザー名を入力して下さい<br>
					ユーザー名：<input type="text" name="id" placeholder="suica01">
				</p>
				<p>
					＊英数字1~8桁のパスワードを入力して下さい<br>
					パスワード：<input type="password"name="pw" placeholder="********">
				</p>
				<p>
					＊確認の為に再度パスワードを入力して下さい<br>
					確認用：<input type="password" name="pw2" placeholder="********">
				</p>
				<p>
					<input type="hidden" name="btn" value="regist">
					<input type="submit" value="新規登録">
				</p>
			</div>
		</form>
	</main>
	<footer>
		<jsp:include page="footer.jsp" />		
	</footer>
</body>
</html>
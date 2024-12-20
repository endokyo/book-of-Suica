<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja"> 
<style>
{
	text-align: center;
}
</style>
<head>
<meta charset="UTF-8">
<title>Suicaブックス</title>
</head>
<body>
	<header>
		<h1><p>Suicaブックス</p></h1>
	</header>
	<main>
		<form action="login" method="post">
			<p>ユーザー名とパスワードを入力してください</p>
			<c:if test="${not empty requestScope.message}">
				<p class="message">${requestScope.message}</p>
			</c:if>
			<div class="input">
			<p>
				ユーザー名：<input type="text" name="id" placeholder="suica01">
			</p>
			<p>
				パスワード：<input type="password" name="pw" placeholder="********">	<!-- cssで*表示が可能 -->
			</p>
			<p><input type="submit" value="ログイン"></p>
			</div>
		</form>

	</main>
</body>
</html>
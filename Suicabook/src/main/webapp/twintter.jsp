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
<title>コメント入力画面</title>
</head>
<body>
	<header>
		<h1>
			<p>コメント入力画面</p>
		</h1>
	</header>
	<main>
		<form action="twintter" method="post">	
			<div class="input">
				<p>
				${requestScope.bookinfo.title }
				</p>
	
				<c:if test="${not empty requestScope.message}">
					<p class="message">${requestScope.message}</p>
				</c:if>
	
				<p>
					*文字数上限は100文字です<br>
					コメント：<br>
					<textarea name="twintter_text" placeholder="コメント" rows="5" cols="30"></textarea>
				</p>
				<p>
					<input type="hidden" name="id" value="${requestScope.bookinfo.id }">
					<input type="hidden" name="who" value="${requestScope.who }">	
					<input type="submit" name="button" value="投稿">
				</p>
			</div>
		</form>
		<form action="details" method="post">	
			<div class="input">
				<p>
					<input type="hidden" name="id" value="${requestScope.bookinfo.id }">
					<input type="hidden" name="who" value="${requestScope.who }">	
					<input type="submit" name="button" value="キャンセル">
				</p>
			</div>
		</form>		
	</main>
	<footer>
		<jsp:include page="footer.jsp" />		
	</footer>
</body>
</html>
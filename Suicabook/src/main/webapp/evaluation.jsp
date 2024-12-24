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
<title>評価入力画面</title>
</head>
<body>
	<header>
		<h1>
			<p>評価入力</p>
		</h1>
	</header>
	<main>
		<form action="evaluation" method="post">	
			<div class="input">
				<p>
					<c:choose>
						<c:when test="${requestScope.mode == 1}">新規</c:when>
						<c:when test="${requestScope.mode == 2}">編集</c:when>
					</c:choose>		
					${requestScope.bookinfo.title }				
				</p>
				<p>
					評価：<tb>
						<select name="evaluation_score">
							<option value="1" selected="selected">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
					</tb>
					<tb>点
				</p>
				<p>
					*文字数上限は500文字です<br>
					レビュー：<br>
					<textarea name="evaluation_review"　placeholder="レビュー" rows="10" cols="60"></textarea>
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
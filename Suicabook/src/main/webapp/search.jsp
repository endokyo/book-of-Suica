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
<title>書籍検索画面</title>
</head>
<body>
	<header>
		<jsp:include page="header.jsp" />
		<h2>
			<p>書籍検索画面</p>
		</h2>
	</header>
	<main>
		<form action="search" method="post">
			<div class="input">
				<p>
					検索ワード：<input type="text" name="id"　placeholder="suica01">
				</p>
			<tb>
				<p>
					ジャンル：
					<select name="genrename">
						<option value="ALL" selected="selected">ALL</option>
	
						<c:forEach var="genre" items="${requestScope.genrelist }">
							<option value="${genre.genre}"><c:out value="${genre.genre}" /></option>
						</c:forEach>
					</select>
				</p>
			</tb>
			<input type="submit" value="検索">
			</div>
		</form>
	</main>
	<footer>
		<jsp:include page="footer.jsp" />		
	</footer>
</body>
</html>
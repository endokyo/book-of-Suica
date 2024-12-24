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
<title>エラー</title>
</head>
<body>
	<header>

	</header>
	<main>
		<c:if test="${not empty requestScope.message}">
			<p class="message">${requestScope.message}</p>
		</c:if>
		
		<c:if test="${not empty requestScope.errormessage}">
			<p class="message">${requestScope.errormessage}</p>
		</c:if>
		
		<form action="${requestScope.back}" method="post">
			<div class="input">
				<p>
					<input type="submit" value="戻る">
				</p>
			</div>
		</form>
	</main>
	<footer>
		<jsp:include page="footer.jsp" />		
	</footer>
</body>
</html>
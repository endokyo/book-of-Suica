<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィール</title>
</head>
<body>
	<header>
		<p><jsp:include page="header.jsp" /></p>
		<p>
		<h1>
			<c:out value="${requestScope.username }" />
		</h1>
		</p>
	</header>
	<main>
		<table border="1">
			<td colspan="3"><c:out value="お気に入り書籍一覧" /></td>
			<tr>
				<th>表紙</th>
				<th>タイトル</th>
				<th></th>
			<tr>
				<c:forEach var="book" items="${requestScope.favoritelist }">
					<tr>
						<td><img src="img/${book.img }" width="128" height="96"
							alt="${book.title } "></td>
						<input type="hidden" name="btn" value="title">
						<td><a href="detail?id=${book.id}"><c:out
									value="${book.title}" /></td>
						<c:if test="${requestScope.userid == sessionScope.user.id  }">
							<td><form action="personal" method="post">
									<input type="hidden" name="btn" value="cancel"> <input
										type="hidden" name="favorite_id" value="${book.favorite_id }">
									<input type="submit" value="登録解除">
								</form></td>
						</c:if>
						<c:if test="${requestScope.userid != sessionScope.user.id  }">
							<td></td>
						</c:if>
				</c:forEach>
		</table>
		<table border="1">
			<td colspan="4"><c:out value="評価済み書籍一覧" /></td>
			<tr>
				<th>表紙</th>
				<th>タイトル</th>
				<th>評価点</th>
				<th></th>
			<tr>
				<c:forEach var="eva" items="${requestScope.evalutionlist }">
					<tr>
						<td><img src="img/${eva.img }" width="128" height="96"
							alt="${eva.title } "></td>
						<td><a href="detail?id=${eva.id}"><c:out value="${eva.title}" /></td>
						<td><c:out value="${eva.evaluation_score }" /></td>
						<c:if test="${requestScope.userid == sessionScope.user.id  }">
							<td><form action="personal" method="post">
									<input type="hidden" name="btn" value="evalution"> <input
										type="hidden" name="bookid" value="${eva.id }"> <input
										type="submit" value="評価編集">
								</form></td>
						</c:if>
						<c:if test="${requestScope.userid != sessionScope.user.id  }">
							<td></td>
						</c:if>
				</c:forEach>
		</table>
	</main>
	<footer>
		<jsp:include page="footer.jsp" />
	</footer>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html lang="ja">
<style>
{
text-align:center;
}
</style>
<head>
<meta charset="UTF-8">
<title>書籍詳細画面</title>
</head>
<body>
	<header>
		<c:import url="header.jsp" />
		<img src="img/${requestScope.bookinfo.img}" width="80" height="120"
			alt="${requestScope.bookinfo.img}">
		<h2>
			<p>書籍詳細</p>
		</h2>
	</header>

	<main>
		<p>${requestScope.bookinfo.title}</p>
		<p>作者名:${requestScope.bookinfo.creater}</p>
		<p>評価点平均:${requestScope.bookinfo.average}</p>
		<p>ジャンル名:${requestScope.bookinfo.genre}</p>
		<p>
			<form action="addfavorite" method="POST">
				<input type="submit" name="addfavorite" value="☆" />
				<input type="hidden" name="button" value="favorite_regist" />
				<input type="hidden" name="id" value="${requestScope.bookinfo.id}" />
			</form>
		</p>
		<p>
			<form action="evaluation" method="POST">
				<input type="hidden" name="who" value="detail"/>
				<input type="submit" name="evaluation" value="評価/レビューを入力" />
				<input type="hidden" name="button" value="evaluation" />
				<input type="hidden" name="id" value="${requestScope.bookinfo.id}" />
			</form>
		</p>
		<p>
			<form action="twintter" method="POST">
				<input type="submit" name="twintter" value="コメントを入力" />
				<input type="hidden" name="button" value="twintter" />
				<input type="hidden" name="id" value="${requestScope.bookinfo.id}" />
			</form>
		</p>
		<p>
			<c:out value="お気に入り数:${requestScope.bookinfo.favcount}" />
		</p>
		<p>	
			<c:choose>
				<%-- コメント表示モード --%>
				<c:when test="${requestScope.mode == 1}">
					<p>
						<form action="detail" method="POST">
							<input type="submit" name="review" value="レビュー表示" />
							<input type="hidden" name="who" value="${requestScope.who}" />
							<input type="hidden" name="id" value="${requestScope.bookinfo.id}" />
							<input type="hidden" name="mode" value="2" />
						</form>
					</p>
					<table border="1">
						<tr>
							<th>コメントNo.</th>
							<th>ユーザー名</th>
							<th>コメント</th>
							<th>投稿日時</th>
						</tr>
						<c:forEach var="twintterinfo" items="${requestScope.twintterlist }" varStatus="status">
							<tr>
								<td>No.<c:out value="${status.index+1}" /></td>
								<td>
									<a href = "/personal?user_name=${twintterinfo.user_name }">
										<c:out value="${twintterinfo.user_name }" />							
									</a>
								</td>
								<td><c:out value="${twintterinfo.twintter_text }" /></td>
								<td><c:out value="${twintterinfo.created_at }" /></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				
				<%-- レビュー表示モード --%>
				<c:when test="${requestScope.mode == 2}">
					<p>
						<form action="detail" method="POST">
							<input type="submit" name="twintter" value="コメント表示" />
							<input type="hidden" name="who" value="${requestScope.who}" />
							<input type="hidden" name="id" value="${requestScope.bookinfo.id}" />
							<input type="hidden" name="mode" value="1" />
						</form>
					</p>	
					<table border="1">
						<tr>
							<th>ユーザー名</th>
							<th>評価点</th>
							<th>レビュー（${fn:length(requestScope.evaluationlist)}件）</th>
						</tr>
						<c:forEach var="evaluationinfo" items="${requestScope.evaluationlist }">
							<tr>
								<td>
									<a href = "/personal?user_name=${evaluationinfo.user_name }">
										<c:out value="${evaluationinfo.user_name }" />							
									</a>
								<td><c:out value="${evaluationinfo.evaluation_score }" /></td>
								<td><c:out value="${evaluationinfo.evaluation_review }" /></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
			</c:choose>
		</p>
	</main>
</body>
</html>
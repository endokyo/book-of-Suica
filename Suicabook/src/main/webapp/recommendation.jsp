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
<title>>おすすめ一覧画面</title>
</head>
<body>
	<header>
		<jsp:include page="header.jsp" />
		<h2>
			<p>おすすめ一覧</p>
		</h2>
		ジャンル：${sessionScope.status.genre}
	</header>
	<main>
		<c:if test="${not empty requestScope.message}">
			<p class="message">${requestScope.message}</p>
		</c:if>

		<table border="1">
			<th colspan="4">おすすめ度ランキング</th>
			<c:forEach var="book" items="${requestScope.ranking}" varStatus="status">
			<tr>
				<td><c:out value="${status.index+1}" />位<td/>
				<td>
					<img src="${book.img}" width="80" height="120" alt="${book.img}" />
				</td>
				<td>
					<a href="detail?id=${book.id}"><c:out value="${book.title}" />
				</td>				
			</tr>
			</c:forEach>
		</table>
		<br>
		
		<%-- （以下、list.jspとだいたい同じ --%>
		<%-- ソートボタン --%>
		<form action="list" method="post"></form>
			<tb>
				<select name="sortname">
					<option value="regist" selected="selected">登録順</option>
					<option value="average">評価順</option>
					<option value="twinter">コメント数</option>
					<option value="favorite">お気に入り数順</option>
				</select>
			</tb>
			<tb><input type="submit" name="button" value="ソート"></tb>
		</form>
		
		<%--書籍一覧テーブル --%>
		<table border="1">
			<tr>
				<th>表紙</th>
				<th>タイトル</th>
				<th>ジャンル</th>
				<th>評価平均</th>
				<th>コメント数</th>
				<th>お気に入り数</th>
				<th>お気に入り登録</th>
			</tr>
			
			<c:forEach var="book" items="${requestScope.booklist }">
				<tr>
					<td><img src="${book.img}" width="80" height="120" alt="${book.img}" /></td>
					<td><c:out value="${book.title }"/></td>
					<td><c:out value="${book.genre }"/></td>
					<td><c:out value="${book.average }"/></td>
					<td><c:out value="${book.twicount }"/></td>
					<td><c:out value="${book.favcount }"/></td>
					<td>
						<form action="list" method="post">
							<c:choose>
								<c:when test="${book.favorite == true}">
									<input type="submit" value="★">
									<input type="hidden" name="button" value="false">
								</c:when>
								<c:otherwise>
									<input type="submit" value="☆">
									<input type="hiddun" name="button" value="true">
								</c:otherwise>
							</c:choose>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<%--ページ送りボタン --%>
		<table>
			<c:choose>
				<c:when test="${sessionScope.page > 1}">
					<%--最初のページ --%>
					<td>
						<form action="list" method="post">
							<input type="submit" value="＜＜">
							<input type="hidden" name="button" value="top">
						</form>
					</td>
					<%--1ページ前 --%>
					<td>
						<form action="list" method="post">
							<input type="submit" value="＜">
							<input type="hidden" name="button" value="back">
						</form>
					</td>
				</c:when>
			</c:choose>
			
			<%--ページ数表示 --%>
			<td>${sessionScope.page }</td>
			<td>
				<%--1ページ先 --%>
				<form action="list" method="post">
					<input type="submit" value="＞">
					<input type="hidden" name="button" value="next">
				</form>
			</td>
			<td>
				<%--最後のページ --%>
				<form action="list" method="post">
					<input type="submit" value="＞＞">
					<input type="hidden" name="button" value="last">
				</form>
			</td>
		</table>
	</main>
	<footer>
		<jsp:include page="footer.jsp" />
	</footer>
</body>
</html>
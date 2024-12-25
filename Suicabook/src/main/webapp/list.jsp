<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>書籍一覧画面</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<h2>本日のおすすめ</h2>
	<%--おすすめ画面遷移 --%>
	<form action="recommend" method="get">
		<input type="submit" value="${sessionScope.status.todaygenre }">
	</form>
	<h1>書籍一覧</h1>
	
	<table>
		<tr>
			<%--ソートボタン --%>
			<form action="list" method="post"></form>
				<tb>
					<select name="sortname">
						<option value="regist" selected="selected">登録順</option>
						<option value="average">評価順</option>
						<option value="twinter">コメント数</option>
						<option value="favorite">お気に入り数順</option>
					</select>
				</tb>
				<tb><input type="submit" value="ソート"></tb>
			</form>
			
			<tb><h2>${requestScope.message }</h2></tb>
			
			<td>
				<%--検索クリアボタン --%>
				<form action="list" method="post">
					<input type="submit" value="検索クリア">
					<input type="hidden" name="button" value="clear">
				</form>
			</td>
			
			<td>
				<%--検索画面遷移 --%>
				<form action="search" method="get">
					<input type="submit" value="検索">
					<input type="hidden" name="button" value="search">
				</form>
			</td>
		</tr>
	</table>
	
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
				<td><img src="img/${book.img }" width="128" height="96" alt="${book.title } "></td>
				<form action="list" method="post">
					<td><input type="submit" value="${book.title }"/></td>
					<input type="hidden" name="title" value="${book.id }">
				</form>
				<td><c:out value="${book.genre }"/></td>
				<td><c:out value="${book.average }"/></td>
				<td><c:out value="${book.twicount }"/></td>
				<td><c:out value="${book.favcount }"/></td>
				<td><c:out value="${book.favorite_id }"/></td>
				<td>
					<form action="list" method="post">
						<c:choose>
							<c:when test="${book.favorite == true}">
								<input type="submit" value="★">
								<input type="hidden" name="button" value="false">
								<input type="hidden" name="favorite_id" value="${book.favorite_id }">
							</c:when>
							<c:otherwise>
								<input type="submit" value="☆">
								<input type="hidden" name="button" value="true">
								<input type="hidden" name="bookid" value="${book.id }">
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
		<%--ページ数表示 
		<%
			ArrayList<BookBean> booklist = (ArrayList<>)request.getAttribute("booklist");
			int bookcount = booklist.size;
			
			double maxpage = bookcount / 20;
			
			int lastpage = math.floor();
		%>
		--%>
		<td>
			${sessionScope.page }/${requestScope.lastpage }
		</td>
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
</body>
</html>
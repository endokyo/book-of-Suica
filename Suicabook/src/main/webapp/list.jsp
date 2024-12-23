<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>書籍一覧画面</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<h2>本日のおすすめ</h2>
	<form action="" method="get">
		<input type="submit" name="button" value="${sessionScope.status }">
	</form>
	<h1>書籍一覧</h1>
	<table>
		<tr>
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
			
			<tb><h2>${requestScope.message }</h2></tb>
			
			<td>
				<form action="list" method="get">
					<input type="submit" value="検索クリア">
				</form>
			</td>
			
			<td>
				<form action="search" method="get">
					<input type="submit" value="検索">
				</form>
			</td>
		</tr>
	</table>
	
	<table>
		<tr>
			<th>表紙</th>
			<th>タイトル</th>
			<th>ジャンル</th>
			<th>評価平均</th>
			<th>コメント数</th>
			<th>お気に入り数</th>
			<th>お気に入り登録</th>
		</tr>
		
		<c:foreach var="book" items="${requestScope.booklist }">
			<tr>
				<td><c:out value="${book.img }"/></td>
				<td><c:out value="${book.title }"/></td>
				<td><c:out value="${book.genre }"/></td>
				<td><c:out value="${book.average }"/></td>
				<td><c:out value="${book.twicount }"/></td>
				<td><c:out value="${book.favcount }"/></td>
				<td>
					<form action="list" method="post">
						
						<c:chose>
							<c:when test="${book.favorite == true}">
								<input type="submit" value="★">
								<input type="hidden" name="button" value="${ }"
							</c:when>
							<c:otherwise>
								
							</c:otherwise>
						</c:chose>
						<input type="hiddun" name="button" value="">
					</form>
				</td>
			</tr>
		</c:foreach>
	</table>
	
	
</body>
</html>
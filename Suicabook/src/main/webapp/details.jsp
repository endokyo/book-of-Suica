<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍詳細画面</title>
</head>
<body>
	<header>
		<c:import url="header.jsp" />
		<h1>
			<img src="img/<%=image[no]%>" width="64" height="48"
				alt="<%=item[no]%>">
		</h1>
		<h2>
			<p>書籍詳細</p>
		</h2>
	</header>

	<main>
	   <p> ${requestScope.title}</p>
	<br>
	   <p>作者名:${requestScope.creater}</p>
	   <p>評価点平均:${requestScope.average}</p>
	   <p>ジャンル名:${requestScope.genre}</p>
	   <p><input type ="button" name="favorite_regist" value="☆"> 
	      <imput type="hidden" name="button" value="favorite_regist"></p>
	<br>
	   <input type ="button" name="favorite_regist" value="評価/レビューを入力"> 
	   <imput type="hidden" name="button" value="evaluation_review">
	   
	   <input type ="button" name="favorite_regist" value="コメントを入力"> 
	   <imput type="hidden" name="button" value="comment">

	   お気に入り数:${requestScope.favcount}
	   
	   <input type ="button" name="comment_review" value="${requestScope.comment_review}"> 
	   <imput type="hidden" name="button" value="comment_review">
	
	<table border="1">
			<tr>
				<th>コメントNo.</th>
				<th>ユーザー名</th>
				<td>コメント</td>
				<th>投稿日時</th>
				<th></th>
			<tr>
				<c:forEach var="bookinfo" items="${requestScope.bookinfoList }">
					<tr>
					    <td><c:out value="${task.twintno }" /></td>
						<td><c:out value="${task.username }" /></td>
						<td><c:out value="${task.twintter_text }" /></td>
						<td><c:out value="${task.post_time }" /></td>
						<td>
							<form action="home" method="post">
								<input type="hidden" name="taskid" value="${task.book_id }">
								<input type="hidden" name="btn" value="delete"> <input
									type="submit" value="削除">
							</form>
						</td>
						<td>
							<form action="home" method="post">
								<input type="hidden" name="taskid" value="${task.book_id }">
								<c:if test="${not empty requestScope.taskid }">
									<input type="hidden" name="btn" value="update">
								</c:if>
								<input type="hidden" name="btn" value="update"> <input
									type="submit" value="編集">
							</form>
						</td>
					</tr>
				</c:forEach>
		</table>
	
	</main>
</body>
</html>
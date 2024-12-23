<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ヘッダー</title>
</head>
<body>
	<main>
		<table>
			<tr>
				<td>
					<form action="home" method="get">
						<input type="submit" value="書籍一覧に戻る">
					</form>
				</td>
				
				<td>
					<h1>Suicaブックス</h1>
				</td>

				<td>
					<form action="personal" method="get">
						<input type="submit" value="プロフィールボタン">
					</form>
				</td>

				<td>
					<form action="login" method="post">
						<input type="submit" value="ログアウト">
						<imput type="hidden" name="button" value="logout">
					</form>
				</td>
			</tr>
		</table>
		<hr>
	</main>
</body>
</html>
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
					<form action="" method="get">
						<input type="submit" value="書籍一覧に戻る">
						<imput type="hidden" name="button" value="backhome">
					</form>
				</td>
				
				<td>
					<h1>Suicaブックス</h1>
				</td>

				<td>
					<form action="" method="get">
						<input type="submit" value="プロフィールボタン">
						<imput type="hidden" name="button" value="prof">
					</form>
				</td>

				<td>
					<form action="" method="get">
						<input type="submit" value="ログアウト">
						<imput type="hidden" name="button" value="logout">
					</form>
				</td>
			</tr>
		</table>
	</main>
</body>
</html>
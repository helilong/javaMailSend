<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<form action="MailServlet" method="post">
			发送邮箱：<input type="text" name="sendUser" id="sendUser" value="" /><br /><br />
			邮箱密码：<input type="text" name="sendPwd" id="sendPwd" value="" /><br /><br />
			收件邮箱：<input type="text" name="recUser" id="recUser" value="" /><br /><br />
			标题：<input type="text" name="subject" id="subject" value="" /><br /><br />
			附件(桌面的文件)：<input type="file" name="Filename" id="Filename" value="" /><br /><br />
			内容：<input type="text" name="context" id="context" value="" /><br /><br />
	
			<input type="submit" value="发送"/>
		</form>
	</body>
</html>
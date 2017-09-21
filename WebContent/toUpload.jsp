<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="upload.do" method="post" enctype="multipart/form-data">
	用户名：<input type="text" name="userName"><br>
	email:<input type="email" name="email"><br>
	用户图片：<input type="file" name="userImage"><br>
	<input type="submit" name="提交">
</form>

</body>
</html>
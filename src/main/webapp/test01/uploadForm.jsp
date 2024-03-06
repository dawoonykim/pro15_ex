<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="http://localhost:8080/pro15_ex/upload.do" method="post" enctype="multipart/form-data">
파일1:<input type="file" name="file1"><br>
파일1:<input type="file" name="file2"><br>
파일1:<input type="text" name="text1"><br>
<input type="submit" value="업로드">
</form>
</body>
</html>
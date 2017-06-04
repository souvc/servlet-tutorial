<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传实例 - 菜鸟教程</title>
</head>
<body>
<h1>文件上传实例 - 菜鸟教程</h1>

<!-- 表单 method 属性应该设置为 POST 方法，不能使用 GET 方法。 -->
<!-- 表单 enctype 属性应该设置为 multipart/form-data.-->
<!-- 表单 action 属性应该设置为在后端服务器上处理文件上传的 Servlet 文件 -->


<form method="post" action="/Servlet-File-Uploading/UploadServlet" enctype="multipart/form-data">
	选择一个文件:
	<input type="file" name="uploadFile" />
	<br/><br/>
	<input type="submit" value="上传" />
</form>

</body>
</html>
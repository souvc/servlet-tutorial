<%@ page contentType="text/html; charset=UTF-8"  %>
<html>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<body>
<h2>Hello World!</h2>

<form action="/muServlet3Upload" method="post" enctype="multipart/form-data">
    文件1: <input type="file" name="file1" value=""/> <br>
    文件2: <input type="file" name="file2" value=""/> <br>
    文件3: <input type="file" name="file3" value=""/> <br><br>
    <input type="submit" value="上传" name="upload"/>
</form>
</body>
</html>

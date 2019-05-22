<%@ page contentType="text/html; charset=UTF-8"  %>
<html>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<body>
<h2>Hello World!</h2>

<form action="/servlet3Upload" method="post" enctype="multipart/form-data">
    上传相片:<input type="file" name="photo"/>
    <br><br>
    <input type="submit" value="上传" name="upload"/>
</form>


</body>
</html>

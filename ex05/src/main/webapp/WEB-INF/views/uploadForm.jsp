<%--
  Created by IntelliJ IDEA.
  User: woong_9yo
  Date: 2022/07/29
  Time: 10:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="uploadFormAction" method="post" enctype="multipart/form-data">
    <input type="file" name="uploadFile" multiple>
    <button>Submit</button>
</form>
</body>
</html>

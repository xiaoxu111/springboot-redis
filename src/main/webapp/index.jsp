<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/6/1
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<hr>
<form action="insertStudent" method="post">
    学号: <input type="text" name="studentNo"> </br>
    账户: <input type="text" name="username"> </br>
    密码: <input type="text" name="password"> </br>
    姓名: <input type="text" name="realname"> </br>
    年龄: <input type="text" name="age"> </br>
    <input type="submit" value="注册">
</form>
<hr>
<%--这里获取到的结果必须和数据库中的结果一致--%>
<form action="find" method="post">
    年龄上限: <input type="number" name="age"> </br>
    <input type="submit" value="查询">
</form>
</hr>
<%--这里获取的总人数和数据库中的差不多就行--%>
<a href="count">查询学生总人数</a>
</body>
</html>

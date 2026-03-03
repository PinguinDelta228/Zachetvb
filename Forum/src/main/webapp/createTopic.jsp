<%--
  Created by IntelliJ IDEA.
  User: StartSoft
  Date: 03.03.2026
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Новая тема</title>
</head>
<body>
<h2>Создать новую тему</h2>
<form method="post" action="create-topic">
  Заголовок: <input type="text" name="title" required size="50"><br>
  Содержание:<br>
  <textarea name="content" rows="10" cols="60" required></textarea><br>
  <input type="submit" value="Создать">
</form>
<p><a href="topics">Назад к списку</a></p>
</body>
</html>

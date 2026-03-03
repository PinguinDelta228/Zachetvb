<%--
  Created by IntelliJ IDEA.
  User: StartSoft
  Date: 03.03.2026
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<h2>Регистрация</h2>
<% if (request.getAttribute("error") != null) { %>
<p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>
<form method="post" action="register">
    Имя пользователя: <input type="text" name="username" required><br>
    Email: <input type="email" name="email" required><br>
    Пароль: <input type="password" name="password" required><br>
    <input type="submit" value="Зарегистрироваться">
</form>
<p>Уже есть аккаунт? <a href="login">Войти</a></p>
</body>
</html>
